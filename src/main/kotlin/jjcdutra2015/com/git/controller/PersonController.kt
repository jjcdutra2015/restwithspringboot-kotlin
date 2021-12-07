package jjcdutra2015.com.git.controller

import jjcdutra2015.com.git.data.vo.PersonVO
import jjcdutra2015.com.git.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(
    private val service: PersonService
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): PersonVO {
        return service.findById(id)
    }

    @GetMapping
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @PostMapping
    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person)
    }

    @PutMapping
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}