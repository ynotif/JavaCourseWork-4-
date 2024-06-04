package student.course.bdsetters;

import lombok.experimental.UtilityClass;
import student.course.model.NPC;

@UtilityClass
public class NPCSetter {
    public void update(NPC npc, NPC npcUpdate, Long id) {
        npc.setNpcId(id);
        npc.setNpcName(npcUpdate.getNpcName());
        npc.setNpcQuest(npcUpdate.getNpcQuest());
        npc.setNpcHitPoints(npcUpdate.getNpcHitPoints());
        npc.setNpcMeetingQuantity(npcUpdate.getNpcMeetingQuantity());
        npc.setNpcSoulsForKill(npcUpdate.getNpcSoulsForKill());
        npc.setNpcEstusQuantity(npcUpdate.getNpcEstusQuantity());
        npc.setNpcSomeInformation(npcUpdate.getNpcSomeInformation());
    }
}
