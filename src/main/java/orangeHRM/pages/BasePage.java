package orangeHRM.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import orangeHRM.constants.FrameworkConstants;
import orangeHRM.driver.DriverManager;
import orangeHRM.enums.WaitStrategy;

public class BasePage {

	protected void click(By by, WaitStrategy waitStrategy) {
		if (waitStrategy == WaitStrategy.CLICKABLE) {
			explicitlyWaitElementClick(by);
		} else if (waitStrategy == WaitStrategy.PRESENCE) {
			explicitlyWaitIfElementPresent(by);
		}

		DriverManager.getDriver().findElement(by).click();
	}

	protected void sendKeys(By by, String keys, WaitStrategy waitStrategy) {
		if (waitStrategy == WaitStrategy.CLICKABLE) {
			explicitlyWaitElementClick(by);
		} else if (waitStrategy == WaitStrategy.PRESENCE) {
			explicitlyWaitIfElementPresent(by);
		}
		DriverManager.getDriver().findElement(by).sendKeys(keys);
	}

	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

	protected String getURL() {
		return DriverManager.getDriver().getCurrentUrl();
	}

	protected String getText(By by) {
		getTextWithWait(by);
		return DriverManager.getDriver().findElement(by).getText();
	}

	protected boolean isElementDisplayed(By by) {
		isElementDisplayedWithWait(by);
		return DriverManager.getDriver().findElement(by).isDisplayed();
	}

	private boolean isElementDisplayedWithWait(By by) {
		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
				.until(ExpectedConditions.presenceOfElementLocated(by)).isDisplayed();
	}

	private String getTextWithWait(By by) {
		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
				.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
	}

	private void explicitlyWaitElementClick(By by) {
		new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
				.until(ExpectedConditions.elementToBeClickable(by));
	}

	private void explicitlyWaitIfElementPresent(By by) {
		new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwait()))
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}

}