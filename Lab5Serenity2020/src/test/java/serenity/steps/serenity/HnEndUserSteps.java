package serenity.steps.serenity;

import net.thucydides.core.annotations.Step;
import serenity.pages.HackerNewsPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;

public class HnEndUserSteps {

    HackerNewsPage hackerNewsPage;

    @Step
    public void looks_up(String keyword) {
        hackerNewsPage.searchAndPressEnter(keyword);
    }

    @Step
    public void should_see_article_title(String articleTitle) {
        assertThat(hackerNewsPage.getSearchResults(), hasItem(containsString(articleTitle)));
    }

    @Step
    public void should_not_see_article_title(String articleTitle) {
        assertThat(hackerNewsPage.getSearchResults(), not(hasItem(containsString(articleTitle))));
    }

    @Step
    public void opens_the_hn_search_page() {
        hackerNewsPage.open();
    }

    @Step
    public void set_custom_range(String range) {
        hackerNewsPage.setRangeTo(range);
    }

}