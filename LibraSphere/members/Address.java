package be.hasan.libraSphere.members;

import jakarta.persistence.Embeddable;

@Embeddable  //Address is a value object class.
public class Address {
    private String street;
    private String houseNumber;
    private String municipality;
    private int postcode;
    protected Address(){}
    public Address(String street, String houseNumber, String municipality, int postcode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.municipality = municipality;
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getMunicipality() {
        return municipality;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}
