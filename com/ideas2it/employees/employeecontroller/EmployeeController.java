package com.ideas2it.employees.employeecontroller;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import com.ideas2it.employees.model.Trainer;
import com.ideas2it.employees.model.Trainee;
import com.ideas2it.employees.service.impl.TrainerServiceImpl;
import com.ideas2it.employees.service.impl.TraineeServiceImpl;
import com.ideas2it.employees.service.TrainerServiceIntf;
import com.ideas2it.employees.service.TraineeServiceIntf;
import com.ideas2it.employees.constant.Constant;
import com.ideas2it.employees.exception.NoEmployeeFound;
import com.ideas2it.employees.exception.BadRequest;
import java.text.ParseException;
import java.util.InputMismatchException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * It can be implemented for input and output operation of trainer,trainee and manager.
 **/
public class EmployeeController {
    private Scanner input = new Scanner(System.in);
    private TrainerServiceIntf trainerService = new TrainerServiceImpl();
    private TraineeServiceIntf traineeService = new TraineeServiceImpl();
    private Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws ParseException {
        EmployeeController employeeController = new EmployeeController();
        employeeController.userMenu();
    }

    /** 
     * <p>
     * To get integer input from the user and calls the corresponding method.
     * </p>
     **/
    private void userMenu() throws ParseException {
        String temporaryInput;
        do {
            logger.info("Menu");
            logger.info("Please select any of the options below" +"\n\n"+
                    "Press 1 to Enter Trainer details" +"\n"+
                    "Press 2 to Enter Trainee details"+"\n"+
                    "Press 3 to view Trainer details"+"\n"+
                    "Press 4 to view Trainee details"+"\n"+
                    "Press 5 to Delete Trainer"+"\n"+
                    "Press 6 to Delete Trainee"+"\n"+
                    "Press 7 to Modify Trainer"+"\n"+
                    "Press 8 to Modify Trainee");

            int userChoice = 0;
            boolean menuChoice = false;
            do {
                try {
                    userChoice = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    logger.warn("Enter only numbers 1 to 8");
                    input.nextLine();
                    menuChoice = true;
                }
            } while (menuChoice);
   
            switch (userChoice) {
            case 1:
                trainerInput();
                break;

             case 2:
                traineeInput();
                break;

             case 3:
                trainerDisplay();
                break;

            case 4:
                traineeDisplay();
                break;

            case 5:
                deleteTrainerMethod();
                break;

            case 6:
                deleteTraineeMethod();
                break;

            case 7:
                updateTrainerMethod();
                break;

            case 8:
                updateTraineeMethod();
                break;

            default:
                logger.warn("Enter only 1 to 8");
            }
            logger.info("Do u want to continue Y/N");
            temporaryInput = input.next();
        } while ( temporaryInput.equalsIgnoreCase("Y"));
    }
 
    /** 
     * <p>
     * To get a trainer input from the user then vaidate them .
     * </p>
     **/
    private void trainerInput() throws ParseException {     
        logger.info("Enter Trainer Name");
        input.nextLine(); 
        String name = input.nextLine();
     
        logger.info("Enter Date of birth in this dd-mm-yyyy format");
        String date = input.next();
              
        String gender = "";
        do {
            logger.info("Select Gender");
            logger.info("Press 1 for Male");
            logger.info("Press 2 for Female");
            String genderInput = input.next();
                    
            switch (genderInput) {
            case "1":
                gender = Constant.MALE;
                break;

            case "2":
                gender = Constant.FEMALE;
                break;

            case "3":
                gender = Constant.OTHERS;
                break;

            default:
                logger.warn("Please select the valid option");
            }
        } while (gender == "");

        logger.info("Select Qualification");
        String qualification = input.next();
          
        logger.info("Enter Trainer domain");
        String domain = input.next();
   
        boolean isSalaryInvalid = false;
        int salary = 0;
        logger.info("Enter Trainer salary");
	do {
            isSalaryInvalid = false;
            try {
	        salary = input.nextInt();
            } catch (InputMismatchException e) {
                logger.info("Enter numbers only");
                input.nextLine();
                isSalaryInvalid = true;
            }
        } while (isSalaryInvalid);
      
        logger.info("Enter Trainer Mail");
	String mail = input.next();
        
        logger.info("Enter Trainer Mobile Number"); 
        String number = input.next();

        List<Integer> errorData = new ArrayList<>();
        do {
            try {
                errorData = trainerService.trainerDetailsValidator(name, date,
                                                                   gender, qualification, 
                                                                   domain, salary, 
                                                                   mail, number);
            } catch (BadRequest e) {
                logger.warn(e.getMessage());
                errorData = e.getValidationErrorAsList();
            } 
            if (errorData.size() > 0) {
                logger.warn("\n" + "These are the invalid data's" + "\n" + "Enter Invalid Data's again" + "\n");
                for (int i = 0; i < errorData.size(); i++) {
                    switch (errorData.get(i)) {
                    case 1:
                        logger.info("Enter Trainer Name"); 
                        name = input.next();
                        break;
                    case 2:
                        logger.info("Enter Date of birth in this dd-mm-yyyy format");
                        date = input.next();
                        break;
                    case 4:
                        isSalaryInvalid = false;
                        salary = 0;
                        logger.info("Enter Trainer salary");
	                do {
                            isSalaryInvalid = false;
                            try {
	                        salary = input.nextInt();
                            } catch (InputMismatchException e) {
                                logger.warn("Enter numbers only");
                                input.nextLine();
                                isSalaryInvalid = true;
                            }
                        } while (isSalaryInvalid);
                        break;
                    case 5:
                        logger.info("Enter Trainer Mail");
	                mail = input.next();
                        break;
                    case 6:
                        logger.info("Enter Trainer Mobile Number"); 
	                number = input.next();
                        break;
                    }
                } 
            }
            
        } while (errorData.size() > 0);
        logger.info("Trainer data added successfully");           
    }   

     /** 
     * <p>
     * To get a trainee input from the user then vaidate them .
     * </p>
     **/      
    private void traineeInput() throws ParseException {
        logger.info("Enter Trainee name");  
	String name = input.next();
        
        logger.info("Enter Date of birth in this dd-mm-yyyy format");     
        String date = input.next();
        
        String gender = "";
        do {
            logger.info("Select Gender");
            logger.info("Press 1 for Male");
            logger.info("Press 2 for Female");
            String genderTraineeInput = input.next();
            switch (genderTraineeInput) {
            case "1":
                gender = Constant.MALE;
                break;
            case "2":
                gender = Constant.FEMALE;
                break;
            case "3":
                gender = Constant.OTHERS;
                break;
            default:
                logger.info("Please select the valid Gender option");
            }
        } while (gender == "");

        logger.info("Select Qualification");
        String qualification = input.next();

        logger.info("Enter Trainee skills");
	String skills = input.next();
        
	logger.info("Enter Trainee Cgpa");
	float cgpa = input.nextFloat();

        logger.info("Enter Trainee mail");
	String mail = input.next(); 
            
        logger.info("Enter Trainee Mobile Number");
        String number = input.next();
       
        List<Integer> trainerId = new ArrayList<>();
        boolean isTrainerExist = false;
        do {
            logger.info("Enter trainer ID");
            int trainer = input.nextInt();
            trainerId.add(trainer);
            logger.info("Do you want to add another trainer ? Y/N");
            String decision = input.next();
            if (decision.equals("Y")) {
                isTrainerExist = true;
            } else {
                isTrainerExist = false;
            }
        } while (isTrainerExist);

        List<Integer> errorData = new ArrayList<>();
        do {
            try {
            errorData = traineeService.traineeDetailsValidator(name, date, gender, qualification, skills,
                                                               cgpa, mail, number,trainerId);
            } catch (BadRequest e) {
                logger.warn(e.getMessage());
                errorData = e.getValidationErrorAsList();
            }
            if (errorData.size() > 0) {
                logger.warn("\n" + "These are the invalid data's" + "\n" + "Enter Invalid Data's again" + "\n");
                for (int i = 0; i < errorData.size(); i++) {
                    switch (errorData.get(i)) {
                    case 1:
                        logger.info("Enter Trainee Name"); 
                        name = input.next();
                        break;
                    case 2:
                        logger.info("Enter Date of birth in this dd-mm-yyyy format");
                        date = input.next();
                        break;
                    case 3:
                        logger.info("Enter Trainer Mail");
	                mail = input.next();
                        break;
                    case 4:
                        logger.info("Enter Trainee Mobile Number");
                        number = input.next();
                        break;
                    }
                } 
            }
        } while (errorData.size() > 0);
        logger.info("Trainee data added successfully");                   
    }

     /** 
     * <p>
     * read the trainee data from traineeDao and displays it .
     * </p>
     **/
    private void traineeDisplay() {
        if (traineeService.getTrainee().size() > 0) {
            for (int i = 0; i < traineeService.getTrainee().size(); i++ ) {
                logger.info(traineeService.getTrainee().get(i));
            }
            logger.info("\n");
        } else if (traineeService.getTrainee().size() == 0) {
            logger.warn("Currently no Trainee details avaliable, Please go to Menu and enter Trainee Details to see here");
        }
    }

    /** 
     * <p>
     * read the trainer data from trainerDao and displays it .
     * </p>
     **/
    private void trainerDisplay() {
        if (trainerService.getTrainer().size() > 0) {
            for (int i = 0; i < trainerService.getTrainer().size(); i++ ) {
                logger.info(trainerService.getTrainer().get(i));
            }
            logger.info("\n");
        } else if (trainerService.getTrainer().size() == 0) {
            logger.warn("Currently no Trainer details avaliable, Please go to Menu and enter Trainer details to see here");
        }
    }

    /** 
     * <p>
     * gets employee id as input and delete it(if exists) .
     * </p>
     **/
    private void deleteTrainerMethod() {
        logger.info("Enter Admin Password");
        String trainerPassword = input.next();
        if (trainerPassword.equals("kishore")) {
            logger.info("Enter Employee ID");
            String trainerId = input.next();
            try {
                trainerService.deleteTrainer(trainerId);
                logger.info("Trainer deleted Successfully");
            } catch (NoEmployeeFound e) {
                logger.warn(e.getMessage());
            }
        } else {
            logger.warn("Wrong Password");
        }
    }

     /** 
     * <p>
     * gets employee id as input and delete it(if exists) .
     * </p>
     **/                
    private void deleteTraineeMethod() {
        logger.info("Enter Admin Password");
        String traineePassword = input.next();
        if (traineePassword.equals("kishore")) {
            logger.info("Enter Employee ID");
            String traineeId = input.next();
            try {
                traineeService.deleteTrainee(traineeId);
                logger.info("Trainer deleted Successfully");
            } catch (NoEmployeeFound e) {
                logger.warn(e.getMessage());
            }
        } else {
            logger.warn("Wrong Password");
        }
    }

     /** 
     * <p>
     * gets employee id as input and update the  existing corresponding data to the id(if exists) .
     * </p>
     **/
    private void updateTrainerMethod() {
        logger.info("Enter Trainer ID");
        int employeeId = input.nextInt();

        logger.info("Enter 1 to Modify Domain");
        logger.info("Enter 2 to Modify Email");
        logger.info("Enter 3 to Modify Mobile Number");
        String userInput = input.next();
        switch (userInput) {
            case "1":
                logger.info("Enter Trainer domain");
	        String domain = input.next();
                trainerService.trainerDomainValidator(domain, employeeId);
                logger.info("Domain Modified Successfully");			
                break;

            case "2":   
                logger.info("Enter Trainer mail");
	        String mail = input.next();
                logger.info(trainerService.trainerEmailValidator(mail, employeeId));
                break;

            case "3":
                logger.info("Enter Trainer Mobile Number");
                String number = input.next();
                logger.info(trainerService.trainerNumberValidator(number, employeeId));    
                break;

            default:
                logger.info("Enter a Valid option");
                break;
        } 
    }
    
    /** 
     * <p>
     * gets employee id as input and update the  existing corresponding data to the id(if exists) .
     * </p>
     **/
    private void updateTraineeMethod() {
        logger.info("Enter Trainee ID");
        int employeeId = input.nextInt();
     
        logger.info("Enter 1 to Modify skills");
        logger.info("Enter 2 to Modify Email");
        logger.info("Enter 3 to Modify Mobile Number");
        String userInput = input.next();
        switch (userInput) {
            case "1":
                logger.info("Enter Trainee skills");
                String skills = input.next();
                traineeService.traineeSkillsValidator(skills,employeeId); 
                logger.info("Skill Modified Successfully");			
                break;

            case "2":
                logger.info("Enter Trainee mail");
	        String mail = input.next(); 
                traineeService.traineeEmailValidator(mail,employeeId);
                logger.info("Mail Modified Successfully");
                break;

            case "3":
                logger.info("Enter Trainee Mobile Number");
	        String number = input.next();
                traineeService.traineeNumberValidator(number, employeeId);
                logger.info("Mobile Number Modified Successfully");
                break;

            default:
                logger.info("Enter a Valid option");
                break;
        }         
    }                     
}
