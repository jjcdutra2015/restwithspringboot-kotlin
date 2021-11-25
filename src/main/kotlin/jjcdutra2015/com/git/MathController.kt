package jjcdutra2015.com.git

import jjcdutra2015.com.git.converters.convertToDouble
import jjcdutra2015.com.git.converters.isNumeric
import jjcdutra2015.com.git.exception.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import kotlin.math.sqrt

@RestController
class MathController {

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"], method = [RequestMethod.GET])
    fun sum(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/sub/{numberOne}/{numberTwo}"], method = [RequestMethod.GET])
    fun sub(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }

        return convertToDouble(numberOne) - convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/mult/{numberOne}/{numberTwo}"], method = [RequestMethod.GET])
    fun mult(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }

        return convertToDouble(numberOne) * convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/div/{numberOne}/{numberTwo}"], method = [RequestMethod.GET])
    fun div(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }

        if (isNumeric(numberOne) && numberOne.toDouble() <= 0) {
            throw Exception("Please set a numeric bigger then zero")
        }

        return convertToDouble(numberOne) / convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/avg/{numberOne}/{numberTwo}"], method = [RequestMethod.GET])
    fun avg(@PathVariable numberOne: String, @PathVariable numberTwo: String): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2
    }

    @RequestMapping(value = ["/sqrt/{number}"], method = [RequestMethod.GET])
    fun sqrt(@PathVariable number: String): Double {
        if (!isNumeric(number)) {
            throw UnsupportedMathOperationException("Please set a numeric value")
        }

        return sqrt(convertToDouble(number))
    }
}