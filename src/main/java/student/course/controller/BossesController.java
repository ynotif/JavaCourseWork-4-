package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.model.Bosses;
import student.course.service.BossesService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bosses")
public class BossesController {

    private final BossesService bossesService;

    @PostMapping
    public ResponseEntity<Bosses> addBoss(@RequestBody Bosses bosse) {
        return ResponseEntity.ok(bossesService.createBosse(bosse));
    }

    @GetMapping
    public ResponseEntity<List<Bosses>> getAllBosses() {
        return ResponseEntity.ok(bossesService.getAllBosses());
    }

    @GetMapping("{/id}")
    public ResponseEntity<Optional<Bosses>> getBossById(@PathVariable Long id) {
        return ResponseEntity.ok(bossesService.getBosseById(id));
    }

}
