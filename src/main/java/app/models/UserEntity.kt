package app.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.neo4j.ogm.annotation.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.validation.constraints.Email

@NodeEntity(label = "user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UserEntity(
		@Index(unique = true)
		@JsonProperty("username")
		@Property
		@Email
		var userUsername : String ,
		@JsonProperty("password")
		@Property
		var userPassword : String ,

		@Relationship(type = "favorite")
		var favorite : List<ProductEntity> = emptyList() ,

		@Property var roles : List<String> = listOf("USER")
                     ) : UserDetails {
	@Id
	@GeneratedValue
	var id : Long? = null

	@JsonIgnore
	@Version
	var version : Long? = null

	@JsonIgnore
	override fun getUsername() = userUsername

	@JsonIgnore
	override fun getPassword() = userPassword

	@JsonIgnore
	override fun getAuthorities() = roles.map { role -> SimpleGrantedAuthority(role) }.toMutableList()

	@JsonIgnore
	override fun isEnabled() = true

	@JsonIgnore
	override fun isCredentialsNonExpired() = true

	@JsonIgnore
	override fun isAccountNonExpired() = true

	@JsonIgnore
	override fun isAccountNonLocked() = true
}