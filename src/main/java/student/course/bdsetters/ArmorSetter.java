package student.course.bdsetters;

import lombok.experimental.UtilityClass;
import student.course.model.Armors;

@UtilityClass // Либо так, либо static можно
public class ArmorSetter {
    public void update(Armors armor, Armors armorUpdate, Long id) { // Сначала старую, потом новую
        armor.setArmorId(id);
        armor.setArmorName(armorUpdate.getArmorName());
        armor.setArmorPhysicalNormalDamageResistance(armorUpdate.getArmorPhysicalNormalDamageResistance());
        armor.setArmorPhysicalCrushingDamageResistance(armorUpdate.getArmorPhysicalCrushingDamageResistance());
        armor.setArmorPhysicalSlashingDamageResistance(armorUpdate.getArmorPhysicalSlashingDamageResistance());
        armor.setArmorPhysicalPiercingDamageResistance(armorUpdate.getArmorPhysicalPiercingDamageResistance());
        armor.setArmorMagicalDamageResistance(armorUpdate.getArmorMagicalDamageResistance());
        armor.setArmorFireDamageResistance(armorUpdate.getArmorFireDamageResistance());
        armor.setArmorLightningDamageResistance(armorUpdate.getArmorLightningDamageResistance());
        armor.setUnitDarknessDamageResistance(armorUpdate.getUnitDarknessDamageResistance());
        armor.setArmorBalance(armorUpdate.getArmorBalance());
        armor.setArmorWeight(armorUpdate.getArmorWeight());
        armor.setArmorEndurance(armorUpdate.getArmorEndurance());
        armor.setArmorSaleCost(armorUpdate.getArmorSaleCost());
        armor.setArmorBuyCost(armorUpdate.getArmorBuyCost());
    }
}
