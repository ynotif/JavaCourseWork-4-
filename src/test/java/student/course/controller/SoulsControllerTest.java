package student.course.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import student.course.model.Souls;
import student.course.repository.SoulsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class SoulsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SoulsRepository soulsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("add soul by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void addSoulsByCreator() throws Exception {
        Souls souls = new Souls();
        Souls expectedSouls = new Souls();
        expectedSouls.setSoulId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/souls")
                        .content(objectMapper.writeValueAsString(souls))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedSouls)));
        Optional<Souls> soulsOptional = soulsRepository.findById(1L);
        assertTrue(soulsOptional.isPresent());
        assertEquals(expectedSouls, soulsOptional.get());
    }

    @DisplayName("update soul by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void updateSoulsByCreator() throws Exception {
        Souls souls = new Souls();
        soulsRepository.save(souls);
        Souls expectedSouls = new Souls();
        Souls updatedSouls = new Souls();
        updatedSouls.setSoulName("Soul");
        expectedSouls.setSoulId(1L);
        expectedSouls.setSoulName("Soul");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/souls/1")
                        .content(objectMapper.writeValueAsString(updatedSouls))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedSouls)));
        Optional<Souls> optionalSouls = soulsRepository.findById(1L);
        assertTrue(optionalSouls.isPresent());
        assertEquals(expectedSouls, optionalSouls.get());
    }

    @DisplayName("add soul by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void addSoulsByUser() throws Exception {
        Souls souls = new Souls();
        Souls expectedSouls = new Souls();
        expectedSouls.setSoulId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/souls")
                                .content(objectMapper.writeValueAsString(souls))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Souls> soulsOptional = soulsRepository.findById(1L);
        assertTrue(soulsOptional.isEmpty());
    }

    @DisplayName("update soul by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void updateSoulsByUser() throws Exception {
        Souls souls = new Souls();
        Souls expectedSouls = new Souls();
        Souls updatedSouls = new Souls();
        updatedSouls.setSoulName("Soul");
        soulsRepository.save(souls);
        expectedSouls.setSoulId(1L);
        expectedSouls.setSoulName("Soul");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/souls/1")
                                .content(objectMapper.writeValueAsString(updatedSouls))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Souls> optionalSouls = soulsRepository.findById(1L);
        assertTrue(optionalSouls.isPresent());
        assertNotEquals(expectedSouls, optionalSouls.get());
    }


    @DisplayName("get all souls")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void getAllSoulsByCreator() throws Exception{
        Souls souls = new Souls();
        Souls souls1 = new Souls();
        List<Souls> soulsList = List.of(souls, souls1);
        soulsRepository.save(souls);
        soulsRepository.save(souls1);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/souls"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(soulsList)));
    }

    @DisplayName("get soul by id")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void getSoulByIdByCreator() throws Exception{
        Souls souls = new Souls();
        soulsRepository.save(souls);
        Souls expectedSouls = new Souls();
        expectedSouls.setSoulId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedSouls)));
    }

    @DisplayName("delete soul by id by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void deleteSoulByIdByCreator() throws Exception{
        Souls souls = new Souls();
        soulsRepository.save(souls);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Soul deleted successfully!"));
        Optional<Souls> optionalSouls = soulsRepository.findById(1L);
        assertTrue(optionalSouls.isEmpty());
    }

    @DisplayName("delete soul by id by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void deleteSoulByIdByUser() throws Exception{
        Souls souls = new Souls();
        soulsRepository.save(souls);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Souls> optionalSouls = soulsRepository.findById(1L);
        assertTrue(optionalSouls.isPresent());
    }
}