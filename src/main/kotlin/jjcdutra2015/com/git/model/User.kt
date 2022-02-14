package jjcdutra2015.com.git.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "user_name", unique = true)
    val userName: String,

    @Column(name = "full_name")
    val fullName: String,

    @Column(name = "password")
    val userPassword: String,

    @Column(name = "account_non_expired")
    val accountNonExpired: Boolean,

    @Column(name = "account_non_locked")
    val accountNonLocked: Boolean,

    @Column(name = "credentials_non_expired")
    val credentialsNonExpired: Boolean,

    @Column(name = "enabled")
    val enabled: Boolean,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]
    )
    val permissions: MutableList<Permission>

) : UserDetails {

    fun getRoles(): List<String> {
        val roles: MutableList<String> = mutableListOf()
        this.permissions.forEach {
            roles.add(it.description)
        }
        return roles
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.permissions
    }

    override fun getPassword(): String {
        return this.userPassword
    }

    override fun getUsername(): String {
        return this.userName
    }

    override fun isAccountNonExpired(): Boolean {
        return this.accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return this.accountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.credentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return this.enabled
    }
}