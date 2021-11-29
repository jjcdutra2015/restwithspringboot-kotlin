package jjcdutra2015.com.git.service

import jjcdutra2015.com.git.exception.ResourceNotFoundException
import jjcdutra2015.com.git.model.Person
import jjcdutra2015.com.git.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val repository: PersonRepository
) {

    fun create(person: Person): Person {
        return repository.save(person)
    }

    fun findAll(): List<Person> {
        return repository.findAll()
    }

    fun findById(id: Long): Person {
        return repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID: $id") }
    }

    fun update(person: Person): Person {
        var entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID: ${person.id}") }

        entity.copy(
            firstName = person.firstName,
            lastName = person.lastName,
            address = person.address,
            gender = person.gender
        )

        return repository.save(entity)
    }

    fun delete(id: Long) {
        val entity =
            repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID: $id") }
        repository.delete(entity)
    }
}