package skamila.kapj.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @ManyToOne
    private AppUser doctor;

    @NotNull
    @ManyToOne
    private AppUser patient;

    @NotNull
    private LocalDateTime time;

    private int lengthOfVisit;

    private int price;

    private boolean confirmed;

    private boolean canceled;

    private boolean billAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getDoctor() {
        return doctor;
    }

    public void setDoctor(AppUser doctor) {
        this.doctor = doctor;
    }

    public AppUser getPatient() {
        return patient;
    }

    public void setPatient(AppUser patient) {
        this.patient = patient;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getLengthOfVisit() {
        return lengthOfVisit;
    }

    public void setLengthOfVisit(int lengthOfVisit) {
        this.lengthOfVisit = lengthOfVisit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isBillAvailable() {
        return billAvailable;
    }

    public void setBillAvailable(boolean billAvailable) {
        this.billAvailable = billAvailable;
    }
}
