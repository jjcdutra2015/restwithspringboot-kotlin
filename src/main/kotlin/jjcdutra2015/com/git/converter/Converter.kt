package jjcdutra2015.com.git.converter

import jjcdutra2015.com.git.data.vo.PersonVO
import jjcdutra2015.com.git.model.Person

fun PersonVO.toPerson(): Person {
    return Person(
        id = id,
        firstName = firstName,
        lastName = lastName,
        address = address,
        gender = gender
    )
}

fun Person.toPersonVO(): PersonVO {
    return PersonVO(
        id = id,
        firstName = firstName,
        lastName = lastName,
        address = address,
        gender = gender
    )
}
