package skamila.kapj.controller.utils;

import skamila.kapj.domain.AppUser;
import skamila.kapj.domain.Visit;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class VisitBuilder {

    private final Visit visit = new Visit();

    public Visit build() {
        return visit;
    }

    public VisitBuilder withDoctor(AppUser doctor) {
        visit.setDoctor(doctor);
        return this;
    }

    public VisitBuilder withPatient(AppUser patient) {
        visit.setPatient(patient);
        return this;
    }

    public VisitBuilder withTime(LocalDateTime time) {
        visit.setTime(time);
        return this;
    }

    public VisitBuilder withPrice(int price) {
        visit.setPrice(price);
        return this;
    }

    public VisitBuilder withConfirmed(boolean confirmed) {
        visit.setConfirmed(confirmed);
        return this;
    }

    public VisitBuilder withCanceled(boolean canceled) {
        visit.setConfirmed(canceled);
        return this;
    }

    public VisitBuilder withBillAvailable(boolean billAvailable) {
        visit.setConfirmed(billAvailable);
        return this;
    }

    public VisitBuilder withTimestamp(Timestamp timestamp) {
        visit.setTimestamp(timestamp);
        return this;
    }

}
