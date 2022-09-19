package com.ideas2it.employees.employeedao;

import java.util.ArrayList;
import java.util.List;
import com.ideas2it.employees.model.Trainer;

/**
* It can be implemented for defining trainerDao methods.
**/
public interface TrainerDaoIntf {

    public List<Trainer> retrieveTrainers();
    public void insertTrainer(Trainer trainer);
    public boolean deleteTrainerById(String id);
    public void trainerEmailUpdater(String mail, int id);
    public void trainerDomainUpdater(String domain, int id);
    public void trainerPhoneNumberUpdater(String number, int id);
}