package com.pocketdoctor.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "patientInfo")
public class PatientData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    //String email;
    String mobileno;
    String otp;
    String type = "Patient";

    public String getType() {
        return type;
    }

    public Integer getId(){
        return id;
    }

    public void setType(String type) {
        this.type = "Patient";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientData patientData = (PatientData) o;
        //return Objects.equals(id, userData.id) && Objects.equals(name, userData.name) && Objects.equals(email, userData.email) && Objects.equals(mobileno, userData.mobileno) && Objects.equals(type, userData.type) && Objects.equals(otp, userData.otp);
        return Objects.equals(id, patientData.id) && Objects.equals(name, patientData.name) && Objects.equals(mobileno, patientData.mobileno) && Objects.equals(type, patientData.type) && Objects.equals(otp, patientData.otp);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(id, name, email, mobileno, type, otp);
        return Objects.hash(id, name, mobileno, otp);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", email='" + email + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
    

    public String getOtp() {
        return otp;


    }

    public void setOtp(String otp) {
        this.otp = otp;
    }



    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    //public String getEmail() {
       // return email;
   // }

    //public void setEmail(String email) {
      //  this.email = email;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
