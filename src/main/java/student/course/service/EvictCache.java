package student.course.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class EvictCache {

    @CacheEvict(cacheNames = {"Armors", "Bosse", "Locations", "Magics", "NPC", "Souls", "Units", "Weapons"}, allEntries = true)
    public void evictCache(){

    }
}
