package jjcdutra2015.com.git.service

import jjcdutra2015.com.git.converter.toBook
import jjcdutra2015.com.git.converter.toBookVO
import jjcdutra2015.com.git.data.vo.BookVO
import jjcdutra2015.com.git.exception.ResourceNotFoundException
import jjcdutra2015.com.git.model.Book
import jjcdutra2015.com.git.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val repository: BookRepository
) {

    fun create(BookVO: BookVO): BookVO {
        val book = BookVO.toBook()
        repository.save(book)

        return book.toBookVO()
    }

    fun findAll(): List<BookVO> {
        val list: List<Book> = repository.findAll()
        val bookVO = list.map {
            it.toBookVO()
        }
        return bookVO
    }

    fun findById(id: Long): BookVO {
        val book: Book =
            repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID: $id") }
        return book.toBookVO()
    }

    fun update(BookVO: BookVO): BookVO {
        val book = BookVO.toBook()
        repository.findById(BookVO.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID: ${book.id}") }
        repository.save(book)

        return book.toBookVO()
    }

    fun delete(id: Long) {
        val entity =
            repository.findById(id).orElseThrow { ResourceNotFoundException("No records found for this ID: $id") }
        repository.delete(entity)
    }
}