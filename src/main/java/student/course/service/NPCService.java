package student.course.service;

import student.course.model.NPC;

import java.util.List;
import java.util.Optional;

public interface NPCService {

    NPC createNPC(NPC npc);

    List<NPC> getAllNPCs();

    Optional<NPC> getNPCById(Long id);

}
