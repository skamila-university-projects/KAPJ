package skamila.kapj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skamila.kapj.dao.VisitRepository;
import skamila.kapj.domain.Visit;

@Service("visitService")
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Transactional
    @Override
    public void addVisit(Visit visit) {
        visitRepository.save(visit);
    }

}
