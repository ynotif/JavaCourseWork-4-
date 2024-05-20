package student.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.N;
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

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
class NPCControllerTest {

    @Autowired
    private NPCRepository npcRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WeaponsRepository weaponsRepository;

    @Autowired
    private ArmorsRepository armorsRepository;

    @Autowired
    private MagicsRepository magicsRepository;

    @Autowired
    private SoulsRepository soulsRepository;

    @DisplayName("create npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void createNPCByCreator() throws Exception {
        NPC npc = new NPC();
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/npc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(npc)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> npcOptional = npcRepository.findById(expectedNpc.getNpcId());
        assertTrue(npcOptional.isPresent());
        assertEquals(expectedNpc, npcOptional.get());
    }

    @DisplayName("add weapon to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addWeaponToNPCByCreator() throws Exception {
        NPC npc = new NPC();
        Weapons weapons = new Weapons();
        npc.setWeapon(new HashSet<>());
        weaponsRepository.save(weapons);
        npcRepository.save(npc);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setWeapon(new HashSet<>());
        expectedNpc.getWeapon().add(weapons);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/npc/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add armor to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addArmorToNPCByCreator() throws Exception {
        NPC npc = new NPC();
        Armors armor = new Armors();
        npc.setArmor(new HashSet<>());
        armorsRepository.save(armor);
        npcRepository.save(npc);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setArmor(new HashSet<>());
        expectedNpc.getArmor().add(armor);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/npc/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add magic to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addMagicToNPCByCreator() throws Exception {
        NPC npc = new NPC();
        Magics magic = new Magics();
        npc.setMagic(new HashSet<>());
        magicsRepository.save(magic);
        npcRepository.save(npc);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setMagic(new HashSet<>());
        expectedNpc.getMagic().add(magic);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/npc/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void addSoulToNPCByCreator() throws Exception {
        NPC npc = new NPC();
        Souls soul = new Souls();
        npc.setSoul(new HashSet<>());
        soulsRepository.save(soul);
        npcRepository.save(npc);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setSoul(new HashSet<>());
        expectedNpc.getSoul().add(soul);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/npc/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("update npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void updateNPCByCreator() throws Exception {
        NPC npc = new NPC();
        npcRepository.save(npc);
        NPC expectedNpc = new NPC();
        NPC updatedNpc = new NPC();
        updatedNpc.setNpcName("NPC");
        expectedNpc.setNpcId(1L);
        expectedNpc.setNpcName("NPC");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/npc/1")
                        .content(objectMapper.writeValueAsString(updatedNpc))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("create npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void createNPCByUser() throws Exception {
        NPC npc = new NPC();
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/npc")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(npc)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        assertTrue(npcRepository.findById(1L).isEmpty());
    }

    @DisplayName("add weapon to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addWeaponToNPCByUser() throws Exception {
        NPC npc = new NPC();
        Weapons weapons = new Weapons();
        npc.setWeapon(new HashSet<>());
        weaponsRepository.save(weapons);
        npcRepository.save(npc);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/npc/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertTrue(optionalNPC.get().getWeapon().isEmpty());
    }

    @DisplayName("add armor to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addArmorToNPCByUser() throws Exception {
        NPC npc = new NPC();
        Armors armor = new Armors();
        npc.setArmor(new HashSet<>());
        armorsRepository.save(armor);
        npcRepository.save(npc);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/npc/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertTrue(optionalNPC.get().getArmor().isEmpty());
    }

    @DisplayName("add magic to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addMagicToNPCByUser() throws Exception {
        NPC npc = new NPC();
        Magics magic = new Magics();
        npc.setMagic(new HashSet<>());
        magicsRepository.save(magic);
        npcRepository.save(npc);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/npc/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertTrue(optionalNPC.get().getMagic().isEmpty());
    }

    @DisplayName("add weapon to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void addSoulToNPCByUser() throws Exception {
        NPC npc = new NPC();
        Souls soul = new Souls();
        npc.setSoul(new HashSet<>());
        soulsRepository.save(soul);
        npcRepository.save(npc);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/npc/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertTrue(optionalNPC.get().getSoul().isEmpty());
    }

    @DisplayName("update npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void updateNPCByUser() throws Exception {
        NPC npc = new NPC();
        npcRepository.save(npc);
        NPC expectedNpc = new NPC();
        NPC updatedNpc = new NPC();
        updatedNpc.setNpcName("NPC");
        expectedNpc.setNpcId(1L);
        expectedNpc.setNpcName("NPC");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/npc/1")
                                .content(objectMapper.writeValueAsString(updatedNpc))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertNotEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("get all npc")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void getNPCs() throws Exception {
        NPC npc = new NPC();
        NPC npc2 = new NPC();
        npcRepository.save(npc);
        npcRepository.save(npc2);
        List<NPC> npcList = List.of(npc, npc2);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/npc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(npcList)));
    }

    @DisplayName("get npc by id")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void getNPCById() throws Exception {
        NPC npc = new NPC();
        npcRepository.save(npc);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/npc/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
    }

    @DisplayName("add weapon to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeWeaponFromNPCByCreator() throws Exception {
        NPC npc = new NPC();
        npc.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        weaponsRepository.save(weapons);
        npcRepository.save(npc);
        npc.getWeapon().add(weapons);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setWeapon(new HashSet<>());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/npc/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeArmorFromNPCByCreator() throws Exception{
        NPC npc = new NPC();
        npc.setArmor(new HashSet<>());
        Armors armors = new Armors();
        armorsRepository.save(armors);
        npcRepository.save(npc);
        npc.getArmor().add(armors);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setArmor(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeMagicFromNPCByCreator() throws Exception{
        NPC npc = new NPC();
        npc.setMagic(new HashSet<>());
        Magics magic = new Magics();
        magicsRepository.save(magic);
        npcRepository.save(npc);
        npc.getMagic().add(magic);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setMagic(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void removeSoulFromNPCByCreator() throws Exception{
        NPC npc = new NPC();
        npc.setSoul(new HashSet<>());
        Souls soul = new Souls();
        soulsRepository.save(soul);
        npcRepository.save(npc);
        npc.getSoul().add(soul);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setSoul(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expectedNpc)));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by creator")
    @Transactional
    @WithMockUser(authorities = {"CREATOR"})
    @Test
    void deleteNPCByIdByCreator() throws Exception{
        NPC npc = new NPC();
        npcRepository.save(npc);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/npc/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("NPC deleted successfully!"));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isEmpty());
    }

    @DisplayName("add weapon to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeWeaponFromNPCByUser() throws Exception {
        NPC npc = new NPC();
        npc.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        weaponsRepository.save(weapons);
        npcRepository.save(npc);
        npc.getWeapon().add(weapons);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setWeapon(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1/weapons/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertNotEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeArmorFromNPCByUser() throws Exception{
        NPC npc = new NPC();
        npc.setArmor(new HashSet<>());
        Armors armors = new Armors();
        armorsRepository.save(armors);
        npcRepository.save(npc);
        npc.getArmor().add(armors);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setArmor(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1/armors/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertNotEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeMagicFromNPCByUser() throws Exception{
        NPC npc = new NPC();
        npc.setMagic(new HashSet<>());
        Magics magic = new Magics();
        magicsRepository.save(magic);
        npcRepository.save(npc);
        npc.getMagic().add(magic);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setMagic(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1/magics/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertNotEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void removeSoulFromNPCByUser() throws Exception{
        NPC npc = new NPC();
        npc.setSoul(new HashSet<>());
        Souls soul = new Souls();
        soulsRepository.save(soul);
        npcRepository.save(npc);
        npc.getSoul().add(soul);
        NPC expectedNpc = new NPC();
        expectedNpc.setNpcId(1L);
        expectedNpc.setSoul(new HashSet<>());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1/souls/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertNotEquals(expectedNpc, optionalNPC.get());
    }

    @DisplayName("add weapon to npc by user")
    @Transactional
    @WithMockUser(authorities = {"USER"})
    @Test
    void deleteNPCByIdByUser() throws Exception{
        NPC npc = new NPC();
        npcRepository.save(npc);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/npc/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
    }
}