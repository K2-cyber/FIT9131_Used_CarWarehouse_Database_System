import java.util.*;
import java.io.*;
/**
 * Car Maker Database class has an instance of Car Maker object stored in an array of list. This class reads file, 
 * creates an object of Car Maker class and stores it in the array of lists. Operations performed on this class are search a car by Maker and Model.
 *
 * @author (Kapish Kuchroo)
 * @version (v3, 27th May 2021)
 */
public class CarMakerDatabase
{
    private ArrayList<CarMaker> carMakers; //array list of makers of car
    
    /**
     *  Constructor for objects of class CarMakerDatabase
     *  
     *  @param DatabaseOfCar
     */
    public CarMakerDatabase()
    {
        carMakers = new ArrayList<CarMaker>();
        readCarMakerFile();   
    }
    
    /**
     * getter method the array list of carmakers
     * @return carMaker array List of car makers is returned
     */
    public ArrayList<CarMaker> getCarMakers()
    {
        return carMakers;
    }
    
    /**
     * methods takes the input from the user, maker name and model name, adds it to the array list makerModel. This returned value is sent to searchByMakerModel method
     * @return makerModel  maker,model are stored in an array list at 0th and 1st position
     */
    public ArrayList<String> getMakerModel()
    {
        int userChoiceInteger = 0;
        int newUserChoiceInteger = 0;
        String makerName = "";
        String modelName = "";
        ArrayList<String> makerModel = new ArrayList<String>();
        Scanner keyBoardInput = new Scanner(System.in);
        
        for (int i = 0; i < carMakers.size(); i++)
        {
            System.out.println(String.valueOf(i+1) + "." + carMakers.get(i).getMakerName()); //i+1 means (0+1). maker name
        }
        
        while (true) 
        {
            System.out.println("Enter a number between 1 and 7 for car maker");
            String userChoiceString = keyBoardInput.nextLine();
            if (userChoiceString.matches("[1-7]"))
            {
                userChoiceInteger = Integer.parseInt(userChoiceString);
                makerName = carMakers.get(userChoiceInteger - 1).getMakerName(); // -1 is done to balance the input given by user
                break;
            }
        }
        
        for (int i = 0; i < carMakers.get(userChoiceInteger - 1).getModelName().size(); i++)
        {
            System.out.println(String.valueOf(i+1) + "." + carMakers.get(userChoiceInteger - 1).getModelName().get(i));
        }
        
        while (true)
        {
            System.out.println("Enter a number to select car model");
            newUserChoiceInteger = Validation.getAndConvertStringToInt();
            ArrayList<String> allModels = carMakers.get(userChoiceInteger - 1).getModelName();
            if (allModels.size() < newUserChoiceInteger)
                System.out.println("Enter a number between 1 and "+ String.valueOf(allModels.size()));
            else
                break;
        }
        
        modelName = carMakers.get(userChoiceInteger - 1).getModelName().get(newUserChoiceInteger - 1);
        makerModel.add(makerName);
        makerModel.add(modelName);
        return makerModel;
    }
    
    /**
     * Reading the file carmakers.txt and create a new CarMaker object, add it to the array list carmakers
     */
    public void readCarMakerFile() 
    { 
        try {
            FileReader reader = new FileReader("carmakers.txt");
            try 
            {
                Scanner parser = new Scanner(reader);
                while (parser.hasNextLine())
                {
                    String line = parser.nextLine();
                    String[] word = line.split(",");
                    String carManufacturer = word[0];
                    ArrayList<String> carModels = new ArrayList<String>();
                    
                    for(int i=1;i<word.length;i++)
                    {
                        carModels.add(word[i]);
                    }
                    
                    CarMaker carMaker = new CarMaker(carManufacturer, carModels);
                    carMakers.add(carMaker);
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
}

