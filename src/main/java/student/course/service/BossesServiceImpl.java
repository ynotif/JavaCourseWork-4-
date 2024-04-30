package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.course.model.Bosses;
import student.course.repository.BossesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BossesServiceImpl implements BossesService {

    private BossesRepository bossesRepository;
    @Override
    public Bosses createBosse(Bosses bosses) {
        return bossesRepository.save(bosses);
    }

    @Override
    public List<Bosses> getAllBosses() {
        return bossesRepository.findAll();
    }

    @Override
    public Optional<Bosses> getBosseById(Long id) {
        return bossesRepository.findById(id);
    }
}
