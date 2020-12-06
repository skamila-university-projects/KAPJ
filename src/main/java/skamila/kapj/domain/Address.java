package skamila.kapj.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private int version;

    @NotNull
//    @Size(min=2, max=20, message = "{error.length.name}")
    private String city;

    @NotNull
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$", message = "{error.telephone.invalid}")
    private String postalCode;

    @NotNull
//    @Size(min=2, max=30, message = "{error.length.name}")
    private String street;

    @NotNull
//    @Size(min=1, max=5, message = "{error.length.name}")
    private String houseNumber;

//    @Size(min=0, max=5, message = "{error.length.name}")
    private String flatNumber;

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
