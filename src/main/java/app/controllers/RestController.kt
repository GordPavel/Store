package app.controllers

import app.models.UserEntity
import app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.http.MediaType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
open class RestController {
	@GetMapping("/authorities" ,
	            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE])
	fun getPrincipal() : Collection<GrantedAuthority>? = SecurityContextHolder.getContext().authentication.authorities
}

@RepositoryRestController
@Transactional(isolation = Isolation.REPEATABLE_READ)
open class UserRestRepository {
	@Autowired
	private lateinit var userRepository : UserRepository
	@Autowired
	private lateinit var passwordEncoder : PasswordEncoder

	@PostMapping("/users" , consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
	fun registration(@RequestBody user : UserEntity) : UserEntity =
			userRepository.save(user.apply {
				userPassword = passwordEncoder.encode(userPassword)
			})
}

