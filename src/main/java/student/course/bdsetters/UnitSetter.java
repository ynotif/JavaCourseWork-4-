package student.course.bdsetters;

import lombok.experimental.UtilityClass;
import student.course.model.Units;

@UtilityClass
public class UnitSetter {
    public void update(Units unit, Units unitUpdate, Long id) {
        unit.setUnitId(id);
        unit.setUnitName(unitUpdate.getUnitName());
        unit.setUnitHitPoints(unitUpdate.getUnitHitPoints());
        unit.setUnitPhysicalNormalDamageResistance(unitUpdate.getUnitPhysicalNormalDamageResistance());
        unit.setUnitPhysicalCrushingDamageResistance(unitUpdate.getUnitPhysicalCrushingDamageResistance());
        unit.setUnitPhysicalSlashingDamageResistance(unitUpdate.getUnitPhysicalSlashingDamageResistance());
        unit.setUnitPhysicalPiercingDamageResistance(unitUpdate.getUnitPhysicalPiercingDamageResistance());
        unit.setUnitMagicalDamageResistance(unitUpdate.getUnitMagicalDamageResistance());
        unit.setUnitFireDamageResistance(unitUpdate.getUnitFireDamageResistance());
        unit.setUnitLightningDamageResistance(unitUpdate.getUnitLightningDamageResistance());
        unit.setUnitDarknessDamageResistance(unitUpdate.getUnitDarknessDamageResistance());
        unit.setUnitBleedingResistance(unitUpdate.getUnitBleedingResistance());
        unit.setUnitPoisonResistance(unitUpdate.getUnitPoisonResistance());
        unit.setUnitFrostResistance(unitUpdate.getUnitFrostResistance());
        unit.setUnitPhysicalNormalDamage(unitUpdate.getUnitPhysicalNormalDamage());
        unit.setUnitPhysicalCrushingDamage(unitUpdate.getUnitPhysicalCrushingDamage());
        unit.setUnitPhysicalSlashingDamage(unitUpdate.getUnitPhysicalSlashingDamage());
        unit.setUnitPhysicalPiercingDamage(unitUpdate.getUnitPhysicalPiercingDamage());
        unit.setUnitMagicalDamage(unitUpdate.getUnitMagicalDamage());
        unit.setUnitFireDamage(unitUpdate.getUnitFireDamage());
        unit.setUnitLightningDamage(unitUpdate.getUnitLightningDamage());
        unit.setUnitDarknessDamage(unitUpdate.getUnitDarknessDamage());
        unit.setUnitBleedingEffect(unitUpdate.getUnitBleedingEffect());
        unit.setUnitPoisonEffect(unitUpdate.getUnitPoisonEffect());
        unit.setUnitFrostEffect(unitUpdate.getUnitFrostEffect());
        unit.setUnitCurseEffect(unitUpdate.getUnitCurseEffect());
        unit.setUnitSoulsQuantity(unitUpdate.getUnitSoulsQuantity());
        unit.setUnitSomeInformation(unitUpdate.getUnitSomeInformation());
    }
}
