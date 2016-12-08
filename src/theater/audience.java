package theater;

public class audience {
	int id; //pk
	String name;
	String gender;
	int age;
	
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
	
	public void setGender(String l)
	{
		
			if(new String("M").equals(l) || new String("F").equals(l))
			{
				this.gender = l;
			}
			else
			{
				theater.message.genderError();
			}
	}
	
	public void setAge(int m)
	{
		if(m < 1)
		{
			theater.message.ageRange();
		}
		else
		{
			this.age = m;
		}
	}
}
