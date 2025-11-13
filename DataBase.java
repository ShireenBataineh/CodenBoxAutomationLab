package CodenBoxAutomationLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class DataBase {
	
	WebDriver driver=new ChromeDriver();
	Random rand=new Random();
	Connection con;
	Statement stmt;
	ResultSet rs;
	String name;
	int numOfRow;
	String firstName;
	String lastName;
	String email;
	String phone;
	String details;
	int randNum;

	
	@BeforeTest
	public void DB() throws SQLException
	{
		driver.navigate().to("https://codenboxautomationlab.com/practice/#top");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","123456");
		randNum=rand.nextInt(1000,2000);
	}
	
	@Test(priority=1, enabled=true)
	public void addToTable() throws SQLException
	{
		//This code to add or insert data in the database
		String query="insert into customers"+"(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country)"+" VALUES("+randNum+", 'ShireenBataineh', 'Shireen', 'Bataineh', '791445979', 'Amman/Shmeisani', '000', 'Amman', 'Amman', '1234', 'Jordan')";
		stmt=con.createStatement();
		numOfRow=stmt.executeUpdate(query);
		if(numOfRow>0)
		{
			System.out.println(numOfRow);
		}
		System.out.println(randNum);
		
	}
	
	@Test(priority=2)
	public void retrieveRead() throws SQLException
	{
		WebElement calendar=driver.findElement(By.linkText("Booking Calendar"));
		calendar.click();
		Set <String> handle=driver.getWindowHandles();
		List<String> tabs=new ArrayList<String>(handle);
		driver.switchTo().window(tabs.get(1));
		
		//This code to retrieve or read data from the database
		String retrieveQuery="select * from customers where customerNumber="+randNum;
		stmt=con.createStatement();
		rs=stmt.executeQuery(retrieveQuery);
		while(rs.next())
		{
			firstName=rs.getString("contactFirstName");
			lastName=rs.getString("contactLastName");
			phone=rs.getString("phone");
			details=rs.getString("customerName");
			
		}
		email=firstName+lastName+randNum+"@gmail.com";
		WebElement FN=driver.findElement(By.id("name1"));
		FN.sendKeys(firstName);
		WebElement LN=driver.findElement(By.id("secondname1"));
		LN.sendKeys(lastName);
		WebElement ph=driver.findElement(By.id("phone1"));
		ph.sendKeys(phone);
		WebElement det=driver.findElement(By.id("details1"));
		det.sendKeys(details);
		WebElement em=driver.findElement(By.id("email1"));
		em.sendKeys(email);
		
	}
	
	@Test (priority=3, enabled=true)
	public void updateData() throws SQLException
	{
		//This code to update data in the database
		String update="UPDATE customers"+" SET customerName='Seleen',phone='794115979'"+"WHERE customerNumber="+randNum;
		stmt=con.createStatement();
		numOfRow=stmt.executeUpdate(update);
		
		String retrieveQuery="select * from customers where customerNumber="+randNum;
		stmt=con.createStatement();
		rs=stmt.executeQuery(retrieveQuery);
		while(rs.next())
		{
			name=rs.getString("customerName");
			System.out.println(name);
		}
		
	}
	
	@Test(priority=4, enabled=true)
	public void deleteData() throws SQLException
	{
		//This code to delete row from the database
		String deleteD="DELETE FROM customers WHERE customerNumber="+randNum;
		stmt=con.createStatement();
		numOfRow=stmt.executeUpdate(deleteD);
		
		
	}
	
	@AfterTest
	public void close() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}

}

















