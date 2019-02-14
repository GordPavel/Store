package app.repositories

import app.models.VKEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PasswordRepository : Neo4jRepository<VKEntity , Long> {
	@Secured("ROLE_ADMIN")
	override fun findAll() : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(depth : Int) : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(sort : Sort) : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(sort : Sort? , depth : Int) : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(pageable : Pageable) : Page<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAll(pageable : Pageable? , depth : Int) : Page<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>) : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>? , depth : Int) : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>? , sort : Sort?) : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findAllById(ids : MutableIterable<Long>? , sort : Sort? , depth : Int) : MutableIterable<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findById(id : Long? , depth : Int) : Optional<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun findById(id : Long) : Optional<VKEntity>

	@Secured("ROLE_ADMIN")
	override fun deleteById(id : Long)

	@Secured("ROLE_ADMIN")
	override fun deleteAll(entities : MutableIterable<VKEntity>)

	@Secured("ROLE_ADMIN")
	override fun deleteAll()

	@Secured("ROLE_ADMIN")
	override fun <S : VKEntity?> saveAll(entities : MutableIterable<S>) : MutableIterable<S>

	@Secured("ROLE_ADMIN")
	override fun count() : Long

	@Secured("ROLE_ADMIN")
	override fun existsById(id : Long) : Boolean

	@Secured("ROLE_ADMIN")
	override fun delete(entity : VKEntity)

	@Secured("ROLE_ADMIN")
	override fun <S : VKEntity?> save(s : S , depth : Int) : S

	@Secured("ROLE_ADMIN")
	override fun <S : VKEntity?> save(entities : MutableIterable<S>? , depth : Int) : MutableIterable<S>
}