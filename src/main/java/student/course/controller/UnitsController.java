package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.model.Units;
import student.course.service.UnitsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/units")
public class UnitsController {

    private final UnitsService unitsService;

    @PostMapping
    public ResponseEntity<Units> addUnit(@RequestBody Units unit) {
        return ResponseEntity.ok(unitsService.createUnit(unit));
    }

    @GetMapping
    public ResponseEntity<List<Units>> getAllUnits() {
        return ResponseEntity.ok(unitsService.getAllUnits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Units>> getUnitById(@PathVariable Long id) {
        return ResponseEntity.ok(unitsService.getUnitById(id));
    }

}
