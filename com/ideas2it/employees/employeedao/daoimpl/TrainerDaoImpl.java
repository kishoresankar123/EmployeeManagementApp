package com.ideas2it.employees.employeedao.daoimpl;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import com.ideas2it.employees.model.Trainer;
import com.ideas2it.employees.model.Role;
import com.ideas2it.employees.model.Qualification;
import com.ideas2it.employees.employeedao.TrainerDaoIntf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
* It can be implemented for Trainer DAO operations.
*
**/
public class TrainerDaoImpl implements TrainerDaoIntf {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private List<Trainer> trainerDetails = new ArrayList<>()

    /**
    * It can be implemented for inserting trainer object in database.
    *
    * @param trainerObject as parameter
    **/
    public void insertTrainer(Trainer trainer) {
         
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);

        criteria.add(Restrictions.eq("description",trainer.getEmployee().getRole().getDescription()));
        List<Role> roleResults = criteria.list();
        if (roleResults.size() > 0) {
            trainer.getEmployee().setRole(roleResults.get(0));
        }
        criteria = session.createCriteria(Qualification.class);
        criteria.add(Restrictions.eq("qualification",trainer.getEmployee().getQualification().getQualification()));
        List<Qualification> qualificationResults = criteria.list();
        if (qualificationResults.size() > 0) {
            trainer.getEmployee().setQualification(qualificationResults.get(0));
        }    
        session.save(trainer);  
        transaction.commit();  
        System.out.println("successfully saved");
        session.close();    
    }

    /**
    * It can be implemented for retrieve trainer objects from database.
    *
    * @param trainerObject as parameter
    * @return trainerDetails Arraylist
    **/
    public List<Trainer> retrieveTrainers() {
        List<Trainer> trainerDetail = new ArrayList<>();
        Session session = sessionFactory.openSession();
        String query = "From Trainer";
        trainerDetail = session.createQuery(query).list();
        session.close();
        return trainerDetail;
    }

    /**
    * It can be implemented for deleting specfic trainer object in database.
    *
    * @param receives trainer id as parameter
    * @return returns boolean as deletion status
    **/
    public boolean deleteTrainerById(String id) {
        Trainer trainer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainer = (Trainer)session.createCriteria(Trainer.class).add(Restrictions.eq("employee.employeeId",Integer.parseInt(id))).uniqueResult();    
        if (null != trainer) {
            session.remove(trainer);
            transaction.commit();
        }
        session.close();
        return (null != trainer);
    }

    /**
    * It can be implemented for updating email for specfic trainer from database.
    *
    * @param receives new mail to be updated as string
    * @param receives trainer id as parameter
    **/
    public void trainerEmailUpdater(String mail, int id) {
        Trainer trainer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainer = (Trainer)session.createCriteria(Trainer.class).add(Restrictions.eq("employee.employeeId",id)).uniqueResult();    
        if (null != trainer) {
            trainer.getEmployee().setEmployeeMail(mail);
            session.merge(trainer);
            transaction.commit();
        }
        session.close();
    }

    /**
    * It can be implemented for updating domain for specfic trainer from database.
    *
    * @param receives new domain to be updated as string
    * @param receives trainer id as parameter
    **/
    public void trainerDomainUpdater(String domain, int id) {
        Trainer trainer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainer = (Trainer)session.createCriteria(Trainer.class).add(Restrictions.eq("employee.employeeId",id)).uniqueResult();    
        if (null != trainer) {
            trainer.setDomain(domain);
            session.merge(trainer);
            transaction.commit();
        }
        session.close();          
    }

    /**
    * It can be implemented for updating phoneNumber for specfic trainer from database.
    *
    * @param receives new phone number to be updated as string
    * @param receives trainer id as parameter
    **/
    public void trainerPhoneNumberUpdater(String number, int id) {
        Trainer trainer = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        trainer = (Trainer)session.createCriteria(Trainer.class).add(Restrictions.eq("employee.employeeId",id)).uniqueResult();    
        if (null != trainer) {
            trainer.getEmployee().setEmployeePhoneNumber(number);
            session.merge(trainer);
            transaction.commit();
        }
        session.close();
    }
}