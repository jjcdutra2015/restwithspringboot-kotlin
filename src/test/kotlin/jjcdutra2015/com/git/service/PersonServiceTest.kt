package jjcdutra2015.com.git.service

import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import jjcdutra2015.com.git.converter.toPerson
import jjcdutra2015.com.git.data.vo.PersonVO
import jjcdutra2015.com.git.exception.ResourceNotFoundException
import jjcdutra2015.com.git.model.Person
import jjcdutra2015.com.git.repository.PersonRepository
import org.assertj.core.api.Assertions.catchThrowable
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
class PersonServiceTest {

    private lateinit var service: PersonService

    @MockBean
    private lateinit var repository: PersonRepository

    @BeforeEach
    fun setUp() {
        service = PersonService(repository)
    }

    @Test
    @DisplayName("should create a person success")
    fun createPersonTest() {
        val personVO = createNewPersonVO()
        val person = personVO.toPerson()

        Mockito.`when`(repository.save(person)).thenReturn(
            Person(
                id = 1,
                firstName = "Julio",
                lastName = "Dutra",
                address = "Jorge Ribeiro",
                gender = "Masculino"
            )
        )

        val personVOSaved = service.create(personVO)

        assertThat(personVOSaved.id).isNotNull
        assertThat(personVOSaved.firstName).isEqualTo("Julio")
        assertThat(personVOSaved.lastName).isEqualTo("Dutra")
        assertThat(personVOSaved.address).isEqualTo("Jorge Ribeiro")
        assertThat(personVOSaved.gender).isEqualTo("Masculino")
    }

    @Test
    @DisplayName("should return list of person")
    fun findAllPersonTest() {
        val personVO = createNewPersonVO()
        val person = personVO.toPerson()

        Mockito.`when`(repository.findAll()).thenReturn(listOf(person))

        val list = service.findAll()

        assertThat(list.isNotEmpty())
        assertThat(list[0].id).isNotNull
        assertThat(list[0].firstName).isEqualTo("Julio")
        assertThat(list[0].lastName).isEqualTo("Dutra")
        assertThat(list[0].address).isEqualTo("Jorge Ribeiro")
        assertThat(list[0].gender).isEqualTo("Masculino")
    }

    @Test
    @DisplayName("should return a person")
    fun findByIdPersonTest() {
        val id: Long = 1
        val personVO = createNewPersonVO()
        val person = personVO.toPerson()

        Mockito.`when`(repository.findById(person.id)).thenReturn(Optional.of(person))

        val foundPersonVO = service.findById(id)

        assertThat(foundPersonVO).isNotNull
        assertThat(foundPersonVO.firstName).isEqualTo(person.firstName)
        assertThat(foundPersonVO.lastName).isEqualTo(person.lastName)
        assertThat(foundPersonVO.address).isEqualTo(person.address)
        assertThat(foundPersonVO.gender).isEqualTo(person.gender)
    }

    @Test
    @DisplayName("should throw error when id is non-existent")
    fun idIsNonExistentTest() {
        val id: Long = 1

        Mockito.`when`(repository.findById(id)).thenReturn(Optional.empty())

        val exception: Throwable = catchThrowable {
            service.findById(id)
        }

        assertThat(exception)
            .isInstanceOf(ResourceNotFoundException::class.java)
            .hasMessage("No records found for this ID: $id")
    }

    @Test
    @DisplayName("should delete person with success")
    fun deletePersonTest() {
        val id: Long = 1
        val personVO = createNewPersonVO()
        val person = personVO.toPerson()

        Mockito.`when`(repository.findById(id)).thenReturn(Optional.of(person))

        assertDoesNotThrow {
            service.delete(id)
        }

        verify(repository, times(1)).delete(person)
    }

    @Test
    @DisplayName("should update person")
    fun updatePersonTest() {
        val id: Long = 1
        val personVO = createNewPersonVO()
        val person = personVO.toPerson()

        Mockito.`when`(repository.findById(id)).thenReturn(Optional.of(person))

        val personVOUpdated = service.update(personVO)

        assertEquals(id, personVOUpdated.id)
    }

    @Test
    @DisplayName("should throw error when person non-existent")
    fun throwErrorUpdatePersonTest() {
        val personVO = createNewPersonVO()
        val person = personVO.toPerson()

        Assertions.assertThrows(ResourceNotFoundException::class.java) {
            service.update(personVO)
        }

        verify(repository, never()).save(person)
    }

    private fun createNewPersonVO() = PersonVO(
        id = 1,
        firstName = "Julio",
        lastName = "Dutra",
        address = "Jorge Ribeiro",
        gender = "Masculino"
    )
}
