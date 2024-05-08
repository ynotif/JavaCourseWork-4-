package student.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheScheduler {

    private final EvictCache evictCache;

    @Scheduled(fixedDelay = 60000L)
    public void cleanCache() {
        evictCache.evictCache();
    }


}
