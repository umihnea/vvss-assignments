package serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://hn.algolia.com/")
public class HackerNewsPage extends PageObject {

    @FindBy(css="#root > div > div > header > div > div.SearchHeader_container > input")
    private WebElementFacade searchBar;

    @FindBy(id="downshift-2-label")
    private WebElementFacade rangeDropDown;

    public void searchAndPressEnter(String keyword) {
        searchBar.typeAndEnter(keyword);
    }

    public void setRangeTo(String range) {
        rangeDropDown.click();
        find(By.xpath("//button[contains(text(),'" + range + "')]")).click();
    }

    public List<String> getSearchResults() {
        WebElementFacade root = find(By.cssSelector("#root"));
        return root.findElements(By.cssSelector("div[class='Story_title'] > a > span")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}