package com.ideas2it.employees.model;

import java.time.LocalDate;
import java.time.Period;
import com.ideas2it.employees.model.Employee;
import com.ideas2it.employees.model.Trainee;
import java.util.HashSet;
import java.util.Set;

/**
 * It can be implemented to get and set all the trainee specfic attributes.
 *
 **/
public class Trainer extends Employee {

    private int trainer_id;
    private int salary;
    private String domain;
    private Employee employee;
    private final String status = "Trainer";
    private Set<Trainee> trainees = new HashSet<>();

    public String getStatus() {
        return status;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary( int inputSalary) {
        this.salary = inputSalary;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String inputDomain) {
        this.domain = inputDomain;
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

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Trainer() {

    }

    public Set<Trainee> getTrainees () {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    /**
    * It can be implemented to get all the trainer attributes in string format
    *
    * @return returns all the trainer data in string format
    **/
    public String toString(){
    return "Employee ID: "+ getEmployee().getEmployeeId() + "\n" + "Name: " + getEmployee().getEmployeeName() +"\n"
	 +"Age: "+ getAge(getEmployee().getEmployeeDateOfBirth()) +"\n"+ "Gender: " + getEmployee().getEmployeeGender()+"\n" 
	 + "Domain: " + domain+"\n" 
         + "Mail: " + getEmployee().getEmployeeMail()+"\n" + "Contact Number: " + getEmployee().getEmployeePhoneNumber() + "\n" + "Salary: " + salary +"\n"+"\n"+"\n";
    }
}