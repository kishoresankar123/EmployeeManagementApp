package com.ideas2it.employees.service.impl;

import java.util.List;
import java.util.Date;
import java.time.Period;
import java.time.LocalDate; 
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import com.ideas2it.employees.service.TrainerServiceIntf;

import com.ideas2it.employees.model.Trainer;
import com.ideas2it.employees.utils.StringUtil;
import com.ideas2it.employees.utils.NumberUtil;
import com.ideas2it.employees.employeedao.TrainerDaoIntf;
import com.ideas2it.employees.model.Employee;
import com.ideas2it.employees.model.Role;
import com.ideas2it.employees.model.Qualification;
import com.ideas2it.employees.employeedao.daoimpl.TrainerDaoImpl;
import com.ideas2it.employees.constant.Constant;
import com.ideas2it.employees.exception.NoEmployeeFound;
import com.ideas2it.employees.exception.BadRequest;	

/**
 * It can be implemented for validation and calling trainerDao.
 **/
public class TrainerServiceImpl implements TrainerServiceIntf {
    
    private TrainerDaoIntf trainerDao = new TrainerDaoImpl();

    /**
    * It can be implemented for getting trainer arraylist.
    * @return returns trainer arraylist
    **/
    public List<Trainer> getTrainer() {
        return trainerDao.retrieveTrainers();
    }

    /**
    * It can be implemented for adding trainer to arraylist.
    * @param trainerObject as parameter
    **/
    public void addTrainer(Trainer trainer) {
        trainerDao.insertTrainer(trainer);
    } 

    /**
    * It can be implemented for deleting trainer from arraylist.
    * @param trainer ID as parameter
    * @return returns boolean
    **/
    public boolean deleteTrainer(String id) throws NoEmployeeFound {
        if (trainerDao.deleteTrainerById(id)) {
            return true;
        } else {
            throw new NoEmployeeFound("Employee Id not found");
        }
    }
 
    /**
    * It can be implemented for validating trainers input.
    *
    * @param String name
    * @param String date
    * @param String gender
    * @param String qualification
    * @param String domain
    * @param int salary
    * @param String email
    * @param String mobileNumber
    * @returns list with error data
    **/
    public List<Integer> trainerDetailsValidator(String name, String date, String gender, String qualification, String domain,
                                                 int salary, String email, String mobileNumber) throws ParseException, BadRequest {
        List<Integer> errorData = new ArrayList<>();
        int counter = 0;
        LocalDate birthDay = null;
        int temporarySalary = 0;
        String errorFoundMessage = "";
        if (StringUtil.nameValidator(name)) {
            errorFoundMessage += "\n Enter Name only with Alphabets";
            errorData.add(1);
        }

        Date temporaryDate = new SimpleDateFormat(Constant.date1).parse(date);
        String parseDate = new SimpleDateFormat(Constant.date2).format(temporaryDate);
        birthDay = LocalDate.parse(parseDate);

        if (NumberUtil.ageCalculator(birthDay) < 1) {
            errorFoundMessage += "\n Please dont enter Future date";
            errorData.add(2);
        } else if (NumberUtil.ageCalculator(birthDay) > 1 && NumberUtil.ageCalculator(birthDay) < 18 ) {
            errorFoundMessage += "\n Above 18 age is only allowed";
            errorData.add(2);
        }
        if (NumberUtil.salaryValidator(salary)) {
            errorFoundMessage += "\n Enter minimum of 4 digits in salary";
            errorData.add(4);
        } else {
            salary = NumberUtil.taxCalculator(salary);
        } 
        if (!StringUtil.isEmailValid(email)) {
            errorFoundMessage += "\n Enter a Valid Email Id";
            errorData.add(5);
        } 
        if (!NumberUtil.isPhoneValid(mobileNumber)) {
            errorFoundMessage += "\n Enter only 10 digit mobile number";
            errorData.add(6);
        }  
        if (errorData.size() == 0) {
            Trainer trainer = new Trainer();
            Employee employee = new Employee();
            Role role = new Role();
            Qualification qualificationObj = new Qualification();
            trainer.setSalary(salary);
            trainer.setDomain(domain);
            role.setDescription("Trainer");
            qualificationObj.setQualification(qualification);
            employee.setEmployeeMail(email);
            employee.setEmployeeName(name);
            employee.setEmployeeDateOfBirth(birthDay);
            employee.setEmployeeGender(gender);
            employee.setEmployeePhoneNumber(mobileNumber);
            employee.setRole(role);
            employee.setQualification(qualificationObj);
            trainer.setEmployee(employee);
            trainerDao.insertTrainer(trainer);
        } else {
            throw new BadRequest(errorFoundMessage, errorData);
        }
        return errorData;
    }
    
    /**
    * It can be implemented for validating trainer domain.
    *
    * @param trainerObject and domain as parameter
    **/
    public void trainerDomainValidator(String domain, int id) {
        trainerDao.trainerDomainUpdater(domain, id);         
    }

    /**
    * It can be implemented for validating trainer email.
    * @param trainerObject and email id as parameter
    **/
    public String trainerEmailValidator(String email, int empid) {
        String result = "";
        if (StringUtil.isEmailValid(email)) {
            trainerDao.trainerEmailUpdater(email,empid);
            result = "Email updated successfully";
        } else {
            result = "Wrong mail, Try again";
        }  
        return result;  
    }

    /**
    * It can be implemented for validating trainer mobile number.
    *
    * @param trainerObject and mobile number as parameter
    **/
    public String trainerNumberValidator(String number, int id) {
        String result = "";
        if (NumberUtil.isPhoneValid(number)) {
            trainerDao.trainerPhoneNumberUpdater(number, id);
            result = "Mobile Number Modified Successfully";
        } else {
            result = "Wrong number, try again";
        }
        return result;  
    } 

}