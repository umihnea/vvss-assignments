package serenity.features.filter;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenity.steps.serenity.HnEndUserSteps;

@RunWith(SerenityRunner.class)
public class HnFilterStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public HnEndUserSteps gilfoyle;

    @Test
    public void filter_for_past_week_articles() {
        gilfoyle.opens_the_hn_search_page();
        gilfoyle.set_custom_range("Week");
        gilfoyle.should_see_article_title("Deno 1.0");
    }

    @Test
    public void filter_for_past_month_articles() {
        gilfoyle.opens_the_hn_search_page();
        gilfoyle.set_custom_range("Month");
        gilfoyle.should_not_see_article_title("Hi, Amazon");
    }

} 