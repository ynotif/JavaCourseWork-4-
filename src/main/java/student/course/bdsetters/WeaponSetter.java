package student.course.bdsetters;

import lombok.experimental.UtilityClass;
import student.course.model.Weapons;

@UtilityClass
public class WeaponSetter {
    public void update(Weapons weapon, Weapons weaponUpdate, Long id) {
        weapon.setWeaponId(id);
        weapon.setWeaponName(weaponUpdate.getWeaponName());
        weapon.setWeaponType(weaponUpdate.getWeaponType());
        weapon.setWeaponSpellManaCost(weaponUpdate.getWeaponSpellManaCost());
        weapon.setWeaponSpellDoes(weaponUpdate.getWeaponSpellDoes());
        weapon.setWeaponPhysicalNormalDamage(weaponUpdate.getWeaponPhysicalNormalDamage());
        weapon.setWeaponPhysicalCrushingDamage(weaponUpdate.getWeaponPhysicalCrushingDamage());
        weapon.setWeaponPhysicalSlashingDamage(weaponUpdate.getWeaponPhysicalSlashingDamage());
        weapon.setWeaponPhysicalPiercingDamage(weaponUpdate.getWeaponPhysicalPiercingDamage());
        weapon.setWeaponMagicalDamage(weaponUpdate.getWeaponMagicalDamage());
        weapon.setWeaponFireDamage(weaponUpdate.getWeaponFireDamage());
        weapon.setWeaponLightningDamage(weaponUpdate.getWeaponLightningDamage());
        weapon.setWeaponDarknessDamage(weaponUpdate.getWeaponDarknessDamage());
        weapon.setWeaponBleedingEffect(weaponUpdate.getWeaponBleedingEffect());
        weapon.setWeaponPoisonEffect(weaponUpdate.getWeaponPoisonEffect());
        weapon.setWeaponFrostEffect(weaponUpdate.getWeaponFrostEffect());
        weapon.setWeaponCriticalDamage(weaponUpdate.getWeaponCriticalDamage());
        weapon.setWeaponPhysicalDamageBlock(weaponUpdate.getWeaponPhysicalDamageBlock());
        weapon.setWeaponMagicalDamageBlock(weaponUpdate.getWeaponMagicalDamageBlock());
        weapon.setWeaponFireDamageBlock(weaponUpdate.getWeaponFireDamageBlock());
        weapon.setWeaponLightingDamageBlock(weaponUpdate.getWeaponLightingDamageBlock());
        weapon.setWeaponDarknessDamageBlock(weaponUpdate.getWeaponDarknessDamageBlock());
        weapon.setWeaponBalance(weaponUpdate.getWeaponBalance());
        weapon.setWeaponStrengthScaling(weaponUpdate.getWeaponStrengthScaling());
        weapon.setWeaponAgilityScaling(weaponUpdate.getWeaponAgilityScaling());
        weapon.setWeaponIntellectScaling(weaponUpdate.getWeaponIntellectScaling());
        weapon.setWeaponFaithScaling(weaponUpdate.getWeaponFaithScaling());
        weapon.setWeaponStrengthRequired(weaponUpdate.getWeaponStrengthRequired());
        weapon.setWeaponAgilityRequired(weaponUpdate.getWeaponAgilityRequired());
        weapon.setWeaponIntellectRequired(weaponUpdate.getWeaponIntellectRequired());
        weapon.setWeaponFaithRequired(weaponUpdate.getWeaponFaithRequired());
        weapon.setWeaponEndurance(weaponUpdate.getWeaponEndurance());
        weapon.setWeaponSaleCost(weaponUpdate.getWeaponSaleCost());
        weapon.setWeaponBuyCost(weaponUpdate.getWeaponBuyCost());
        weapon.setWeaponSomeInformation(weaponUpdate.getWeaponSomeInformation());
    }
}
