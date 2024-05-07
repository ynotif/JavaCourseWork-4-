package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.bdsetters.BosseSetter;
import student.course.exceptions.ArmorNotFoundException;
import student.course.exceptions.BosseNotFoundException;
import student.course.exceptions.SoulNotFoundException;
import student.course.exceptions.WeaponNotFoundException;
import student.course.model.Armors;
import student.course.model.Bosses;
import student.course.model.Souls;
import student.course.model.Weapons;
import student.course.service.ArmorsService;
import student.course.service.BossesService;
import student.course.service.SoulsService;
import student.course.service.WeaponsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bosses")
public class BossesController {

    private final BosseSetter bosseSetter = new BosseSetter();
    private final BossesService bossesService;
    private final ArmorsService armorsService;
    private final WeaponsService weaponsService;
    private final SoulsService soulsService;

    @PostMapping
    public ResponseEntity<Bosses> addBoss(@RequestBody Bosses bosse) {
        return ResponseEntity.ok(bossesService.createBosse(bosse));
    }

    @PostMapping("/{bossId}/armors/{armorId}")
    public ResponseEntity<Bosses> addArmorToBoss(@PathVariable Long bossId, @PathVariable Long armorId) throws ArmorNotFoundException, BosseNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bossId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if (optionalBosses.isPresent() && optionalArmors.isPresent()) {
            Bosses bosses = optionalBosses.get();
            if(!bosses.getArmor().contains(optionalArmors.get())) {
                return ResponseEntity.ok(bossesService.addArmorToBoss(bossId, armorId));
            }
            return ResponseEntity.ok(bosses);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{bossId}/weapons/{weaponId}")
    public ResponseEntity<Bosses> addWeaponToBoss(@PathVariable Long bossId, @PathVariable Long weaponId) throws BosseNotFoundException, WeaponNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bossId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if (optionalBosses.isPresent() && optionalWeapons.isPresent()) {
            Bosses bosses = optionalBosses.get();
            if(!bosses.getWeapon().contains(optionalWeapons.get())) {
                return ResponseEntity.ok(bossesService.addWeaponToBoss(bossId, weaponId));
            }
            return ResponseEntity.ok(bosses);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{bossId}/souls/{soulId}")
    public ResponseEntity<Bosses> addSoulToBoss(@PathVariable Long bossId, @PathVariable Long soulId) throws BosseNotFoundException, SoulNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bossId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalBosses.isPresent() && optionalSouls.isPresent()) {
            Bosses bosses = optionalBosses.get();
            if(!bosses.getSoul().contains(optionalSouls.get())) {
                return ResponseEntity.ok(bossesService.addSoulToBoss(bossId, soulId));
            }
            return ResponseEntity.ok(bosses);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bosses> updateBoss(@PathVariable Long id, @RequestBody Bosses updateBosse) throws BosseNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(id);

        if (optionalBosses.isPresent()) {
            bossesService.updateBosse(updateBosse);

            return ResponseEntity.ok(updateBosse);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Bosses>> getAllBosses() {
        return ResponseEntity.ok(bossesService.getAllBosses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bosses>> getBossById(@PathVariable Long id) throws BosseNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(id);
        if (optionalBosses.isPresent()) {
            return ResponseEntity.ok(bossesService.getBosseById(id));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bossId}/armors/{armorId}")
    public ResponseEntity<Bosses> removeArmorFromBoss(@PathVariable Long bossId, @PathVariable Long armorId) throws ArmorNotFoundException, BosseNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bossId);
        Optional<Armors> optionalArmors = armorsService.getArmorById(armorId);
        if (optionalBosses.isPresent() && optionalArmors.isPresent()) {
            Bosses bosses = optionalBosses.get();
            if (bosses.getArmor().contains(optionalArmors.get())) {
                return ResponseEntity.ok(bossesService.removeArmorFromBoss(bossId, armorId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{bossId}/weapons/{weaponId}")
    public ResponseEntity<Bosses> removeWeaponFromBoss(@PathVariable Long bossId, @PathVariable Long weaponId) throws BosseNotFoundException, WeaponNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bossId);
        Optional<Weapons> optionalWeapons = weaponsService.getWeaponById(weaponId);
        if(optionalBosses.isPresent() && optionalWeapons.isPresent()){
            Bosses bosses = optionalBosses.get();
            if(bosses.getWeapon().contains(optionalWeapons.get())){
                return ResponseEntity.ok(bossesService.removeWeaponFromBoss(bossId, weaponId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{bossId}/souls/{soulId}")
    public ResponseEntity<Bosses> removeSoulFromBoss(@PathVariable Long bossId, @PathVariable Long soulId) throws BosseNotFoundException, SoulNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(bossId);
        Optional<Souls> optionalSouls = soulsService.getSoulById(soulId);
        if(optionalBosses.isPresent() && optionalSouls.isPresent()){
            Bosses bosses = optionalBosses.get();
            if(bosses.getSoul().contains(optionalSouls.get())){
                return ResponseEntity.ok(bossesService.removeSoulFromBoss(bossId, soulId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBossById(@PathVariable Long id) throws BosseNotFoundException {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(id);
        if (optionalBosses.isPresent()) {
            bossesService.deleteBosseById(id);
            return ResponseEntity.ok("Bosse deleted successfully!");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
