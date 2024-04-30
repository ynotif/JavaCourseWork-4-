package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Units;
import student.course.repository.UnitsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitsServiceImpl implements UnitsService {

    private final UnitsRepository unitsRepository;

    @Override
    public Units createUnit(Units units) {
        return unitsRepository.save(units);
    }

    @Override
    public List<Units> getAllUnits() {
        return unitsRepository.findAll();
    }

    @Override
    public Optional<Units> getUnitById(Long id) {
        return unitsRepository.findById(id);
    }
}
