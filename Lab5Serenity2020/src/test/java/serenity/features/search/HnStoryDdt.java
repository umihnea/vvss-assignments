package serenity.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenity.steps.serenity.HnEndUserSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/HackerNewsTestData.csv")
public class HnStoryDdt {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "https://hn.algolia.com/")
    public Pages pages;

    public String keyword;
    public String topArticleTitle;

    @Qualifier
    public String getQualifier() {
        return String.format("Searching '%s' gives top article '%s'.", keyword, topArticleTitle);
    }

    @Steps
    public HnEndUserSteps gilfoyle;

    @Test
    public void searchHackerNewsByKeywordTestDDT() {
        gilfoyle.opens_the_hn_search_page();
        gilfoyle.looks_up(getKeyword());
        gilfoyle.should_see_article_title(getTopArticle());
    }

    public HnStoryDdt(String keyword, String topArticleTitle) {
        this.keyword = keyword;
        this.topArticleTitle = topArticleTitle;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTopArticle() {
        return topArticleTitle;
    }

    public void setTopArticle(String topArticleTitle) {
        this.topArticleTitle = topArticleTitle;
    }

}