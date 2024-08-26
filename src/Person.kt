data class Person (val name: String, val age: Int? = null, val cart: String? = null, val password: String? = null) {

    override fun toString(): String {
        val nameOut = "name=$name"
        val ageOut = "age=$age"
        val cartOut = "cart=$cart"
        val passwordOut = "password=$password"

        val outStrokeList = mutableListOf(nameOut,ageOut,cartOut,passwordOut)

        if (age == null) outStrokeList.remove(ageOut)
        if (cart == null) outStrokeList.remove(cartOut)
        if (password == null) outStrokeList.remove(passwordOut)

        val outStroke = "Person" + outStrokeList.joinToString (
            prefix = "[",
            postfix = "]",
            separator = ", "
        )

        return outStroke
    }
}