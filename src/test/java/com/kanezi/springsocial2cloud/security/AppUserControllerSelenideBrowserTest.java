package com.kanezi.springsocial2cloud.security;

import com.codeborne.selenide.ElementsCollection;
import com.kanezi.springsocial2cloud.selenide.SelenideTestcontainerRunner;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@ActiveProfiles("postgres")
public class AppUserControllerSelenideBrowserTest extends SelenideTestcontainerRunner {

    @Test
    void test() {
        open("/");

        $("h1").shouldHave(text("Spring social 2 cloud"));

        ElementsCollection buttons = $$("div .button");

        buttons.shouldHave(size(2));
    }

}
