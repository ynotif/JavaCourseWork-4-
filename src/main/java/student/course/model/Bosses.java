package student.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "bosses")
@Table(name = "bosses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bosses {

    @Id
    @Column(name = "bossId")
    @GeneratedValue(generator = "boss_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "boss_id_seq", sequenceName = "boss_id_seq", initialValue = 1, allocationSize = 1)
    private Long bossId;

    @Column(name = "bossName")
    private String bossName;

    @Column(name = "bossHitPointsSum")
    private double bossHitPointsSum;

    //FP - First Phase
    @Column(name = "bossPhysicalNormalDamageResistanceFP")
    private double bossPhysicalNormalDamageResistanceFP;

    @Column(name = "bossPhysicalCrushingDamageResistanceFP")
    private double bossPhysicalCrushingDamageResistanceFP;

    @Column(name = "bossPhysicalSlashingDamageResistanceFP")
    private double bossPhysicalSlashingDamageResistanceFP;

    @Column(name = "bossPhysicalPiercingDamageResistanceFP")
    private double bossPhysicalPiercingDamageResistanceFP;

    @Column(name = "bossMagicalDamageResistanceFP")
    private double bossMagicalDamageResistanceFP;

    @Column(name = "bossFireDamageResistanceFP")
    private double bossFireDamageResistanceFP;

    @Column(name = "bossLightningDamageResistanceFP")
    private double bossLightningDamageResistanceFP;

    @Column(name = "bossDarknessDamageResistanceFP")
    private double bossDarknessDamageResistanceFP;

    @Column(name = "bossBleedingResistanceFP")
    private double bossBleedingResistanceFP;

    @Column(name = "bossPoisonResistanceFP")
    private double bossPoisonResistanceFP;

    @Column(name = "bossFrostResistanceFP")
    private double bossFrostResistanceFP;

    @Column(name = "bossPhysicalNormalDamageFP")
    private double bossPhysicalNormalDamageFP;

    @Column(name = "bossPhysicalCrushingDamageFP")
    private double bossPhysicalCrushingDamageFP;

    @Column(name = "bossPhysicalSlashingDamageFP")
    private double bossPhysicalSlashingDamageFP;

    @Column(name = "bossPhysicalPiercingDamageFP")
    private double bossPhysicalPiercingDamageFP;

    @Column(name = "bossMagicalDamageFP")
    private double bossMagicalDamageFP;

    @Column(name = "bossFireDamageFP")
    private double bossFireDamageFP;

    @Column(name = "bossLightningDamageFP")
    private double bossLightningDamageFP;

    @Column(name = "bossDarknessDamageFP")
    private double bossDarknessDamageFP;

    @Column(name = "bossBleedingEffectFP")
    private double bossBleedingEffectFP;

    @Column(name = "bossPoisonEffectFP")
    private double bossPoisonEffectFP;

    @Column(name = "bossFrostEffectFP")
    private double bossFrostEffectFP;

    @Column(name = "bossHitPointsFP")
    private double bossHitPointsFP;

    //SP - Second Phase
    @Column(name = "bossPhysicalNormalDamageResistanceSP")
    private double bossPhysicalNormalDamageResistanceSP;

    @Column(name = "bossPhysicalCrushingDamageResistanceSP")
    private double bossPhysicalCrushingDamageResistanceSP;

    @Column(name = "bossPhysicalSlashingDamageResistanceSP")
    private double bossPhysicalSlashingDamageResistanceSP;

    @Column(name = "bossPhysicalPiercingDamageResistanceSP")
    private double bossPhysicalPiercingDamageResistanceSP;

    @Column(name = "bossMagicalDamageResistanceSP")
    private double bossMagicalDamageResistanceSP;

    @Column(name = "bossFireDamageResistanceSP")
    private double bossFireDamageResistanceSP;

    @Column(name = "bossLightningDamageResistanceSP")
    private double bossLightningDamageResistanceSP;

    @Column(name = "bossDarknessDamageResistanceSP")
    private double bossDarknessDamageResistanceSP;

    @Column(name = "bossBleedingResistanceSP")
    private double bossBleedingResistanceSP;

    @Column(name = "bossPoisonResistanceSP")
    private double bossPoisonResistanceSP;

    @Column(name = "bossFrostResistanceSP")
    private double bossFrostResistanceSP;

    @Column(name = "bossPhysicalNormalDamageSP")
    private double bossPhysicalNormalDamageSP;

    @Column(name = "bossPhysicalCrushingDamageSP")
    private double bossPhysicalCrushingDamageSP;

    @Column(name = "bossPhysicalSlashingDamageSP")
    private double bossPhysicalSlashingDamageSP;

    @Column(name = "bossPhysicalPiercingDamageSP")
    private double bossPhysicalPiercingDamageSP;

    @Column(name = "bossMagicalDamageSP")
    private double bossMagicalDamageSP;

    @Column(name = "bossFireDamageSP")
    private double bossFireDamageSP;

    @Column(name = "bossLightningDamageSP")
    private double bossLightningDamageSP;

    @Column(name = "bossDarknessDamageSP")
    private double bossDarknessDamageSP;

    @Column(name = "bossBleedingEffectSP")
    private double bossBleedingEffectSP;

    @Column(name = "bossPoisonEffectSP")
    private double bossPoisonEffectSP;

    @Column(name = "bossFrostEffectSP")
    private double bossFrostEffectSP;

    @Column(name = "bossHitPointsSP")
    private double bossHitPointsSP;

    //TP - Third Phase
    @Column(name = "bossPhysicalNormalDamageResistanceTP")
    private double bossPhysicalNormalDamageResistanceTP;

    @Column(name = "bossPhysicalCrushingDamageResistanceTP")
    private double bossPhysicalCrushingDamageResistanceTP;

    @Column(name = "bossPhysicalSlashingDamageResistanceTP")
    private double bossPhysicalSlashingDamageResistanceTP;

    @Column(name = "bossPhysicalPiercingDamageResistanceTP")
    private double bossPhysicalPiercingDamageResistanceTP;

    @Column(name = "bossMagicalDamageResistanceTP")
    private double bossMagicalDamageResistanceTP;

    @Column(name = "bossFireDamageResistanceTP")
    private double bossFireDamageResistanceTP;

    @Column(name = "bossLightningDamageResistanceTP")
    private double bossLightningDamageResistanceTP;

    @Column(name = "bossDarknessDamageResistanceTP")
    private double bossDarknessDamageResistanceTP;

    @Column(name = "bossBleedingResistanceTP")
    private double bossBleedingResistanceTP;

    @Column(name = "bossPoisonResistanceTP")
    private double bossPoisonResistanceTP;

    @Column(name = "bossFrostResistanceTP")
    private double bossFrostResistanceTP;

    @Column(name = "bossPhysicalNormalDamageTP")
    private double bossPhysicalNormalDamageTP;

    @Column(name = "bossPhysicalCrushingDamageTP")
    private double bossPhysicalCrushingDamageTP;

    @Column(name = "bossPhysicalSlashingDamageTP")
    private double bossPhysicalSlashingDamageTP;

    @Column(name = "bossPhysicalPiercingDamageTP")
    private double bossPhysicalPiercingDamageTP;

    @Column(name = "bossMagicalDamageTP")
    private double bossMagicalDamageTP;

    @Column(name = "bossFireDamageTP")
    private double bossFireDamageTP;

    @Column(name = "bossLightningDamageTP")
    private double bossLightningDamageTP;

    @Column(name = "bossDarknessDamageTP")
    private double bossDarknessDamageTP;

    @Column(name = "bossBleedingEffectTP")
    private double bossBleedingEffectTP;

    @Column(name = "bossPoisonEffectTP")
    private double bossPoisonEffectTP;

    @Column(name = "bossFrostEffectTP")
    private double bossFrostEffectTP;

    @Column(name = "bossHitPointsTP")
    private double bossHitPointsTP;

    @Column(name = "bossSoulsQuantity")
    private int bossSoulsQuantity;

    //Для дропа
    @JoinColumn(name = "armorId")
    @ManyToOne
    private Armors armor;

    @JoinColumn(name = "weaponId")
    @ManyToOne
    private Weapons weapon;

    @JoinColumn(name = "soulId")
    @ManyToOne
    private Souls soul;

    @Column(name = "bossHistory")
    private String bossHistory;

    @JsonIgnore
    @ManyToMany
    private Set<Locations> locations;

}
