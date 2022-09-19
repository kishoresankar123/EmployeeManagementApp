package com.ideas2it.employees.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;
import com.ideas2it.employees.model.Employee;
import java.util.HashSet;
import java.util.Set;

/**
 * It can be implemented to get and set all the trainee specfic attributes.
 *
 **/
public class Trainee extends Employee {

    private int trainee_id;
    private String skills;
    private float cgpa;
    private final String status= "Trainee";
    private Employee employee;
    private Set<Trainer> trainers = new HashSet<>();
    private List<Integer> trainerId = new ArrayList<>();
 
    public String getStatus() {
        return status;
    }

    public String getSkills() {
	return skills;
    }

    public float getCgpa() {
	return cgpa;
    }
 
    public void setSkills(String inputSkills){
        this.skills = inputSkills;
    }

    public void setCgpa(float inputCgpa){
	this.cgpa = inputCgpa;
    } 

    /**
    * It can be implemented to get age from date of birth.
    *
    * @param gets birthDay in localDate format
    * @return returns age in int format
    **/
    public int getAge(LocalDate birthDay) {
        LocalDate presentDate = LocalDate.now();
        Period age = Period.between(birthDay,presentDate);
        return age.getYears();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getTrainee_id() {
        return trainee_id;
    }

    public void setTrainee_id(int trainee_id) {
        this.trainee_id = trainee_id;
    }

    public Set<Trainer> getTrainers () {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Integer> getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(List<Integer> trainerId) {
        this.trainerId = trainerId;
    }

    /**
    * It can be implemented to get all the trainee attributes in string format
    *
    * @return returns all the trainee data in string format
    **/
    public String toString(){

    return "Employee ID: "+getEmployee().getEmployeeId()+"\n"+ "Name: " + getEmployee().getEmployeeName()+"\n" 
            + "Age: "+ getAge(getEmployee().getEmployeeDateOfBirth()) +"\n"+ "Gender: "+ getEmployee().getEmployeeGender()+"\n"
            + "Skills: "+ skills+"\n" + "CGPA: "+ cgpa+"\n" + "Mail ID: "+ getEmployee().getEmployeeMail()+"\n" 
            + "Contact Number: "+ getEmployee().getEmployeePhoneNumber() + "\n"+"\n"+"\n";
    }
}



