package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.NPC;
import student.course.repository.NPCRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NPCServiceImpl implements NPCService {

    private final NPCRepository npcRepository;

    @Override
    public NPC createNPC(NPC npc) {
        return npcRepository.save(npc);
    }


    @Override
    public List<NPC> getAllNPCs() {
        return npcRepository.findAll();
    }

    @Override
    public Optional<NPC> getNPCById(Long id) {
        return npcRepository.findById(id);
    }

    @Override
    public void updateNPC(NPC npc) {
        Optional<NPC> optionalNPC = getNPCById(npc.getNpcId());
        if (optionalNPC.isPresent()) {
            npcRepository.save(npc);
        }
    }

    @Override
    public void deleteNPC(Long id) {
        Optional<NPC> optionalNPC = getNPCById(id);
        optionalNPC.ifPresent(npcRepository::delete);
    }
}
