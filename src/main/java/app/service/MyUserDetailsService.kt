package app.service

import app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service("userDetails")
open class MyUserDetailsService : UserDetailsService {

	@Autowired
	private lateinit var userRepository : UserRepository
	@Autowired
	@Qualifier("passwordEncoder")
	private lateinit var encoder : PasswordEncoder
	@Value("\${adminUserName}")
	private lateinit var adminUsername : String
	@Value("\${adminPassword}")
	private lateinit var adminPassword : String
	@Value("\${userUserName}")
	private lateinit var userUsername : String
	@Value("\${userPassword}")
	private lateinit var userPassword : String

	private lateinit var memoryUsers : Map<String , UserDetails>

	override fun loadUserByUsername(username : String) : UserDetails =
			memoryUsers[username] ?: userRepository.findByUsername(username)
			?: throw UsernameNotFoundException(username)

	@PostConstruct
	fun initMemoryUsers() {
		memoryUsers = hashMapOf(
				adminUsername to User.builder()
						.username(adminUsername)
						.password(adminPassword)
						.roles("ADMIN" , "USER")
						.passwordEncoder(encoder::encode)
						.build() ,
				userUsername to User.builder()
						.username(userUsername)
						.password(userPassword)
						.roles("USER")
						.passwordEncoder(encoder::encode)
						.build()
		                       )
	}
}