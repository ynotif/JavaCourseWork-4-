package student.course.service;

import student.course.model.Armors;

import java.util.List;
import java.util.Optional;

public interface ArmorsService {

    Armors createArmor(Armors armors);

    List<Armors> getAllArmors();

    Optional<Armors> getArmorById(Long id);

    void updateArmor(Armors armors);

    void deleteArmorById(Long id);
}
