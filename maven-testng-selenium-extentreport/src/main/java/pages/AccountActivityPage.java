package pages;

import java.text.ParseException;

import org.openqa.selenium.By;

public class AccountActivityPage extends BasePage{
	
	By SubTabsLink=By.xpath("//ul[contains(@class,'ui-tabs-nav')]//a");
	By FromDate=By.id("aa_fromDate");
	
	public void navigateToTab(String tabname)
	{
		getElementByText(SubTabsLink,tabname).click();
	}
	
	public void selectFromData(String date) throws ParseException
	{
		waitForElement(FromDate,"visibility").click();
		selectDate(date);
	}
	
}
