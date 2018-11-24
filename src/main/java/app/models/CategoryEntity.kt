package app.models

import com.fasterxml.jackson.annotation.*
import org.neo4j.ogm.annotation.*

@NodeEntity(label = "category")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(value = ["id" , "name" , "parentCategory" , "properties" , "subCategories" , "products"])
data class CategoryEntity(
		@Index(unique = true)
		var name : String ,

		@JsonManagedReference
		@Relationship(type = "subCategory")
		var subCategories : List<CategoryEntity> = emptyList() ,

		@JsonBackReference
		@Relationship(type = "subCategory" , direction = Relationship.INCOMING)
		var parentCategory : CategoryEntity? = null ,

		@Relationship(type = "product")
		@JsonManagedReference
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

	override fun hashCode() = id?.toString()?.hashCode() ?: name.hashCode()

	override fun equals(other : Any?) : Boolean {
		return id?.equals((other as? CategoryEntity ?: return false).id) ?: false
	}

	override fun toString() = "id: $id,name: $name"
}

data class CategoryDTO(val name : String , val subCategoriesIds : List<Long> = emptyList() ,
                       val parentCategoryId : Long? , val productsIds : List<Long> = emptyList() ,
                       val properties : Map<String , String> = emptyMap())