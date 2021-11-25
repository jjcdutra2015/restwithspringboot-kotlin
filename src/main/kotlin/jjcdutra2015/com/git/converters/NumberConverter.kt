package jjcdutra2015.com.git.converters

fun convertToDouble(strNumber: String): Double {
    val number = strNumber.replace(",", ".")
    if (isNumeric(number)) return number.toDouble()
    return 0.0
}

fun isNumeric(strNumber: String): Boolean {
    val number = strNumber.replace(",", ".")
    return number.matches("[-+]?[0-9]*\\.?[0-9]+".toRegex())
}