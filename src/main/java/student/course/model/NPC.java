package student.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "NPC_weapons",
            joinColumns = @JoinColumn(name = "npcId"),
            inverseJoinColumns = @JoinColumn(name = "weaponId")
    )
    private Set<Weapons> weapon;

    @ManyToMany
    @JoinTable(
            name = "NPC_armors",
            joinColumns = @JoinColumn(name = "npcId"),
            inverseJoinColumns = @JoinColumn(name = "armorId")
    )
    private Set<Armors> armor;

    @ManyToMany
    @JoinTable(
            name = "NPC_magic",
            joinColumns = @JoinColumn(name = "npcId"),
            inverseJoinColumns = @JoinColumn(name = "magicId")
    )
    private Set<Magics> magic;

    @ManyToMany
    @JoinTable(
            name = "NPC_souls",
            joinColumns = @JoinColumn(name = "npcId"),
            inverseJoinColumns = @JoinColumn(name = "soulId")
    )
    private Set<Souls> soul;

    @Column(name = "npcEstusQuantity")
    private int npcEstusQuantity;

    @Column(name = "npcSomeInformation")
    private String npcSomeInformation;

    @ManyToMany
    @JsonIgnore
    private Set<Locations> locations;

}
