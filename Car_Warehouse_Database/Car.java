import java.util.*;
/**
 * Class template of car holds the behaviour and attributes of the Car class which are required for the assignment.
 * It has a paramterized and a default constructor.
 * Class sets and gets different field values such as reigistration number, year Made, car Colour etc.
 * Setters and getters methods are used for the purpose of providing possible information about a single car object.
 *
 * @author (Kapish Kuchroo)
 * @version (v3, 27th May 2021)
 */
public class Car
{
    private String registrationNumber;
    private int yearMade;
    private String[] carColour;
    private String carMaker;
    private String carModel;
    private int carPrice;
    /**
     * Default constructor will assign default values to the all the fields of this class.
     */
    
    public Car()
    {
        registrationNumber = "DEFAULT VALUES INSERTED";
        yearMade = 000;
        carColour = new String[3];
        carColour[0] = "DEFAULT VALUES";
        carColour[1] = "DEFAULT VALUES";
        carColour[2] = "DEFAULT VALUES";
        carMaker = "DEFAULT VALUES";
        carModel = "DEFAULT VALUES";
        carPrice = 000;
    }
    
    /**
     * Parametrized constructor of Car class which takes input and validates them according to the requirements of the assessment. 
     * If validation passess then it stores the field value of class.
     * 
     * @param registrationNumberCar registration number of car
     * @param yearMadeCar           car made year
     * @param colourOfCar           colours of car in an array
     * @param makerOfCar            String maker of car
     * @param modelOfCar            String model of car 
     * @param priceOfCar            price of car
     * 
     * 
     */
    
    public Car(String registrationNumberCar, int yearMadeCar, String[] colourOfCar, String makerOfCar, String modelOfCar, int priceOfCar)
    {
        if (Validation.validateCar(registrationNumberCar, yearMadeCar, colourOfCar, makerOfCar, modelOfCar, priceOfCar)) 
        {
            registrationNumber = registrationNumberCar;
            yearMade = yearMadeCar;
            carColour = colourOfCar;
            carMaker = makerOfCar;
            carModel = modelOfCar;
            carPrice = priceOfCar;
        }
        
        else
        {
           registrationNumber = "DEFAULT VALUES INSERTED";
            yearMade = 000;
            carColour = new String[3];
            carColour[0] = "DEFAULT VALUES";
            carColour[1] = "DEFAULT VALUES";
            carColour[2] = "DEFAULT VALUES";
            carMaker = "DEFAULT VALUES";
            carModel = "DEFAULT VALUES";
            carPrice = 000;
            System.out.println("Validation Failed!! Please check the following: registrationNumber, yearMade, carColour, carMaker, carModel, carPrice");
        }
    }
    
    /**
     * Display Method - for displaying car details
     * @return registrationNumberCar registration number of car
     * @return yearMadeCar           car made year
     * @return colourOfCar           colours of car in an array
     * @return makerOfCar            String maker of car
     * @return modelOfCar            String model of car 
     * @return priceOfCar            price of car
     * @return boolean              (true or false)
     */
    
    public String display()
    {
        return (registrationNumber + "," + yearMade + "," + printCarColour(carColour) + "," + carMaker + "," + carModel + "," + carPrice );
    }
    
    /**
     * Getter Method - for displaying registration number of car
     * @return registrationNumber registration number of car
     */
    
    public String getRegistrationNumber()
    {
        return registrationNumber;
    }
    
    /**
     * Getter Method - for displaying colour of car
     * @return carColour colours of car in an array
     */
    public String[] getCarColour()
    {
        return carColour;
    }
    
    /**
     * Getter Method - for displaying manufacturer of car
     * @return carMaker maker of the car
     */
    public String getCarMaker()
    {
        return carMaker;
    }
    
    /**
     * Getter Method - for displaying model of car
     * @return carModel model of the car
     */
    public String getCarModel()
    {
        return carModel;
    }
    
    /**
     * Getter Method - for displaying made year of car
     * @return yearMade car made year
     */
    public int getYearMade()
    {
        return yearMade;
    }
    
    /**
     * Getter Method - for displaying price of car
     * @return carPrice price of the car
     */
    public int getCarPrice()
    {
        return carPrice;
    }
    
    /**
     * Setter Method - for setting price of car
     * @param newCarPrice setting the car price
     */
    public void setCarPrice(int newCarPrice)
    {
        if (newCarPrice >= 500 && newCarPrice <= 30000)
            carPrice = newCarPrice;
        else
            System.out.println("Invalid values for car price!");
    }
    
    /**
     * Setter Method - for setting colour of car
     * @param newCarColour setting the new colours of car
     */
    public void setCarColour(String[] newCarColour) 
    {
        if (newCarColour.length == 1)
            carColour[0] = newCarColour[0];
        else if (newCarColour.length == 2)
        {
            carColour[0] = newCarColour[0];
            carColour[1] = newCarColour[1];
        }
        else if (newCarColour.length == 3)
        {
            carColour[0] = newCarColour[0];
            carColour[1] = newCarColour[1];
            carColour[2] = newCarColour[2];
        }
        else
            System.out.println("Incompatible value for the car colour");
    }
    
    /**
     * Display method prints the car colour bsed on the values of a car colour
     * @param carColour string of car colours
     * @return carColourResult print in the specific format as required
     */
    public String printCarColour(String[] carColour)
    {
        String carColourResult = "";
        for (int i = 0; i < carColour.length - 1; i++)
        {
            carColourResult = carColourResult + carColour[i] + ",";
        }
        carColourResult = carColourResult + carColour[carColour.length - 1];
        return carColourResult;
    }
}

