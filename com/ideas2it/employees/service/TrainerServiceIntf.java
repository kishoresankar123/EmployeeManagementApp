package com.ideas2it.employees.service;

import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;
import com.ideas2it.employees.model.Trainer;
import com.ideas2it.employees.exception.NoEmployeeFound;

/**
* It can be implemented for defining trainerService methods.
**/
public interface TrainerServiceIntf {
    
    public List<Trainer> getTrainer();
    public void addTrainer(Trainer trainer);
    public boolean deleteTrainer(String id) throws NoEmployeeFound;
    //public Trainer getTrainer(String id);
    public List<Integer> trainerDetailsValidator(String name, String date, String gender, String qualification, String domain,
                                                 int salary, String email, String mobileNumber) throws ParseException;
    public void trainerDomainValidator(String domain, int empid);
    public String trainerEmailValidator(String email, int empid);
    public String trainerNumberValidator(String number, int id);
}