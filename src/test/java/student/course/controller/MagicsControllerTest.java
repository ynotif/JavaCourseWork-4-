package student.course.controller;

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
import student.course.model.Magics;
import student.course.repository.MagicsRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MagicsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MagicsRepository magicsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("add magic by creator")
    @Transactional
    @WithMockUser(authorities = "CREATOR")
    @Test
    void addMagicsByCreator() throws Exception {
        Magics magic = new Magics();
        Magics expectedMagic = new Magics();
        expectedMagic.setMagicId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/magics")
                                .content(objectMapper.writeValueAsString(magic))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedMagic)));
        Optional<Magics> optionalMagics = magicsRepository.findById(1L);
        assertTrue(optionalMagics.isPresent());
        assertEquals(expectedMagic, optionalMagics.get());
    }

    @DisplayName("update magic by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void updateMagicsByCreator() throws Exception {
        Magics magic = new Magics();
        magicsRepository.save(magic);
        Magics updateMagic = new Magics();
        updateMagic.setMagicName("Magic");
        Magics expectedMagic = new Magics();
        expectedMagic.setMagicId(1L);
        expectedMagic.setMagicName("Magic");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/magics/1")
                                .content(objectMapper.writeValueAsString(updateMagic))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedMagic)));
        Optional<Magics> optionalMagics = magicsRepository.findById(1L);
        assertTrue(optionalMagics.isPresent());
        assertEquals(expectedMagic, optionalMagics.get());
    }

    @DisplayName("get all magics")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void getMagics() throws Exception {
        Magics magic = new Magics();
        Magics magic2 = new Magics();
        magicsRepository.save(magic);
        magicsRepository.save(magic2);
        List<Magics> magicsList = List.of(magic, magic2);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/magics"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(magicsList)));
    }
    @DisplayName("get magic by id ")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void getMagicById() throws Exception {
        Magics magic = new Magics();
        Magics expectedMagic = new Magics();
        expectedMagic.setMagicId(1L);
        magicsRepository.save(magic);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedMagic)));
    }

    @DisplayName("delete magics by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void deleteMagicsByCreator() throws Exception {
        Magics magic = new Magics();
        magicsRepository.save(magic);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Magic deleted successfully!"));
        assertTrue(magicsRepository.findById(1L).isEmpty());
    }

    @DisplayName("add magic by user")
    @Transactional
    @WithMockUser(authorities = "USER")
    @Test
    void addMagicsByUser() throws Exception {
        Magics magic = new Magics();
        Magics expectedMagic = new Magics();
        expectedMagic.setMagicId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/magics")
                                .content(objectMapper.writeValueAsString(magic))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertTrue(magicsRepository.findById(1L).isEmpty());
    }

    @DisplayName("update magic by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void updateMagicsByUser() throws Exception {
        Magics magic = new Magics();
        magicsRepository.save(magic);
        Magics updateMagic = new Magics();
        updateMagic.setMagicName("Magic");
        Magics expectedMagic = new Magics();
        expectedMagic.setMagicId(1L);
        expectedMagic.setMagicName("Magic");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/magics/1")
                                .content(objectMapper.writeValueAsString(updateMagic))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Magics> optionalMagics = magicsRepository.findById(1L);
        assertTrue(optionalMagics.isPresent());
        assertNotEquals(expectedMagic, optionalMagics.get());
    }

    @DisplayName("delete magics by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void deleteMagicsByUser() throws Exception {
        Magics magic = new Magics();
        magicsRepository.save(magic);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertTrue(magicsRepository.findById(1L).isPresent());
    }
}