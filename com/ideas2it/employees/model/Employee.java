package com.ideas2it.employees.model;

import java.text.SimpleDateFormat;  
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.text.ParseException;
import com.ideas2it.employees.model.Trainer;
import com.ideas2it.employees.model.Trainee;
import com.ideas2it.employees.model.Role;
import com.ideas2it.employees.model.Qualification;

/**
 * It can be implemented to get and set all the common employee attributes.
 *
 **/
public class Employee {

    private String employeeName;
    private String employeeMail;
    private String employeePhoneNumber;
    private LocalDate employeeDateOfBirth;
    private int employeeId;
    private String employeeGender;
    private Trainer trainer;
    private Trainee trainee;
    private Role role;
    private Qualification qualification;
    

    public String getEmployeeName(){
        return employeeName;
    }

    public void setEmployeeName(String name){
        this.employeeName=name;
    }

    public String getEmployeeMail(){
        return employeeMail;
    }

    public void setEmployeeMail(String mail){
        this.employeeMail = mail;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(LocalDate dob) {
        this.employeeDateOfBirth = dob;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String phoneNumber) {
        this.employeePhoneNumber = phoneNumber ;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int temporaryId) {
        this.employeeId = temporaryId;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String temporaryGender) {
        this.employeeGender = temporaryGender;
    }
   
    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
    public Employee() {

    }

}
  
