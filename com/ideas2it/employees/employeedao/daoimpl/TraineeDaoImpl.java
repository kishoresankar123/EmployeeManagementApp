package com.ideas2it.employees.employeedao.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.ideas2it.employees.model.Trainee;
import com.ideas2it.employees.model.Trainer;
import com.ideas2it.employees.model.Role;
import com.ideas2it.employees.model.Qualification;
import com.ideas2it.employees.employeedao.TraineeDaoIntf;
import com.ideas2it.employees.constant.Constant;
import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;  
import java.util.Set;
import java.util.HashSet;

/**
* It can be implemented for Trainee DAO operations.
*
**/
public class TraineeDaoImpl implements TraineeDaoIntf {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private List<Trainee> traineeDetails = new ArrayList<>();

    /**
    * It can be implemented for inserting trainee object in database.
    *
    * @param traineeObject as parameter
    **/
    public void insertTrainee(Trainee trainee) {
        
        List<Trainer> trainers = new ArrayList<>();
        Set<Trainer> trainersForTrainee = new HashSet<>();
        List<Integer> validTrainerId = new ArrayList<>();
        List<Integer> inValidTrainerId = new ArrayList<>(); 

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("description",trainee.getEmployee().getRole().getDescription()));
        List<Role> roleResults = criteria.list();
        System.out.println(roleResults.toString() + "Trainee sysout");
        if (roleResults.size() > 0) {
        trainee.getEmployee().setRole(roleResults.get(0));
        }
        criteria = session.createCriteria(Qualification.class);
        criteria.add(Restrictions.eq("qualification",trainee.getEmployee().getQualification().getQualification()));
        List<Qualification> qualificationResults = criteria.list();
        if (qualificationResults.size() > 0) {
            trainee.getEmployee().setQualification(qualificationResults.get(0));
        }    
        trainers = session.createCriteria(Trainer.class).list();
        boolean isValidTrainerId = false;
        for (int trainerId : trainee.getTrainerId()) {
            for (Trainer trainer : trainers) {
                if (trainer.getEmployee().getEmployeeId() == trainerId) {
                    trainersForTrainee.add(trainer);
                    validTrainerId.add(trainerId);
                    isValidTrainerId = true;
                    break;
                }
            }
            if (!isValidTrainerId) {
                inValidTrainerId.add(trainerId);
            }
        }
        trainee.setTrainerId(validTrainerId);
        trainee.setTrainers(trainersForTrainee);

        session.save(trainee);  
        transaction.commit();  
        System.out.println("successfully saved");     
        session.close();       
    }

    /**
    * It can be implemented for retrieve trainee objects from database.
    *
    * @param traineeObject as parameter
    * @return traineeDetails Arraylist
    **/
    public List<Trainee> retrieveTrainee() {
        List<Trainee> traineeDetail = new ArrayList<>();
        Session session = sessionFactory.openSession();
        String query = "From Trainee";
        traineeDetail = session.createQuery(query).list();
        session.close();
        return traineeDetail;          
    }


    /**
    * It can be implemented for deleting specfic trainee object in database.
    *
    * @param receives trainee id as parameter
    * @return returns boolean as deletion status
    **/
    public boolean deleteTraineeById(String id) {
        Trainee trainee = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainee = (Trainee)session.createCriteria(Trainee.class).add(Restrictions.eq("employee.employeeId",Integer.parseInt(id))).uniqueResult();    
        if (null != trainee) {
            session.remove(trainee);
            transaction.commit();
        }
        session.close();
        return (null != trainee); 
    }
    
    /**
    * It can be implemented for updating email for specfic trainee from database.
    *
    * @param receives new mail to be updated as string
    * @param receives trainee id as parameter
    **/
    public void traineeEmailUpdater(String mail, int id) {
        Trainee trainee = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainee = (Trainee)session.createCriteria(Trainee.class).add(Restrictions.eq("employee.employeeId",id)).uniqueResult();    
        if (null != trainee) {
            trainee.getEmployee().setEmployeeMail(mail);
            session.merge(trainee);
            transaction.commit();
        }
        session.close();    
    }

    /**
    * It can be implemented for updating phone number for specfic trainee from database.
    *
    * @param receives new phone number to be updated as string
    * @param receives trainee id as parameter
    **/
    public void traineePhoneNumberUpdater(String number, int id) {
        Trainee trainee = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainee = (Trainee)session.createCriteria(Trainee.class).add(Restrictions.eq("employee.employeeId",id)).uniqueResult();    
        if (null != trainee) {
            trainee.getEmployee().setEmployeePhoneNumber(number);
            session.merge(trainee);
            transaction.commit();
        }
        session.close();
 
    }

    /**
    * It can be implemented for updating skills for specfic trainee from database.
    *
    * @param receives new skill to be updated as string
    * @param receives trainee id as parameter
    **/
    public void traineeSkillsUpdater(String skill, int id) {
        Trainee trainee = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainee = (Trainee)session.createCriteria(Trainee.class).add(Restrictions.eq("employee.employeeId",id)).uniqueResult();    
        if (null != trainee) {
            trainee.setSkills(skill);
            session.merge(trainee);
            transaction.commit();
        }
        session.close();          
            
    }


}