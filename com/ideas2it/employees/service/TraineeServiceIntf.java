package com.ideas2it.employees.service;

import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;
import com.ideas2it.employees.model.Trainee;
import com.ideas2it.employees.exception.NoEmployeeFound;

/**
* It can be implemented for defining traineeService methods.
**/
public interface TraineeServiceIntf {

    public void addTrainee(Trainee trainee);
    public boolean deleteTrainee(String id) throws NoEmployeeFound;
    public List<Trainee> getTrainee();
    public List<Integer> traineeDetailsValidator(String name, String date, String gender, String qualification, String skills,
                                        float cgpa, String mail, String mobileNumber, List<Integer> trainerId) throws ParseException;
    public void traineeSkillsValidator(String skill, int id);
    public String traineeEmailValidator(String mail, int id);
    public String traineeNumberValidator(String number, int id);
}