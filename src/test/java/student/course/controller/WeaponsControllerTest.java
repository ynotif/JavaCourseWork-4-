package student.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
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
import student.course.model.Weapons;
import student.course.repository.SoulsRepository;
import student.course.repository.WeaponsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
class WeaponsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WeaponsRepository weaponsRepository;

    @Autowired
    private SoulsRepository soulsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("create weapon by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void createWeaponsByCreator() throws Exception {
        Weapons weapon = new Weapons();
        Weapons expectedWeapon = new Weapons();
        expectedWeapon.setWeaponId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/weapons")
                        .content(objectMapper.writeValueAsString(weapon))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedWeapon)));
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isPresent());
        assertEquals(expectedWeapon, weaponsOptional.get());
    }

    @DisplayName("add soul to weapon by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void addSoulToWeaponByCreator() throws Exception {
        Souls soul = new Souls();
        soulsRepository.save(soul);
        Weapons weapons = new Weapons();
        weapons.setSoul(new HashSet<>());
        weaponsRepository.save(weapons);
        Weapons expectedWeapons = new Weapons();
        expectedWeapons.setWeaponId(1L);
        expectedWeapons.setSoul(new HashSet<>());
        expectedWeapons.getSoul().add(soul);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/weapons/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedWeapons)));
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isPresent());
        assertEquals(expectedWeapons, weaponsOptional.get());
    }

    @DisplayName("update weapon by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void updateWeaponByCreator() throws Exception {
        Weapons weapon = new Weapons();
        weaponsRepository.save(weapon);
        Weapons updateWeapon = new Weapons();
        Weapons expectedWeapons = new Weapons();
        updateWeapon.setWeaponName("GOD'S EATER");
        expectedWeapons.setWeaponName("GOD'S EATER");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/weapons/1")
                        .content(objectMapper.writeValueAsString(updateWeapon))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isPresent());
        assertEquals(expectedWeapons, weaponsOptional.get());
    }

    @DisplayName("create weapon by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void createWeaponsByUser() throws Exception {
        Weapons weapon = new Weapons();
        Weapons expectedWeapon = new Weapons();
        expectedWeapon.setWeaponId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/weapons")
                                .content(objectMapper.writeValueAsString(weapon))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isEmpty());
    }

    @DisplayName("add soul to weapon by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void addSoulToWeaponByUser() throws Exception {
        Souls soul = new Souls();
        soulsRepository.save(soul);
        Weapons weapons = new Weapons();
        weapons.setSoul(new HashSet<>());
        weaponsRepository.save(weapons);
        Weapons expectedWeapons = new Weapons();
        expectedWeapons.setWeaponId(1L);
        expectedWeapons.setSoul(new HashSet<>());
        expectedWeapons.getSoul().add(soul);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/weapons/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isPresent());
        assertNotEquals(expectedWeapons, weaponsOptional.get());
    }

    @DisplayName("update weapon by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void updateWeaponByUser() throws Exception {
        Weapons weapon = new Weapons();
        weaponsRepository.save(weapon);
        Weapons updateWeapon = new Weapons();
        Weapons expectedWeapons = new Weapons();
        updateWeapon.setWeaponName("GOD'S EATER");
        expectedWeapons.setWeaponName("GOD'S EATER");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/weapons/1")
                                .content(objectMapper.writeValueAsString(updateWeapon))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isPresent());
        assertNotEquals(expectedWeapons, weaponsOptional.get());
    }

    @DisplayName("get all weapons")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void getAllWeapons() throws Exception {
        Weapons weapon1 = new Weapons();
        Weapons weapon2 = new Weapons();
        weaponsRepository.save(weapon1);
        weaponsRepository.save(weapon2);
        List<Weapons> weaponsList = List.of(weapon1, weapon2);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/weapons"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(weaponsList)));
    }

    @DisplayName("get weapon by id")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void getWeaponById() throws Exception {
        Weapons weapon = new Weapons();
        weaponsRepository.save(weapon);
        Weapons expectedWeapon = new Weapons();
        expectedWeapon.setWeaponId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedWeapon)));
    }

    @DisplayName("remove soul from weapon by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void removeSoulFromWeaponByCreator() throws Exception {
        Souls soul = new Souls();
        soulsRepository.save(soul);
        Weapons weapons = new Weapons();
        weapons.setSoul(new HashSet<>());
        weaponsRepository.save(weapons);
        weapons.getSoul().add(soul);
        Weapons expectedWeapons = new Weapons();
        expectedWeapons.setWeaponId(1L);
        expectedWeapons.setSoul(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/weapons/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedWeapons)));
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isPresent());
        assertFalse(weaponsOptional.get().getSoul().contains(soul));
    }

    @DisplayName("delete weapon by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void deleteWeaponByIdByCreator() throws Exception {
        Weapons weapon = new Weapons();
        weaponsRepository.save(weapon);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertTrue(weaponsRepository.findById(1L).isPresent());
    }

    @DisplayName("remove soul from weapon by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void removeSoulFromWeaponByUser() throws Exception {
        Souls soul = new Souls();
        soulsRepository.save(soul);
        Weapons weapons = new Weapons();
        weapons.setSoul(new HashSet<>());
        weaponsRepository.save(weapons);
        weapons.getSoul().add(soul);
        Weapons expectedWeapons = new Weapons();
        expectedWeapons.setWeaponId(1L);
        expectedWeapons.setSoul(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/weapons/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Weapons> weaponsOptional = weaponsRepository.findById(1L);
        assertTrue(weaponsOptional.isPresent());
        assertTrue(weaponsOptional.get().getSoul().contains(soul));
    }

    @DisplayName("delete weapon by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void deleteWeaponByIdByUser() throws Exception {
        Weapons weapon = new Weapons();
        weaponsRepository.save(weapon);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Weapon deleted successfully!"));
        assertTrue(weaponsRepository.findById(1L).isEmpty());
    }
}