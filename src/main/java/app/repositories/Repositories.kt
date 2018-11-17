package app.repositories

import app.models.CategoryEntity
import app.models.ProductEntity
import app.models.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
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

@RepositoryRestResource(path = "products" , collectionResourceRel = "products" , itemResourceRel = "product")
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

@RepositoryRestResource(path = "users" , collectionResourceRel = "users" , itemResourceRel = "user")
interface UserRepository : Neo4jRepository<UserEntity , Long> {
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