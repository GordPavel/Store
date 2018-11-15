package app.repositories

import app.models.CategoryEntity
import app.models.ProductEntity
import app.models.UserEntity
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "categories" , collectionResourceRel = "categories")
interface CategoryRepository : Neo4jRepository<CategoryEntity , Long>

@RepositoryRestResource(path = "products" , collectionResourceRel = "prodicts")
interface ProductRepository : Neo4jRepository<ProductEntity , Long>

@RepositoryRestResource(path = "users" , collectionResourceRel = "users")
interface UserRepository : Neo4jRepository<UserEntity , String>