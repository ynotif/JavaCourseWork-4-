package student.course.bdsetters;

import student.course.model.Souls;

public class SoulSetter {
    public void update(Souls soul, Souls soulUpdate) {
        soul.setSoulName(soulUpdate.getSoulName());
        soul.setSoulEqualSoulCurrency(soulUpdate.getSoulEqualSoulCurrency());
        soul.setSoulSomeInformation(soulUpdate.getSoulSomeInformation());
    }
}
