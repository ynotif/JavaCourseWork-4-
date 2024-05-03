package student.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name = "weapons")
@Entity(name = "weapons")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weapons {
    @Id
    @Column(name = "weaponId")
    @GeneratedValue(generator = "weapon_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "weapon_id_seq", sequenceName = "weapon_id_seq", allocationSize = 1, initialValue = 1)
    private Long weaponId;

    @Column(name = "weaponName")
    private String weaponName;

    @Column(name = "weaponType")
    private String weaponType;

    @Column(name = "weaponSpellManaCost")
    private int weaponSpellManaCost;

    @Column(name = "weaponSpellDoes")
    private String weaponSpellDoes;

    @Column(name = "weaponPhysicalNormalDamage")
    private double weaponPhysicalNormalDamage;

    @Column(name = "weaponPhysicalCrushingDamage")
    private double weaponPhysicalCrushingDamage;

    @Column(name = "weaponPhysicalSlashingDamage")
    private double weaponPhysicalSlashingDamage;

    @Column(name = "weaponPhysicalPiercingDamage")
    private double weaponPhysicalPiercingDamage;

    @Column(name = "weaponMagicalDamage")
    private double weaponMagicalDamage;

    @Column(name = "weaponFireDamage")
    private double weaponFireDamage;

    @Column(name = "weaponLightningDamage")
    private double weaponLightningDamage;

    @Column(name = "weaponDarknessDamage")
    private double weaponDarknessDamage;

    @Column(name = "weaponBleedingEffect")
    private double weaponBleedingEffect;

    @Column(name = "weaponPoisonEffect")
    private double weaponPoisonEffect;

    @Column(name = "weaponFrostEffect")
    private double weaponFrostEffect;

    @Column(name = "weaponCriticalDamage")
    private int weaponCriticalDamage;

    @Column(name = "weaponPhysicaDamagelBlock")
    private double weaponPhysicalDamageBlock;

    @Column(name = "weaponMagicalDamageBlock")
    private double weaponMagicalDamageBlock;

    @Column(name = "weaponFireDamageBlock")
    private double weaponFireDamageBlock;

    @Column(name = "weaponLightingDamagelBlock")
    private double weaponLightingDamageBlock;

    @Column(name = "weaponDarknessDamageBlock")
    private double weaponDarknessDamageBlock;

    @Column(name = "weaponBalance")
    private int weaponBalance; //Баланс (устойчивость)

    @Column(name = "weaponStrengthScaling")
    private String weaponStrengthScaling; //Скейл силы

    @Column(name = "weaponAgilityScaling")
    private String weaponAgilityScaling; //Скейл агилы

    @Column(name = "weaponIntellectScaling")
    private String weaponIntellectScaling; //Скейл интеллекта

    @Column(name = "weaponFaithScaling")
    private String weaponFaithScaling; //Скейл веры

    @Column(name = "weaponStrengthRequired")
    private int weaponStrengthRequired; //Необходимая сила

    @Column(name = "weaponAgilityRequired")
    private int weaponAgilityRequired; //Необходимая ловкость

    @Column(name = "weaponIntellectRequired")
    private int weaponIntellectRequired; //Необходимый интеллект

    @Column(name = "weaponFaithRequired")
    private int weaponFaithRequired; //Необходимая вера

    @Column(name = "weaponEndurance")
    private int weaponEndurance; //Прочность

    @Column(name = "weaponSaleCost")
    private int weaponSaleCost;

    @Column(name = "weaponBuyCost")
    private int weaponBuyCost;

    @Column(name = "weaponsSoulForCraft")
    private String weaponsSoulForCraft; // Душа необходимая для крафта уникального оружия

    @Column(name = "weaponSomeInformation")
    private String weaponSomeInformation;

    @ManyToMany
    @JsonIgnore
    private Set<Units> units;
}
