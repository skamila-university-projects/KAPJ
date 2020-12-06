package skamila.kapj.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "appuser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private int version;

    @NotNull
    @Size(min=2, max=30, message = "{error.length.name}")
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotNull
    @Size(min=2, max=30, message = "{error.length.name}")
    private String lastName;

    @NotNull
    private String email;

    @Pattern(regexp = "^\\+[0-9]{1,3}-[0-9]{3}-[0-9]{3}-[0-9]{3}$", message = "{error.telephone.invalid}")
    private String telephone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
