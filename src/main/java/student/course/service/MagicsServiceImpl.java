package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import student.course.bdsetters.MagicSetter;
import student.course.exceptions.MagicNotFoundException;
import student.course.model.Magics;
import student.course.repository.MagicsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MagicsServiceImpl implements MagicsService {

    private final MagicsRepository magicsRepository;

    @CacheEvict(cacheNames = "Magics", allEntries = true)
    @Override
    public Magics createMagic(Magics magics) {
        return magicsRepository.save(magics);
    }

    @Cacheable(cacheNames = "Magics")
    @Override
    public List<Magics> getAllMagics() {
        return magicsRepository.findAll();
    }

    @CacheEvict(cacheNames = "Magics", allEntries = true)
    @Override
    public Optional<Magics> getMagicById(Long id) throws MagicNotFoundException {
        Optional<Magics> optionalMagic = magicsRepository.findById(id);
        if (optionalMagic.isPresent()){
            return optionalMagic;
        }
        else{
            throw new MagicNotFoundException(id);
        }
    }

    @CacheEvict(cacheNames = "Magics", allEntries = true)
    @Override
    public void updateMagic(Magics updateMagic, Long id) throws MagicNotFoundException {
        Magics magic = magicsRepository.findById(id)
                .orElseThrow(() -> new MagicNotFoundException(id));

        MagicSetter.update(magic, updateMagic, id);

        magicsRepository.save(magic);
    }

    @CacheEvict(cacheNames = "Magics", allEntries = true)
    @Override
    public void deleteMagicById(Long id) throws MagicNotFoundException {
        Optional<Magics> optionalMagics = getMagicById(id);
        optionalMagics.ifPresent(magicsRepository::delete);
    }
}
