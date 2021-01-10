package skamila.kapj.service;

import org.springframework.transaction.annotation.Transactional;
import skamila.kapj.domain.Visit;

public interface VisitService {
    @Transactional
    void addVisit(Visit visit);
}
