package jjcdutra2015.com.git.controller

import jjcdutra2015.com.git.repository.UserRepository
import jjcdutra2015.com.git.security.AccountsCredencialsVO
import jjcdutra2015.com.git.security.jwt.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val repository: UserRepository,
    private val tokenProvider: JwtTokenProvider
) {
    @PostMapping("/signin")
    fun signin(@RequestBody data: AccountsCredencialsVO): ResponseEntity<Any> {
        try {
            val username = data.userName
            val password = data.password

            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))

            val user = repository.findByUserName(username)

            var token = ""
            if (user != null) {
                token = tokenProvider.createToken(username, user.getRoles())
            } else {
                throw UsernameNotFoundException("Username $username not found")
            }

            val model = mapOf(Pair("username", username), Pair("token", token))

            return ok(model)
        } catch (e: AuthenticationException) {
            throw BadCredentialsException("Invalid username/password supplied!")
        }
    }
}