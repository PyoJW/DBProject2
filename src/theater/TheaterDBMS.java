package theater;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class TheaterDBMS {

	public static void main(String[] args) {
		String serverName = "147.46.15.238";
		String dbName = "16DB-2013-11594";
		String userName = "16DB-2013-11594";
		String password = "16DB-2013-11594";
		String url = "jdbc:mariadb://" + serverName + "/" + dbName;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			System.out.println("DB connection error");
			System.exit(1);
		}

		System.out.print("============================================================\n" + "1. print all buildings\n"
				+ "2. print all performances\n" + "3. print all audiences\n" + "4. insert a new building\n"
				+ "5. remove a building\n" + "6. insert a new performance\n" + "7. remove a performance\n"
				+ "8. insert a new audience\n" + "9. remove an audience\n" + "10. assign a performance to a building\n"
				+ "11. book a performance\n" + "12. print all performances which assigned at a building\n"
				+ "13. print all audiences who booked for a performance\n"
				+ "14. print ticket booking status of a performance\n" + "15. exit\n"
				+ "============================================================\n");

		while (true) {
			System.out.print("Select your action: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			int number = Integer.parseInt(input);
			menu(number, conn);
		}

	}

	public static void menu(int i, Connection conn) {
		if (i == 1) // print all buildings
		{
			String sql = "SELECT * FROM building";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				System.out.println("--------------------------------------------------------------------------------");
				System.out.printf("%-10s%-25s%-15s%-15s%-15s\n", "id", "name", "location", "capacity", "assigned");
				System.out.println("--------------------------------------------------------------------------------");
				while (rs.next()) {
					building build = new building();
					build.setId(rs.getInt("id"));
					build.setName(rs.getString("name"));
					build.setLocation(rs.getString("location"));
					build.setCapacity(rs.getInt("capacity"));
					build.setAssigned(rs.getInt("assigned"));
					System.out.printf("%-10s%-25s%-15s%-15s%-15s\n", build.id, build.name, build.location,
							build.capacity, build.assigned);

				}
			} catch (SQLException e) {
				System.out.println("Error in 1. print all buildings");
			}

		}
		if (i == 2) // print all performances.
		{
			String sql = "SELECT * FROM performance";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				System.out.println("--------------------------------------------------------------------------------");
				System.out.printf("%-10s%-25s%-15s%-15s%-15s\n", "id", "name", "type", "price", "booked");
				System.out.println("--------------------------------------------------------------------------------");
				while (rs.next()) {
					performance perf = new performance();
					perf.setId(rs.getInt("id"));
					perf.setName(rs.getString("name"));
					perf.setType(rs.getString("type"));
					perf.setPrice(rs.getInt("price"));
					perf.setBooked(rs.getInt("booked"));
					System.out.printf("%-10s%-25s%-15s%-15s%-15s\n", perf.id, perf.name, perf.type, perf.price,
							perf.booked);

				}
			} catch (SQLException e) {
				System.out.println("Error in 2. print all performances");
			}

		}
		if (i == 3) // print all audiences.
		{

			String sql = "SELECT * FROM audience";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				System.out.println("--------------------------------------------------------------------------------");
				System.out.printf("%-10s%-25s%-15s%-15s\n", "id", "name", "gender", "age");
				System.out.println("--------------------------------------------------------------------------------");
				while (rs.next()) {
					audience aud = new audience();
					aud.setId(rs.getInt("id"));
					aud.setName(rs.getString("name"));
					aud.setGender(rs.getString("gender"));
					aud.setAge(rs.getInt("age"));
					System.out.printf("%-10s%-25s%-15s%-15s\n", aud.id, aud.name, aud.gender, aud.age);

				}
			} catch (SQLException e) {
				System.out.println("Error in 3. print all audiences");
			}
		}

		if (i == 4) // insert the building.
		{
			building build = new building();
			try {
				System.out.print("Building name: ");
				Scanner scan = new Scanner(System.in);
				build.setName(scan.nextLine().toString());
				System.out.print("Building location: ");
				scan = new Scanner(System.in);
				build.setLocation(scan.nextLine().toString());
				System.out.print("Building capacity: ");
				scan = new Scanner(System.in);
				build.setCapacity(Integer.parseInt(scan.nextLine()));
				build.setAssigned(0); // the default of assigned is 0
				String sql = "INSERT INTO building(name, location, capacity, assigned) values('" + build.name + "', '"
						+ build.location + "', " + build.capacity + ", " + build.assigned + ")";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				theater.message.buildingInserted();

			} catch (SQLException e) {
				System.out.println("Error in 4. insert the building.");
			}

		}
		if (i == 5) // delete the building.
		{

			try {
				System.out.print("Building ID that you want to delete: ");
				Scanner scan = new Scanner(System.in);
				int id = scan.nextInt();
				String testSql = "SELECT * FROM building where id=" + id;
				PreparedStatement testStmt = conn.prepareStatement(testSql);
				ResultSet testRs = testStmt.executeQuery();
				if (!testRs.next()) // if id no exists
				{
					theater.message.buildingNoExist(id);
				} else {
					String sql = "DELETE FROM building where id=" + id;
					PreparedStatement stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					theater.message.buildingRemoved();
				}
			} catch (SQLException e) {
				System.out.println("Error in 5. delete the building.");
			}

		}

		if (i == 6) // insert the performance.
		{
			performance perf = new performance();
			try {
				System.out.print("Performance name: ");
				Scanner scan = new Scanner(System.in);
				perf.setName(scan.nextLine().toString());
				System.out.print("Performance type: ");
				scan = new Scanner(System.in);
				perf.setType(scan.nextLine().toString());
				System.out.print("Performance price: ");
				scan = new Scanner(System.in);
				perf.setPrice(Integer.parseInt(scan.nextLine()));
				perf.setBooked(0); // the default of assigned is 0
									// how about perf.build_id?? I wanna make it
									// default null.
				String sql = "INSERT INTO performance(name, type, price, booked) values('" + perf.name + "', '"
						+ perf.type + "', " + perf.price + ", " + perf.booked + ")";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				theater.message.performanceInserted();
				
				

			} catch (SQLException e) {
				System.out.println("Error in 6. insert the performance.");
			}

		}

		if (i == 7) // delete the performance.
		{

			try {
				System.out.print("Performance ID that you want to delete: ");
				Scanner scan = new Scanner(System.in);
				int id = scan.nextInt();
				String testSql = "SELECT * FROM performance where id=" + id;
				PreparedStatement testStmt = conn.prepareStatement(testSql);
				ResultSet testRs = testStmt.executeQuery();
				if (!testRs.next()) // if id no exists
				{
					theater.message.performanceNoExist(id);
				} else {
					
					// check if build_id of deleted record is not null.
					String sql0 = "SELECT build_id FROM performance where build_id is not null and id="+id;
					PreparedStatement stmt = conn.prepareStatement(sql0);
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) // if build_id is not null
					{
						//count down assigned attribute.
						String sql1 ="UPDATE building SET assigned=assigned-1 WHERE id="+rs.getInt("build_id");
						PreparedStatement stmt1 = conn.prepareStatement(sql1);
						ResultSet rs1 = stmt1.executeQuery();
					}
					// delete performance record
					String sql = "DELETE FROM performance where id=" + id;
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();					
					
					
					
					
					theater.message.performanceRemoved();
				}
			} catch (SQLException e) {
				System.out.println("Error in 7. delete the performance.");
			}

		}

		if (i == 8) // insert the audience.
		{
			audience aud = new audience();
			try {
				System.out.print("Audience name: ");
				Scanner scan = new Scanner(System.in);
				aud.setName(scan.nextLine().toString());
				System.out.print("Audience gender: ");
				scan = new Scanner(System.in);
				aud.setGender(scan.nextLine().toString());
				System.out.print("Audience age: ");
				scan = new Scanner(System.in);
				aud.setAge(Integer.parseInt(scan.nextLine()));
				String sql = "INSERT INTO audience(name, gender, age) values('" + aud.name + "', '" + aud.gender + "', "
						+ aud.age + ")";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				theater.message.audienceInserted();

			} catch (SQLException e) {
				System.out.println("Error in 8. insert the audience.");
			}

		}

		if (i == 9) // delete the audience.
		{

			try {
				System.out.print("Audience ID that you want to delete: ");
				Scanner scan = new Scanner(System.in);
				int id = scan.nextInt();
				String testSql = "SELECT * FROM audience where id=" + id;
				PreparedStatement testStmt = conn.prepareStatement(testSql);
				ResultSet testRs = testStmt.executeQuery();
				if (!testRs.next()) // if id no exists
				{
					theater.message.audienceNoExist(id);
				} else {
					String sql = "DELETE FROM audience where id=" + id;
					PreparedStatement stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					theater.message.audienceRemoved();
				}
			} catch (SQLException e) {
				System.out.println("Error in 9. delete the audience.");
			}

		}

		if (i == 10) // match the building with performance
		{
			int id;
			int build_id;
			try {
				System.out.print("Building ID: ");
				Scanner scan = new Scanner(System.in);
				build_id = scan.nextInt();
				
				String testSql1 = "SELECT * FROM building where id="+build_id;
				PreparedStatement testStmt1 = conn.prepareStatement(testSql1);
				ResultSet testRs1 = testStmt1.executeQuery();
				if(!testRs1.next())
				{
					theater.message.buildingNoExist(build_id);
					return;
				}

				System.out.print("Performance ID: ");
				scan = new Scanner(System.in);
				id = scan.nextInt();
				
				String testSql2 = "SELECT capacity FROM building where id="+id;
				PreparedStatement testStmt2 = conn.prepareStatement(testSql2);
				ResultSet testRs2 = testStmt2.executeQuery();
				if(!testRs2.next())
				{
					theater.message.performanceNoExist(id);
					return;
				}
				int capacity = testRs2.getInt("capacity");

				String testSql = "SELECT * FROM performance where id=" + id + " and build_id IS NULL";
				PreparedStatement testStmt = conn.prepareStatement(testSql);
				ResultSet testRs = testStmt.executeQuery();
				if (!testRs.next())
				{
					theater.message.performanceAlreadyAssigned(id);
					return;
				}
				// assign build_id attribute in performance.
				String sql = "UPDATE performance SET build_id=" + build_id + " where id=" + id;
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				// count up the assigned attribute in building.
				String sql1 = "UPDATE building SET assigned=assigned+1 where id="+build_id;
				PreparedStatement stmt1 = conn.prepareStatement(sql1);
				ResultSet rs1 = stmt1.executeQuery();
				// insert booked_list table seat_number, performance_id
				for(int seat_num = 1; seat_num<=capacity ; seat_num++)
				{
					sql = "INSERT INTO booked_list(performance_id, seat_number) values("+id+", "+seat_num+")";
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
				}
				
				theater.message.performanceAssign();
				
			} catch (SQLException e) {
				System.out.println("Error in 10. match the building with performance");
			}
		}
		
		if(i==11) // book the performance
		{
			int performance_id;
			int audience_id;
			int age;
			int ticket_count = 0;
			int price;
			double total;
			try{
				
				System.out.print("Performance ID: ");
				Scanner scan = new Scanner(System.in);
				performance_id = scan.nextInt();
				
				String sql = "SELECT * FROM performance where id="+performance_id;
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if(!rs.next()) // if no performance ID
				{
					theater.message.performanceNoExist(performance_id);
					return;
				}
				//price
				price = rs.getInt("price");
				
				System.out.print("Audience ID: ");
				scan = new Scanner(System.in);
				audience_id = scan.nextInt();
				
				sql = "SELECT * FROM audience where id="+audience_id;
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if(!rs.next()) // if no audience ID
				{
					theater.message.audienceNoExist(audience_id);
					return;
				}
				//age
				age = rs.getInt("age");
				
				System.out.print("Seat number: ");
				scan = new Scanner(System.in);
				String seat_list = scan.nextLine();
				seat_list.replaceAll("\\s+", "");
				ArrayList<Integer> seat_list_array = new ArrayList<Integer>(); // seat number
				for(String seat : seat_list.split(",") )
				{
					int seat_int = Integer.parseInt(seat);
					seat_list_array.add(seat_int);
				}
				
				for(int j=0; j< seat_list_array.size() ; j++)
				{
					int seat_num = seat_list_array.get(j);
					
					//check if this seat number is exist or not occupied.
					sql = "SELECT * FROM booked_list where seat_number="+seat_num+" and performance_id="+performance_id;
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					if(rs.next())
					{
						if(rs.getInt("audience_id") != 0)
						{
							theater.message.seatAlreadyTaken();
							return;
						}
					}
					else
					{
						theater.message.seatNumRange();
						return;
					}
				}
				
				//update the booked_list
				for(int j=0; j< seat_list_array.size() ; j++)
				{
					int seat_num = seat_list_array.get(j);
					
					//check if this seat number is exist or not occupied.
					sql = "SELECT * FROM booked_list where seat_number="+seat_num+" and performance_id="+performance_id;
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					
					if(rs.next())
					{
						if(rs.getInt("audience_id") == 0)
						{ 	//it it is OK, book the seat
							sql = "UPDATE booked_list SET audience_id="+audience_id+" WHERE seat_number="+seat_num+" and performance_id="+performance_id;
							stmt = conn.prepareStatement(sql);
							rs = stmt.executeQuery();
							ticket_count++;
						}
						
					}
					else
					{
						theater.message.seatNumRange();
						return;
					}
				}
				// calculate the price
				
				if(age>=1 && age <=7)
				{
					total = 0;
				}
				else if(age >=8 && age <=12)
				{
					total = ticket_count*price*0.5;
				}
				else if(age >=13 && age <= 18)
				{
					total = ticket_count*price*0.8;
				}
				else
				{
					total = ticket_count*price;
				}
				
				theater.message.bookSuccess(price);

				
				
			} catch(SQLException e){System.out.println("Error in 11. book the performance");}
		}
		
		if(i==12) // print all performances which assigned in certain building.
		{
			try
			{
				System.out.print("Building ID: ");
				Scanner scan = new Scanner(System.in);
				int build_id = scan.nextInt();
				
				String sql = "SELECT * FROM performance WHERE build_id="+build_id;
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if(!rs.next()) // if building is not exist
				{
					theater.message.buildingNoExist(build_id);
					return;
				}
				System.out.println("--------------------------------------------------------------------------------");
				System.out.printf("%-10s%-25s%-15s%-15s%-15s\n", "id", "name", "type", "price", "booked");
				System.out.println("--------------------------------------------------------------------------------");
				
				do{
					performance perf = new performance();
					perf.setId(rs.getInt("id"));
					perf.setName(rs.getString("name"));
					perf.setType(rs.getString("type"));
					perf.setPrice(rs.getInt("price"));
					perf.setBooked(rs.getInt("booked"));
					System.out.printf("%-10s%-25s%-15s%-15s%-15s\n", perf.id, perf.name, perf.type, perf.price,
							perf.booked);
				} while(rs.next());
				System.out.println("--------------------------------------------------------------------------------");
			} catch(SQLException e){System.out.println("Error in 12. print all performances which assigned in certain building.");}
		}
		
		if(i==13) // print all audiences who book for performances.
		{
			try
			{
				System.out.print("Performance ID: ");
				Scanner scan = new Scanner(System.in);
				int id = scan.nextInt();
				String sql = "SELECT * FROM performance where id="+id;
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if(!rs.next()) // if no performance ID
				{
					theater.message.performanceNoExist(id);
					return;
				}
				
				
				
			}catch(SQLException e){System.out.println("Error in 13. print all audiences who book for performances.");}
		}

		
		if (i == 15) // end the program
		{
			theater.message.exitProgram();
			System.exit(0);
		}
	}
}
