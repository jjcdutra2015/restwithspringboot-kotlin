package jjcdutra2015.com.git.service

import jjcdutra2015.com.git.model.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class PersonService {

    private val counter: AtomicLong = AtomicLong()

    fun create(person: Person): Person {
        return person
    }

    fun update(person: Person): Person {
        return person
    }

    fun delete(id: String) {
    }

    fun findById(id: String): Person {
        return Person(
            id = counter.incrementAndGet(),
            firstName = "Julio",
            lastName = "Dutra",
            address = "Carmo - Rio de Janeiro - Brasil",
            gender = "Male"
        )
    }

    fun findAll(): List<Person> {
        val persons: ArrayList<Person> = ArrayList()
        for (i in 1..7) {
            val person = Person(
                id = counter.incrementAndGet(),
                firstName = "First Name $i",
                lastName = "Last Name $i",
                address = "Carmo $i",
                gender = "Male"
            )
            persons.add(person)
        }
        return persons
    }
}