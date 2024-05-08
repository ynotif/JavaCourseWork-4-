package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.exceptions.MagicNotFoundException;
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
    public ResponseEntity<Magics> addMagics(@RequestBody Magics magic) {
        return ResponseEntity.ok(magicsService.createMagic(magic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Magics> updateMagics(@PathVariable Long id, @RequestBody Magics updateMagic) throws MagicNotFoundException {
        Optional<Magics> optionalMagics = magicsService.getMagicById(id);
        if (optionalMagics.isPresent()) {

            magicsService.updateMagic(updateMagic, id);

            updateMagic.setMagicId(id);

            return ResponseEntity.ok(updateMagic);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<Magics>> getMagics() {
        return ResponseEntity.ok(magicsService.getAllMagics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Magics>> getMagics(@PathVariable Long id) throws MagicNotFoundException {
        return ResponseEntity.ok(magicsService.getMagicById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMagics(@PathVariable Long id) throws MagicNotFoundException {
        Optional<Magics> optionalMagics = magicsService.getMagicById(id);
        if (optionalMagics.isPresent()) {
            magicsService.deleteMagicById(id);
            return ResponseEntity.ok("Magics deleted.");
        }
        return ResponseEntity.notFound().build();
    }
}
