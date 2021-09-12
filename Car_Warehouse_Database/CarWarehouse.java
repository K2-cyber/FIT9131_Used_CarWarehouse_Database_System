import java.util.*;
import java.io.*;
/**
 * CarWarehouse class is front facing class to the user, where he will input the values to see the repititive Menu.
 * This is also a controller class which means, the CarDatabase and CarMakerDatabase classe's instances are used in this class to suffice the needs of the assessment. 
 *
 * @author (Kapish Kuchroo)
 * @version (v3, 27th May 2021)
 */
public class CarWarehouse
{
    CarDatabase carDatabase;
    CarMakerDatabase carMakerDatabase;
    /**
     * Constructor for objects of class CarWarehouse which consists of two instances from CarDatabase and CarMakerDatabase
     */
    public CarWarehouse()
    {
        carDatabase = new CarDatabase();
        carMakerDatabase = new CarMakerDatabase();
    }
    
    /**
     * prompts for the input of all the fields required to add a new car to the database and if successful it adds them to database.
     */
    private void addCarDatabase()
    {
        int userChoiceCarYearInteger = 0;
        int userChoiceInteger = 0;
        String[] userChoiceColourString = new String[3];
        int userChoiceCarPrice = 0;
        String userChoiceRegistrationNumber = "";
        int userChoiceCarYearValidInteger = 0;
        int userChoiceCarPriceInteger = 0;
        
        for (int i = 0; i < 3; i++)
        {
            userChoiceColourString[i] = "";
        }
        
        while (true)
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the registration number of the car");
            String userRegistrationNumber = keyboard.nextLine();
            
            if (Validation.regisNoCheck(userRegistrationNumber))
            {
                userChoiceRegistrationNumber = userRegistrationNumber;
                if (carDatabase.searchByRegistrationNumber(userChoiceRegistrationNumber))
                {
                System.out.println("Registration Number exsists in database. No duplicates allowed.");
                continue;
                }
                break;
            }
            else
                continue;
        }
        
        while (true)
        {
            System.out.println("Enter the car year : valid choices are between 1950 & 2021");
            userChoiceCarYearInteger = Validation.getAndConvertStringToInt(); 
            if (Validation.yearMadeCheck(userChoiceCarYearInteger))
            {
                userChoiceCarYearValidInteger = userChoiceCarYearInteger;
                break;
            }
            else
                continue;
        }
        
        while (true)
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the number of car colours of car: 1, 2 or 3 are the valid options");
            String userChoice = keyboard.nextLine();
            if (userChoice.matches("[1-3]"))
            {
                userChoiceInteger = Integer.parseInt(userChoice);
                
                for (int i = 0; i < userChoiceInteger; i++)
                {
                    System.out.println("Enter the " + String.valueOf(i+1) + " Colour");
                    String userChoiceColour = keyboard.nextLine();
                    userChoiceColourString[i] = userChoiceColour;
                }
                break;
            }
            else
                continue;
        }
        
        ArrayList<String> makerModelInstance = carMakerDatabase.getMakerModel(); 
        String userChoiceMakerCar = makerModelInstance.get(0);
        String userChoiceModelCar = makerModelInstance.get(1);
        
        while(true)
        {
            
            System.out.println("Enter the price of the car: Validate car price ranges between 500 and 30000 AUD");
            userChoiceCarPrice = Validation.getAndConvertStringToInt();
            if (Validation.priceOfCarCheck(userChoiceCarPrice))
            {
                userChoiceCarPriceInteger = userChoiceCarPrice;
                break;
            }
            
            else
                continue;
        }
       
        carDatabase.addCar(userChoiceRegistrationNumber, userChoiceCarYearValidInteger, userChoiceColourString, userChoiceMakerCar, userChoiceModelCar,userChoiceCarPriceInteger);
    }
    
    /**
     * method deletes a car found by registration number entered by user, registration number is checked from carDatabase class
     * 
     */
    private void deleteCar()
    {
        String userChoiceRegistrationNumber = "";
        userChoiceRegistrationNumber = getValidateRegistrationNumber();
        boolean carFound = false;
        carFound = carDatabase.deleteCar(userChoiceRegistrationNumber);
        if(!carFound)
            System.out.println("There is no car with the registration number you entered!");
        else
            System.out.println("Car with registration number " + userChoiceRegistrationNumber + " is deleted");
    }
    
    /**
     * method edits a car by registration number entered by user. Editing method gives you three options to edit car colour, car price or car colour and price
     */
    private void editCar()
    {
        int userChoiceInteger = 0;
        String userChoiceRegistrationNumber = "";
        userChoiceRegistrationNumber = getValidateRegistrationNumber();
        
        if (carDatabase.searchByRegistrationNumber(userChoiceRegistrationNumber))
        {
            Car currentCar = carDatabase.getCarByRegistrationNumber(userChoiceRegistrationNumber);
            System.out.println("Database system has found a match for registration number you have entered!!");
            Scanner keyBoardInput = new Scanner(System.in);
            System.out.println("Choose an option for editing");
            System.out.println("###Choose option (1) for only editing Car Colours###");
            System.out.println("***Choose option (2) for only editing Car Price***");
            System.out.println("!!!Choose option (3) for editing both Car Colour and Car Price!!!");
            String userChoiceString = keyBoardInput.nextLine();
            if (userChoiceString.matches("[1-3]"))
            {
                userChoiceInteger = Integer.parseInt(userChoiceString);
                if (userChoiceInteger == 1)
                {
                    String[] carColour = editCarColour();
                    currentCar.setCarColour(carColour);
                }
                
                else if (userChoiceInteger == 2)
                {
                    int carPrice = editCarPrice();
                    currentCar.setCarPrice(carPrice);
                }
                
                else
                {
                    String[] carColours = editCarColour();
                    currentCar.setCarColour(carColours);
                    int carPrices = editCarPrice();
                    currentCar.setCarPrice(carPrices);
                }
            }
        }
        else
            System.out.println("Car Not Found in the database!");
    }
    
    /**
     * method edits car colours entered by user depndeing on how many colours does the user want to change.
     */
    
    private String[] editCarColour()
    {
        String[] editCarColour; 
        int userChoiceInteger = 0;
        Scanner keyBoardInput = new Scanner(System.in);
        System.out.println("How many colours do you want to change??");
        System.out.println("Enter a choice between 1 and 3");
        System.out.println("###Choose option (1) for only editing Single Car Colour###");
        System.out.println("***Choose option (2) for only editing Two Car Colours***");
        System.out.println("!!!Choose option (3) for editing all Car Colours!!!");
        
        while (true) 
        {
            String userChoiceString = keyBoardInput.nextLine();
            if (userChoiceString.matches("[1-3]"))
            {
                userChoiceInteger = Integer.parseInt(userChoiceString);
                
                if (userChoiceInteger == 1)
                {
                    editCarColour = new String[3];
                    for (int i = 0; i < 3; i++) 
                    {
                        editCarColour[i] = "";
                    }
                    Scanner colourInput = new Scanner(System.in);
                    System.out.println("Enter a single colour");
                    String userChoice = colourInput.nextLine();
                    editCarColour[0] = userChoice;
                    return editCarColour;
                }
                
                else if (userChoiceInteger == 2)
                {
                    editCarColour = new String[3];
                    for (int i = 0; i < 3; i++) 
                    {
                        editCarColour[i] = "";
                    }
                    Scanner colourInput = new Scanner(System.in); 
                    System.out.println("Enter first colour");
                    String userChoice = colourInput.nextLine();
                    Scanner secondColourInput = new Scanner(System.in); 
                    System.out.println("Enter second colour");
                    String secondUserChoice = secondColourInput.nextLine();
                    editCarColour[0] = userChoice;
                    editCarColour[1] = secondUserChoice;
                    return editCarColour;
                }
                
                else
                {
                    editCarColour = new String[3];
                    for (int i = 0; i < 3; i++) 
                    {
                        editCarColour[i] = "";
                    }
                    Scanner colourInput = new Scanner(System.in); 
                    System.out.println("Enter first colour");
                    String userChoice = colourInput.nextLine();
                    Scanner secondColourInput = new Scanner(System.in); 
                    System.out.println("Enter second colour");
                    String secondUserChoice = secondColourInput.nextLine();
                    Scanner thirdColourInput = new Scanner(System.in);
                    System.out.println("Enter third colour");
                    String thirdUserChoice = thirdColourInput.nextLine();
                    editCarColour[0] = userChoice;
                    editCarColour[1] = secondUserChoice;
                    editCarColour[2] = thirdUserChoice;
                    return editCarColour;
                } 
            }
            else
            System.out.println("Enter a choice between 1 and 3");
        }
    }    
    
    /**
     * method edits the car price with a valid price of car
     * 
     * @return userChoiceInteger returns a valid car price
     */
    
    private int editCarPrice()
    {
        int userChoiceInteger = 0;
        Scanner keyBoardInput = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter the new price of the Car");
            userChoiceInteger = Validation.getAndConvertStringToInt();
            if (Validation.priceOfCarCheck(userChoiceInteger))
                return userChoiceInteger;
            else
                System.out.println("Invalid values entered for car price");
        }
    }
    
    /**
     * takes the response from the user and returns it if it is a valid response.
     * @return userChoice choice entered by user
     */
    private int getMainMenuResponse()
    {
        int userChoice = 0;
        String choiceString = "";
        Scanner consoleInput = new Scanner(System.in);
        System.out.println("Choose an option between 1 and 5 from main menu: ");
        userChoice = Validation.getAndConvertStringToInt();
        if (userChoice > 0 && userChoice < 6)
            return userChoice;
        else
        {
            System.out.println("You can only choose between number 1 & 5 for main menu");
            return 0;
        }
    }
    
    /**
     * takes the response from the user and returns it if it is a valid response.
     * @return userChoiceInteger choice entered by user
     */
    
    private int getSearchMenuResponse()
    {
        int userChoiceInteger = 0;
        String choiceString = "";
        Scanner consoleInput = new Scanner(System.in);
        System.out.println("Choose an option between 1 and 5 from search menu: ");
        userChoiceInteger = Validation.getAndConvertStringToInt();
        if (userChoiceInteger > 0 && userChoiceInteger < 6)
            return userChoiceInteger;
        else
        {
            System.out.println("You can only choose between number 1 & 5 for search menu");
            return 0;
        }
    }
    
    /**
     * method validates the registration number for a car and also validates the input for a valid registration number.
     * @return userChoice which is a valid registration number 
     */
    private String getValidateRegistrationNumber()
    {
        Scanner keyBoardInput = new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter a valid resgistration Number of 6 characters");
            String userChoice = keyBoardInput.nextLine();
            if (Validation.regisNoCheck(userChoice))
                return userChoice;
        }
    }
    
     /**
     * print menu prints the main menu of the porgram to the user.
     */
    private void printMainMenu() 
    {
        System.out.println("(1) Search Cars");
        System.out.println("(2) Add Cars");
        System.out.println("(3) Delete Cars");
        System.out.println("(4) Edit Cars");
        System.out.println("(5) Exit Cars");
    }
    
    /**
     * print search menu prints of the porgram to the user.
     */
    private void printSearchCarMenu()
    {
        System.out.println("(1) Search Cars BY REGISTRATION NUMBER");
        System.out.println("(2) Search Cars BY MAKE AND MODEL");
        System.out.println("(3) Search Cars BY CAR AGE");
        System.out.println("(4) Search Cars BY PRICE RANGE");
        System.out.println("(5) BACK TO MAIN MENU");
    }
    
    /**
     * processes the main menu response from the user which has been entered in getMainMenuResponse
     */
    public void processMainMenuResponse()
    {
        while (true)
        {
            printMainMenu();
            int userResponse = getMainMenuResponse();
            if (userResponse > 0)
            {
                if (userResponse == 1)
                
                  processSearchMenuResponse();
                
                
                else if (userResponse == 2)
                
                    addCarDatabase();
                
                
                else if (userResponse == 3)
                
                    deleteCar();
                
                
                else if (userResponse == 4)
                
                    editCar();
                
                
                else if (userResponse == 5) 
                {
                    System.out.println("EXITING DATABASE SYSTEM....");
                    carDatabase.writeLinesToFile();
                    break;
                }
                else
                    System.out.println("Enter a number between 1 and 5 only"); 
            }   
        }
    }
    
    /**
     * processes the search responses by the user which will give the updated information about the database.
     */
    private void processSearchMenuResponse()
    {
        while (true)
        
        {
            printSearchCarMenu();
            int userResponse = getSearchMenuResponse();
            if (userResponse > 0)
            {
                if (userResponse == 1)
                
                    searchByRegistrationNumber();
                
                
                else if (userResponse == 2)
                
                    searchByMakerModel();            
                
                
                else if (userResponse == 3)
                
                    searchByCarAge();
                
                
                else if (userResponse == 4)
                
                    searchByPrice();
                
                
                else if (userResponse == 5)
                
                    break;
                
                else
                    System.out.println("Enter a number between 1 and 5");
            }
            
        }
    }
    
    /**
     * method gets the input for a valid car age and checks carDatabase for valid cars.
     */
    private void searchByCarAge() 
    {
        int validUserChoice = 0;
        validUserChoice = validateCarAge();
        boolean carFound = false;
        carFound = carDatabase.searchByCarAge(validUserChoice);
        if(!carFound)
            System.out.println("Year entered is not a match in database!");
    }
    
    /**
     * Method gets list of car maker model from carMakerDatabase. Stores a particular maker and model in a string, checks for the maker and model in carDatabase class. 
     * Prints if found.
     */
    private void searchByMakerModel()
    {
        ArrayList<String> carMakerDetails = carMakerDatabase.getMakerModel();
        String userChoiceMaker = carMakerDetails.get(0); //maker of the car is returned
        String userChoiceModel = carMakerDetails.get(1); //model of the car is returned
        ArrayList<Car> carsFound = carDatabase.searchByMakerModel(userChoiceMaker, userChoiceModel);
        if (carsFound.size() == 0)
            System.out.println("No car Found with that maker model");
        for(int i = 0; i < carsFound.size(); i++)
        {
            System.out.println(carsFound.get(i).display());
        }
    }
    
    /**
     * method searches a car by car price entered by user
     */
    private void searchByPrice()
    {
        int[] validUserChoice = validateCarPrice();
        boolean carFound = false;
        carFound = carDatabase.searchByPrice(validUserChoice);
        if(!carFound)
            System.out.println("Price entered is not a match in database!");
    }
    
    private void searchByRegistrationNumber()
    {
        String userChoiceRegistrationNumber = "";
        userChoiceRegistrationNumber = getValidateRegistrationNumber();
        System.out.println("registration Number entered is " + userChoiceRegistrationNumber);
        boolean carFound = carDatabase.searchByRegistrationNumber(userChoiceRegistrationNumber);
        if (! carFound)
            System.out.println("Car not found in database");
    }
    
    /**
     * this methods returns userChoiceInteger to searchBYCarAge method to verify if a user entered correct values
     * 
     * @return userChoiceInteger valid car age
     */
    private int validateCarAge()
    {
        int userChoiceInteger = 0;
        Scanner keyBoardInput = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter a valid car age year");
            userChoiceInteger = Validation.getAndConvertStringToInt(); 
            return userChoiceInteger;
        }   
    }
    
    /**
     * method validates the car price input by the user and sends the value of the function to searchByPrice
     * 
     * @return userChoiceInteger valid integer
     */
    
    private int[] validateCarPrice()
    {
        int userChoiceInteger[] = new int[2];
        Scanner keyBoardInput = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter a minimum car price");
            int userChoiceIntegerMin = Validation.getAndConvertStringToInt();
            System.out.println("Enter a maximum car price");
            int userChoiceIntegerMax = Validation.getAndConvertStringToInt();
            if (Validation.validateCarPriceRange(userChoiceIntegerMin, userChoiceIntegerMax))
            {
                userChoiceInteger[0] = userChoiceIntegerMin;
                userChoiceInteger[1] = userChoiceIntegerMax;
                return userChoiceInteger;
            }
            
            else
                System.out.println("You can only choose between numbers for a valid car price");
        }
    }
}
