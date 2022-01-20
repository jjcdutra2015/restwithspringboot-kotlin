package jjcdutra2015.com.git.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import jjcdutra2015.com.git.data.vo.PersonVO
import jjcdutra2015.com.git.service.PersonService
import org.springframework.web.bind.annotation.*

@Api(value = "Person API", description = "Persons descriptions", tags = ["Person API"])
//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
class PersonController(
    private val service: PersonService
) {

    //@CrossOrigin(origins = ["http://localhost:8080", "https://google.com.br"])
    @GetMapping(value = ["/{id}"], produces = ["application/json", "application/xml"])
    fun findById(@PathVariable id: Long): PersonVO {
        return service.findById(id)
    }

    //@CrossOrigin(origins = ["http://localhost:8080"])
    @ApiOperation("Find all record people")
    @GetMapping(produces = ["application/json", "application/xml"])
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @PostMapping(
        produces = ["application/json", "application/xml"],
        consumes = ["application/json", "application/xml"]
    )
    fun create(@RequestBody person: PersonVO): PersonVO {
        return service.create(person)
    }

    @PutMapping(
        produces = ["application/json", "application/xml"],
        consumes = ["application/json", "application/xml"]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}