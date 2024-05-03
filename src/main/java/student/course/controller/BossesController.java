package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.bdsetters.BosseSetter;
import student.course.model.Bosses;
import student.course.service.BossesService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bosses")
public class BossesController {

    private final BosseSetter bosseSetter = new BosseSetter();
    private final BossesService bossesService;

    @PostMapping
    public ResponseEntity<Bosses> addBoss(@RequestBody Bosses bosse) {
        return ResponseEntity.ok(bossesService.createBosse(bosse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bosses> updateBoss(@PathVariable Long id, @RequestBody Bosses updateBosse) {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(id);

        if (optionalBosses.isPresent()) {

            Bosses bosses = optionalBosses.get();

            bosseSetter.update(bosses, updateBosse);

            bossesService.updateBosse(bosses);
            return ResponseEntity.ok(bosses);
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
    public ResponseEntity<Optional<Bosses>> getBossById(@PathVariable Long id) {
        Optional<Bosses> optionalBosses = bossesService.getBosseById(id);
        if (optionalBosses.isPresent()) {
            return ResponseEntity.ok(bossesService.getBosseById(id));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoss(@PathVariable Long id) {
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
