package skamila.kapj.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import skamila.kapj.domain.Visit;

import java.util.List;

@Service("schedulingServiceImpl")
public class SchedulingServiceImpl implements SchedulingService {

    private final VisitService visitService;
    private final int frequency = 60000; // 2 min (120000 ms) w celu prezentacji

    public SchedulingServiceImpl(VisitService visitService) {
        this.visitService = visitService;
    }

    @Scheduled(fixedDelay = 30000) // co pół minuty
    public void scheduleFixedDelayTask() {
        long currentTime = System.currentTimeMillis();
        List<Visit> visits = visitService.listVisits();
        for (Visit visit : visits) {
            if (visit.getTimestamp() != null && !visit.isConfirmed() && !visit.isCanceled()
                    && currentTime - visit.getTimestamp().getTime() > frequency) {
                visitService.cancelVisit(visit.getId());
            }
        }
    }

}
