package student.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.SoulNotFoundException;
import student.course.exceptions.WeaponNotFoundException;
import student.course.model.Souls;
import student.course.model.Weapons;
import student.course.service.SoulsService;
import student.course.service.WeaponsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weapons")
@Slf4j
public class WeaponsController {

    private final WeaponsService weaponsService;
    private final SoulsService soulsService;

    @PostMapping
    public ResponseEntity<Weapons> createWeapons(@RequestBody Weapons weapon) {
        log.info("HTTP: Create Weapons");
        return ResponseEntity.ok(weaponsService.createWeapon(weapon));
    }

    @PostMapping("/{weaponId}/souls/{soulId}")
    public ResponseEntity<Weapons> addSoulToWeapon(@PathVariable Long weaponId, @PathVariable Long soulId) throws SoulNotFoundException, WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalWeapons.isPresent() && optionalSouls.isPresent()){
            Weapons weapons = optionalWeapons.get();
            if(!weapons.getSoul().contains(optionalSouls.get())){
                log.info("HTTP: Add Soul to Weapons (weapon id {}, soul id {})", weaponId, soulId);
                return ResponseEntity.ok(weaponsService.addSoulToWeapon(weaponId, soulId));
            }
            log.warn("HTTP: weapon has this soul (weapon id {}, soul id {})", weaponId, soulId);
            return ResponseEntity.ok(weapons);
        }
        log.error("HTTP: weapon or soul not found for add (weapon id {}, soul id {})", weaponId, soulId);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Weapons> updateWeapon(@RequestBody Weapons updateWeapon, @PathVariable Long id) throws WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(id);
        if(optionalWeapons.isPresent()){
            weaponsService.updateWeapon(updateWeapon, id);
            log.info("HTTP: Update Weapons by id {}", id);
            return ResponseEntity.ok(updateWeapon);
        }
        log.error("HTTP: weapon not found for update by id {}", id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Weapons>> getAllWeapons() {
        log.info("HTTP: Get All Weapons");
       return ResponseEntity.ok(weaponsService.getAllWeapons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Weapons>> getWeaponById(@PathVariable Long id) throws WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(id);
        if(optionalWeapons.isPresent()){
            log.info("HTTP: Get Weapons by id {}", id);
            return ResponseEntity.ok(optionalWeapons);
        }
        log.error("HTTP: weapon not found for get by id {}", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{weaponId}/souls/{soulId}")
    public ResponseEntity<Weapons> removeSoulFromWeapon(@PathVariable Long weaponId, @PathVariable Long soulId) throws SoulNotFoundException, WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalWeapons.isPresent() && optionalSouls.isPresent()){
            Weapons weapons = optionalWeapons.get();
            if(weapons.getSoul().contains(optionalSouls.get())){
                log.info("HTTP: remove soul from weapon (weapon id {}, soul id {})", weaponId, soulId);
                return ResponseEntity.ok(weaponsService.removeSoulFromWeapon(weaponId, soulId));
            }
        }
        log.info("HTTP: error weapon or soul not found for remove (weapon id {}, soul id {})", weaponId, soulId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeaponById(@PathVariable Long id) throws WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(id);
        if(optionalWeapons.isPresent()){
            weaponsService.deleteWeaponById(id);
            log.info("HTTP: Delete Weapons by id {}", id);
            return ResponseEntity.ok("Weapon deleted successfully!");
        }
        log.error("HTTP: weapon not found for delete by id {}", id);
        return ResponseEntity.notFound().build();
    }

}
