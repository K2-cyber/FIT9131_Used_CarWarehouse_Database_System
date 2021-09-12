import java.util.*;
/**
 * Class template of CarMaker holds the behaviour and attributes of the carMaker class which are required for the assignment.
 * It has a paramterized and a default constructor.
 * Class sets and gets different field values such as reigistration number, year Made, car Colour etc.
 * Setters and getters methods are used for the purpose of providing possible information about a single car object.
 *
 * @author (Kapish Kuchroo)
 * @version (v3, 27th May 2021)
 */
public class CarMaker
{
    private String makerName; //manufacturer Name
    private ArrayList<String> modelName; //ArrayList of Strings of ModelName of Manufacturer

    /**
     * Default Constructor for objects of class CarMaker.
     */
    public CarMaker()
    {
        makerName = "";
        modelName = new ArrayList<String>();
    }
    
    /**
     * Parametrised Constructor for objects of class CarMaker.
     * @param newMakerName String type maker name of a car
     * @param newModelName Array List of strings of model names
     */
    public CarMaker(String newMakerName, ArrayList<String> newModelName)
    {
        makerName = newMakerName;
        modelName = newModelName;
    }
    
    /**
     * 
     * Getter for ModelName of the CarMaker
     * @return makerName maker name of the car is returned
     * 
     */
    public String getMakerName()
    {
        return makerName;
    }
    
    /**
     * 
     * Getter for ModelName of the CarMaker
     * @return modelName retuns the array list of models
     * 
     */
    public ArrayList<String> getModelName()
    {
        return modelName;
    }
    
    /**
     * 
     * Setter for MakerName of the Car.
     * @param newMakerName String value for a new maker name
     * 
     */
    public void setMakerName(String newMakerName)
    {
        makerName = newMakerName;
    }
    
    /**
     * Setter for ModelName of the CarMaker
     * @param newModelName String of a new model name of a car
     * 
     */
    public void setModelName(String newModelName)
    {
        if (newModelName.trim().length() > 0)
            modelName.add(newModelName);
        else
            System.out.println("Invalid Model Name for the CarManufacturer");
    }
}
