package tests;

import java.text.ParseException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountActivityPage;
import pages.AccountSummaryPage;
public class FindTransaction extends BaseTest {
	
	@BeforeTest
	public void beforeTest()
	{
		AccountSummaryPage accpage=new AccountSummaryPage();
		accpage.navigateToTab("Account Activity");
	}
	
  @Test
  public void findTransactionByDate() throws ParseException{
	  test = report.createTest("Find Transaction By Date");
	  AccountActivityPage fndtra=new AccountActivityPage();
	  fndtra.navigateToTab("Find Transactions");
	  fndtra.selectFromData("20-Sep-2012");
  }
  
  @Test
  public void findTransactionByDescription() {
	  test = report.createTest("Find Transaction By Description");
  }
  
}
