package skamila.kapj.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="doctor")
public class Doctor {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private AppUser appUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Specialization> specializations;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }
}
