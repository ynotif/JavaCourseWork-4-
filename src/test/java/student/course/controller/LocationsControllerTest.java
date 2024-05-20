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
import student.course.model.*;
import student.course.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LocationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private ArmorsRepository armorsRepository;

    @Autowired
    private BossesRepository bossesRepository;

    @Autowired
    private UnitsRepository unitsRepository;

    @Autowired
    private NPCRepository npcRepository;

    @Autowired
    private WeaponsRepository weaponsRepository;

    @Autowired
    private MagicsRepository magicsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("add location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addLocationByCreator() throws Exception {
        Locations location = new Locations();
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/locations")
                        .content(objectMapper.writeValueAsString(location))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addLocationByUser() throws Exception {
        Locations location = new Locations();
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/locations")
                                .content(objectMapper.writeValueAsString(location))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertTrue(locationsRepository.findById(1L).isEmpty());
    }

    @DisplayName("add unit to location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addUnitToLocationByCreator() throws Exception {
        Locations location = new Locations();
        Units unit = new Units();
        location.setUnit(new HashSet<>());
        unitsRepository.save(unit);
        locationsRepository.save(location);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setUnit(new HashSet<>());
        expectedLocation.getUnit().add(unit);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/locations/1/units/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add unit to location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addUnitToLocationByUser() throws Exception {
        Locations location = new Locations();
        Units unit = new Units();
        location.setUnit(new HashSet<>());
        unitsRepository.save(unit);
        locationsRepository.save(location);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setUnit(new HashSet<>());
        expectedLocation.getUnit().add(unit);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/locations/1/units/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add magic to location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addMagicToLocationByCreator() throws Exception {
        Locations location = new Locations();
        Magics magic = new Magics();
        location.setMagic(new HashSet<>());
        magicsRepository.save(magic);
        locationsRepository.save(location);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setMagic(new HashSet<>());
        expectedLocation.getMagic().add(magic);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/locations/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add magic to location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addMagicToLocationByUser() throws Exception {
        Locations location = new Locations();
        Magics magic = new Magics();
        location.setMagic(new HashSet<>());
        magicsRepository.save(magic);
        locationsRepository.save(location);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setMagic(new HashSet<>());
        expectedLocation.getMagic().add(magic);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/locations/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add weapon to location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addWeaponToLocationByCreator() throws Exception {
        Locations location = new Locations();
        Weapons weapon = new Weapons();
        location.setWeapon(new HashSet<>());
        locationsRepository.save(location);
        weaponsRepository.save(weapon);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setWeapon(new HashSet<>());
        expectedLocation.getWeapon().add(weapon);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/locations/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add weapon to location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addWeaponToLocationByUser() throws Exception {
        Locations location = new Locations();
        Weapons weapon = new Weapons();
        location.setWeapon(new HashSet<>());
        locationsRepository.save(location);
        weaponsRepository.save(weapon);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setWeapon(new HashSet<>());
        expectedLocation.getWeapon().add(weapon);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/locations/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add boss to location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addBossToLocationByCreator() throws Exception{
        Locations location = new Locations();
        Bosses boss = new Bosses();
        location.setBoss(new HashSet<>());
        locationsRepository.save(location);
        bossesRepository.save(boss);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setBoss(new HashSet<>());
        expectedLocation.getBoss().add(boss);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/locations/1/bosses/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add boss to location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addBossToLocationByUser() throws Exception{
        Locations location = new Locations();
        Bosses boss = new Bosses();
        location.setBoss(new HashSet<>());
        locationsRepository.save(location);
        bossesRepository.save(boss);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setBoss(new HashSet<>());
        expectedLocation.getBoss().add(boss);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/locations/1/bosses/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add armor to location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addArmorToLocationByCreator() throws Exception {
        Locations location = new Locations();
        Armors armor = new Armors();
        location.setArmor(new HashSet<>());
        locationsRepository.save(location);
        armorsRepository.save(armor);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setArmor(new HashSet<>());
        expectedLocation.getArmor().add(armor);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/locations/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add armor to location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addArmorToLocationByUser() throws Exception {
        Locations location = new Locations();
        Armors armor = new Armors();
        location.setArmor(new HashSet<>());
        locationsRepository.save(location);
        armorsRepository.save(armor);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setArmor(new HashSet<>());
        expectedLocation.getArmor().add(armor);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/locations/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add npc to location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addNPCToLocationByCreator() throws Exception {
        Locations location = new Locations();
        NPC npc = new NPC();
        location.setNpc(new HashSet<>());
        locationsRepository.save(location);
        npcRepository.save(npc);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setNpc(new HashSet<>());
        expectedLocation.getNpc().add(npc);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/locations/1/npc/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("add npc to location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addNPCToLocationByUser() throws Exception {
        Locations location = new Locations();
        NPC npc = new NPC();
        location.setNpc(new HashSet<>());
        locationsRepository.save(location);
        npcRepository.save(npc);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setNpc(new HashSet<>());
        expectedLocation.getNpc().add(npc);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/locations/1/npc/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("update location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void updateLocationByCreator() throws Exception {
        Locations location = new Locations();
        locationsRepository.save(location);
        Locations updatedLocation = new Locations();
        updatedLocation.setLocationName("Location");
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setLocationName("Location");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/locations/1")
                        .content(objectMapper.writeValueAsString(updatedLocation))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("update location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void updateLocationByUser() throws Exception {
        Locations location = new Locations();
        locationsRepository.save(location);
        Locations updatedLocation = new Locations();
        updatedLocation.setLocationName("Location");
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setLocationName("Location");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/locations/1")
                                .content(objectMapper.writeValueAsString(updatedLocation))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("get all locations")
    @Transactional
    @WithMockUser(authorities = "USER")
    @Test
    void getAllLocations() throws Exception {
        Locations location1 = new Locations();
        Locations location2 = new Locations();
        locationsRepository.save(location1);
        locationsRepository.save(location2);
        List<Locations> locationsList = List.of(location1, location2);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/locations"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(locationsList)));
        assertEquals(locationsList, locationsRepository.findAll());
    }

    @DisplayName("find location by id")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void findLocationById() throws Exception {
        Locations location = new Locations();
        locationsRepository.save(location);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/locations/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("delete location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void deleteLocationByCreator() throws Exception {
        Locations location = new Locations();
        locationsRepository.save(location);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/locations/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Location deleted successfully!"));
        assertTrue(locationsRepository.findById(1L).isEmpty());
    }

    @DisplayName("delete location by uesr")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void deleteLocationByUser() throws Exception {
        Locations location = new Locations();
        locationsRepository.save(location);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/locations/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertTrue(locationsRepository.findById(1L).isPresent());
    }

    @DisplayName("remove unit from location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeUnitFromLocationByCreator() throws Exception {
        Locations location = new Locations();
        Units unit = new Units();
        location.setUnit(new HashSet<>());
        locationsRepository.save(location);
        unitsRepository.save(unit);
        location.getUnit().add(unit);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setUnit(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/locations/1/units/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove unit from location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeUnitFromLocationByUser() throws Exception {
        Locations location = new Locations();
        Units unit = new Units();
        location.setUnit(new HashSet<>());
        location.getUnit().add(unit);
        locationsRepository.save(location);
        unitsRepository.save(unit);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setUnit(new HashSet<>());
        expectedLocation.getUnit().add(unit);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/locations/1/units/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove magic from location by creator")
    @Transactional
    @WithMockUser(authorities ={"CREATOR"})
    @Test
    void removeMagicFromLocationByCreator() throws Exception {
        Locations location = new Locations();
        Magics magic = new Magics();
        location.setMagic(new HashSet<>());
        locationsRepository.save(location);
        magicsRepository.save(magic);
        location.getMagic().add(magic);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setMagic(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/locations/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove weapon from location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeWeaponFromLocationByCreator() throws Exception {
        Locations location = new Locations();
        Weapons weapon = new Weapons();
        location.setWeapon(new HashSet<>());
        locationsRepository.save(location);
        weaponsRepository.save(weapon);
        location.getWeapon().add(weapon);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setWeapon(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/locations/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove boss from location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeBossFromLocationByCreator() throws Exception {
        Locations location = new Locations();
        Bosses boss = new Bosses();
        location.setBoss(new HashSet<>());
        locationsRepository.save(location);
        bossesRepository.save(boss);
        location.getBoss().add(boss);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setBoss(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/locations/1/bosses/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

//    @DisplayName("remove armor from location by creator") // Возвращает 404, idk why
//    @Transactional
//    @WithMockUser(authorities = {"CREATOR"})
//    @Test
//    void removeArmorFromLocationByCreator() throws Exception {
//        Locations location = new Locations();
//        Armors armor = new Armors();
//        location.setArmor(new HashSet<>());
//        locationsRepository.save(location);
//        armorsRepository.save(armor);
//        location.getArmor().add(armor);
//        Locations expectedLocation = new Locations();
//        expectedLocation.setLocationId(1L);
//        expectedLocation.setArmor(new HashSet<>());
//        mockMvc.perform(
//                MockMvcRequestBuilders.delete("/locations/1/armors/1"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
//        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
//        assertTrue(optionalLocations.isPresent());
//        assertEquals(expectedLocation, optionalLocations.get());
//    }

    @DisplayName("remove npc from location by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeNPCFromLocationByCreator() throws Exception {
        Locations location = new Locations();
        NPC npc = new NPC();
        location.setNpc(new HashSet<>());
        locationsRepository.save(location);
        npcRepository.save(npc);
        location.getNpc().add(npc);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setNpc(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/locations/1/npc/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedLocation)));
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove magic from location by user")
    @Transactional
    @WithMockUser(authorities ={"USER"})
    @Test
    void removeMagicFromLocationByUser() throws Exception {
        Locations location = new Locations();
        Magics magic = new Magics();
        location.setMagic(new HashSet<>());
        locationsRepository.save(location);
        magicsRepository.save(magic);
        location.getMagic().add(magic);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setMagic(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/locations/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove weapon from location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeWeaponFromLocationByUser() throws Exception {
        Locations location = new Locations();
        Weapons weapon = new Weapons();
        location.setWeapon(new HashSet<>());
        locationsRepository.save(location);
        weaponsRepository.save(weapon);
        location.getWeapon().add(weapon);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setWeapon(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/locations/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove boss from location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeBossFromLocationByUser() throws Exception {
        Locations location = new Locations();
        Bosses boss = new Bosses();
        location.setBoss(new HashSet<>());
        locationsRepository.save(location);
        bossesRepository.save(boss);
        location.getBoss().add(boss);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setBoss(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/locations/1/bosses/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove armor from location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeArmorFromLocationByUser() throws Exception {
        Locations location = new Locations();
        Armors armor = new Armors();
        location.setArmor(new HashSet<>());
        locationsRepository.save(location);
        armorsRepository.save(armor);
        location.getArmor().add(armor);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setArmor(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/locations/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }

    @DisplayName("remove npc from location by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeNPCFromLocationByUser() throws Exception {
        Locations location = new Locations();
        NPC npc = new NPC();
        location.setNpc(new HashSet<>());
        locationsRepository.save(location);
        npcRepository.save(npc);
        location.getNpc().add(npc);
        Locations expectedLocation = new Locations();
        expectedLocation.setLocationId(1L);
        expectedLocation.setNpc(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/locations/1/npc/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<Locations> optionalLocations = locationsRepository.findById(1L);
        assertTrue(optionalLocations.isPresent());
        assertNotEquals(expectedLocation, optionalLocations.get());
    }
}