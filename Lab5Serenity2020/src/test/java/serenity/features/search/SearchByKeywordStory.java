package serenity.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import serenity.steps.serenity.EndUserSteps;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps anna;

    @Test
    public void searching_by_keyword_apple_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("apple");
        anna.should_see_definition("A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.");

    }

    @Test
    public void searching_by_keyword_banana_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("banana");
        anna.should_see_definition("An elongated curved tropical fruit that grows in bunches and has a creamy flesh and a smooth skin.");
    }

    @Test
    public void searching_by_keyword_pear_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("pear");
        anna.should_see_definition("An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.");
    }

    @Test
    public void searching_by_keyword_mitigation_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("mitigation");
        anna.should_see_definition("A reduction or decrease of something harmful or unpleasant.");
    }

    @Test
    public void searching_by_keyword_quacksalver_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("quacksalver");
        anna.should_see_definition("(archaic) One falsely claiming to possess medical or other skills, especially one who dispenses potions, ointments, etc., supposedly having curative powers; a quack.");
    }

    @Pending @Test
    public void searching_by_ambiguious_keyword_should_display_the_disambiguation_page() {
    }
} 