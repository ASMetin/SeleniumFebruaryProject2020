package com.hubspot.pages;

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.xpath("//h1[@class='private-page__title']");
	By accountName = By.xpath("//a[@id='account-menu']");
	By contactMainTab = By.id("nav-primary-contacts-branch");
	By contatcChildTab = By.id("nav-secondary-contacts");
	
	//Constructor
	public HomePage(WebDriver driver){
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
	}
	 public String getHomePageTitle() {	 
		 return elementUtil.waitForGetPageTitle(Constants.HOME_PAGE_TITLE);
	//return driver.getTitle();	
	}
	 public String getHomePageHeader(){
		return elementUtil.doGetText(header); 
		 //return driver.findElement(header).getText();
	 }
	 public String verifyLoggedInAccountName() {		 
		 return elementUtil.doGetText(accountName); 
		// return driver.findElement(accountName).getText();
	 }
		//helper method //datalari private method icinde store eder, public'le cagiririz.
	 private void clickOnContacts() throws InterruptedException{
		elementUtil.doClick(contactMainTab);
		Thread.sleep(2000);
		elementUtil.doClick(contatcChildTab);
	}
	public ContactsPage gotoContactsPage() throws InterruptedException{
		clickOnContacts();
		
		return new ContactsPage(driver);
		}
		 
	
}
