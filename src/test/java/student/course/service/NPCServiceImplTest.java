package student.course.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import student.course.exceptions.NPCNotFoundException;
import student.course.model.*;
import student.course.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NPCServiceImplTest {

    private final NPCRepository npcRepository = Mockito.mock(NPCRepository.class);
    private final WeaponsRepository weaponsRepository = Mockito.mock(WeaponsRepository.class);
    private final ArmorsRepository armorsRepository = Mockito.mock(ArmorsRepository.class);
    private final MagicsRepository magicsRepository = Mockito.mock(MagicsRepository.class);
    private final SoulsRepository soulsRepository = Mockito.mock(SoulsRepository.class);

    private final NPCService npcService = new NPCServiceImpl(
            npcRepository, weaponsRepository, armorsRepository, magicsRepository, soulsRepository);

    @DisplayName("create npc")
    @Test
    void createNPC() {
        NPC npc = new NPC();
        when(npcRepository
                .save(npc))
                .thenReturn(npc);
        assertEquals(npc, npcService.createNPC(npc));
    }

    @DisplayName("get all npc")
    @Test
    void getAllNPCs() {
        NPC npc1 = new NPC();
        NPC npc2 = new NPC();
        when(npcRepository
                .findAll())
                .thenReturn(List.of(npc1, npc2));
        assertEquals(List.of(npc1, npc2), npcService.getAllNPCs());
    }

    @DisplayName("get npc by id")
    @Test
    void getNPCById() throws NPCNotFoundException {
        Long npcId = 1L;
        NPC npc = new NPC();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        assertTrue(optionalNPC.isPresent());
        assertEquals(npc, optionalNPC.get());
    }

    @DisplayName("get npc by id but exception")
    @Test
    void getNPCByIdButException(){
        when(npcRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(NPCNotFoundException.class, () -> npcService.getNPCById(1L));
    }

    @DisplayName("update npc")
    @Test
    void updateNPC() {
        NPC npc = new NPC();
        when(npcRepository
                .findById(1L))
                .thenReturn(Optional.of(npc));
        NPC updatedNpc = new NPC();
        assertDoesNotThrow(() -> npcService.updateNPC(updatedNpc, 1L));
        Optional<NPC> optionalNPC = npcRepository.findById(1L);
        assertTrue(optionalNPC.isPresent());
        assertEquals(npc, optionalNPC.get());
    }

    @DisplayName("update npc but exception")
    @Test
    void updateNpcButException(){
        when(npcRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(NPCNotFoundException.class, () -> npcService.updateNPC(new NPC(), 1L));
    }

    @DisplayName("delete npc by id")
    @Test
    void deleteNPCById() throws NPCNotFoundException {
        NPC npc = new NPC();
        when(npcRepository
                .findById(1L))
                .thenReturn(Optional.of(npc));
        doNothing().when(npcRepository).delete(npc);
        npcService.deleteNPCById(1L);
        verify(npcRepository, times(1)).findById(1L);
        verify(npcRepository, times(1)).delete(npc);
    }

    @DisplayName("delete npc by id but exception")
    @Test
    void deleteNpcByIdButException(){
        when(npcRepository
                .findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(NPCNotFoundException.class, () -> npcService.deleteNPCById(1L));
        verify(npcRepository, times(1)).findById(1L);
        verify(npcRepository, never()).delete(any());
    }

    @DisplayName("add weapon to npc")
    @Test
    void addWeaponToNPC() {
        Long npcId = 1L;
        Long weaponId = 1L;
        NPC npc = new NPC();
        npc.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapons));
        npcService.addWeaponToNPC(npcId, weaponId);
        assertTrue(npc.getWeapon().contains(weapons));
    }

    @DisplayName("remove weapon from npc")
    @Test
    void removeWeaponFromNPC() {
        Long npcId = 1L;
        Long weaponId = 1L;
        NPC npc = new NPC();
        npc.setWeapon(new HashSet<>());
        Weapons weapons = new Weapons();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(weaponsRepository
                .findById(weaponId))
                .thenReturn(Optional.of(weapons));
        npc.getWeapon().add(weapons);
        npcService.removeWeaponFromNPC(npcId, weaponId);
        assertFalse(npc.getWeapon().contains(weapons));
    }

    @DisplayName("add armor to npc")
    @Test
    void addArmorToNPC() {
        Long npcId = 1L;
        Long armorId = 1L;
        NPC npc = new NPC();
        npc.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(armorsRepository
                .findById(armorId))
                .thenReturn(Optional.of(armor));
        npcService.addArmorToNPC(npcId, armorId);
        assertTrue(npc.getArmor().contains(armor));
    }

    @DisplayName("remove armor from NPC")
    @Test
    void removeArmorFromNPC() {
        Long npcId = 1L;
        Long armorId = 1L;
        NPC npc = new NPC();
        npc.setArmor(new HashSet<>());
        Armors armor = new Armors();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(armorsRepository
                .findById(armorId))
                .thenReturn(Optional.of(armor));
        npc.getArmor().add(armor);
        npcService.removeArmorFromNPC(npcId, armorId);
        assertFalse(npc.getArmor().contains(armor));
    }

    @DisplayName("add magic to npc")
    @Test
    void addMagicToNPC() {
        Long npcId = 1L;
        Long magicId = 1L;
        NPC npc = new NPC();
        npc.setMagic(new HashSet<>());
        Magics magic = new Magics();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(magicsRepository
                .findById(magicId))
                .thenReturn(Optional.of(magic));
        npcService.addMagicToNPC(npcId, magicId);
        assertTrue(npc.getMagic().contains(magic));
    }

    @DisplayName("remove magic from npc")
    @Test
    void removeMagicFromNPC() {
        Long npcId = 1L;
        Long magicId = 1L;
        NPC npc = new NPC();
        npc.setMagic(new HashSet<>());
        Magics magic = new Magics();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(magicsRepository
                .findById(magicId))
                .thenReturn(Optional.of(magic));
        npc.getMagic().add(magic);
        npcService.removeMagicFromNPC(npcId, magicId);
        assertFalse(npc.getMagic().contains(magic));
    }

    @DisplayName("add soul to npc")
    @Test
    void addSoulToNPC() {
        Long npcId = 1L;
        Long soulId = 1L;
        NPC npc = new NPC();
        npc.setSoul(new HashSet<>());
        Souls soul = new Souls();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));
        npcService.addSoulToNPC(npcId, soulId);
        assertTrue(npc.getSoul().contains(soul));
    }

    @DisplayName("remove soul from NPC")
    @Test
    void removeSoulFromNPC() {
        Long npcId = 1L;
        Long soulId = 1L;
        NPC npc = new NPC();
        npc.setSoul(new HashSet<>());
        Souls soul = new Souls();
        when(npcRepository
                .findById(npcId))
                .thenReturn(Optional.of(npc));
        when(soulsRepository
                .findById(soulId))
                .thenReturn(Optional.of(soul));
        npc.getSoul().add(soul);
        npcService.removeSoulFromNPC(npcId, soulId);
        assertFalse(npc.getSoul().contains(soul));
    }
}