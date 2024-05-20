package student.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.ArmorNotFoundException;
import student.course.exceptions.SoulNotFoundException;
import student.course.exceptions.UnitNotFoundException;
import student.course.exceptions.WeaponNotFoundException;
import student.course.model.Armors;
import student.course.model.Souls;
import student.course.model.Units;
import student.course.model.Weapons;
import student.course.service.ArmorsService;
import student.course.service.SoulsService;
import student.course.service.UnitsService;
import student.course.service.WeaponsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/units")
@Slf4j
public class UnitsController {

    private final UnitsService unitsService;
    private final ArmorsService armorsService;
    private final WeaponsService weaponsService;
    private final SoulsService soulsService;

    @PostMapping
    public ResponseEntity<Units> addUnit(@RequestBody Units unit) {
        log.info("HTTP: add unit");
        return ResponseEntity.ok(unitsService.createUnit(unit));
    }

    @PostMapping("/{unitId}/armors/{armorId}")
    public ResponseEntity<Units> addArmorToUnit(@PathVariable Long unitId, @PathVariable Long armorId) throws ArmorNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalUnits.isPresent() && optionalArmors.isPresent()) {
            Units units = optionalUnits.get();
            if(!units.getArmor().contains(optionalArmors.get())){
                log.info("HTTP: add armor to unit (unit id {}, armor id {})", unitId, armorId);
                return ResponseEntity.ok(unitsService.addArmorToUnit(unitId, armorId));
            }
            log.warn("HTTP: unit has this armor (unit id {}, armor id {})", unitId, armorId);
            return ResponseEntity.ok(units);
        }
        log.error("HTTP: unit or armor not found for update (unit id {}, armor id {})", unitId, armorId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{unitId}/weapons/{weaponId}")
    public ResponseEntity<Units> addWeaponToUnit(@PathVariable Long unitId, @PathVariable Long weaponId) throws UnitNotFoundException, WeaponNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalUnits.isPresent() && optionalWeapons.isPresent()) {
            Units units = optionalUnits.get();
            if(!units.getWeapon().contains(optionalWeapons.get())){
                log.info("HTTP: add weapon to unit (unit id {}, weapon id {})", unitId, weaponId);
                return ResponseEntity.ok(unitsService.addWeaponToUnit(unitId, weaponId));
            }
            log.warn("HTTP: unit has this weapon (unit id {}, weapon id {})", unitId, weaponId);
            return ResponseEntity.ok(units);
        }
        log.error("HTTP: unit or weapon not found for add (unit id {}, weapon id {})", weaponId, unitId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{unitId}/souls/{soulId}")
    public ResponseEntity<Units> addSoulToUnit(@PathVariable Long unitId, @PathVariable Long soulId) throws SoulNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalUnits.isPresent() && optionalSouls.isPresent()) {
            Units units = optionalUnits.get();
            if(!units.getSoul().contains(optionalSouls.get())){
                log.info("HTTP: add soul to unit (unit id {}, soul id {})", unitId, soulId);
                return ResponseEntity.ok(unitsService.addSoulToUnit(unitId, soulId));
            }
            log.warn("HTTP: unit has this soul (unit id {}, soul id {})", unitId, soulId);
            return ResponseEntity.ok(units);
        }
        log.error("HTTP: unit or soul not found for add (unit id {}, soul id {})", unitId, soulId);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Units> updateUnit(@PathVariable Long id, @RequestBody Units updateUnit) throws UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(id);
        if (optionalUnits.isPresent()) {
            unitsService.updateUnit(updateUnit, id);
            Optional<Units> optionalUnit = unitsService.getUnitById(id);
            log.info("HTTP: update unit by id {}", id);
            if(optionalUnit.isPresent()) {
                return ResponseEntity.ok(optionalUnit.get());
            }
        }
        log.error("HTTP: unit not found for update by id {}", id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Units>> getAllUnits() {
        log.info("HTTP: get all units");
        return ResponseEntity.ok(unitsService.getAllUnits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Units>> getUnitById(@PathVariable Long id) throws UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(id);
        if(optionalUnits.isPresent()) {
            log.info("HTTP: get unit by id {}", id);
            return ResponseEntity.ok(optionalUnits);
        }
        log.error("HTTP: unit not found for get by id {}", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unitId}/armors/{armorId}")
    public ResponseEntity<Units> removeArmorFromUnit(@PathVariable Long unitId, @PathVariable Long armorId) throws ArmorNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalUnits.isPresent() && optionalArmors.isPresent()) {
            Units units = optionalUnits.get();
            if(units.getArmor().contains(optionalArmors.get())){
                log.info("HTTP: remove armor from unit (unit id {}, armor id {})", unitId, armorId);
                return ResponseEntity.ok(unitsService.removeArmorFromUnit(unitId, armorId));
            }
        }
        log.error("HTTP: unit or armor not found for remove (unit id {}, armor id {})", unitId, armorId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unitId}/weapons/{weaponId}")
    public ResponseEntity<Units> removeWeaponFromUnit(@PathVariable Long unitId, @PathVariable Long weaponId) throws UnitNotFoundException, WeaponNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalUnits.isPresent() && optionalWeapons.isPresent()) {
            Units units = optionalUnits.get();
            if(units.getWeapon().contains(optionalWeapons.get())){
                log.info("HTTP: remove weapon from unit (unit id {}, weapon id {})", unitId, weaponId);
                return ResponseEntity.ok(unitsService.removeWeaponFromUnit(unitId, weaponId));
            }
        }
        log.error("HTTP: unit or weapon not found for remove (unit id {}, weapon id {}", unitId, weaponId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unitId}/souls/{soulId}")
    public ResponseEntity<Units> removeSoulFromUnit(@PathVariable Long unitId, @PathVariable Long soulId) throws SoulNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalUnits.isPresent() && optionalSouls.isPresent()) {
            Units units = optionalUnits.get();
            if(units.getSoul().contains(optionalSouls.get())){
                log.info("HTTP: remove soul from unit (unit id {}, soul id {})", unitId, soulId);
                return ResponseEntity.ok(unitsService.removeSoulFromUnit(unitId, soulId));
            }
        }
        log.error("HTTP: unit or soul not found for remove (unit id {}, soul id {}", unitId, soulId);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUnitById(@PathVariable Long id) throws UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(id);
        if(optionalUnits.isPresent()) {
            unitsService.deleteUnitById(id);
            log.info("HTTP: delete unit by id {}", id);
            return ResponseEntity.ok("Unit deleted successfully!");
        }
        log.error("HTTP: unit not found for delete by id {}", id);
        return ResponseEntity.notFound().build();
    }

}
