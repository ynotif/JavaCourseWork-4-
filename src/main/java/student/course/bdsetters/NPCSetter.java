package student.course.bdsetters;

import student.course.model.NPC;

public class NPCSetter {
    public void update(NPC npc, NPC npcUpdate) {
        npc.setNpcName(npcUpdate.getNpcName());
        npc.setNpcQuest(npcUpdate.getNpcQuest());
        npc.setNpcHitPoints(npcUpdate.getNpcHitPoints());
        npc.setNpcMeetingQuantity(npcUpdate.getNpcMeetingQuantity());
        npc.setNpcSoulsForKill(npcUpdate.getNpcSoulsForKill());
        npc.setNpcEstusQuantity(npcUpdate.getNpcEstusQuantity());
        npc.setNpcSomeInformation(npcUpdate.getNpcSomeInformation());
    }
}
