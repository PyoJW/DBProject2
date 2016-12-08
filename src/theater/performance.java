package theater;

public class performance {
	int id; //pk
	String name;
	String type;
	int price;
	int booked;
	int build_id; //fk to building(id)
	
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
	
	public void setType(String l)
	{
		
			this.type = l;
	}
	
	public void setPrice(int m)
	{
		if(m < 0)
		{
			theater.message.priceRange();
		}
		else
		{
			this.price = m;
		}
	}
	
	public void setBooked(int s)
	{
		this.booked = s;
	}
	
	public void setBuild_id(int s)
	{
		this.build_id = s;
	}
}
