package app.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.*
import org.neo4j.ogm.id.UuidStrategy

@NodeEntity(label = "user")
data class UserEntity(
		@Index(unique = true)
		var username : String ,

		var password : String
                     ) {
	@Id
	@GeneratedValue(strategy = UuidStrategy::class)
	var id : String? = null

	@Relationship(type = "favorite")
	var products : List<Product> = emptyList()

	@JsonIgnore
	@Version
	var version : Long? = null
}

@NodeEntity(label = "category")
data class CategoryEntity(
		@Index(unique = true)
		var name : String ,

		@Relationship(type = "subCategory" , direction = Relationship.INCOMING)
		var parentCategory : CategoryEntity?
                         ) {
	@Id
	@GeneratedValue(strategy = UuidStrategy::class)
	var id : String? = null

	@Relationship(type = "subCategory")
	var subCategories : Set<Category> = emptySet()

	@Relationship(type = "product")
	var products : List<Product> = emptyList()

	@JsonIgnore
	@Version
	var version : Long? = null
}

@NodeEntity(label = "product")
data class ProductEntity(
		@Index(unique = true)
		var name : String ,

		var price : Double ,

		@Relationship(type = "product" , direction = Relationship.INCOMING)
		var category : Category
                        ) {
	@Id
	@GeneratedValue(strategy = UuidStrategy::class)
	var id : String? = null

	var oldPrice : Double? = null

	var visitorsCount : Int = 0

	@JsonIgnore
	@Version
	var version : Long? = null
}

@NodeEntity(label = "savedPrincipal")
data class VKEntity(
		val username : String ,
		val password : String
                   ) {
	@Id
	@GeneratedValue(strategy = UuidStrategy::class)
	var id : String? = null
}