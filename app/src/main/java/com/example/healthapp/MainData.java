

package com.example.healthapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

@SuppressWarnings({"UnusedDeclaration"})
public class MainData  {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    //EMPTY CONSTRUCTOR, no parameters. Used to create our current day
    public MainData() {
	
		this.name = "";
		this.weight = 0;
		this.feet = 0;
		this.inches = 0;
		this.location = "";
		this.calsEaten = 0;
		this.calsBreakfast = 0;
		this.calsLunch = 0;
		this.calsDinner = 0;
		this.calsSnack = 0;
		this.breakfast = new Hashtable<String, ArrayList<Object>>();
		this.lunch = new Hashtable<String, ArrayList<Object>>();
		this.dinner = new Hashtable<String, ArrayList<Object>>();
		this.snack = new Hashtable<String, ArrayList<Object>>();
		this.dailyWorkout=null;
		this.calsBurned = 0;
		this.day = LocalDate.now();
		this.friends =new Hashtable<String, ArrayList<Object>>();
        this.calsGoal=0;
        this.burnedGoal=0;
        this.age=0;
	}


	public int weight;
    private int feet;
    private int inches;
    private String location;

    public int getHeight() {
        return feet * 12 + inches;
    }

    public void setHeight(int feet1, int inches1){
        feet = feet1;
        inches = inches1;
    }

    /**************
     *  Nutrition *
     **************/
    private final int CALS = 0; // location of calories in the object array list
    private final int NOTES = 1; // location of notes in the object array list

    int calsEaten;
    int calsGoal;
    int calsBreakfast;
    int calsLunch;
    int calsDinner;

    public int getWeight() {
        return weight;
    }

    public int getCalsEaten() {
        return calsEaten;
    }

    public int getCalsBreakfast() {
        return calsBreakfast;
    }

    public int getCalsLunch() {
        return calsLunch;
    }

    public int getCalsDinner() {
        return calsDinner;
    }

    public int getCalsSnack() {
        return calsSnack;
    }

    int calsSnack;

    /* Meal Type Hash Variables */
    Hashtable<String, ArrayList<Object>> breakfast;
    Hashtable<String, ArrayList<Object>> lunch;
    Hashtable<String, ArrayList<Object>> dinner;
    Hashtable<String, ArrayList<Object>> snack;

    private final int BREAKFAST = 0;
    private final int LUNCH = 1;
    private final int DINNER = 2;
    private final int SNACK = 3;

    /* Functions */

    /**
     * Adds a meal item to the respective meal hashtable given a meal name, number of calories, and
     * String containing any notes.
     *
     * @param mealType Integer representing the meal type
     * @param meal String representing the meal name
     * @param calories Integer representing the calories of the meal
     * @param notes String representing the notes input
     * @return false if add failed, true if add succeeded
     */
    public boolean addMeal (Integer mealType, String meal, Integer calories, String notes ) {
        if (mealType == null || meal == null || calories == null ) { return false ;}

        Hashtable<String, ArrayList<Object>> mealTable;

        switch(mealType){
            case BREAKFAST:
                mealTable = breakfast;
                calsBreakfast += calories;
                break;

            case LUNCH:
                mealTable = lunch;
                calsLunch += calories;
                break;

            case DINNER:
                mealTable = dinner;
                calsDinner += calories;
                break;

            case SNACK:
                mealTable = snack;
                calsSnack += calories;
                break;

            default:
                return false;
        }

        //Generic ArrayList for storing mealData
        ArrayList<Object> mealData= new ArrayList<>();

        // Check if the meal name is included in the hashtable
        if (mealTable.containsKey(meal)) {
            //Get old ArrayList of old meal data
            ArrayList<Object> oldMeal = mealTable.get(meal);

            try {
                int oldCalories = (int) oldMeal.get(CALS); // Old Calories int
                String oldNotes = (String) oldMeal.get(NOTES); // Old Notes String

                mealData.add(oldCalories + calories); // new value of calories;); // Add n

                // if notes is not filled, keep old notes. Else, use new notes.
                boolean b = notes == null ? mealData.add(oldNotes) : mealData.add(notes);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        // if the meal's name dose not exist within the table, add meal to the list directly
        else {
            mealData.add(calories); // calories of the meal
            mealData.add(notes); // notes from the meal
        }

        mealTable.put(meal, mealData); // update meal information

        return true; // indicating add succeeded
    }

    /* ******************************************************************************** */
    /**
     * Simplified ways of adding each meal, which makes use of the add meal function
     *
     * @param meal String representing the meal name
     * @param calories Integer representing the calories of the meal
     * @param notes String representing the notes input
     * @return boolean representing success of failure
     */
    public boolean addBreakfast(String meal, Integer calories, String notes ) {
        return addMeal (BREAKFAST, meal, calories, notes );
    }

    public boolean addLunch(String meal, Integer calories, String notes ) {
        return addMeal (LUNCH, meal, calories, notes );
    }

    public boolean addDinner(String meal, Integer calories, String notes ) {
        return addMeal (DINNER, meal, calories, notes );
    }

    public boolean addSnack(String meal, Integer calories, String notes ) {
        return addMeal (SNACK, meal, calories, notes );
    }

    /* ******************************************************************************** */

    /**
     * Removes meal of type mealType with String "meal" from data if it exists by switching mealType
     * to Perform the proper operations on the correct meals.
     *
     * @param  mealType Integer representation of the meal type
     * @param  meal The String representing the meal name
     * @return whether item was successfully removed
     */
    public boolean removeMeal (Integer mealType, String meal) {
        // Check that the parameters are valid
        if (mealType == null || meal == null) { return false ;}

        Hashtable<String, ArrayList<Object>> mealTable; // used to switch between variables
        int calories;

        // remove calories depending on the mealType
        try {
            switch (mealType) {
                case BREAKFAST:
                    mealTable = breakfast;
                    if (!mealTable.containsKey(meal)) {
                        return false;
                    }
                    calories = (int) mealTable.get(meal).get(CALS);
                    calsBreakfast -= calories;
                    break;

                case LUNCH:
                    mealTable = lunch;
                    if (!mealTable.containsKey(meal)) {
                        return false;
                    }
                    calories = (int) mealTable.get(meal).get(CALS);
                    calsLunch -= calories;
                    break;

                case DINNER:
                    mealTable = dinner;
                    if (!mealTable.containsKey(meal)) {
                        return false;
                    }
                    calories = (int) mealTable.get(meal).get(CALS);
                    calsDinner -= calories;
                    break;

                case SNACK:
                    mealTable = snack;
                    if (!mealTable.containsKey(meal)) {
                        return false;
                    }
                    calories = (int) mealTable.get(meal).get(CALS);
                    calsSnack -= calories;
                    break;

                default:
                    return false;
            }

            mealTable.remove(meal); // remove lunch with name "meal"
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /* ******************************************************************************** */
    /**
     * Simplified ways of removing each meal, which makes use of the add meal function
     *
     * @param meal String representing the meal name
     * @param calories Integer representing the calories of the meal
     * @param notes String representing the notes input
     * @return boolean representing success of failure
     */
    public boolean removeBreakfast(String meal, Integer calories, String notes ) {
        return removeMeal (BREAKFAST, meal);
    }

    public boolean removeLunch(String meal, Integer calories, String notes ) {
        return removeMeal (LUNCH, meal);
    }

    public boolean removeDinner(String meal, Integer calories, String notes ) {
        return removeMeal (DINNER, meal);
    }

    public boolean removeSnack(String meal, Integer calories, String notes ) {
        return removeMeal (SNACK, meal);
    }

    /* ******************************************************************************** */

    /**
     * Gets the respective meal information if it exists
     *
     * @param  mealType Integer represenation fo the meal type
     * @param  mealName The String representing the meal name
     * @return ArrayList of meal or null if it's not found or something went wrong
     */
    public ArrayList<Object> getMeal (Integer mealType, String mealName) {

        if ( mealType == null || mealName == null ) { return null;}
        Hashtable<String, ArrayList<Object>> mealTable;

        // Switch mealTable to the proper meal type
        switch(mealType){
            case BREAKFAST:
                mealTable = breakfast;
                break;
            case LUNCH:
                mealTable = lunch;
                break;
            case DINNER:
                mealTable = dinner;
                break;
            case SNACK:
                mealTable = snack;
                break;
            default:
                return null;
        }

        if (mealTable != null) {
            return mealTable.get(mealName);

        } else {
            return null;
        }
    }

    /**
     * Gets the notes from the meal
     *
     * @param mealType breakfast, lunch, dinner, or snack
     * @param mealName name of the item
     * @return String of the nose from the meal
     */
    public String getMealNotes (Integer mealType, String mealName) {
        if ( mealType == null || mealName == null ) { return "Null params" ;}
            return (String) getMeal(mealType, mealName).get(NOTES);
    }

    /**
     * Gets the cals from the meal
     *
     * @param mealType breakfast, lunch, dinner, or snack
     * @param mealName name of the item
     * @return String of the nose from the meal
     */
    public Integer getMealCals(Integer mealType, String mealName) {
        if ( mealType == null || mealName == null ) { return 0;}
        return (Integer) getMeal(mealType, mealName).get(CALS);
    }

    /**
     * updates cals eaten and returns the current value of cals eaten after the update
     *
     * @return int value representing the total calories eaten this day.
     */

    public int updateCalsEaten() {
        return calsEaten = calsBreakfast + calsLunch + calsDinner + calsSnack;
    }

    /***********
     * Workout *
     ***********/
    private int calsBurned;
    int burnedGoal;

    public int getCalsGoal() {
        return calsGoal;
    }

    public void setCalsGoal(int calsGoal) {
        this.calsGoal = calsGoal;
    }

    public int getBurnedGoal() {
        return burnedGoal;
    }

    public void setBurnedGoal(int burnedGoal) {
        this.burnedGoal = burnedGoal;
    }

    //enumerated type for workout
    public enum workoutType{
    	RUN, WALK, WEIGHTS
    }
    //getters and setters for todays workout
    private workoutType dailyWorkout;
    public void setWorkout(workoutType today) {
    	dailyWorkout=today;
    }
    public workoutType getWorkout() {
    	if (dailyWorkout==null) {return null;}
    	return dailyWorkout;
    }
    
    //RUN/WALK
    
    int runWalkSteps;
    
    int runWalkTime;//in seconds? we'll have to cast whatever type our timer returns
	//to an Integer when we call the setter for this. it'll make our step calc easier
    public int getRunWalkSteps() {
		return runWalkSteps;
	}

	public void setRunWalkSteps() {
		this.runWalkSteps = this.getRunWalkTime()*(this.dailyWorkout==workoutType.WALK?2:3);
	}

	public int getRunWalkTime() {
		return runWalkTime;
	}

	public void setRunWalkTime(int runWalkTime) {
		this.runWalkTime = runWalkTime;
	}


    float avgSpeed=this.getDistance()/(float)this.runWalkTime;
    		//steps per second
    //no setter for avgspeed, calcd at runtime
    public float getAvgSpeed() {
		return avgSpeed;
	}

	int userHeight=this.getHeight();
    /*GLOBAL ASSUMPTIONS:
     * we are using the math that for the average person, their 
     * height in inches * 0.414(from the internet)
     * equals their stride length.
     * For walks, we will assume one step per second for our mock data.
     * For runs, it will be 3 steps per second.
     * If we multiply their steps by their stride length, we can calculate a 
     * distance for the activity.
     */
    float strideLength= (float)userHeight * (float)0.414;
   //no setter for distance as it is calculated at runtime
    public float getDistance() {
    	
    		return strideLength*runWalkSteps;//inches
    	
    }
    
    
    
    
    
    
    
    /***********
     * History *
     ***********/
    LocalDate day;

    /**********
     * Social *
     **********/
    Hashtable<String, ArrayList<Object>> friends;
    final int STEPS = 0;
    final int LOCATION = 1;

    public void addFriend(String name, String location, int stepsWalked) {
        ArrayList<Object> friendData= new ArrayList<>();
        friendData.add(stepsWalked);
        friendData.add(location);
        friends.put(name, friendData);
    }

    public void removeFriend(String name) {
        friends.remove(name);
    }

    public int getFriendStepsWalked(String name) {
        return (int) friends.get(name).get(STEPS);
    }

    public String getFriendLocation(String name) {
        return (String) friends.get(name).get(LOCATION);
    }

    public void stepUpdate(String name, int stepsWalked) { friends.get(name).set(STEPS, stepsWalked); }


    //Default constructor here to initialize everything
    //Only used for history, so we dont have to use the setters for everything


    /***************
     * User detail *
     ***************/
    //will have to fill some stuff in here

    public int age;
    public MainData(String name, int weight, int feet, int inches, String location, int calsEaten, int calsBreakfast,
                    int calsLunch, int calsDinner, int calsSnack, Hashtable<String, ArrayList<Object>> breakfast,
                    Hashtable<String, ArrayList<Object>> lunch, Hashtable<String, ArrayList<Object>> dinner,
                    Hashtable<String, ArrayList<Object>> snack, int calsBurned, workoutType dailyWorkout,  LocalDate day,
                    Hashtable<String, ArrayList<Object>> friends, int calsGoal,int burnedGoal, int age) {

        this.name = name;
        this.weight = weight;
        this.feet = feet;
        this.inches = inches;
        this.location = location;
        this.calsEaten = calsEaten;
        this.calsBreakfast = calsBreakfast;
        this.calsLunch = calsLunch;
        this.calsDinner = calsDinner;
        this.calsSnack = calsSnack;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snack = snack;
        this.calsBurned = calsBurned;
        this.dailyWorkout = dailyWorkout;

        this.day = day;
        this.friends = friends;
        this.calsGoal=calsGoal;
        this.burnedGoal=burnedGoal;
        this.age=age;
    }

    public MainData yesterday() {
        String name = "Me";
        int weight = 150;
        int feet = 5;
        int inches = 9;
        String location = "College Park, MD";
        int calsEaten = 2500;
        int calsGoal=2600;
        int calsBreakfast = 500;
        int calsLunch = 1000;
        int calsDinner = 800;
        int calsSnack = 200;
        Hashtable breakfast = new Hashtable<String, ArrayList<Object>>();
        ArrayList<Object> bfast = new ArrayList<Object>();
        bfast.add(new Integer(500));
        bfast.add("None");
        breakfast.put("Waffles",bfast);
        Hashtable lunch = new Hashtable<String, ArrayList<Object>>();
        ArrayList<Object> lun = new ArrayList<Object>();
        lun.add(new Integer(1000));
        lun.add("None");
        lunch.put("Nachos",lun);
        Hashtable dinner = new Hashtable<String, ArrayList<Object>>();
        ArrayList<Object> din = new ArrayList<Object>();
        din.add(new Integer(800));
        din.add("High protein");
        dinner.put("Tofu melt",din);
        Hashtable snack = new Hashtable<String, ArrayList<Object>>();
        ArrayList<Object> sn = new ArrayList<Object>();
        sn.add(new Integer(200));
        sn.add("High sugar");
        snack.put("Protein shake",sn);
        workoutType dailyWorkout=workoutType.RUN;
        int calsBurned = 700;
        int burnedGoal=800;
        LocalDate day = LocalDate.now().minusDays(1);
        Hashtable friends =new Hashtable<String, ArrayList<Object>>();
        ArrayList<Object> friendY = new ArrayList<>();
        friendY.add(1000);
        friendY.add("Greenbelt, MD");
        friends.put("Josh",friendY);
        return new MainData(name,weight,feet,inches,location,calsEaten,calsBreakfast,calsLunch,calsDinner,calsSnack,breakfast,lunch,dinner,snack, calsBurned, dailyWorkout,day,friends,calsGoal,burnedGoal, 24);
    }
}

  
  
  
  
  
