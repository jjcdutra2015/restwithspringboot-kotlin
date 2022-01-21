package jjcdutra2015.com.git.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "permission")
data class Permission(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val description: String

) : GrantedAuthority {
    override fun getAuthority(): String {
        return this.description
    }
}