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
import student.course.model.Armors;
import student.course.repository.ArmorsRepository;
import student.course.service.ArmorsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ArmorsControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArmorsRepository armorsRepository;

    @Autowired
    private ArmorsService armorsService;

    @Transactional // Можно вместо этого добавить DTO, чтобы избежать ошибки при assertEquals и увеличить скорость
    @DisplayName("add armor controller by creator")
    @Test
    @WithMockUser(authorities = {"CREATOR"})
    void addArmorByCreator() throws Exception {
        Armors inputArmor = new Armors();
        Armors expectedArmor = new Armors();
        expectedArmor.setArmorId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/armors")
                        .content(objectMapper.writeValueAsString(inputArmor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedArmor)));
        Optional<Armors> optionalArmor = armorsRepository.findById(1L);
        assertTrue(optionalArmor.isPresent());
        assertEquals(expectedArmor, optionalArmor.get());
    }

    @Transactional // Можно вместо этого добавить DTO, чтобы избежать ошибки при assertEquals и увеличить скорость
    @DisplayName("add armor controller by user with 403")
    @Test
    @WithMockUser(authorities = {"USER"})
    void addArmorByUser() throws Exception {
        Armors inputArmor = new Armors();
        Armors expectedArmor = new Armors();
        expectedArmor.setArmorId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/armors")
                                .content(objectMapper.writeValueAsString(inputArmor))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @DisplayName("update armor controller by creator")
    @Transactional
    @Test
    @WithMockUser(authorities = {"CREATOR"})
    void updateArmorByCreator() throws Exception {
        Armors inputArmor = new Armors();
        armorsRepository.save(inputArmor);
        Armors updatedArmor = new Armors();
        updatedArmor.setArmorName("Test Armor");
        Armors expectedArmor = new Armors();
        expectedArmor.setArmorId(1L);
        expectedArmor.setArmorName("Test Armor");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/armors/1")
                        .content(objectMapper.writeValueAsString(updatedArmor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedArmor)));
        Optional<Armors> optionalArmor = armorsRepository.findById(1L);
        assertTrue(optionalArmor.isPresent());
        assertEquals(expectedArmor, optionalArmor.get());
    }

    @DisplayName("update armor controller by user with 403")
    @Test
    @WithMockUser(authorities = {"USER"})
    void updateArmorByUser() throws Exception {
        Armors inputArmor = new Armors();
        armorsRepository.save(inputArmor);
        Armors updatedArmor = new Armors();
        updatedArmor.setArmorName("Test Armor");
        Armors expectedArmor = new Armors();
        expectedArmor.setArmorId(1L);
        expectedArmor.setArmorName("Test Armor");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/armors/1")
                                .content(objectMapper.writeValueAsString(updatedArmor))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @DisplayName("get all armors")
    @Test
    @Transactional
    @WithMockUser(authorities = {"USER"})
    void getAllArmors() throws Exception {
        Armors armor1 = new Armors();
        Armors armor2 = new Armors();
        List<Armors> armorsList  = new ArrayList<>();
        armorsList.add(armor1);
        armorsList.add(armor2);
        armorsService.createArmor(armor1);
        armorsService.createArmor(armor2);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/armors"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(armorsList)));
        assertEquals(armorsList, armorsRepository.findAll());
    }

    @DisplayName("get armor by id")
    @Test
    @Transactional
    @WithMockUser(authorities = {"USER"})
    void getArmorById() throws Exception {
        Armors armor = new Armors();
        armorsRepository.save(armor);
        Armors expectedArmor = new Armors();
        expectedArmor.setArmorId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedArmor)));
        Optional<Armors> optionalArmors = armorsRepository.findById(1L);
        assertTrue(optionalArmors.isPresent());
        assertEquals(expectedArmor, optionalArmors.get());
    }

    @DisplayName("delete armor by creator")
    @Test
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    void deleteArmorByCreator() throws Exception {
        Armors armor = new Armors();
        armorsRepository.save(armor);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Armor deleted successfully!"));
        Optional<Armors> optionalArmor = armorsRepository.findById(1L);
        assertTrue(optionalArmor.isEmpty());
    }

    @DisplayName("delete armor by user")
    @Test
    @Transactional
    @WithMockUser(authorities = {"USER"})
    void deleteArmorByUser() throws Exception {
        Armors armor = new Armors();
        armorsRepository.save(armor);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Armors> optionalArmor = armorsRepository.findById(1L);
        assertTrue(optionalArmor.isPresent());
    }

}