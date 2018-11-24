package app.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.neo4j.ogm.annotation.*

@NodeEntity(label = "product")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ProductEntity(
		@Index(unique = true)
		var name : String ,

		@Property
		var price : Double ,

		@Property
		var oldPrice : Double? = null ,

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
}