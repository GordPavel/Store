package app.repositories

import app.models.CategoryEntity
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.security.access.annotation.Secured

@RepositoryRestResource(path = "categories" , collectionResourceRel = "categories" , itemResourceRel = "category")
interface CategoryRepository : Neo4jRepository<CategoryEntity , Long> {

	@Secured("ROLE_ADMIN")
	override fun <S : CategoryEntity?> save(s : S , depth : Int) : S

	@Secured("ROLE_ADMIN")
	override fun <S : CategoryEntity?> save(entities : MutableIterable<S>? , depth : Int) : MutableIterable<S>

	@Secured("ROLE_ADMIN")
	override fun <S : CategoryEntity?> save(entity : S) : S

	@Secured("ROLE_ADMIN")
	override fun deleteById(id : Long)

	@Secured("ROLE_ADMIN")
	override fun deleteAll(entities : MutableIterable<CategoryEntity>)

	@Secured("ROLE_ADMIN")
	override fun deleteAll()

	@Secured("ROLE_ADMIN")
	override fun <S : CategoryEntity?> saveAll(entities : MutableIterable<S>) : MutableIterable<S>

	@Secured("ROLE_ADMIN")
	override fun delete(entity : CategoryEntity)
}