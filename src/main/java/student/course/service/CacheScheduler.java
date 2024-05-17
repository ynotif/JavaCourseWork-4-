package student.course.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheScheduler {

    private final EvictCache evictCache;

    @Scheduled(fixedDelay = 60000L)
    public void cleanCache() {
        evictCache.evictCache();
        log.info("cache cleaned");
    }


}
