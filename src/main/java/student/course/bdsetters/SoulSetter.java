package student.course.bdsetters;

import lombok.experimental.UtilityClass;
import student.course.model.Souls;

@UtilityClass
public class SoulSetter {
    public void update(Souls soul, Souls soulUpdate, Long id) {
        soul.setSoulId(id);
        soul.setSoulName(soulUpdate.getSoulName());
        soul.setSoulEqualSoulCurrency(soulUpdate.getSoulEqualSoulCurrency());
        soul.setSoulSomeInformation(soulUpdate.getSoulSomeInformation());
    }
}
