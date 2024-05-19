package student.course.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import student.course.model.Armors;
import student.course.model.Users;
import student.course.repository.UsersRepository;
import student.course.repository.UsersRolesRepository;
import student.course.service.ArmorsService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ArmorsControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArmorsService armorsService;

    @DisplayName("add armor controller")
    @Test
    @WithMockUser(username = "creator", password = "creator", authorities = {"CREATOR"})
    void addArmor() throws Exception {
        Armors inputArmor = new Armors();
        Armors expectedArmor = new Armors();
        expectedArmor.setArmorId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/armors")
                        .content(objectMapper.writeValueAsString(inputArmor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedArmor)));
    }

    @Test
    @WithMockUser(username = "creator", password = "creator", roles = {"CREATOR"})
    void updateArmor() throws Exception {
        Armors inputArmor = new Armors();

        Armors expectedArmor = new Armors();
        expectedArmor.setArmorId(1L);
        expectedArmor.setArmorName("Test Armor");
    }

    @DisplayName("get all armors")
    @Test
    @WithMockUser(authorities = {"username = creator", "password = creator"})
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
    }

    @Test
    void getArmorById() {
    }

    @Test
    void deleteArmor() {
    }

}