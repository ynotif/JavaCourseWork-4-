package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.model.Magics;
import student.course.service.MagicsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/magics")
@RestController
public class MagicsController {

    private final MagicsService magicsService;

    @PostMapping
    public ResponseEntity<Magics> addMagics(Magics magic) {
        return ResponseEntity.ok(magicsService.createMagic(magic));
    }

    @GetMapping
    public ResponseEntity<List<Magics>> getMagics() {
        return ResponseEntity.ok(magicsService.getAllMagics());
    }

    @GetMapping("{/id}")
    public ResponseEntity<Optional<Magics>> getMagics(@PathVariable Long id) {
        return ResponseEntity.ok(magicsService.getMagicById(id));
    }

}
