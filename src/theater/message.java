package theater;

public class message {
	
	public static void buildingInserted()
	{
		System.out.println("A building is successfully inserted");
	}
	
	public static void performanceInserted()
	{
		System.out.println("A performance is successfully inserted");
	}
	
	public static void audienceInserted()
	{
		System.out.println("An audience is successfully inserted");
	}
	
	public static void buildingRemoved()
	{
		System.out.println("A building is successfully removed");
	}
	
	public static void performanceRemoved()
	{
		System.out.println("A performance is successfully removed");
	}
	
	public static void audienceRemoved()
	{
		System.out.println("An audience is successfully removed");
	}
	public static void memberCapacity()
	{
		System.out.println("Capacity should be larger than 0");
	}
	
	public static void genderError()
	{
		System.out.println("Gender should be 'M' or 'F'");
	}
	
	public static void ageRange()
	{
		System.out.println("Age should be more than 0");
	}
	
	public static void priceRange()
	{
		System.out.println("Price should be 0 or more");
	}
	
	public static void performanceAssign()
	{
		System.out.println("Successfully assign a performance");
	}
	
	public static void buildingNoExist(int id)
	{
		System.out.println("Building "+id+" doesn't exist");
	}
	
	public static void performanceNoExist(int id)
	{
		System.out.println("Performance "+id+" doesn't exist");
	}
	
	public static void audienceNoExist(int id)
	{
		System.out.println("Audience "+id+" doesn't exist");
	}
	
	public static void performanceAlreadyAssigned(int id)
	{
		System.out.println("Performance "+id+" is already assigned");
	}
	
	public static void performanceNotAssigned(int id)
	{
		System.out.println("Performance "+id+" isn't assigned");
	}
	
	public static void seatNumRange()
	{
		System.out.println("Seat number out of range");
	}
	
	public static void seatAlreadyTaken()
	{
		System.out.println("The seat is already taken");
	}
	
	public static void bookSuccess(int price)
	{
		System.out.println("Successfully book a performance");
		System.out.println("Total ticket price is "+price);
	}
	
	public static void exitProgram()
	{
		System.out.println("Bye!");
	}
	
	public static void menuSelectError()
	{
		System.out.println("Invalid action");
	}
	
}
