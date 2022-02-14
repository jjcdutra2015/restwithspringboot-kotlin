package jjcdutra2015.com.git.security.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.FilterChain
import javax.servlet.GenericFilter
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtTokenFilter(
    private val tokenProvider: JwtTokenProvider
) : GenericFilter() {
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val request = req as HttpServletRequest
        if (request.requestURI.contains("/auth/signin")) {
            chain.doFilter(req, res)
        } else {
            val token: String? = tokenProvider.resolveToken(request)
            if (token != null && tokenProvider.validateToken(token)) {
                val auth: Authentication = tokenProvider.getAuthentication(token)
                if (auth != null) {
                    SecurityContextHolder.getContext().authentication = auth
                }
            }
            chain.doFilter(req, res)
        }
    }
}