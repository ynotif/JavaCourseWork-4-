package student.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "npc")
@Entity(name = "npc")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NPC {

    @Id
    @Column(name = "npcId")
    @GeneratedValue(generator = "npc_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "npc_id_seq", sequenceName = "npc_id_seq", initialValue = 1, allocationSize = 1)
    private Long npcId;

    @Column(name = "npcName")
    private String npcName;

    @Column(name = "npcQuest")
    private String npcQuest;

    @Column(name = "npcHitPoints")
    private double npcHitPoints;

    @Column(name = "npcMeetingQuantity")
    private int npcMeetingQuantity;

    @Column(name = "npcSoulsForKill")
    private int npcSoulsForKill;

    @ManyToOne
    @JoinColumn(name = "weaponId")
    private Weapons weapon;

    @ManyToOne
    @JoinColumn(name = "armorId")
    private Armors armor;

    @ManyToOne
    @JoinColumn(name = "magicId")
    private Magics magic;

    @Column(name = "npcEstusQuantity")
    private int npcEstusQuantity;

    @Column(name = "npcSomeInformation")
    private String npcSomeInformation;



}
