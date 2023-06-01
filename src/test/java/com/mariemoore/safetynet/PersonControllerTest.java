package com.mariemoore.safetynet;

import com.mariemoore.safetynet.controller.PersonController;
import com.mariemoore.safetynet.model.Person;
import com.mariemoore.safetynet.service.PersonService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    PersonService personService;

    private List<Person> personList;
    private Person john;
    private Person jacob;
    private Person tenley;

    @BeforeAll
    void setup() {
        john = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        jacob = new Person(
                "Jacob",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6513",
                "drk@email.com");

        tenley = new Person(
                "Tenley",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "tenz@email.com");

        personList = Arrays.asList(john, jacob, tenley);
    }

    @Test
    public void getAllPersonsShouldReturnListOfPersons() throws Exception{
        when(personService.getPersons()).thenReturn(personList);
        mvc.perform(get("/person/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[1].firstName", is("Jacob")))
                .andExpect(jsonPath("$[2].firstName", is("Tenley")));
    }

    @Test
    public void addPersonShouldAddAndReturnPerson() throws Exception {
        Person toAdd = new Person(
                "toAdd",
                "toAdd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "tenz@email.com");
        when(personService.addPerson(toAdd)).thenReturn(toAdd);
        mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(toAdd)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addPersonWhoAlreadyExistsShouldNotReturnPerson() throws Exception {
        Person alreadyExistingPerson = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personService.addPerson(alreadyExistingPerson)).thenReturn(null);
        mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(alreadyExistingPerson)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void addPersonWithInvalidPropertiesShouldNotReturnPerson() throws Exception {
        Person invalidPropertiesPerson = new Person(
                "John",
                "Boyd",
                "",
                "",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personService.addPerson(invalidPropertiesPerson)).thenReturn(null);
        mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(invalidPropertiesPerson)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void updatePersonShouldUpdateAndReturnPerson() throws Exception {
        Person toUpdate = new Person(
                "John",
                "Boyd",
                "Updated Address!",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personService.updatePerson(toUpdate)).thenReturn(toUpdate);
        mvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(toUpdate)))
                .andDo(print())
                .andExpect(result -> assertEquals(jsonUtils.asJsonString(toUpdate), result.getResponse().getContentAsString()))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePersonWithInvalidPropertiesShouldNotReturnPerson() throws Exception {
        Person invalidPropertiesPerson = new Person(
                "John",
                "Boyd",
                "",
                "",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personService.updatePerson(invalidPropertiesPerson)).thenReturn(null);
        mvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(invalidPropertiesPerson)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void updatePersonWhoDoesNotExistShouldNotReturnPerson() throws Exception {
        Person toUpdate = new Person(
                "John",
                "Boyd",
                "Updated Address!",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personService.updatePerson(toUpdate)).thenReturn(null);
        mvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(toUpdate)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deletePersonShouldReturnNoContent() throws Exception {
        Person toDelete = new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personService.deletePerson(toDelete.getFirstName(), toDelete.getLastName())).thenReturn(
                new Person(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com"));
        mvc.perform(delete("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(toDelete)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deletePersonWhoDoesNotExistsShouldReturnNoContent() throws Exception {
        Person toDelete = new Person(
                "I don't exist",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personService.deletePerson(toDelete.getFirstName(), toDelete.getLastName())).thenReturn(null);
        mvc.perform(delete("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUtils.asJsonString(toDelete)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
