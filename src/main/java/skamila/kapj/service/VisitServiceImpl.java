package skamila.kapj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skamila.kapj.dao.VisitRepository;
import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.Visit;

import java.util.List;

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

    @Override
    public List<Visit> listVisits() {
        return visitRepository.findAll();
    }

    @Override
    public List<Visit> findByPatient(AppUser patient) {
        return visitRepository.findByPatient(patient);
    }

    @Override
    public List<Visit> findByDoctor(AppUser doctor) {
        return visitRepository.findByDoctor(doctor);
    }

    @Override
    public void cancelVisit(long visitId) {
        Visit visit = visitRepository.findById(visitId);
        visit.setCanceled(true);
        visitRepository.save(visit);
    }

    @Override
    public void confirmVisit(long visitId) {
        Visit visit = visitRepository.findById(visitId);
        visit.setConfirmed(true);
        visit.setBillAvailable(true);
        visitRepository.save(visit);
    }

}
