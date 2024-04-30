package student.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.course.model.NPC;
import student.course.service.NPCService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/npc")
@RestController
public class NPCController {

    private final NPCService npcService;

    @PostMapping
    public ResponseEntity<NPC> createNPC(@RequestBody NPC npc) {
        return ResponseEntity.ok(npcService.createNPC(npc));
    }

    @GetMapping
    public ResponseEntity<List<NPC>> getNPCs() {
        return ResponseEntity.ok(npcService.getAllNPCs());
    }

    @GetMapping("{/id}")
    public ResponseEntity<Optional<NPC>> getNPC(@PathVariable Long id) {
        return ResponseEntity.ok(npcService.getNPCById(id));
    }

}
