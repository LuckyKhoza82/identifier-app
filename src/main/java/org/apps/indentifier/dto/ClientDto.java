package org.apps.indentifier.dto;

public class ClientDto {
    private String idNumber;
    private String name;
    private String surname;
    private String mobileNumber;
    private String physicalAddress;

    public ClientDto(){}

    public ClientDto(String idNumber, String name, String surname, String mobileNumber, String physicalAddress) {
        this.idNumber = idNumber;
        this.name = name;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
        this.physicalAddress = physicalAddress;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
}
