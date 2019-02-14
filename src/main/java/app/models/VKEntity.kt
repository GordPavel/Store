package app.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.typeconversion.Convert
import org.neo4j.ogm.typeconversion.AttributeConverter

@NodeEntity(label = "password")
data class VKEntity(
		@Property
		@JsonProperty("username")
		var username : String ,
		@Property
		@JsonProperty("password")
		var password : String ,
		@Property
		@Convert(SocialNetworkConverter::class)
		var network : SocialNetwork
                   ) {
	@Id
	@GeneratedValue
	var id : Long? = null
}

enum class SocialNetwork(val russian : String) {
	VK("Вконтакте") ,
	ODNOKLASSNIKI("Одноклассники");

	companion object {
		private val map = SocialNetwork.values().map { it.russian to it }.toMap()
		@JsonCreator
		fun getValue(name : String) = map[name] ?: throw IllegalArgumentException("No SocialNetwork with name $name")
	}

	@JsonValue
	fun toJson() = this.russian
}

open class SocialNetworkConverter : AttributeConverter<SocialNetwork , String> {
	override fun toGraphProperty(value : SocialNetwork) = value.toJson()

	override fun toEntityAttribute(value : String) = SocialNetwork.getValue(value)
}