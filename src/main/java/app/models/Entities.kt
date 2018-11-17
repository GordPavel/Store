package app.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.neo4j.ogm.annotation.*
import org.neo4j.ogm.id.InternalIdStrategy

@NodeEntity(label = "user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UserEntity(
		@Index(unique = true)
		var username : String ,

		var password : String ,

		@Relationship(type = "favorite")
		var favorite : List<ProductEntity> = emptyList() ,

		var roles : Set<String> = setOf("ROLE_USER")
                     ) {
	@Id
	@GeneratedValue
	var id : Long? = null

	@JsonIgnore
	@Version
	var version : Long? = null
}

//@JsonIdentityInfo(
//		generator = ObjectIdGenerators.PropertyGenerator::class ,
//		property = "id")
@NodeEntity(label = "category")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CategoryEntity(
		@Index(unique = true)
		var name : String ,

		@Relationship(type = "subCategory")
		var subCategories : Set<CategoryEntity> = emptySet() ,

		@Relationship(type = "subCategory" , direction = Relationship.INCOMING)
		var parentCategory : CategoryEntity? = null ,

		@JsonBackReference
		@Relationship(type = "product")
		var products : List<ProductEntity> = emptyList()
                         ) {
	@Id
	@GeneratedValue
	var id : Long? = null

	@JsonIgnore
	@Version
	var version : Long? = null

	@Properties(prefix = "property")
	var properties : Map<String , String> = emptyMap()
}

//@JsonIdentityInfo(
//		generator = ObjectIdGenerators.PropertyGenerator::class ,
//		property = "id")
@NodeEntity(label = "product")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ProductEntity(
		@Index(unique = true)
		var name : String ,

		var price : Double ,

		var oldPrice : Double? = null ,

		@JsonManagedReference
		@Relationship(type = "product" , direction = Relationship.INCOMING)
		var category : CategoryEntity
                        ) {
	@Id
	@GeneratedValue
	var id : Long? = null

	var visitorsCount : Int = 0

	@JsonIgnore
	@Version
	var version : Long? = null

	@Properties(prefix = "property")
	var properties : Map<String , String> = emptyMap()
}

@NodeEntity(label = "savedPrincipal")
data class VKEntity(
		val username : String ,
		val password : String
                   ) {
	@Id
	@GeneratedValue(strategy = InternalIdStrategy::class)
	var id : Long? = null
}