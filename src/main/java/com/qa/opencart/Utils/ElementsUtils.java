package com.qa.opencart.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.frameworks.ElementsExceptions;

public class ElementsUtils {

	private WebDriver driver;
	private Actions act;

	public ElementsUtils(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}

	public WebElement getelement(By locator) {

		WebElement id = driver.findElement(locator);
		return id;
	}

	public void dosendkeys(By locators, String value) {
		if (value == null) {
			throw new ElementsExceptions("Invalid ----Can'nt Be null");
		} else {
			getelement(locators).sendKeys(value);
		}
	}

	public void doClick(By locators) {

		getelement(locators).click();
	}

	public String getelementtext(By locators) {
		String gettext = getelement(locators).getText();
		return gettext;

	}

	public boolean iselementDisplayed(By locators) {
		try {
			boolean flag = getelement(locators).isDisplayed();

			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Element not found");
			return false;
		}
	}

	public boolean iselementeEnabled(By locator) {
		try {
			boolean br = getelement(locator).isEnabled();

			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Element not found or interacatble");
			return false;
		}
	}

	public String getDomPropertyvalue(By locator, String attname) {

		String attValue = getelement(locator).getDomProperty(attname);
		return attValue;
	}

	public String getDomattributevalue(By locator, String attname) {
		String attvalue = getelement(locator).getDomAttribute(attname);
		return attvalue;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> eleList = driver.findElements(locator);
		return eleList;

	}

	public int getTotalElementsize(By locator) {
		int count = getElements(locator).size();
		return count;
	}

	public List<String> getElementsTextlist(By locator) {

		List<WebElement> eleList = getElements(locator);
		List<String> linkslists = new ArrayList<String>();

		for (int i = 0; i < eleList.size(); i++) {
			String lists = eleList.get(i).getText();
			if (lists.length() != 0) {
				linkslists.add(lists);
			}

		}
		return linkslists;
	}

	public void printElementsTextlist(By locator) {
		List<WebElement> eleList = driver.findElements(locator);
		for (int i = 0; i < eleList.size(); i++) {
			String elements = eleList.get(i).getText();
			if (elements.length() != 0) {
				System.out.println(elements);
			}

		}
		// System.out.println(eleList.size());
	}

	public boolean isElementExit(By locator) {
		int count = getTotalElementsize(locator);
		if (count == 1) {
			System.out.println("Elemnt is Available 1 Time");
			return true;
		} else {
			System.out.println("Element is not Avialble");
			return false;
		}

	}

	public boolean isElementExit(By locator, int Expectedcount) {
		int count = getTotalElementsize(locator);
		if (count == Expectedcount) {
			System.out.println("Elemnt is Available1" + Expectedcount + "Time ");
			return true;
		} else {
			System.out.println("Element is not Avialble " + Expectedcount + "Time");
			return false;
		}

	}

	public void clickSingleElementList(By locator, String text) {
		List<WebElement> elist = getElements(locator);

		for (WebElement e : elist) {

			String textlist = e.getText();
			System.out.println(e.getText());
			if (textlist.equals(text)) {
				e.click();
				break;

			}
		}

	}

	public void doSearchClickAutosuggestion(By Searchlocator, String Searchvalue, By Searchsuggestion, String Value)
			throws InterruptedException {

		dosendkeys(Searchlocator, Value);

		Thread.sleep(3000);

//				IN this we should start from UL tag means unordered list

		List<WebElement> elements = getElements(Searchlocator);

		for (WebElement e : elements) {
			String listtext = e.getText();

			System.out.println(listtext);

			if (listtext.equals(Value)) {
				e.click();
				break; // why Break:if we dont apply break it will keep searching the list and give
						// STALE ELEMENT EXCEPTION
			}

		}
	}
	// ***********Select Class Methods******************

	public void doselectByindex(By locator, int index) {
		Select select = new Select(getelement(locator));
		select.selectByIndex(index);
	}

	public void doselectByvalue(By locator, String value) {
		Select select = new Select(getelement(locator));
		select.selectByValue(value);
	}

	public void doselectByStringText(By locator, String text) {
		Select select = new Select(getelement(locator));
		select.selectByVisibleText(text);
	}

	public void menuSubmenuHandling(By parentmenu, By childmenu) throws InterruptedException {
		WebElement menu = getelement(parentmenu);

//				Actions act=new Actions(driver);
		act.moveToElement(menu).perform();

		Thread.sleep(2000);

		WebElement SubMenu = getelement(childmenu);
		doClick(childmenu);

	}

	public void deselectByIndex(By locator, int index) {
		Select sec = new Select(driver.findElement(locator));
		sec.deselectByIndex(index);
	}

	public void deselectByValue(By locator, String Value) {
		Select sec = new Select(driver.findElement(locator));
		sec.deselectByValue(Value);

	}

	public void deselectByVisisbleText(By locator, String text) {
		Select sec = new Select(driver.findElement(locator));
		sec.deselectByValue(text);
	}

	public void getFashionsetail(By Categorymenu, By Parentmenu, By Childmenu, By ChildSubmenu)
			throws InterruptedException {

		WebElement Category = getelement(Categorymenu);
		doClick(Categorymenu);

		WebElement fashion = getelement(Parentmenu);

		Thread.sleep(2000);

		// Actions action=new Actions(driver);
		act.moveToElement(fashion).perform();

		Thread.sleep(2000);
		WebElement footwear = getelement(Childmenu);
		act.moveToElement(footwear).perform();

		Thread.sleep(2000);
		WebElement kidsfoot = getelement(ChildSubmenu);
		System.out.println(kidsfoot.getText());
		doClick(ChildSubmenu);

	}

	public WebElement getelemntByactionsClass(By locator) {
		WebElement ele = driver.findElement(locator);
		act = new Actions(driver);
		return ele;
	}

	public void sendkeysByActionsclass(By locator, String text) {
		act = new Actions(driver);
		act.sendKeys(getelemntByactionsClass(locator), text).perform();
	}

	public void doclickByActionClass(By locator) {
		act = new Actions(driver);
		act.click(driver.findElement(locator)).perform();

	}

	// ***********ExplicitWaits utility************************
	public boolean checkelementpresence(By locator, int timeout) {
		WebDriverWait wc = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			WebElement wait = wc.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			System.out.println("*****TimeoutException is coming*******");
		}

		return false;
	}

	public void sendkeyspresenceElement(By locator, String text) {
		if (checkelementpresence(locator, 5)) {
			WebElement el = driver.findElement(locator);
			el.sendKeys(text);
		} else {
			System.out.println("*****Element not found ");
		}
	}

	public String getTextwithWait(By locator, String text) {
		driver.findElement(locator).click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(6));
		Alert al2 = wait2.until(ExpectedConditions.alertIsPresent());
		al2.sendKeys(text);
		String text4 = al2.getText();
		System.out.println(text4);
		al2.accept();

		return text;
	}

	public String dismissAlertandGetText(By locator) {
		driver.findElement(locator).click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(6));
		Alert al2 = wait2.until(ExpectedConditions.alertIsPresent());
		String ele = al2.getText();

		al2.dismiss();
		return ele;
	}

	public String AcceptAlertandGetText(By locator) {
		driver.findElement(locator).click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(6));
		Alert al2 = wait2.until(ExpectedConditions.alertIsPresent());
		String ele = al2.getText();

		al2.accept();
		return ele;
	}

	public String WaitforTitle(String ExpectedValue, int Timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
		try {
			boolean flag = wait.until(ExpectedConditions.titleContains(ExpectedValue));
		} catch (TimeoutException e) {
			System.out.println("Exception is coming");
			e.printStackTrace();
		}
		return driver.getTitle();

	}

	public String WaitforTitleis(String ExpectedValue, int Timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
		try {
			boolean flag = wait.until(ExpectedConditions.titleIs(ExpectedValue));
		} catch (TimeoutException e) {
			System.out.println("Exception is coming");
			e.printStackTrace();
		}
		return driver.getTitle();

	}

	public String WaitforURL(String expectedvalue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			boolean flag = wait.until(ExpectedConditions.urlContains(expectedvalue));
		} catch (TimeoutException e) {

			System.out.println("Exception is Coming");

		}
		return driver.getCurrentUrl();
	}

	public String WaitforURLis(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			boolean flag = wait.until(ExpectedConditions.urlToBe(url));
		} catch (TimeoutException e) {

			System.out.println("Exception is Coming");
		}
		return driver.getCurrentUrl();
	}

	public List<WebElement> waitforElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		List<WebElement> elist = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return elist;

	}

	public List<WebElement> waitforElementsVisibbility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		List<WebElement> elist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return elist;

	}

	public void WaitforElementClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementPresence(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
