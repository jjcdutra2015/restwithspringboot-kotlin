package jjcdutra2015.com.git.converter

import jjcdutra2015.com.git.data.vo.BookVO
import jjcdutra2015.com.git.data.vo.PersonVO
import jjcdutra2015.com.git.model.Book
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

fun BookVO.toBook(): Book {
    return Book(
        id = id,
        author = author,
        launchDate = launchDate,
        price = price,
        title = title
    )
}

fun Book.toBookVO(): BookVO {
    return BookVO(
        id = id,
        author = author,
        launchDate = launchDate,
        price = price,
        title = title
    )
}

