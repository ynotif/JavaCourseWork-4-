package student.course.service;

import student.course.model.NPC;

import java.util.List;
import java.util.Optional;

public interface NPCService {

    NPC createNPC(NPC npc);

    List<NPC> getAllNPCs();

    Optional<NPC> getNPCById(Long id);

    void updateNPC(NPC npc);

    void deleteNPCById(Long id);

    NPC addWeaponToNPC(Long npcId, Long weaponId);

    NPC removeWeaponFromNPC(Long npcId, Long weaponId);

    NPC addArmorToNPC(Long npcId, Long armorId);

    NPC removeArmorFromNPC(Long npcId, Long armorId);

    NPC addMagicToNPC(Long npcId, Long magicId);

    NPC removeMagicFromNPC(Long npcId, Long magicId);

    NPC addSoulToNPC(Long npcId, Long soulId);

    NPC removeSoulFromNPC(Long npcId, Long soulId);
}
