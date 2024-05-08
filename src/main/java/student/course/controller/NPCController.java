package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.*;
import student.course.model.*;
import student.course.repository.NPCRepository;
import student.course.service.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/npc")
@RestController
public class NPCController {

    private final NPCService npcService;
    private final WeaponsService weaponsService;
    private final ArmorsService armorsService;
    private final MagicsService magicsService;
    private final SoulsService soulsService;

    @PostMapping
    public ResponseEntity<NPC> createNPC(@RequestBody NPC npc) {
        return ResponseEntity.ok(npcService.createNPC(npc));
    }

    @PostMapping("/{npcId}/weapons/{weaponId}")
    public ResponseEntity<NPC> addWeaponToNPC(@PathVariable Long npcId, @PathVariable Long weaponId) throws NPCNotFoundException, WeaponNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalNPC.isPresent() && optionalWeapons.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getWeapon().contains(optionalWeapons.get())){
                return ResponseEntity.ok(npcService.addWeaponToNPC(npcId, weaponId));
            }
            return ResponseEntity.ok(npc);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{npcId}/armors/{armorId}")
    public ResponseEntity<NPC> addArmorToNPC(@PathVariable Long npcId, @PathVariable Long armorId) throws ArmorNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalNPC.isPresent() && optionalArmors.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getArmor().contains(optionalArmors.get())){
                return ResponseEntity.ok(npcService.addArmorToNPC(npcId, armorId));
            }
            return ResponseEntity.ok(npc);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{npcId}/magics/{magicId}")
    public ResponseEntity<NPC> addMagicToNPC(@PathVariable Long npcId, @PathVariable Long magicId) throws LocationNotFoundException, MagicNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if(optionalNPC.isPresent() && optionalMagics.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getMagic().contains(optionalMagics.get())){
                return ResponseEntity.ok(npcService.addMagicToNPC(npcId, magicId));
            }
            return ResponseEntity.ok(npc);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{npcId}/souls/{soulId}")
    public ResponseEntity<NPC> addSoulToNPC(@PathVariable Long npcId, @PathVariable Long soulId) throws NPCNotFoundException, SoulNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalNPC.isPresent() && optionalSouls.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getSoul().contains(optionalSouls.get())){
                return ResponseEntity.ok(npcService.addSoulToNPC(npcId, soulId));
            }
            return ResponseEntity.ok(npc);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NPC> updateNPC(@PathVariable Long id, @RequestBody NPC updateNPC) throws NPCNotFoundException {
        Optional<NPC> npc = npcService.getNPCById(id);
        if (npc.isPresent()) {
            npcService.updateNPC(updateNPC, id);

            return ResponseEntity.ok(npc.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<NPC>> getNPCs() {
        return ResponseEntity.ok(npcService.getAllNPCs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<NPC>> getNPC(@PathVariable Long id) throws NPCNotFoundException {
        return ResponseEntity.ok(npcService.getNPCById(id));
    }

    @DeleteMapping("/{npcId}/weapons/{weaponId}")
    public ResponseEntity<NPC> removeWeaponFromNPC(@PathVariable Long npcId, @PathVariable Long weaponId) throws NPCNotFoundException, WeaponNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalNPC.isPresent() && optionalWeapons.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getWeapon().contains(optionalWeapons.get())){
                return ResponseEntity.ok(npcService.removeWeaponFromNPC(npcId, weaponId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{npcId}/armors/{armorId}")
    public ResponseEntity<NPC> removeArmorFromNPC(@PathVariable Long npcId, @PathVariable Long armorId) throws ArmorNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalNPC.isPresent() && optionalArmors.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getArmor().contains(optionalArmors.get())){
                return ResponseEntity.ok(npcService.removeArmorFromNPC(npcId, armorId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{npcId}/magics/{magicId}")
    public ResponseEntity<NPC> removeMagicFromNPC(@PathVariable Long npcId, @PathVariable Long magicId) throws MagicNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if(optionalNPC.isPresent() && optionalMagics.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getMagic().contains(optionalMagics.get())){
                return ResponseEntity.ok(npcService.removeMagicFromNPC(npcId, magicId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{npcId}/souls/{soulId}")
    public ResponseEntity<NPC> removeSoulFromNPC(@PathVariable Long npcId, @PathVariable Long soulId) throws NPCNotFoundException, SoulNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalNPC.isPresent() && optionalSouls.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getSoul().contains(optionalSouls.get())){
                return ResponseEntity.ok(npcService.removeSoulFromNPC(npcId, soulId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNPCById(@PathVariable Long id) throws NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(id);
        if(optionalNPC.isPresent()){
            npcService.deleteNPCById(id);
            return ResponseEntity.ok("NPC deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

}
