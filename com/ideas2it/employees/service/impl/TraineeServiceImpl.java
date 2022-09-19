package com.ideas2it.employees.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ideas2it.employees.employeedao.daoimpl.TraineeDaoImpl;
import com.ideas2it.employees.model.Trainee;
import com.ideas2it.employees.model.Employee;
import com.ideas2it.employees.model.Role;
import com.ideas2it.employees.model.Qualification;
import com.ideas2it.employees.utils.StringUtil;
import com.ideas2it.employees.utils.NumberUtil;
import com.ideas2it.employees.service.TraineeServiceIntf;
import com.ideas2it.employees.exception.NoEmployeeFound;
import com.ideas2it.employees.exception.BadRequest;

import java.util.Date;
import java.time.Period;
import java.time.LocalDate; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import com.ideas2it.employees.constant.Constant;
import com.ideas2it.employees.employeedao.TraineeDaoIntf;
import com.ideas2it.employees.employeedao.daoimpl.TraineeDaoImpl;

/**
 * It can be implemented for validation and calling traineeDao.
 **/
public class TraineeServiceImpl implements TraineeServiceIntf {

    private TraineeDaoIntf traineeDao = new TraineeDaoImpl();

    /**
    * It can be implemented for getting trainee arraylist.
    *
    * @return returns trainee arraylist
    **/
    public List<Trainee> getTrainee() {
        return traineeDao.retrieveTrainee();
    }

    /**
    * It can be implemented for adding trainee to arraylist.
    *
    * @param traineeObject as parameter
    **/
    public void addTrainee(Trainee trainee) {
        traineeDao.insertTrainee(trainee);
    } 

    /**
    * It can be implemented for deleting trainee from arraylist.
    *
    * @param trainee ID as parameter
    * @return returns boolean
    **/
    public boolean deleteTrainee(String id) throws NoEmployeeFound {
        if (traineeDao.deleteTraineeById(id)) {
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
    * @param String skills
    * @param float cgpa
    * @param String email
    * @param String mobileNumber
    **/
    public List<Integer> traineeDetailsValidator(String name, String date, String gender,String qualification, String skills,
                                        float cgpa, String mail, String mobileNumber, List<Integer> trainerId) throws ParseException, BadRequest {

        List<Integer> errorData = new ArrayList<>();
        String errorFoundMessage = "";
        System.out.println("\n"+"\n");
        if (StringUtil.nameValidator(name)) {
            errorFoundMessage += "\n Enter Name only with Alphabets"; 
            errorData.add(1);  
        } 
        LocalDate birthDay = null;
        Date temporaryDate = new SimpleDateFormat(Constant.date1).parse(date);
        String parseDate = new SimpleDateFormat(Constant.date2).format(temporaryDate);
        birthDay = LocalDate.parse(parseDate);

        if (NumberUtil.ageCalculator(birthDay) < 1) {
            errorFoundMessage += "\nPlease dont enter Future date";
            errorData.add(2);
        } else if (NumberUtil.ageCalculator(birthDay) > 1 && NumberUtil.ageCalculator(birthDay) < 18 ) {
            errorFoundMessage += "\n Above 18 age is only allowed";
            errorData.add(2);
        }
        if (!StringUtil.isEmailValid(mail)) {
            errorFoundMessage += "\n Enter a Valid Email Id";
            errorData.add(3);
        }
        if (!NumberUtil.isPhoneValid(mobileNumber)) {
            errorFoundMessage += " \n Enter Correct Contact Number";
            errorData.add(4);
        }
        if (errorData.size() == 0) {
            Trainee trainee = new Trainee();
            Employee employee = new Employee();
            Role role = new Role();
            Qualification qualificationObj = new Qualification();
            role.setDescription("Trainee");
            qualificationObj.setQualification(qualification);
            trainee.setSkills(skills);
            trainee.setCgpa(cgpa);
            trainee.setTrainerId(trainerId);
            employee.setEmployeeDateOfBirth(birthDay);
            employee.setEmployeeName(name); 
            employee.setEmployeeGender(gender); 
            employee.setEmployeeMail(mail);
            employee.setEmployeePhoneNumber(mobileNumber);
            employee.setQualification(qualificationObj);
            employee.setRole(role);
            trainee.setEmployee(employee);
            traineeDao.insertTrainee(trainee);       
        } else {
             throw new BadRequest(errorFoundMessage, errorData);
        }
        return errorData;
    }

    public void traineeSkillsValidator(String skill, int id) {
        traineeDao.traineeSkillsUpdater(skill, id);
    }

    /**
    * It can be implemented for validating trainee skill.
    *
    * @param traineeObject and skill as parameter
    **/
    public String traineeEmailValidator(String mail, int id) {
        String result = "";
        if (StringUtil.isEmailValid(mail)) {
            traineeDao.traineeEmailUpdater(mail, id);
            result = "Email updated successfully";
        } else {
            result = "Wrong mail, Try again";
        } 
        return result; 
    }

    /**
    * It can be implemented for validating trainee mobile number.
    *
    * @param traineeObject and mobile number as parameter
    **/
    public String traineeNumberValidator(String number, int id) {
        String result = "";
        if (NumberUtil.isPhoneValid(number)) {
            traineeDao.traineePhoneNumberUpdater(number, id);
            result = "Mobile Number Modified Successfully";
        } else {
            result = "Wrong number, try again";
        } 
        return result;
    }

}