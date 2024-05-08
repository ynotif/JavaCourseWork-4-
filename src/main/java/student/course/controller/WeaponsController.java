package student.course.controller;

import lombok.RequiredArgsConstructor;
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
public class WeaponsController {

    private final WeaponsService weaponsService;
    private final SoulsService soulsService;

    @PostMapping
    public ResponseEntity<Weapons> createWeapons(@RequestBody Weapons weapon) {
        return ResponseEntity.ok(weaponsService.createWeapon(weapon));
    }

    @PostMapping("/{weaponId}/souls/{soulId}")
    public ResponseEntity<Weapons> addSoulToWeapon(@PathVariable Long weaponId, @PathVariable Long soulId) throws SoulNotFoundException, WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalWeapons.isPresent() && optionalSouls.isPresent()){
            Weapons weapons = optionalWeapons.get();
            if(!weapons.getSoul().contains(optionalSouls.get())){
                return ResponseEntity.ok(weaponsService.addSoulToWeapon(weaponId, soulId));
            }
            return ResponseEntity.ok(weapons);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Weapons> updateWeapon(@RequestBody Weapons updateWeapon, @PathVariable Long id) throws WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(id);
        if(optionalWeapons.isPresent()){
            weaponsService.updateWeapon(updateWeapon, id);
            return ResponseEntity.ok(updateWeapon);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Weapons>> getAllWeapons() {
       return ResponseEntity.ok(weaponsService.getAllWeapons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Weapons>> getWeaponById(@PathVariable Long id) throws WeaponNotFoundException {
        return ResponseEntity.ok(weaponsService.getWeaponById(id));
    }

    @DeleteMapping("/{weaponId}/souls/{soulId}")
    public ResponseEntity<Weapons> removeSoulFromWeapon(@PathVariable Long weaponId, @PathVariable Long soulId) throws SoulNotFoundException, WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalWeapons.isPresent() && optionalSouls.isPresent()){
            Weapons weapons = optionalWeapons.get();
            if(weapons.getSoul().contains(optionalSouls.get())){
                return ResponseEntity.ok(weaponsService.removeSoulFromWeapon(weaponId, soulId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeaponById(@PathVariable Long id) throws WeaponNotFoundException {
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(id);
        if(optionalWeapons.isPresent()){
            weaponsService.deleteWeaponById(id);
            return ResponseEntity.ok("Weapon deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

}
