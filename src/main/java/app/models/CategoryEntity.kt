package app.models

import com.fasterxml.jackson.annotation.*
import org.neo4j.ogm.annotation.*

interface IdentifiedEntity {
	var id : Long?
	var name : String
}

interface WithParent : IdentifiedEntity {
	var parent : CategoryEntity?
}

@NodeEntity(label = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = ["id" , "name" , "parentCategory" , "properties" , "subCategories" , "products"])
data class CategoryEntity(
		@Index(unique = true)
		override var name : String ,

		@JsonManagedReference
		@Relationship(type = "SUB_CATEGORY")
		var subCategories : List<CategoryEntity> = emptyList() ,

		@JsonBackReference
		@Relationship(type = "SUB_CATEGORY" , direction = Relationship.INCOMING)
		var parentCategory : CategoryEntity? = null ,

		@Relationship(type = "PRODUCT")
		@JsonBackReference
		var products : List<ProductEntity> = emptyList()
                         ) : WithParent {

	@JsonIgnore
	override var parent : CategoryEntity? = null
		get() = parentCategory

	@Id
	@GeneratedValue
	override var id : Long? = null

	@JsonIgnore
	@Version
	var version : Long? = null

	@Properties(prefix = "property")
	var properties : Map<String , String> = emptyMap()

	override fun hashCode() = id?.toString()?.hashCode() ?: name.hashCode()

	override fun equals(other : Any?) : Boolean {
		return id?.equals((other as? CategoryEntity ?: return false).id) ?: false
	}

	override fun toString() = "id: ${id ?: "unregistered"},name: $name"
}

data class CategoryDTO(val id : Long , val name : String , val subCategoriesIds : List<Long> = emptyList() ,
                       val parentCategoryId : Long? , val productsIds : List<Long> = emptyList() ,
                       val properties : Map<String , String> = emptyMap())