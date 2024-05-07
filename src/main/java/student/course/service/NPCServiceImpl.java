package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.exceptions.NPCNotFoundException;
import student.course.model.*;
import student.course.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NPCServiceImpl implements NPCService {

    private final NPCRepository npcRepository;
    private final WeaponsRepository weaponsRepository;
    private final ArmorsRepository armorsRepository;
    private final MagicsRepository magicsRepository;
    private final SoulsRepository soulsRepository;

    @Override
    public NPC createNPC(NPC npc) {
        return npcRepository.save(npc);
    }


    @Override
    public List<NPC> getAllNPCs() {
        return npcRepository.findAll();
    }

    @Override
    public Optional<NPC> getNPCById(Long id) throws NPCNotFoundException {
        Optional<NPC> npcOptional = npcRepository.findById(id);
        if(npcOptional.isPresent()){
            return npcOptional;
        }
        else{
            throw new NPCNotFoundException(id);
        }
    }

    @Override
    public void updateNPC(NPC npc) throws NPCNotFoundException {
        Optional<NPC> optionalNPC = getNPCById(npc.getNpcId());
        if (optionalNPC.isPresent()) {
            npcRepository.save(npc);
        }
    }

    @Override
    public void deleteNPCById(Long id) throws NPCNotFoundException {
        Optional<NPC> optionalNPC = getNPCById(id);
        optionalNPC.ifPresent(npcRepository::delete);
    }

    @Override
    public NPC addWeaponToNPC(Long npcId, Long weaponId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        npc.getWeapon().add(weapons);
        return npcRepository.save(npc);
    }

    @Override
    public NPC removeWeaponFromNPC(Long npcId, Long weaponId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));
        Weapons weapons = weaponsRepository.findById(weaponId)
                .orElseThrow(() -> new RuntimeException("Weapon not found"));

        npc.getWeapon().remove(weapons);
        return npcRepository.save(npc);
    }

    @Override
    public NPC addArmorToNPC(Long npcId, Long armorId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));
        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armor not found"));

        npc.getArmor().add(armors);
        return npcRepository.save(npc);
    }

    @Override
    public NPC removeArmorFromNPC(Long npcId, Long armorId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));
        Armors armors = armorsRepository.findById(armorId)
                .orElseThrow(() -> new RuntimeException("Armor not found"));

        npc.getArmor().remove(armors);
        return npcRepository.save(npc);
    }

    @Override
    public NPC addMagicToNPC(Long npcId, Long magicId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));
        Magics magics = magicsRepository.findById(magicId)
                .orElseThrow(() -> new RuntimeException("Magics not found"));
        npc.getMagic().add(magics);
        return npcRepository.save(npc);
    }

    @Override
    public NPC removeMagicFromNPC(Long npcId, Long magicId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));
        Magics magics = magicsRepository.findById(magicId)
                .orElseThrow(() -> new RuntimeException("Magics not found"));

        npc.getMagic().remove(magics);
        return npcRepository.save(npc);
    }

    @Override
    public NPC addSoulToNPC(Long npcId, Long soulId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));

        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Souls not found"));

        npc.getSoul().add(souls);
        return npcRepository.save(npc);
    }

    @Override
    public NPC removeSoulFromNPC(Long npcId, Long soulId) {
        NPC npc = npcRepository.findById(npcId)
                .orElseThrow(() -> new RuntimeException("NPC not found"));
        Souls souls = soulsRepository.findById(soulId)
                .orElseThrow(() -> new RuntimeException("Souls not found"));

        npc.getSoul().remove(souls);
        return npcRepository.save(npc);
    }
}
