package jjcdutra2015.com.git.controller

import jjcdutra2015.com.git.model.Person
import jjcdutra2015.com.git.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(
    private val service: PersonService
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Person {
        return service.findById(id)
    }

    @GetMapping
    fun findAll(): List<Person> {
        return service.findAll()
    }

    @PostMapping
    fun create(@RequestBody person: Person): Person {
        return service.create(person)
    }

    @PutMapping
    fun update(@RequestBody person: Person): Person {
        return service.update(person)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}