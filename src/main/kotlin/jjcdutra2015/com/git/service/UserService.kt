package jjcdutra2015.com.git.service

import jjcdutra2015.com.git.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(userName: String): UserDetails {
        return repository.findByUserName(userName)
    }
}