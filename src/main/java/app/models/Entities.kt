package app.models

import com.fasterxml.jackson.annotation.*
import org.neo4j.ogm.annotation.*
import org.neo4j.ogm.annotation.typeconversion.Convert
import org.neo4j.ogm.typeconversion.AttributeConverter
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.validation.constraints.Email


@NodeEntity(label = "user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UserEntity(
		@Index(unique = true)
		@JsonProperty("username")
		@Property
		@Email
		var userUsername : String ,
		@JsonProperty("password")
		@Property
		var userPassword : String ,

		@Relationship(type = "favorite")
		var favorite : List<ProductEntity> = emptyList() ,

		@Property var roles : List<String> = listOf("USER")
                     ) : UserDetails {
	@Id
	@GeneratedValue
	var id : Long? = null

	@JsonIgnore
	@Version
	var version : Long? = null

	@JsonIgnore
	override fun getUsername() = userUsername

	@JsonIgnore
	override fun getPassword() = userPassword

	@JsonIgnore
	override fun getAuthorities() = roles.map { role -> SimpleGrantedAuthority(role) }.toMutableList()

	@JsonIgnore
	override fun isEnabled() = true

	@JsonIgnore
	override fun isCredentialsNonExpired() = true

	@JsonIgnore
	override fun isAccountNonExpired() = true

	@JsonIgnore
	override fun isAccountNonLocked() = true
}

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

@NodeEntity(label = "product")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ProductEntity(
		@Index(unique = true)
		var name : String ,

		@Property
		var price : Double ,

		@Property
		var oldPrice : Double? = null ,

		@JsonManagedReference
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