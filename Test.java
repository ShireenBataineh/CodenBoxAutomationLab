package CodenBoxAutomationLab;

import java.awt.Desktop.Action;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class Test {
	
	WebDriver driver=new ChromeDriver();
	Random rand=new Random();
	//To use date from the database:
	Connection con;
	Statement stmt;
	ResultSet rs;
	String firstName;
	String lastName;
	String phone;
	String details;
	
	@BeforeTest
	public void BTest() throws SQLException
	{
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","123456");
		
		driver.navigate().to("https://codenboxautomationlab.com/practice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
	}
	
	@org.testng.annotations.Test(priority=1, enabled=false)
	public void radio()
	{
		List<WebElement> radios=driver.findElements(By.className("radioButton"));
		int value=rand.nextInt(radios.size());
		radios.get(value).click();
		
	}
	
	@org.testng.annotations.Test(priority=2, enabled=false)
	public void dynamicDropDown() throws InterruptedException
	{
		WebElement dyDropDwn=driver.findElement(By.id("autocomplete"));
		String [] dropDown= {"Jord","Leban","Syr","Cana","Amer"};
		int index=rand.nextInt(dropDown.length);
		dyDropDwn.sendKeys(dropDown[index]);
		Thread.sleep(1000);
		dyDropDwn.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
		
	}
	
	@org.testng.annotations.Test(priority=3, enabled=false)
	public void staticDropDown()
	{
		WebElement stDropDwn=driver.findElement(By.id("dropdown-class-example"));
		List<WebElement> staticDropDwn=stDropDwn.findElements(By.tagName("option"));
		int value=rand.nextInt(staticDropDwn.size());
		staticDropDwn.get(value).click();
		
	}
	
	@org.testng.annotations.Test(priority=4, enabled=false)
	public void checkBox()
	{
		WebElement chkBox=driver.findElement(By.id("checkbox-example"));
		List<WebElement> chekBox=chkBox.findElements(By.tagName("input"));
		int value=chekBox.size();

		for(int i=0;i<value;i++)
		{
		chekBox.get(i).click();
		}
		
	}

	@org.testng.annotations.Test(priority=5, enabled=false)
	public void openWind() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,650)");
		
		WebElement swchWind=driver.findElement(By.id("openwindow"));
		swchWind.click();
		
		Set <String> handle=driver.getWindowHandles();
		List <String> tabs=new ArrayList<String>(handle);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		WebElement contact=driver.findElement(By.id("menu-item-9680"));
		contact.click();
		Thread.sleep(3000);
		driver.navigate().back();
		driver.close();
		
		driver.switchTo().window(tabs.get(0));
		
	}
	
	@org.testng.annotations.Test(priority=6, enabled=false)
	public void openTab() throws InterruptedException
	{
		Thread.sleep(1000);
		WebElement opnTab=driver.findElement(By.id("opentab"));
		opnTab.click();
		
		Set<String> handle=driver.getWindowHandles();
		List<String> tabs=new ArrayList<String>(handle);
		driver.switchTo().window(tabs.get(1));
		
		WebElement home=driver.findElement(By.id("endpoint"));
		Thread.sleep(2000);
		home.click();
		Thread.sleep(2000);
		driver.navigate().back();
		driver.switchTo().window(tabs.get(0));
		
	}
	
	@org.testng.annotations.Test(priority=7, enabled=false)
	public void Alert() throws InterruptedException
	{
		WebElement alert=driver.findElement(By.id("name"));
		alert.sendKeys("Shireen");
		Thread.sleep(1000);
		WebElement clickAlert=driver.findElement(By.id("alertbtn"));
		clickAlert.click();
		
		boolean assertion=driver.switchTo().alert().getText().contains("Shireen");
		Assert.assertEquals(assertion, true);
		
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		WebElement confirm=driver.findElement(By.id("confirmbtn"));
		confirm.click();
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
		
	}
	
	@org.testng.annotations.Test(priority=8, enabled=false)
	public void arrow()
	{	
		WebElement arrow=driver.findElement(By.cssSelector(".theChampSharingArrow.theChampPullOut"));
		arrow.click();
	}
	
	@org.testng.annotations.Test(priority=9, enabled=false)
	public void table()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,950)");
		
		WebElement tableData=driver.findElement(By.id("product"));
		List<WebElement> allTableData=driver.findElements(By.tagName("tr"));
		int numberOfRow=allTableData.size();
		for(int i=0;i<numberOfRow;i++)
		{
			System.out.println(allTableData.get(i).getText());
		}
		
	}
	
	@org.testng.annotations.Test(priority=10, enabled=false)
	public void elementDisplayed() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,1700)");
		
		Thread.sleep(1000);
		WebElement hide=driver.findElement(By.id("hide-textbox"));
		hide.click();
		Thread.sleep(2000);
		WebElement show=driver.findElement(By.id("show-textbox"));
		show.click();
		
	}
	
	@org.testng.annotations.Test(priority=11, enabled=false)
	public void abled() throws InterruptedException
	{
		WebElement box=driver.findElement(By.id("enabled-example-input"));
		box.sendKeys("Shireen");
		Thread.sleep(1000);
		WebElement disabled=driver.findElement(By.id("disabled-button"));
		disabled.click();
		Thread.sleep(1000);
		try {
		WebElement box1=driver.findElement(By.id("enabled-example-input"));
		box1.sendKeys("Nareen");
		}
		catch(Exception e)
		{
			System.out.println("The text couldn't be send because the box is disabled");
		}
		WebElement enabled=driver.findElement(By.id("enabled-button"));
		enabled.click();
		Thread.sleep(1000);
		WebElement box2=driver.findElement(By.id("enabled-example-input"));
		box2.sendKeys("Seleen");
		
	}
	
	@org.testng.annotations.Test(priority=12, enabled=false)
	public void hover() throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement hover=driver.findElement(By.id("mousehover"));
		Actions action=new Actions(driver);
		action.moveToElement(hover).build().perform();
		WebElement top=driver.findElement(By.linkText("Top"));
		top.click();
		Thread.sleep(2000);
		action.moveToElement(hover).build().perform();
		WebElement reload=driver.findElement(By.linkText("Reload"));
		reload.click();
		
	}
	
	@org.testng.annotations.Test(priority=13, enabled=true)
	public void calendar() throws InterruptedException, SQLException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,2200)");
		Thread.sleep(1000);
		WebElement calendarBooking=driver.findElement(By.linkText("Booking Calendar"));
		calendarBooking.click();
		Set<String>handle=driver.getWindowHandles();
		List<String> tabs=new ArrayList<String>(handle);
		driver.switchTo().window(tabs.get(1));
		WebElement search=driver.findElement(By.cssSelector(".genericon.genericon-search"));
		search.click();
		WebElement date=driver.findElement(By.linkText("25"));
		date.click();
		JavascriptExecutor js2=(JavascriptExecutor)driver;
		js2.executeScript("window.scrollTo(0,800)");
		
		//int randValue=rand.nextInt(103,497);
		String query="select * from customers where customerNumber=103";
		stmt=con.createStatement();
		rs=stmt.executeQuery(query);
		while(rs.next())
		{
			firstName=rs.getString("contactFirstName");
			lastName=rs.getString("contactLastName");
			phone=rs.getString("phone");
			details=rs.getString("customerName");
		}
		
		WebElement fName=driver.findElement(By.id("name1"));
		fName.sendKeys(firstName);
		WebElement lName=driver.findElement(By.id("secondname1"));
		lName.sendKeys(lastName);
		WebElement email=driver.findElement(By.id("email1"));
		int randomVal=rand.nextInt(5000);
		email.sendKeys(firstName+lastName+randomVal+"@gmail.com");
		WebElement phoneNum=driver.findElement(By.id("phone1"));
		phoneNum.sendKeys(phone);
		WebElement custDetails=driver.findElement(By.id("details1"));
		custDetails.sendKeys(details);
		
	}
	
	@AfterTest
	public void afterTest() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
}
