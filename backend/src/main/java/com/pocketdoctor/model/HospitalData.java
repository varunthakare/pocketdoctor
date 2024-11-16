package com.pocketdoctor.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "hospitalInfo")
public class HospitalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String username;
    String name;
    String password;
    String address;
    boolean verify;

    public HospitalData(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public HospitalData(Integer id, String username, String name, String password, String address, boolean verify) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.address = address;
        this.verify = verify;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HospitalData that = (HospitalData) o;
        return verify == that.verify && Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, password, address, verify);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
}
