package serenity.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenity.steps.serenity.HnEndUserSteps;

@RunWith(SerenityRunner.class)
public class HnSearchStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public HnEndUserSteps gilfoyle;

    @Test
    public void searching_by_keyword_cpp_should_display_the_corresponding_article() {
        gilfoyle.opens_the_hn_search_page();
        gilfoyle.looks_up("linux");
        gilfoyle.should_see_article_title("My Business Card Runs Linux");
    }

    @Test
    public void searching_by_keyword_swift_should_not_display_results_for_cpp() {
        gilfoyle.opens_the_hn_search_page();
        gilfoyle.looks_up("swift");
        gilfoyle.should_not_see_article_title("KABOOM in 180 lines of bare C++");
    }
} 