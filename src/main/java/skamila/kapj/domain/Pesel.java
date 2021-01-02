package skamila.kapj.domain;

import javax.persistence.*;

@Entity
public class Pesel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String PESEL;

    @OneToOne(mappedBy = "pesel")
    private AppUser appUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
