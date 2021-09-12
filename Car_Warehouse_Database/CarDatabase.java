import java.util.*;
import java.io.*;
/**
 *Car Database class has an instance of Car object stored in an array of list. This class reads the file, creates an object of car class and stores it in the array of lists. 
 *Operations perfomred on this class are add car, delete car, edit car, search a car and many more
 *
 * @author (Kapish Kuchroo)
 * @version (v3, 27th May 2021)
 */
public class CarDatabase
{
    private ArrayList<Car> cars;
    
    /**
     * Default Constructor for reading contents from file and creating an array list of car objects
     */
    public CarDatabase()
    {
        cars = new ArrayList<Car>();
        readCarFile();
    }
    
    /**
     * Adding a new object to the array list of cars
     * @param registrationNumberCar 
     * @param yearMadeCar
     * @param colourOfCar
     * @param makerOfCar
     * @param modelOfCar
     * @param priceOfCar
     */
    public void addCar(String registrationNumberCar, int yearMadeCar, String[] colourOfCar, String makerOfCar, String modelOfCar, int priceOfCar)
    {
        cars.add(new Car(registrationNumberCar, yearMadeCar, colourOfCar, makerOfCar, modelOfCar, priceOfCar));
    }

    /**
     * It recevies a call from carwarehouse class and a string is passed as a choice of registration number entered by user.
     * 
     * @param userChoiceRegistrationNumber String input provided by user
     * @return boolean carFound 
     */
    public boolean deleteCar(String userChoiceRegistrationNumber)
    {
        boolean carFound = false;
        for (int i = 0; i < cars.size(); i++) 
        {
            Car currentCar = cars.get(i);
            if (currentCar.getRegistrationNumber().equals(userChoiceRegistrationNumber))
            {   
                carFound = true;
                cars.remove(currentCar);
            }
        }
        return carFound;
    }
    
    /**
     * Displaying the current cars of array list by itterating over each car object of the array list.
     */
    public void displayCars()
    {
        for (Car currentCar: cars)
            System.out.println(currentCar.display());
    }
    
    public Car getCarByRegistrationNumber(String registrationNumber)
    {
        for (int i = 0; i < cars.size(); i++) 
        {
            Car currentCar = cars.get(i); 
            if (currentCar.getRegistrationNumber().equals(registrationNumber))
                return currentCar;
        }
        return null;
    }
    
    /**
     * getter method returns the current car objects of array list
     * @return cars array list of cars 
     */
    
    public ArrayList<Car> getCarList() 
    {
        return cars;
    }
    
    /**
     * Reading the file usedcars.txt and create a new Car object, add it to the array list cars
     */
    
    public void readCarFile() 
    {
        try 
        {
            FileReader reader = new FileReader("usedcars.txt");
            try 
            {
                Scanner parser = new Scanner(reader);
                while (parser.hasNextLine())
                {
                    String line = parser.nextLine();
                    String[] word = line.split(",");
                    
                    String regisNo = word[0];
                    int yearMadeCar = Integer.parseInt(word[1]);
                    String[] colourOfCar = new String[]{word[2], word[3], word[4]};
                    String makerOfCar = word[5];
                    String modelOfCar = word[6];
                    int priceOfCar = Integer.parseInt(word[7]);
                    Car car = new Car(regisNo, yearMadeCar, colourOfCar, makerOfCar, modelOfCar,priceOfCar);
                    cars.add(car);
                }
            } 
            catch (Exception exception) 
            {
                System.out.println("Error: Unknown");
            } 
            finally 
            {
                reader.close();
            }
        } 
        catch (FileNotFoundException exception) 
        {
            System.out.println("Error: File cant be found");
        } 
        catch (IOException exception) 
        {
            System.out.println("Error: cant close the file");
        }
    }
    
    /**
     * method accepts a integer value and returns the cars which are validated under the number of car age year
     * @param carAge integer carAge from the user
     * @return carFound  if car Found return true
     */
    public boolean searchByCarAge(int carAge)
    {
        boolean carFound = false;
        for (int i = 0; i < cars.size(); i++)
        {
            Car currentCar = cars.get(i);
            if (2021 - currentCar.getYearMade() <= carAge)
            {
                carFound = true;
                System.out.println(currentCar.display());
            }
        }
        return carFound;
    }
    
    /**
     * Method receives a strings of user entered maker and model and checks the database to find a match for the same.
     * @param userChoiceMaker String value of user choice of maker
     * @param userChoiceModel String value of user choice of model
     * @return carsFound array list of cars found in the cardatabase
     */
    
    public ArrayList<Car> searchByMakerModel(String userChoiceMaker, String userChoiceModel)
    {
        ArrayList<Car> carsFound = new ArrayList();
        for (int i = 0; i < cars.size(); i++) 
        {
            Car currentCar = cars.get(i); // Car object is stored while itterating over list of cars objects.
            if (currentCar.getCarMaker().equals(userChoiceMaker) && currentCar.getCarModel().equals(userChoiceModel))
                carsFound.add(currentCar); //prints the complete details for the searched car
        }
        return carsFound;
    }
    
    
    /**
     * method prints all the cars which exist in the choices entered by user.
     * @param validUserChoice minimum maximum values
     * @return boolean 
     */
    
    public boolean searchByPrice(int[] validUserChoice)
    {
        boolean carFound = false;
        for (int i = 0; i < cars.size(); i++)
        {
            Car currentCar = cars.get(i);
            if (currentCar.getCarPrice() >= validUserChoice[0] && currentCar.getCarPrice() <= validUserChoice[1])
            {
                carFound = true;
                System.out.println(currentCar.display());
            }
        }
        return carFound;
    }
    
    /**
     * method searches a car by registration number entered by user
     * @param userChoiceRegistrationNumber
     * @return boolean (true or false )
     */
    public boolean searchByRegistrationNumber(String userChoiceRegistrationNumber)
    {
        
        for (int i = 0; i < cars.size(); i++) 
        {
            Car currentCar = cars.get(i); 
            if (currentCar.getRegistrationNumber().equals(userChoiceRegistrationNumber))
            {
                System.out.println(currentCar.display()); 
                return true;
            }
        }
        return false;
    }
    
    /**
     * method is called when user exits the program using option 5 and changes need to be written to the text file usedcars.txt
     */
    public void writeLinesToFile()
    {
        PrintWriter outputFile = null;
        try
        {
            outputFile = new PrintWriter("usedcars.txt");
            
            for (Car currentCar: cars)
            {
                outputFile.println(currentCar.display());
            }
        }
        catch (IOException execption)
        {
            System.out.println("Unable to save to usedcars.txt");
        }
        finally
        {
            if (outputFile!= null)
            {
                System.out.println("Closing the file now. Please check usedcars.txt for the updated database information");
                outputFile.close();
            }
            
            else
                System.out.println("PrintWriter not open");
        }
    }
}
