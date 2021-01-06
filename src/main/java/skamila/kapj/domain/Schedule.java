package skamila.kapj.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<WorkDay> workDays;

    private int lengthOfVisit;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Set<WorkDay> workDays) {
        this.workDays = workDays;
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

}
