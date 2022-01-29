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
        val token: String? = tokenProvider.resolveToken(req as HttpServletRequest)
        if (token != null && tokenProvider.validateToken(token)) {
            val auth: Authentication = tokenProvider.getAuthentication(token)
            if (auth != null) {
                SecurityContextHolder.getContext().authentication
            }
        }
        chain.doFilter(req, res)
    }
}