package com.ideas2it.employees.employeedao;

import java.util.ArrayList;
import java.util.List;
import com.ideas2it.employees.model.Trainee;

/**
* It can be implemented for defining traineeDao methods.
**/
public interface TraineeDaoIntf {
   
    public void insertTrainee(Trainee trainee);
    public List<Trainee> retrieveTrainee();
    public boolean deleteTraineeById(String id);
    public void traineeEmailUpdater(String mail, int id);
    public void traineePhoneNumberUpdater(String number, int id);
    public void traineeSkillsUpdater(String skill, int id);
}