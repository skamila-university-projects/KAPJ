package skamila.kapj.service;

import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.Visit;

import java.util.List;

public interface VisitService {

    void addVisit(Visit visit);

    List<Visit> listVisits();

    List<Visit> findByPatient(AppUser patient);

    List<Visit> findByDoctor(AppUser patient);
}
