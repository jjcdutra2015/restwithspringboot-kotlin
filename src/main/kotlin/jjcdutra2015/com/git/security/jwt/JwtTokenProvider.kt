package jjcdutra2015.com.git.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jjcdutra2015.com.git.exception.InvalidJwtAuthenticationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class JwtTokenProvider @Autowired constructor(
    val userDetailsService: UserDetailsService,

    @Value("security.jwt.token.secret-key:secret")
    var secretKey: String = "secret",

    @Value("security.jwt.token.expire-length:3600000")
    val validityInMilliseconds: Long = 3600000
) {
    init {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createToken(username: String, roles: List<String>): String {
        val claims: Claims = Jwts.claims().setSubject(username)
        claims["roles"] = roles

        val now: Date = Date()
        val validity: Date = Date(now.time + validityInMilliseconds)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails: UserDetails = this.userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken: String = req.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length)
        }
        return null
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims: Jws<Claims> = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            if (claims.body.expiration.before(Date())) {
                return false
            }
            return true
        } catch (e: Exception) {
            throw InvalidJwtAuthenticationException("Expired or invalid token")
        }
    }
}