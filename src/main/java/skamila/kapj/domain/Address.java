package skamila.kapj.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private int version;

    @NotNull
    @Size(min=2, max=20, message = "{error.length.name}")
    private String city;

    @NotNull
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$", message = "{error.address.invalid.value}")
    private String postalCode;

    @NotNull
    @Size(min=2, max=30, message = "{error.length.name}")
    private String street;

    @NotNull
    @Pattern(regexp = "^[0-9]+([a-z]|(\\/[0-9]+))?$", message = "{error.address.invalid.value}")
    private String houseNumber;

    @Pattern(regexp = "^([0-9]+([a-z]|(\\/[0-9]+))?)?$", message = "{error.address.invalid.value}")
    private String flatNumber;

    @OneToMany(mappedBy = "address")
    private List<AppUser> appUserList;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
}
