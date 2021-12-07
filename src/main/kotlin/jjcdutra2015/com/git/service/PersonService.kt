package jjcdutra2015.com.git.service

import jjcdutra2015.com.git.converter.toPerson
import jjcdutra2015.com.git.converter.toPersonVO
import jjcdutra2015.com.git.data.vo.PersonVO
import jjcdutra2015.com.git.exception.ResourceNotFoundException
import jjcdutra2015.com.git.model.Person
import jjcdutra2015.com.git.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val repository: PersonRepository
) {

    fun create(personVO: PersonVO): PersonVO {
        val person = personVO.toPerson()
        repository.save(person)

        return person.toPersonVO()
    }

    fun findAll(): List<PersonVO> {
        val list: List<Person> = repository.findAll()
        val personsVO = list.map {
            it.toPersonVO()
        }
        return personsVO
    }

    fun findById(id: Long): PersonVO {
        val person: Person =
            repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID: $id") }
        return person.toPersonVO()
    }

    fun update(personVO: PersonVO): PersonVO {
        val person = personVO.toPerson()
        repository.findById(personVO.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID: ${person.id}") }
        repository.save(person)

        return person.toPersonVO()
    }

    fun delete(id: Long) {
        val entity =
            repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID: $id") }
        repository.delete(entity)
    }
}