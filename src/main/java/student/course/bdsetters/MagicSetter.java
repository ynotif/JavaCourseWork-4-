package student.course.bdsetters;

import student.course.model.Magics;

public class MagicSetter {
    public static void update(Magics magic, Magics magicUpdate, Long id) {
        magic.setMagicId(id);
        magic.setMagicName(magicUpdate.getMagicName());
        magic.setMagicManaCost(magicUpdate.getMagicManaCost());
        magic.setMagicDamage(magicUpdate.getMagicDamage());
        magic.setMagicPhysicalDamage(magicUpdate.getMagicPhysicalDamage());
        magic.setMagicFireDamage(magicUpdate.getMagicFireDamage());
        magic.setMagicLightningDamage(magicUpdate.getMagicLightningDamage());
        magic.setMagicDarknessDamage(magicUpdate.getMagicDarknessDamage());
        magic.setMagicHeal(magicUpdate.getMagicHeal());
        magic.setMagicCastTime(magicUpdate.getMagicCastTime());
        magic.setMagicCastRange(magicUpdate.getMagicCastRange());
        magic.setMagicIntellectRequired(magicUpdate.getMagicIntellectRequired());
        magic.setMagicFaithRequired(magicUpdate.getMagicFaithRequired());
        magic.setMagicNumberCells(magicUpdate.getMagicNumberCells());
        magic.setMagicBuyCost(magicUpdate.getMagicBuyCost());
        magic.setMagicSoulCraft(magicUpdate.getMagicSoulCraft());
        magic.setMagicSomeInformation(magicUpdate.getMagicSomeInformation());
        magic.setNpc(magicUpdate.getNpc());
        magic.setLocations(magicUpdate.getLocations());
    }
}
