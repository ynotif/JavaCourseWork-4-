package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Magics;
import student.course.repository.MagicsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MagicsServiceImpl implements MagicsService {

    private final MagicsRepository magicsRepository;


    @Override
    public Magics createMagic(Magics magics) {
        return magicsRepository.save(magics);
    }

    @Override
    public List<Magics> getAllMagics() {
        return magicsRepository.findAll();
    }

    @Override
    public Optional<Magics> getMagicById(Long id) {
        return magicsRepository.findById(id);
    }
}
