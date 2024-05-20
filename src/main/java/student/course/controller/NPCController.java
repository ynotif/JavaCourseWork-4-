package student.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.*;
import student.course.model.*;
import student.course.service.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/npc")
@RestController
@Slf4j
public class NPCController {

    private final NPCService npcService;
    private final WeaponsService weaponsService;
    private final ArmorsService armorsService;
    private final MagicsService magicsService;
    private final SoulsService soulsService;

    @PostMapping
    public ResponseEntity<NPC> createNPC(@RequestBody NPC npc) {
        log.info("HTTP: Creating NPC: {}", npc);
        return ResponseEntity.ok(npcService.createNPC(npc));
    }

    @PostMapping("/{npcId}/weapons/{weaponId}")
    public ResponseEntity<NPC> addWeaponToNPC(@PathVariable Long npcId, @PathVariable Long weaponId) throws NPCNotFoundException, WeaponNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalNPC.isPresent() && optionalWeapons.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getWeapon().contains(optionalWeapons.get())){
                log.info("HTTP: add weapon to npc (npc id {} weapon id {})", npcId, weaponId);
                return ResponseEntity.ok(npcService.addWeaponToNPC(npcId, weaponId));
            }
            log.warn("HTTP: npc has this weapon(npc id {} weapon id {})", npcId, weaponId);
            return ResponseEntity.ok(npc);
        }
        log.error("HTTP: npc or weapon not found for add (npc id {} weapon id {})", npcId, weaponId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{npcId}/armors/{armorId}")
    public ResponseEntity<NPC> addArmorToNPC(@PathVariable Long npcId, @PathVariable Long armorId) throws ArmorNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalNPC.isPresent() && optionalArmors.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getArmor().contains(optionalArmors.get())){
                log.info("HTTP: add armor to npc (npc id {} armor id {})", npcId, armorId);
                return ResponseEntity.ok(npcService.addArmorToNPC(npcId, armorId));
            }
            log.warn("HTTP: npc has this armor (npc id {} armor id {})", npcId, armorId);
            return ResponseEntity.ok(npc);
        }
        log.error("HTTP: npc or armor not found for add (npc id {} armor id {})", npcId, armorId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{npcId}/magics/{magicId}")
    public ResponseEntity<NPC> addMagicToNPC(@PathVariable Long npcId, @PathVariable Long magicId) throws MagicNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if(optionalNPC.isPresent() && optionalMagics.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getMagic().contains(optionalMagics.get())){
                log.info("HTTP: add magic to npc (npc id {} magic id {})", npcId, magicId);
                return ResponseEntity.ok(npcService.addMagicToNPC(npcId, magicId));
            }
            log.warn("HTTP: npc has this magic (npc id {} magic id {})", npcId, magicId);
            return ResponseEntity.ok(npc);
        }
        log.error("HTTP: npc or magic not found for add (npc id {} magic id {})", npcId, magicId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{npcId}/souls/{soulId}")
    public ResponseEntity<NPC> addSoulToNPC(@PathVariable Long npcId, @PathVariable Long soulId) throws NPCNotFoundException, SoulNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalNPC.isPresent() && optionalSouls.isPresent()){
            NPC npc = optionalNPC.get();
            if(!npc.getSoul().contains(optionalSouls.get())){
                log.info("HTTP: add soul to npc (npc id {} soul id {})", npcId, soulId);
                return ResponseEntity.ok(npcService.addSoulToNPC(npcId, soulId));
            }
            log.warn("HTTP: npc has this soul (npc id {} soul id {})", npcId, soulId);
            return ResponseEntity.ok(npc);
        }
        log.error("HTTP: npc or soul not found for add (npc id {} soul id {})", npcId, soulId);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NPC> updateNPC(@PathVariable Long id, @RequestBody NPC updateNPC) throws NPCNotFoundException {
        Optional<NPC> npc = npcService.getNPCById(id);
        if (npc.isPresent()) {
            npcService.updateNPC(updateNPC, id);

            log.info("HTTP: update npc by id {}", id);
            return ResponseEntity.ok(npc.get());
        }
        log.error("HTTP: npc not found for update by id: {}", id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<NPC>> getNPCs() {
        log.info("HTTP: get all npc");
        return ResponseEntity.ok(npcService.getAllNPCs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<NPC>> getNPC(@PathVariable Long id) throws NPCNotFoundException {
        Optional<NPC> npc = npcService.getNPCById(id);
        if(npc.isPresent()){
            log.info("HTTP: get npc by id {}", id);
            return ResponseEntity.ok(npc);
        }
        log.error("HTTP: npc not found for delete by id: {}", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{npcId}/weapons/{weaponId}")
    public ResponseEntity<NPC> removeWeaponFromNPC(@PathVariable Long npcId, @PathVariable Long weaponId) throws NPCNotFoundException, WeaponNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalNPC.isPresent() && optionalWeapons.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getWeapon().contains(optionalWeapons.get())){
                log.info("HTTP: remove weapon from npc (npc id {} weapon id {})", npcId, weaponId);
                return ResponseEntity.ok(npcService.removeWeaponFromNPC(npcId, weaponId));
            }
        }
        log.error("HTTP: npc or weapon not found for remove (npc id {} weapon id {})", npcId, weaponId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{npcId}/armors/{armorId}")
    public ResponseEntity<NPC> removeArmorFromNPC(@PathVariable Long npcId, @PathVariable Long armorId) throws ArmorNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalNPC.isPresent() && optionalArmors.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getArmor().contains(optionalArmors.get())){
                log.info("HTTP: remove armor from npc (npc id {} armor id {})", npcId, armorId);
                return ResponseEntity.ok(npcService.removeArmorFromNPC(npcId, armorId));
            }
        }
        log.error("HTTP: npc or armor not found for remove (npc id {} armor id {})", npcId, armorId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{npcId}/magics/{magicId}")
    public ResponseEntity<NPC> removeMagicFromNPC(@PathVariable Long npcId, @PathVariable Long magicId) throws MagicNotFoundException, NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Magics> optionalMagics = magicsService.getMagicById(magicId);
        if(optionalNPC.isPresent() && optionalMagics.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getMagic().contains(optionalMagics.get())){
                log.info("HTTP: remove magic from npc (npc id {} magic id {})", npcId, magicId);
                return ResponseEntity.ok(npcService.removeMagicFromNPC(npcId, magicId));
            }
        }
        log.error("HTTP: npc or magic not found for remove (npc id {} magic id {})", npcId, magicId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{npcId}/souls/{soulId}")
    public ResponseEntity<NPC> removeSoulFromNPC(@PathVariable Long npcId, @PathVariable Long soulId) throws NPCNotFoundException, SoulNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(npcId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalNPC.isPresent() && optionalSouls.isPresent()){
            NPC npc = optionalNPC.get();
            if(npc.getSoul().contains(optionalSouls.get())){
                log.info("HTTP: remove soul from npc (npc id {} soul id {})", npcId, soulId);
                return ResponseEntity.ok(npcService.removeSoulFromNPC(npcId, soulId));
            }
        }
        log.error("HTTP: npc or soul not found for remove (npc id {} soul id {})", npcId, soulId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNPCById(@PathVariable Long id) throws NPCNotFoundException {
        Optional<NPC> optionalNPC = npcService.getNPCById(id);
        if(optionalNPC.isPresent()){
            npcService.deleteNPCById(id);
            log.info("HTTP: npc deleted (id {})", id);
            return ResponseEntity.ok("NPC deleted successfully!");
        }
        log.error("HTTP: npc not found for delete (npc id {})", id);
        return ResponseEntity.notFound().build();
    }

}
