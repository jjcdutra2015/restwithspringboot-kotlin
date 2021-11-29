package jjcdutra2015.com.git.repository

import jjcdutra2015.com.git.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {
}