package app.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.neo4j.ogm.annotation.*
import org.neo4j.ogm.annotation.Properties
import java.util.*
import java.util.function.Predicate

@NodeEntity(label = "product")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ProductEntity(
		@Index(unique = true)
		var name : String ,

		@Property
		var price : Double ,

		@JsonBackReference
		@Relationship(type = "product" , direction = Relationship.INCOMING)
		var category : CategoryEntity
                        ) {
	@Id
	@GeneratedValue
	var id : Long? = null

	@Property
	var visitorsCount : Int = 0

	@JsonIgnore
	@Version
	var version : Long? = null

	@Properties(prefix = "property")
	var properties : Map<String , String> = emptyMap()

	override fun hashCode() = id?.toString()?.hashCode() ?: name.hashCode()

	override fun equals(other : Any?) : Boolean {
		return id?.equals((other as? ProductEntity ?: return false).id) ?: false
	}

	override fun toString() = "id: ${id ?: "unregistered"},name: $name"
}

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ProductDTO(val name : String , val price : Double , val categoryId : Long ,
                      val properties : Map<String , String> = emptyMap())

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class SearchProductFilter(
		val searchString : String? ,
		val ids : List<Long>? ,
		val exceptIds : List<Long>? ,
		val categories : List<Long>? ,
		val properties : Map<String , String>?
                              ) {
	fun toPredicate() : Predicate<ProductEntity> {
		var predicate = Predicate<ProductEntity> { true }

		if(ids != null) predicate = predicate.and { ids.contains(it.id) }
		if(exceptIds != null) predicate = predicate.and { !exceptIds.contains(it.id) }
		if(categories != null) predicate = predicate.and { categories.contains(it.category.id) }
		if(properties != null) predicate = predicate.and { product ->
			properties.entries.stream()
					.anyMatch { (prop , value) ->
						Optional.ofNullable(product.properties[prop]).map(value::equals)
								.orElse(false)
					}
		}
		return predicate
	}
}