package student.course.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MagicsController {

    private final MagicsService magicsService;

    @PostMapping
    public ResponseEntity<Magics> addMagics(@RequestBody Magics magic) {
        log.info("HTTP: add new magic: {}", magic);
        return ResponseEntity.ok(magicsService.createMagic(magic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Magics> updateMagics(@PathVariable Long id, @RequestBody Magics updateMagic) throws MagicNotFoundException {
        Optional<Magics> optionalMagics = magicsService.getMagicById(id);
        if (optionalMagics.isPresent()) {

            magicsService.updateMagic(updateMagic, id);

            updateMagic.setMagicId(id);

            log.info("HTTP: update magic by id: {}", id);
            return ResponseEntity.ok(updateMagic);
        }

        log.error("HTTP: error update magic by id: {}", id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Magics>> getMagics() {
        log.info("HTTP: get all magics");
        return ResponseEntity.ok(magicsService.getAllMagics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Magics>> getMagics(@PathVariable Long id) throws MagicNotFoundException {
        Optional<Magics> optionalMagics = magicsService.getMagicById(id);
        if (optionalMagics.isPresent()) {
            log.info("HTTP: get magic by id: {}", id);
            return ResponseEntity.ok(optionalMagics);
        }
        else{
            log.error("HTTP: error get magic by id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMagics(@PathVariable Long id) throws MagicNotFoundException {
        Optional<Magics> optionalMagics = magicsService.getMagicById(id);
        if (optionalMagics.isPresent()) {
            magicsService.deleteMagicById(id);
            log.info("HTTP: delete magic by id: {}", id);
            return ResponseEntity.ok("Magics deleted.");
        }
        log.error("HTTP: error delete magic by id: {}", id);
        return ResponseEntity.notFound().build();
    }
}
