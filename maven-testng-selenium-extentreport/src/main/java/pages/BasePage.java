package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;

public class BasePage {

	protected static WebDriver driver=BaseTest.driver;
	private static String parentwindow;
	
	protected void click(By locator)
	{
		WebElement element=driver.findElement(locator);
		highlightElement(element);
		element.click();
	}
	
	protected void enterText(By locator,String text)
	{
		WebElement element=driver.findElement(locator);
		highlightElement(element);
		element.sendKeys(text);
	}
	
	protected WebElement getElementByText(By locator, String text)
	{
		List <WebElement> tabs=driver.findElements(locator);
		for(WebElement tab: tabs)
		{
			if(tab.getText().equals(text))
			{
				return tab;
			}
		}
		return null;
	}
	
	
	protected void selectFromDropDown(By locator,String text)
	{
		Select select=new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}
	
	protected WebElement waitForElement(By locator,String condition)
	{
		WebDriverWait wait=new WebDriverWait(driver,3);
		switch(condition)
		{
		case "visibility":
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		}
		
		return null;
	}

	protected void switchControlToWindowByTitle(String title) {
		parentwindow=driver.getWindowHandle();
		Set<String> handles= driver.getWindowHandles();
		for(String handle:handles)
		{
			driver.switchTo().window(handle);
			if(driver.getTitle().equals(title))
			{
				break;
			}
		}
	}
	
	protected boolean close()
	{
		Set<String> handles= driver.getWindowHandles();
		if(handles.size()>1 && driver.getWindowHandle()!=parentwindow)
		{
			driver.close();
			driver.switchTo().window(parentwindow);
			return true;
		}
		else
		{
			driver.quit();
			return false;
		}
	}
	protected void selectDate(String date) throws ParseException {
		Date m = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(date.split("-")[1]);
		Calendar cal = Calendar.getInstance();
		cal.setTime(m);
		int month = cal.get(Calendar.MONTH);
		goToYear(Integer.parseInt(date.split("-")[2]));
		goToMonth(month);
		date=date.split("-")[0];
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[text()='"+date+"']")).click();
	}

	private void goToMonth(int month) throws ParseException {

		while(true)
		{
			Date d = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText().substring(0, 3));
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int Month = cal.get(Calendar.MONTH);
			
			if(month<Month)
			{
				driver.findElement(By.xpath("//span[text()='Prev']")).click();				
			}
			else if (month>Month)
			{
				driver.findElement(By.xpath("//span[text()='Next']")).click();
			}
			else
			{
				break;
			}
		}
		
	}

	private void goToYear(int year)
	{
		while(true)
		{
			int Year=Integer.parseInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
			if(year<Year)
			{
				driver.findElement(By.xpath("//span[text()='Prev']")).click();				
			}
			else if (year>Year)
			{
				driver.findElement(By.xpath("//span[text()='Next']")).click();
			}
			else
			{
				break;
			}
		}
	}
	
	protected void highlightElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
	}

}
