package app.repositories

import app.models.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : Neo4jRepository<UserEntity , Long> {

	fun findByUsername(userName : String) : UserEntity?

	@Secured("ROLE_ADMIN")
	override fun findAll() : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(depth : Int) : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(sort : Sort) : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(sort : Sort? , depth : Int) : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(pageable : Pageable) : Page<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(pageable : Pageable? , depth : Int) : Page<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>) : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>? , depth : Int) : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>? , sort : Sort?) : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>? , sort : Sort? ,
	                         depth : Int) : MutableIterable<UserEntity>

	@Secured("ROLE_ADMIN")
	override fun <S : UserEntity?> save(entities : MutableIterable<S>? , depth : Int) : MutableIterable<S>

	@Secured("ROLE_ADMIN")
	override fun deleteAll(entities : MutableIterable<UserEntity>)

	@Secured("ROLE_ADMIN")
	override fun deleteAll()

	@Secured("ROLE_ADMIN")
	override fun <S : UserEntity?> saveAll(entities : MutableIterable<S>) : MutableIterable<S>
}