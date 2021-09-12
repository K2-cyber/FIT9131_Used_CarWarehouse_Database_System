import java.util.*;
/**
 * Valdiation class helpds validate all the requiremenets specified such as registration number , colour of the car, model of the car and so on.
 * We are using static methods for verification which can be called anywhere in the program. If the condition is true then boolean is returned and program 
 * is further executed else false is returned and neccesary actions are taken.
 *
 * @author (Kapish Kuchroo)
 * @version (v3, 27th May 2021)
 */
public class Validation
{
    
    /**
     * Empty Constructor of class Validation
     */
    public Validation() 
    {
    }

    /**
     * Static method available to validate the inputs for a car object. This method is a class method and available to all the other classes.
     *
     * @param registrationNumberCar registration number of car
     * @param yearMadeCar           car made year
     * @param colourOfCar           colours of car in an array
     * @param makerOfCar            String maker of car
     * @param modelOfCar            String model of car 
     * @param priceOfCar            price of car
     * @return boolean              (true or false)
     */
    public static boolean validateCar(String registrationNumberCar, int yearMadeCar, String[] colourOfCar, String makerOfCar, String modelOfCar, int priceOfCar)
    {
        if (regisNoCheck(registrationNumberCar) && yearMadeCheck(yearMadeCar) && carColourCheck(colourOfCar) && makerOfCarCheck(makerOfCar) && modelOfCarCheck(modelOfCar) 
        && priceOfCarCheck(priceOfCar)) 
        
            return true;
        else
            return false;
    }
    
    /**
     * Static method is used to check if the entered entry by user for validateCarAge method is correct or not
     * @param carAge
     * @return boolean   (true or false)
     */
    public static boolean carAgeCheck(int carAge)
    {
        return carAge >= 0 && carAge <= 2021;
    }
    
     /**
     * Static method used to validate the colour of a Car.
     * @param colourCar  car colour
     * @return boolean  (true or false)
     */
    public static boolean carColourCheck(String[] colourCar)
    {
        return colourCar.length > 0 && colourCar.length <= 3; 
    }
    
    /**
     * Thie method is used where there is need to take input continuosly from user and convert the string to integer.
     * @return Integer.parseInt(userChoiceString) converts the string to number
     */
    public static int getAndConvertStringToInt()
    {
        while(true)
        {
            Scanner keyBoardInput = new Scanner(System.in);
            try
            {
                String userChoiceString = keyBoardInput.nextLine();
                return Integer.parseInt(userChoiceString);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Enter an integer value only");
            }
        }
    }
    
    /**
     * Static method used to validate the maker of a Car.
     * @param makerOfTheCar  maker of the car
     * @return boolean     (true or false)
     */
    public static boolean makerOfCarCheck(String makerOfTheCar)
    {
        return makerOfTheCar.trim().length() > 0;
    }
    
    /**
     * Static method used to validate the model of a Car.
     * @param modelOfTheCar model of the car is given by user
     * @return boolean (true or false)
     */
    public static boolean modelOfCarCheck(String modelOfTheCar)
    {
        return modelOfTheCar.trim().length() > 0;
    }
    
    /**
     * Static method used to validate the price of a Car.
     * @param priceOfTheCar car price is entered by user
     * @return boolean (true or false)
     */
    public static boolean priceOfCarCheck(int priceOfTheCar)
    {
        return priceOfTheCar >= 500 && priceOfTheCar <= 30000;
    }
    
    /**
     * Static method used to validate the registration number of a Car.
     * @param regisNumber   registration number of car
     * @return boolean      (true or false)
     */
    public static boolean regisNoCheck(String regisNumber) 
    {
        return regisNumber.trim().length() > 0 && regisNumber.trim().length() < 7;
    }
    
    /**
     * Static method to validate validateCarPrice mthod to check for correct inputs by user.
     * 
     * @param minPrice
     * @param maxPrice
     * @return boolean (true or false)
     */
    
    public static boolean validateCarPriceRange(int minPrice, int maxPrice)
    {
        return minPrice > 0 && maxPrice > 0 && maxPrice >= minPrice;
    }
    
    /**
     * Static method used to validate the made year of a Car.
     * @param madeYearCar    car made year
     * @return boolean       (true or false)
     */
    public static boolean yearMadeCheck(int madeYearCar)
    {
        return madeYearCar > 1949 && madeYearCar < 2022;
    }
}
