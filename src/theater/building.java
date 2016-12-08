package theater;

public class building {
	public int id; //pk
	public String name;
	public String location;
	public int capacity;
	public int assigned;
	
	public void setId(int i)
	{
		this.id = i;
	}
	
	public void setName(String n)
	{
		if(n.length() > 200)
		{
			this.name = n.substring(0, 200);
		}
		else
		{
			this.name = n;
		}
	}
	
	public void setLocation(String l)
	{
		if(l.length() > 200)
		{
			this.location = l.substring(0, 200);
		}
		else
		{
			this.location = l;
		}
	}
	
	public void setCapacity(int m)
	{
		if(m < 1)
		{
			theater.message.memberCapacity();
		}
		else
		{
			this.capacity = m;
		}
	}
	
	public void setAssigned(int a)
	{
		this.assigned = a;
	}
}
