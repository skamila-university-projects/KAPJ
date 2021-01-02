package skamila.kapj.domain;

import javax.persistence.*;

@Entity
public class Pesel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String PESEL;

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

}
