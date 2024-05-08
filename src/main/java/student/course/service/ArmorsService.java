package student.course.service;

import student.course.exceptions.ArmorNotFoundException;
import student.course.model.Armors;

import java.util.List;
import java.util.Optional;

public interface ArmorsService {

    Armors createArmor(Armors armors);

    List<Armors> getAllArmors();

    Optional<Armors> getArmorById(Long id) throws ArmorNotFoundException;

    void updateArmor(Armors armors, Long id) throws ArmorNotFoundException;

    void deleteArmorById(Long id) throws ArmorNotFoundException;
}
