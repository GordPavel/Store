package app.repositories

import app.models.CategoryEntity
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.repository.query.Param
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : Neo4jRepository<CategoryEntity , Long> {

	@Query("MATCH (cat:category) WHERE NOT (:category)-[:SUB_CATEGORY]->(cat) RETURN cat;")
	fun getRootCategories() : Iterable<CategoryEntity>

	@Query("MATCH (parent:category)-[:SUB_CATEGORY*1..1]->(sub:category) WHERE id(parent)={0} RETURN sub;")
	fun getSubCategoriesByParentId(@Param("id") id : Long) : Iterable<CategoryEntity>

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