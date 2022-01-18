package jjcdutra2015.com.git.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import jjcdutra2015.com.git.data.vo.PersonVO
import jjcdutra2015.com.git.exception.ResourceNotFoundException
import jjcdutra2015.com.git.service.PersonService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest
@AutoConfigureMockMvc
class PersonControllerTest {

    private val personApi: String = "/api/person/v1"

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var service: PersonService

    @Test
    @DisplayName("Should create a successful person")
    fun createPersonTest() {
        val personVO = createNewPersonVO()

        BDDMockito.given(service.create(any())).willReturn(personVO)

        val json: String = ObjectMapper().writeValueAsString(personVO)

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post(personApi)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("id").value(personVO.id))
            .andExpect(MockMvcResultMatchers.jsonPath("firstName").value(personVO.firstName))
            .andExpect(MockMvcResultMatchers.jsonPath("lastName").value(personVO.lastName))
            .andExpect(MockMvcResultMatchers.jsonPath("address").value(personVO.address))
            .andExpect(MockMvcResultMatchers.jsonPath("gender").value(personVO.gender))
    }

    @Test
    @DisplayName("Should not create a person with incomplete data")
    fun createInvalidPersonTest() {

        val json = ObjectMapper().writeValueAsString(null)

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post(personApi)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    @DisplayName("should return a person")
    fun findByIdPersonTest() {
        val id: Long = 1
        val personVO = createNewPersonVO()

        BDDMockito.given(service.findById(id)).willReturn(personVO)

        val json = ObjectMapper().writeValueAsString(personVO)

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders.get("$personApi/$id")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("id").value(personVO.id))
            .andExpect(MockMvcResultMatchers.jsonPath("firstName").value(personVO.firstName))
            .andExpect(MockMvcResultMatchers.jsonPath("lastName").value(personVO.lastName))
            .andExpect(MockMvcResultMatchers.jsonPath("address").value(personVO.address))
            .andExpect(MockMvcResultMatchers.jsonPath("gender").value(personVO.gender))
    }

    @Test
    @DisplayName("should throw error when not finding person")
    fun notFindPersonTest() {
        val id: Long = 3

        BDDMockito.given(service.findById(id)).willThrow(ResourceNotFoundException("No records found for this ID: $id"))

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders.get("$personApi/$id")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
//            .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)))
    }

    @Test
    @DisplayName("should delete person")
    fun deletePersonTest() {
        BDDMockito.given(service.findById(1)).willReturn(createNewPersonVO())

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("$personApi/1")
            .accept(MediaType.APPLICATION_JSON)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @DisplayName("should update person")
    fun updatePersonTest() {
        val id: Long = 1
        BDDMockito.given(service.findById(id)).willReturn(createNewPersonVO())

        val updatedPersonVO = PersonVO(
            id = 1,
            firstName = "Julio Jose",
            lastName = "Carvalo Dutra",
            address = "Jorge Ribeiro",
            gender = "Masculino"
        )

        BDDMockito.given(service.update(createNewPersonVO())).willReturn(updatedPersonVO)

        val json = ObjectMapper().writeValueAsString(createNewPersonVO())

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders.put(personApi)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("id").value(updatedPersonVO.id))
            .andExpect(MockMvcResultMatchers.jsonPath("firstName").value(updatedPersonVO.firstName))
            .andExpect(MockMvcResultMatchers.jsonPath("lastName").value(updatedPersonVO.lastName))
            .andExpect(MockMvcResultMatchers.jsonPath("address").value(updatedPersonVO.address))
            .andExpect(MockMvcResultMatchers.jsonPath("gender").value(updatedPersonVO.gender))
    }
}

private fun createNewPersonVO() = PersonVO(
    id = 1,
    firstName = "Julio",
    lastName = "Dutra",
    address = "Jorge Ribeiro",
    gender = "Masculino"
)