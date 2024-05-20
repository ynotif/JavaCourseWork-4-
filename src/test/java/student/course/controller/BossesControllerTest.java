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
import student.course.model.Bosses;
import student.course.model.Souls;
import student.course.model.Weapons;
import student.course.repository.ArmorsRepository;
import student.course.repository.BossesRepository;
import student.course.repository.SoulsRepository;
import student.course.repository.WeaponsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
class BossesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BossesRepository bossesRepository;

    @Autowired
    private ArmorsRepository armorsRepository;

    @Autowired
    private WeaponsRepository weaponsRepository;

    @Autowired
    private SoulsRepository soulsRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("add boss by creator")
    @Test
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    void addBossByCreator() throws Exception {
        Bosses boss = new Bosses();
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/bosses")
                        .content(objectMapper.writeValueAsString(boss))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        Optional<Bosses> optionalBosses = bossesRepository.findById(expectedBoss.getBossId());
        assertTrue(optionalBosses.isPresent());
        assertEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("add boss by user")
    @Test
    @Transactional
    @WithMockUser(authorities = {"USER"})
    void addBossByUser() throws Exception {
        Bosses boss = new Bosses();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/bosses")
                                .content(objectMapper.writeValueAsString(boss))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @DisplayName("add armor to boss by creator")
    @Test
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    void addArmorToBossByCreator()  throws Exception{
        Armors armor = new Armors();
        armorsRepository.save(armor);
        Bosses boss = new Bosses();
        boss.setArmor(new HashSet<>());
        bossesRepository.save(boss);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setArmor(new HashSet<>());
        expectedBoss.getArmor().add(armor);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/bosses/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("add armor to boss by user")
    @Test
    @Transactional
    @WithMockUser(authorities = {"USER"})
    void addArmorToBossByUser()  throws Exception{
        Armors armor = new Armors();
        armorsRepository.save(armor);
        Bosses boss = new Bosses();
        boss.setArmor(new HashSet<>());
        bossesRepository.save(boss);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/bosses/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertFalse(boss.getArmor().contains(armor));
    }

    @DisplayName("add weapon to boss by creator")
    @WithMockUser(authorities = {"CREATOR"})
    @Transactional
    @Test
    void addWeaponToBossByCreator() throws Exception{
        Bosses boss = new Bosses();
        boss.setWeapon(new HashSet<>());
        bossesRepository.save(boss);
        Weapons weapons = new Weapons();
        weaponsRepository.save(weapons);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setWeapon(new HashSet<>());
        expectedBoss.getWeapon().add(weapons);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/bosses/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        assertTrue(boss.getWeapon().contains(weapons));
    }

    @DisplayName("add weapon to boss by user")
    @WithMockUser(authorities = {"USER"})
    @Transactional
    @Test
    void addWeaponToBossByUser() throws Exception{
        Bosses boss = new Bosses();
        boss.setWeapon(new HashSet<>());
        bossesRepository.save(boss);
        Weapons weapons = new Weapons();
        weaponsRepository.save(weapons);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setWeapon(new HashSet<>());
        expectedBoss.getWeapon().add(weapons);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/bosses/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertFalse(boss.getWeapon().contains(weapons));
    }

    @DisplayName("add soul to boss by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addSoulToBossByCreator() throws Exception{
        Bosses boss = new Bosses();
        boss.setSoul(new HashSet<>());
        Souls soul = new Souls();
        bossesRepository.save(boss);
        soulsRepository.save(soul);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setSoul(new HashSet<>());
        expectedBoss.getSoul().add(soul);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/bosses/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        assertTrue(boss.getSoul().contains(soul));
    }

    @DisplayName("add soul to boss by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addSoulToBossByUser() throws Exception{
        Bosses boss = new Bosses();
        boss.setSoul(new HashSet<>());
        Souls soul = new Souls();
        bossesRepository.save(boss);
        soulsRepository.save(soul);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/bosses/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertFalse(boss.getSoul().contains(soul));
    }

    @DisplayName("update boss by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void updateBossByCreator() throws Exception {
        Bosses boss = new Bosses();
        bossesRepository.save(boss);
        Bosses updateBoss = new Bosses();
        updateBoss.setBossName("Boss");
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setBossName("Boss");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/bosses/1")
                        .content(objectMapper.writeValueAsString(updateBoss))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("update boss by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void updateBossByUser() throws Exception {
        Bosses boss = new Bosses();
        bossesRepository.save(boss);
        Bosses updateBoss = new Bosses();
        updateBoss.setBossName("Boss");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/bosses/1")
                                .content(objectMapper.writeValueAsString(updateBoss))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertNotEquals("Boss", boss.getBossName());
    }

    @DisplayName("get all bosses")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void getAllBosses() throws Exception {
        Bosses boss1 = new Bosses();
        Bosses boss2 = new Bosses();
        bossesRepository.save(boss1);
        bossesRepository.save(boss2);
        List<Bosses> bossesList = bossesRepository.findAll();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/bosses"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(bossesList)));
        assertEquals(bossesList, bossesRepository.findAll());
    }

    @DisplayName("get boss by id")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void getBossById() throws Exception {
        Bosses boss = new Bosses();
        bossesRepository.save(boss);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/bosses/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(boss)));
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertEquals(boss, optionalBosses.get());
    }

    @DisplayName("remove armor from boss by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeArmorFromBossByCreator() throws Exception {
        Bosses boss = new Bosses();
        Armors armor = new Armors();
        boss.setArmor(new HashSet<>());
        armorsRepository.save(armor);
        bossesRepository.save(boss);
        boss.getArmor().add(armor);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setArmor(new HashSet<>());
        expectedBoss.setBossId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/bosses/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("remove armor from boss by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeArmorFromBossByUser() throws Exception {
        Bosses boss = new Bosses();
        Armors armor = new Armors();
        boss.setArmor(new HashSet<>());
        armorsRepository.save(armor);
        bossesRepository.save(boss);
        boss.getArmor().add(armor);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setArmor(new HashSet<>());
        expectedBoss.setBossId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/bosses/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertNotEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("remove weapon from boss by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeWeaponFromBossByCreator() throws Exception {
        Bosses boss = new Bosses();
        Weapons weapon = new Weapons();
        boss.setWeapon(new HashSet<>());
        bossesRepository.save(boss);
        weaponsRepository.save(weapon);
        boss.getWeapon().add(weapon);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setWeapon(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/bosses/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("remove weapon from boss by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeWeaponFromBossByUser() throws Exception {
        Bosses boss = new Bosses();
        Weapons weapon = new Weapons();
        boss.setWeapon(new HashSet<>());
        bossesRepository.save(boss);
        weaponsRepository.save(weapon);
        boss.getWeapon().add(weapon);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setWeapon(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/bosses/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertNotEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("remove soul from boss by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeSoulFromBossByCreator() throws Exception{
        Bosses boss = new Bosses();
        Souls soul = new Souls();
        boss.setSoul(new HashSet<>());
        bossesRepository.save(boss);
        soulsRepository.save(soul);
        boss.getSoul().add(soul);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setSoul(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/bosses/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedBoss)));
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("remove soul from boss by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeSoulFromBossByUser() throws Exception{
        Bosses boss = new Bosses();
        Souls soul = new Souls();
        boss.setSoul(new HashSet<>());
        bossesRepository.save(boss);
        soulsRepository.save(soul);
        boss.getSoul().add(soul);
        Bosses expectedBoss = new Bosses();
        expectedBoss.setBossId(1L);
        expectedBoss.setSoul(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/bosses/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Bosses> optionalBosses = bossesRepository.findById(1L);
        assertTrue(optionalBosses.isPresent());
        assertNotEquals(expectedBoss, optionalBosses.get());
    }

    @DisplayName("delete boss by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void deleteBossByIdByCreator() throws Exception {
        Bosses boss = new Bosses();
        bossesRepository.save(boss);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/bosses/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Boss deleted successfully!"));
        assertFalse(bossesRepository.findById(1L).isPresent());
    }

    @DisplayName("delete boss by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void deleteBossByIdByUser() throws Exception {
        Bosses boss = new Bosses();
        bossesRepository.save(boss);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/bosses/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertTrue(bossesRepository.findById(1L).isPresent());
    }
}