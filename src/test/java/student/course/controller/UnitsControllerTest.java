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
import student.course.model.Armors;
import student.course.model.Souls;
import student.course.model.Units;
import student.course.model.Weapons;
import student.course.repository.ArmorsRepository;
import student.course.repository.SoulsRepository;
import student.course.repository.UnitsRepository;
import student.course.repository.WeaponsRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
class UnitsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UnitsRepository unitsRepository;

    @Autowired
    private ArmorsRepository armorsRepository;

    @Autowired
    private WeaponsRepository weaponsRepository;

    @Autowired
    private SoulsRepository soulsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("add unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addUnitByCreator() throws Exception {
        Units unit = new Units();
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/units")
                        .content(objectMapper.writeValueAsString(unit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertEquals(expectedUnit, unitsOptional.get());
    }

    @DisplayName("add armor to unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addArmorToUnitByCreator() throws Exception {
        Armors armor = new Armors();
        Units unit = new Units();
        armorsRepository.save(armor);
        unit.setArmor(new HashSet<>());
        unitsRepository.save(unit);
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        expectedUnit.setArmor(new HashSet<>());
        expectedUnit.getArmor().add(armor);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/units/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertTrue(unitsOptional.get().getArmor().contains(armor));
    }

    @DisplayName("add weapon to unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addWeaponToUnitByCreator() throws Exception {
        Weapons weapon = new Weapons();
        Units unit = new Units();
        weaponsRepository.save(weapon);
        unit.setWeapon(new HashSet<>());
        unitsRepository.save(unit);
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        expectedUnit.setWeapon(new HashSet<>());
        expectedUnit.getWeapon().add(weapon);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/units/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertTrue(unitsOptional.get().getWeapon().contains(weapon));
    }

    @DisplayName("add soul to unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addSoulToUnitByCreator() throws Exception {
        Souls soul = new Souls();
        Units unit = new Units();
        soulsRepository.save(soul);
        unit.setSoul(new HashSet<>());
        unitsRepository.save(unit);
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        expectedUnit.setSoul(new HashSet<>());
        expectedUnit.getSoul().add(soul);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/units/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertTrue(unitsOptional.get().getSoul().contains(soul));
    }

    @DisplayName("update unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void updateUnitByCreator() throws Exception {
        Units unit = new Units();
        unitsRepository.save(unit);
        Units updateUnit = new Units();
        Units expectedUnit = new Units();
        updateUnit.setUnitName("UNIT");
        expectedUnit.setUnitId(1L);
        expectedUnit.setUnitName("UNIT");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/units/1")
                        .content(objectMapper.writeValueAsString(updateUnit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertEquals(expectedUnit, unitsOptional.get());
    }

    @DisplayName("add unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addUnitByUser() throws Exception {
        Units unit = new Units();
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/units")
                                .content(objectMapper.writeValueAsString(unit))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isEmpty());
    }

    @DisplayName("add armor to unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addArmorToUnitByUser() throws Exception {
        Armors armor = new Armors();
        Units unit = new Units();
        armorsRepository.save(armor);
        unit.setArmor(new HashSet<>());
        unitsRepository.save(unit);
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        expectedUnit.setArmor(new HashSet<>());
        expectedUnit.getArmor().add(armor);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/units/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertFalse(unitsOptional.get().getArmor().contains(armor));
    }

    @DisplayName("add weapon to unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addWeaponToUnitByUser() throws Exception {
        Weapons weapon = new Weapons();
        Units unit = new Units();
        weaponsRepository.save(weapon);
        unit.setWeapon(new HashSet<>());
        unitsRepository.save(unit);
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        expectedUnit.setWeapon(new HashSet<>());
        expectedUnit.getWeapon().add(weapon);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/units/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertFalse(unitsOptional.get().getWeapon().contains(weapon));
    }

    @DisplayName("add soul to unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addSoulToUnitByUser() throws Exception {
        Souls soul = new Souls();
        Units unit = new Units();
        soulsRepository.save(soul);
        unit.setSoul(new HashSet<>());
        unitsRepository.save(unit);
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        expectedUnit.setSoul(new HashSet<>());
        expectedUnit.getSoul().add(soul);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/units/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertFalse(unitsOptional.get().getSoul().contains(soul));
    }

    @DisplayName("update armor by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void updateUnitByUser() throws Exception {
        Units unit = new Units();
        unitsRepository.save(unit);
        Units updateUnit = new Units();
        Units expectedUnit = new Units();
        updateUnit.setUnitName("UNIT");
        expectedUnit.setUnitId(1L);
        expectedUnit.setUnitName("UNIT");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/units/1")
                                .content(objectMapper.writeValueAsString(updateUnit))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> unitsOptional = unitsRepository.findById(1L);
        assertTrue(unitsOptional.isPresent());
        assertNotEquals(expectedUnit, unitsOptional.get());
    }

    @DisplayName("get all armors")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void getAllUnits() throws Exception {
        Units unit = new Units();
        unitsRepository.save(unit);
        Units unit2 = new Units();
        unitsRepository.save(unit2);
        List<Units> unitsList = List.of(unit, unit2);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/units"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(unitsList)));
        assertEquals(unitsList, unitsRepository.findAll());
    }

    @DisplayName("get unit by id")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void getUnitById() throws Exception {
        Units unit = new Units();
        unitsRepository.save(unit);
        Units expectedUnit = new Units();
        expectedUnit.setUnitId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/units/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
    }

    @DisplayName("remove armor from unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeArmorFromUnitByCreator() throws Exception {
        Armors armor = new Armors();
        armorsRepository.save(armor);
        Units unit = new Units();
        unit.setArmor(new HashSet<>());
        Units expectedUnit = new Units();
        unitsRepository.save(unit);
        unit.getArmor().add(armor);
        expectedUnit.setUnitId(1L);
        expectedUnit.setArmor(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/units/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
        assertFalse(optionalUnits.get().getArmor().contains(armor));
    }

    @DisplayName("remove weapon from unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeWeaponFromUnitByCreator() throws Exception {
        Weapons weapon = new Weapons();
        weaponsRepository.save(weapon);
        Units unit = new Units();
        unit.setWeapon(new HashSet<>());
        Units expectedUnit = new Units();
        unitsRepository.save(unit);
        unit.getWeapon().add(weapon);
        expectedUnit.setUnitId(1L);
        expectedUnit.setWeapon(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/units/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
        assertFalse(optionalUnits.get().getWeapon().contains(weapon));
    }

    @DisplayName("remove soul from unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeSoulFromUnitByCreator() throws Exception {
        Souls soul = new Souls();
        soulsRepository.save(soul);
        Units unit = new Units();
        unit.setSoul(new HashSet<>());
        Units expectedUnit = new Units();
        unitsRepository.save(unit);
        unit.getSoul().add(soul);
        expectedUnit.setUnitId(1L);
        expectedUnit.setSoul(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/units/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedUnit)));
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
        assertFalse(optionalUnits.get().getSoul().contains(soul));
    }

    @DisplayName("delete unit by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void deleteUnitByIdByCreator() throws Exception {
        Units unit = new Units();
        unitsRepository.save(unit);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/units/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Unit deleted successfully!"));
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isEmpty());
    }


    @DisplayName("remove armor from unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeArmorFromUnitByUser() throws Exception {
        Armors armor = new Armors();
        armorsRepository.save(armor);
        Units unit = new Units();
        unit.setArmor(new HashSet<>());
        Units expectedUnit = new Units();
        unitsRepository.save(unit);
        unit.getArmor().add(armor);
        expectedUnit.setUnitId(1L);
        expectedUnit.setArmor(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/units/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
        assertTrue(optionalUnits.get().getArmor().contains(armor));
    }

    @DisplayName("remove weapon from unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeWeaponFromUnitByUser() throws Exception {
        Weapons weapon = new Weapons();
        weaponsRepository.save(weapon);
        Units unit = new Units();
        unit.setWeapon(new HashSet<>());
        Units expectedUnit = new Units();
        unitsRepository.save(unit);
        unit.getWeapon().add(weapon);
        expectedUnit.setUnitId(1L);
        expectedUnit.setWeapon(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/units/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
        assertTrue(optionalUnits.get().getWeapon().contains(weapon));
    }

    @DisplayName("remove soul from unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeSoulFromUnitByUser() throws Exception {
        Souls soul = new Souls();
        soulsRepository.save(soul);
        Units unit = new Units();
        unit.setSoul(new HashSet<>());
        Units expectedUnit = new Units();
        unitsRepository.save(unit);
        unit.getSoul().add(soul);
        expectedUnit.setUnitId(1L);
        expectedUnit.setSoul(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/units/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
        assertTrue(optionalUnits.get().getSoul().contains(soul));
    }

    @DisplayName("delete unit by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void deleteUnitByIdByUser() throws Exception {
        Units unit = new Units();
        unitsRepository.save(unit);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/units/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Units> optionalUnits = unitsRepository.findById(1L);
        assertTrue(optionalUnits.isPresent());
    }
}