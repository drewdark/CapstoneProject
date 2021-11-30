package com.example.capstone.controller;

import com.example.capstone.model.Request;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CapstoneControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenDBInitilizedWith3Records_whenGETPeople_shouldReturn3Items() throws Exception {
        final var mvcResult = mockMvc
                .perform(get("/requests"))
                .andDo(print())
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();

        List<Request> peopleFromDB = objectMapper.readValue(contentAsString, new TypeReference<>(){});

        assertEquals(3, peopleFromDB.size());
    }

    @Test
    @DirtiesContext
    void givenDBInitializedWith3Records_whenPOSTPeople_shouldSaveAndReturn() throws Exception {
        String firstName = "Andrew";
        String lastName = "Dark";
        Long id = 1L;
        String prefix = "Mr";
        String telNumber= "12345678910";
        String address1 = "123";
        String address2 = "Something Road";
        String city = "Belfast";
        String postCode = "BT123";
        String carType = "Cabriolet";
        String engineSize = "1600";
        String additionalDrivers = "1";
        String commercialPurposes = "false";
        String outsideState = "false";
        String dateRegistered = "10-02-2021";
        String vehicleValue = "6000";
        String comments = ":)";
        Request person = new Request(id, prefix, firstName, lastName,  telNumber, address1, address2, city, postCode, carType, engineSize, additionalDrivers, commercialPurposes, outsideState, vehicleValue, dateRegistered, comments);

        final String personAsJSON = objectMapper.writeValueAsString(person);

        final var mvcResult = mockMvc
                .perform(post("/requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsJSON)
                )
                .andDo(print())
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();

        Request savedPerson = objectMapper.readValue(contentAsString, Request.class);

        assertAll(
                () -> assertNotNull(savedPerson.getId()),
                () -> assertEquals(firstName, savedPerson.getFirstname()),
                () -> assertEquals(lastName, savedPerson.getLastname())
        );
    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenDELETEPeopleWithExistingID_shouldReturn200() throws Exception {

        int existingId= 1;

        final var mvcResult = mockMvc
                .perform(delete("/requests/" + existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}