package app.controllers

import app.models.UserEntity
import app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@RepositoryRestController
open class UserRestController {
	@Autowired
	private lateinit var userRepository : UserRepository
	@Autowired
	private lateinit var passwordEncoder : PasswordEncoder

	@PostMapping("/users" , consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
	@ResponseBody
	fun registration(@RequestBody user : UserEntity) : UserEntity =
			userRepository.save(user.apply {
				userPassword = passwordEncoder.encode(userPassword)
			})
}