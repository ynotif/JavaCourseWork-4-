package student.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Table(name = "weapons")
@Entity(name = "weapons")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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

    @Column(name = "weaponSomeInformation")
    private String weaponSomeInformation;

    @ManyToMany
    @JoinTable(
            name = "weapons_souls",
            joinColumns = @JoinColumn(name = "weaponId"),
            inverseJoinColumns = @JoinColumn(name = "soulId")
    )
    private Set<Souls> soul; // Душа необходимая для крафта уникального оружия

    @ManyToMany
    @JsonIgnore
    private Set<Units> units;

    @ManyToMany
    @JsonIgnore
    private Set<Locations> locations;

    @ManyToMany
    @JsonIgnore
    private Set<Bosses> bosses;

    @ManyToMany
    @JsonIgnore
    private Set<NPC> npc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapons weapons = (Weapons) o;
        return Double.compare(weapons.weaponPhysicalNormalDamage, weaponPhysicalNormalDamage) == 0 &&
                Double.compare(weapons.weaponPhysicalCrushingDamage, weaponPhysicalCrushingDamage) == 0 &&
                Double.compare(weapons.weaponPhysicalSlashingDamage, weaponPhysicalSlashingDamage) == 0 &&
                Double.compare(weapons.weaponPhysicalPiercingDamage, weaponPhysicalPiercingDamage) == 0 &&
                Double.compare(weapons.weaponMagicalDamage, weaponMagicalDamage) == 0 &&
                Double.compare(weapons.weaponFireDamage, weaponFireDamage) == 0 &&
                Double.compare(weapons.weaponLightningDamage, weaponLightningDamage) == 0 &&
                Double.compare(weapons.weaponDarknessDamage, weaponDarknessDamage) == 0 &&
                Double.compare(weapons.weaponBleedingEffect, weaponBleedingEffect) == 0 &&
                Double.compare(weapons.weaponPoisonEffect, weaponPoisonEffect) == 0 &&
                Double.compare(weapons.weaponFrostEffect, weaponFrostEffect) == 0 &&
                weaponCriticalDamage == weapons.weaponCriticalDamage &&
                Double.compare(weapons.weaponPhysicalDamageBlock, weaponPhysicalDamageBlock) == 0 &&
                Double.compare(weapons.weaponMagicalDamageBlock, weaponMagicalDamageBlock) == 0 &&
                Double.compare(weapons.weaponFireDamageBlock, weaponFireDamageBlock) == 0 &&
                Double.compare(weapons.weaponLightingDamageBlock, weaponLightingDamageBlock) == 0 &&
                Double.compare(weapons.weaponDarknessDamageBlock, weaponDarknessDamageBlock) == 0 &&
                weaponBalance == weapons.weaponBalance &&
                weaponStrengthRequired == weapons.weaponStrengthRequired &&
                weaponAgilityRequired == weapons.weaponAgilityRequired &&
                weaponIntellectRequired == weapons.weaponIntellectRequired &&
                weaponFaithRequired == weapons.weaponFaithRequired &&
                weaponEndurance == weapons.weaponEndurance &&
                weaponSaleCost == weapons.weaponSaleCost &&
                weaponBuyCost == weapons.weaponBuyCost &&
                Objects.equals(weaponName, weapons.weaponName) &&
                Objects.equals(weaponType, weapons.weaponType) &&
                Objects.equals(weaponSpellManaCost, weapons.weaponSpellManaCost) &&
                Objects.equals(weaponSpellDoes, weapons.weaponSpellDoes) &&
                Objects.equals(weaponStrengthScaling, weapons.weaponStrengthScaling) &&
                Objects.equals(weaponAgilityScaling, weapons.weaponAgilityScaling) &&
                Objects.equals(weaponIntellectScaling, weapons.weaponIntellectScaling) &&
                Objects.equals(weaponFaithScaling, weapons.weaponFaithScaling) &&
                Objects.equals(weaponSomeInformation, weapons.weaponSomeInformation) &&
                Objects.equals(soul, weapons.soul) &&
                Objects.equals(units, weapons.units) &&
                Objects.equals(locations, weapons.locations) &&
                Objects.equals(bosses, weapons.bosses) &&
                Objects.equals(npc, weapons.npc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponId, weaponName, weaponType, weaponSpellManaCost
                , weaponSpellDoes, weaponPhysicalNormalDamage, weaponPhysicalCrushingDamage
                , weaponPhysicalSlashingDamage, weaponPhysicalPiercingDamage, weaponMagicalDamage, weaponFireDamage, weaponLightningDamage, weaponDarknessDamage, weaponBleedingEffect, weaponPoisonEffect, weaponFrostEffect, weaponCriticalDamage, weaponPhysicalDamageBlock, weaponMagicalDamageBlock, weaponFireDamageBlock, weaponLightingDamageBlock, weaponDarknessDamageBlock, weaponBalance, weaponStrengthScaling, weaponAgilityScaling, weaponIntellectScaling, weaponFaithScaling, weaponStrengthRequired, weaponAgilityRequired, weaponIntellectRequired, weaponFaithRequired, weaponEndurance, weaponSaleCost, weaponBuyCost, weaponSomeInformation, soul, units, locations, bosses, npc);
    }

}
