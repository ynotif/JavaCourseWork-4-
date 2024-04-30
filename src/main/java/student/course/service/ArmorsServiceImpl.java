package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Armors;
import student.course.repository.ArmorsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArmorsServiceImpl implements ArmorsService {

    private final ArmorsRepository armorsRepository;

    @Override
    public Armors createArmor(Armors armors) {
        return armorsRepository.save(armors);
    }

    @Override
    public List<Armors> getAllArmors() {
        return armorsRepository.findAll();
    }

    @Override
    public Optional<Armors> getArmorById(Long id) {
        return armorsRepository.findById(id);
    }
}
