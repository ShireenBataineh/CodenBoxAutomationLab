package CodenBoxAutomationLab;

import java.awt.Window;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CodenBox {
	
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
	public void Navigate() throws SQLException
	{
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","123456");
		driver.navigate().to("https://codenboxautomationlab.com/practice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
	}
	
	@Test(priority=1)
	public void radioButton()
	{
		List <WebElement> radioBtn=driver.findElements(By.className("radioButton"));
		int size=radioBtn.size();
		int selectedIindex=rand.nextInt(size);
		radioBtn.get(selectedIindex).click();
		
	}
	
	@Test(priority=2)
	public void dynamicDropDown() throws InterruptedException
	{
//		WebElement dropDown=driver.findElement(By.id("autocomplete"));
//		String [] country= {"Jordan","France","Lebanon","South Africa","Belgium"};
//		int arrLength=country.length;
//		int randomIndex=rand.nextInt(1,arrLength);
//		dropDown.sendKeys(country[randomIndex]);
		
		//OR
		WebElement dropDown1=driver.findElement(By.id("autocomplete"));
		String [] country1= {"Jord","Fran","Leba","South Africa","Belg"};
		int arrLength1=country1.length;
		int randomIndex1=rand.nextInt(1,arrLength1);
		dropDown1.sendKeys(country1[randomIndex1]);
		Thread.sleep(1000);
		dropDown1.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
		
	}
	
	@Test(priority=3)
	public void staticDropDown()
	{
		WebElement dropDownList=driver.findElement(By.id("dropdown-class-example"));
		Select selectOption=new Select(dropDownList);
		int size=dropDownList.findElements(By.tagName("option")).size();
		int selectedOption=rand.nextInt(1,size);
		selectOption.selectByIndex(selectedOption);
		
	}
	
	@Test(priority=4)
	public void checkBox()
	{
		WebElement checkBox=driver.findElement(By.id("checkbox-example"));
		List <WebElement> checkBoxes=checkBox.findElements(By.tagName("input"));		
		int size=checkBoxes.size();
		for(int i=0;i<size;i++)
		{
			checkBoxes.get(i).click();
		}
		
		
	}
	
	@Test(priority=5, enabled=true)
	public void openWindow() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,700)");
		
		WebElement openWind=driver.findElement(By.id("openwindow"));
		openWind.click();
		
		Set <String> handles=driver.getWindowHandles();
		List <String> tabs=new ArrayList<String>(handles);
		driver.switchTo().window(tabs.get(1));
		WebElement contact=driver.findElement(By.id("menu-item-9680"));
		contact.click();
		driver.close();
		
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(2000);
		
	}
	
	@Test(priority=6)
	public void openTab() throws InterruptedException
	{
		WebElement opnTab=driver.findElement(By.id("opentab"));
		opnTab.click();
		
		Set<String> handle=driver.getWindowHandles();
		List<String> windows=new ArrayList<String>(handle);
		driver.switchTo().window(windows.get(1));
		Thread.sleep(1000);
		
		WebElement Home=driver.findElement(By.id("endpoint"));
		Home.click();
		Thread.sleep(1000);
		driver.close();
		driver.switchTo().window(windows.get(0));
		
	}
	
	@Test(priority=7, enabled=true)
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
	
	@Test(priority=8, enabled=true)
	public void arrow()
	{	
		WebElement arrow=driver.findElement(By.cssSelector(".theChampSharingArrow.theChampPullOut"));
		arrow.click();
	}
	
	@Test(priority=9, enabled=true)
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
	
	@Test(priority=10, enabled=true)
	public void elementDisplayed() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,1700)");
		
		Thread.sleep(1000);
		WebElement hide=driver.findElement(By.id("hide-textbox"));
		hide.click();
		Thread.sleep(1000);
		WebElement show=driver.findElement(By.id("show-textbox"));
		show.click();
		
	}
	
	@Test(priority=11, enabled=true)
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
	
	@Test(priority=12, enabled=true)
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
	
	@Test(priority=13, enabled=true)
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
















