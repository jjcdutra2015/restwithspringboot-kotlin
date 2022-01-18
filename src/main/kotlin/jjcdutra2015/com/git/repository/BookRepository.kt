package jjcdutra2015.com.git.repository

import jjcdutra2015.com.git.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>