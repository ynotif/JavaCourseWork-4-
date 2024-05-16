package student.course.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import student.course.model.Souls;
import student.course.repository.SoulsRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class SoulsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SoulsRepository soulsRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("add Soul")
    @Test
    public void addSouls() throws Exception {
        Souls inputSoul = new Souls(null, "Name", 100, "Info", null, null, null, null);
        Souls expectedSoul = new Souls(1L, "Name", 100, "Info", null, null, null, null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/souls")
                        .content(objectMapper.writeValueAsString(inputSoul))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedSoul)));
        assertEquals(expectedSoul, soulsRepository.findById(1L).get());
    }

    @Test
    void updateSouls() {
    }

    @DisplayName("get all souls")
    @Test
    void getAllSouls() throws Exception {
        Souls savedSoul = new Souls(null, "Name", 100, "Info", null, null, null, null);
        soulsRepository.save(savedSoul);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/souls")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(List.of(savedSoul))));
    }

    @Test
    void getSoulById() {
    }

    @Test
    void deleteSoulById() {
    }
}