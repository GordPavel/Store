package app.repositories

import app.models.ProductEntity
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : Neo4jRepository<ProductEntity , Long> {

	@Secured("ROLE_ADMIN")
	override fun delete(entity : ProductEntity)

	@Secured("ROLE_ADMIN")
	override fun <S : ProductEntity?> save(s : S , depth : Int) : S

	@Secured("ROLE_ADMIN")
	override fun <S : ProductEntity?> save(entities : MutableIterable<S>? , depth : Int) : MutableIterable<S>

	@Secured("ROLE_ADMIN")
	override fun <S : ProductEntity?> save(entity : S) : S

	@Secured("ROLE_ADMIN")
	override fun deleteById(id : Long)

	@Secured("ROLE_ADMIN")
	override fun deleteAll(entities : MutableIterable<ProductEntity>)

	@Secured("ROLE_ADMIN")
	override fun deleteAll()

	@Secured("ROLE_ADMIN")
	override fun <S : ProductEntity?> saveAll(entities : MutableIterable<S>) : MutableIterable<S>
}