package jjcdutra2015.com.git.controller

import jjcdutra2015.com.git.data.vo.BookVO
import jjcdutra2015.com.git.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/book/v1")
class BookController(
    private val service: BookService
) {

    @GetMapping(value = ["/{id}"], produces = ["application/json", "application/xml"])
    fun findById(@PathVariable id: Long): BookVO {
        return service.findById(id)
    }

    @GetMapping(produces = ["application/json", "application/xml"])
    fun findAll(): List<BookVO> {
        return service.findAll()
    }

    @PostMapping(
        produces = ["application/json", "application/xml"],
        consumes = ["application/json", "application/xml"]
    )
    fun create(@RequestBody book: BookVO): BookVO {
        return service.create(book)
    }

    @PutMapping(
        produces = ["application/json", "application/xml"],
        consumes = ["application/json", "application/xml"]
    )
    fun update(@RequestBody book: BookVO): BookVO {
        return service.update(book)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}