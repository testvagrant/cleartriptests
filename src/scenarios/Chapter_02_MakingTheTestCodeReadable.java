package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Chapter_02_MakingTheTestCodeReadable {

    WebDriver driver = new FirefoxDriver();

    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        driver.get("http://www.cleartrip.com/");
        chooseToHaveAOneWayJourney();

        enterOriginAs("Bangalore");
        selectTheFirstAvailableAutoCompleteOption();

        enterDestinationAs("Delhi");
        selectTheFirstAvailableAutoCompleteOption();

        enterDepartureDate();

        //all fields filled in. Now click on search
        searchForTheJourney();
        waitForSearchResultsToAppear();

        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.id("outbound")));

        //close the browser
        driver.close();

    }


    public String dayAfterTomorrow() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 2);
        return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
    }

    private void searchForTheJourney() {
        driver.findElement(By.id("button_flight_search")).click();


    }

    public void waitForSearchResultsToAppear() {
        waitFor(5000);
    }


    private void enterDepartureDate() {
        driver.findElement(By.id("dpt_date")).clear();
        driver.findElement(By.id("dpt_date")).sendKeys(tomorrow());
    }


    private void enterDestinationAs(String destination) {
        driver.findElement(By.id("destination_autocomplete")).clear();
        driver.findElement(By.id("destination_autocomplete")).sendKeys(destination);
    }


    private void enterOriginAs(String origin) {
        driver.findElement(By.id("origin_autocomplete")).clear();
        driver.findElement(By.id("origin_autocomplete")).sendKeys(origin);
    }


    private void chooseToHaveAOneWayJourney() {
        driver.findElement(By.id("one_way")).click();
    }


    private void selectTheFirstAvailableAutoCompleteOption() {

        //select the first item from the auto complete list
        waitFor(2000);
        List<WebElement> optionsList = driver.findElement(By.id("autocompleteOptionsContainer")).findElements(By.tagName("li"));
        optionsList.get(0).click();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }



    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String tomorrow() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        return new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
    }


}
