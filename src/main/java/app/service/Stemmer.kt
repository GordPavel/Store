package app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.tartarus.snowball.ext.RussianStemmer
import java.util.regex.Pattern.compile

interface Stemmer {
	fun stem(str : String) : String

	fun fits(search : String , property : String) =
			stem(property).split(compile("\\s+")).stream().anyMatch { search.contains(it , true) }
}

@Service
open class StemmerImpl : Stemmer {

	@Autowired
	@Qualifier("apacheStemmer")
	private lateinit var stemmer : RussianStemmer

	override fun stem(str : String) : String {
		stemmer.current = str
		stemmer.stem()
		return stemmer.current
	}
}