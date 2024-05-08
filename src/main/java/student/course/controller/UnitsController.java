package student.course.controller;

import lombok.RequiredArgsConstructor;
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
public class UnitsController {

    private final UnitsService unitsService;
    private final ArmorsService armorsService;
    private final WeaponsService weaponsService;
    private final SoulsService soulsService;

    @PostMapping
    public ResponseEntity<Units> addUnit(@RequestBody Units unit) {
        return ResponseEntity.ok(unitsService.createUnit(unit));
    }

    @PostMapping("/{unitId}/armors/{armorId}")
    public ResponseEntity<Units> addArmorToUnit(@PathVariable Long unitId, @PathVariable Long armorId) throws ArmorNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalUnits.isPresent() && optionalArmors.isPresent()) {
            Units units = optionalUnits.get();
            if(!units.getArmor().contains(optionalArmors.get())){
                return ResponseEntity.ok(unitsService.addArmorToUnit(unitId, armorId));
            }
            return ResponseEntity.ok(units);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{unitId}/weapons/{weaponId}")
    public ResponseEntity<Units> addWeaponToUnit(@PathVariable Long unitId, @PathVariable Long weaponId) throws UnitNotFoundException, WeaponNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalUnits.isPresent() && optionalWeapons.isPresent()) {
            Units units = optionalUnits.get();
            if(!units.getWeapon().contains(optionalWeapons.get())){
                return ResponseEntity.ok(unitsService.addWeaponToUnit(unitId, weaponId));
            }
            return ResponseEntity.ok(units);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{unitId}/souls/{soulId}")
    public ResponseEntity<Units> addSoulToUnit(@PathVariable Long unitId, @PathVariable Long soulId) throws SoulNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalUnits.isPresent() && optionalSouls.isPresent()) {
            Units units = optionalUnits.get();
            if(!units.getSoul().contains(optionalSouls.get())){
                return ResponseEntity.ok(unitsService.addSoulToUnit(unitId, soulId));
            }
            return ResponseEntity.ok(units);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Units> updateUnit(@PathVariable Long id, @RequestBody Units updateUnit) throws UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(id);
        if (optionalUnits.isPresent()) {
            unitsService.updateUnit(updateUnit, id);
            return ResponseEntity.ok(updateUnit);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Units>> getAllUnits() {
        return ResponseEntity.ok(unitsService.getAllUnits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Units>> getUnitById(@PathVariable Long id) throws UnitNotFoundException {
        return ResponseEntity.ok(unitsService.getUnitById(id));
    }

    @DeleteMapping("/{unitId}/armors/{armorId}")
    public ResponseEntity<Units> removeArmorFromUnit(@PathVariable Long unitId, @PathVariable Long armorId) throws ArmorNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if(optionalUnits.isPresent() && optionalArmors.isPresent()) {
            Units units = optionalUnits.get();
            if(units.getArmor().contains(optionalArmors.get())){
                return ResponseEntity.ok(unitsService.removeArmorFromUnit(unitId, armorId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unitId}/weapons/{weaponId}")
    public ResponseEntity<Units> removeWeaponFromUnit(@PathVariable Long unitId, @PathVariable Long weaponId) throws UnitNotFoundException, WeaponNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalUnits.isPresent() && optionalWeapons.isPresent()) {
            Units units = optionalUnits.get();
            if(units.getWeapon().contains(optionalWeapons.get())){
                return ResponseEntity.ok(unitsService.removeWeaponFromUnit(unitId, weaponId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unitId}/souls/{soulId}")
    public ResponseEntity<Units> removeSoulFromUnit(@PathVariable Long unitId, @PathVariable Long soulId) throws SoulNotFoundException, UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(unitId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalUnits.isPresent() && optionalSouls.isPresent()) {
            Units units = optionalUnits.get();
            if(units.getSoul().contains(optionalSouls.get())){
                return ResponseEntity.ok(unitsService.removeSoulFromUnit(unitId, soulId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUnitById(@PathVariable Long id) throws UnitNotFoundException {
        Optional<Units> optionalUnits = unitsService.getUnitById(id);
        if(optionalUnits.isPresent()) {
            unitsService.deleteUnitById(id);
            return ResponseEntity.ok("Unit deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }

}
