package com.kanezi.springsocial2cloud.security;

import com.kanezi.springsocial2cloud.db.CleanUpTestData;
import com.kanezi.springsocial2cloud.pages.*;
import com.kanezi.springsocial2cloud.selenide.SelenideTestcontainerRunnerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;

@ActiveProfiles("postgres")
@CleanUpTestData
public class UsersE2EBrowserTest extends SelenideTestcontainerRunnerTest {

    private final Menu menu = new Menu();
    private final HomePage homePage = new HomePage();
    private final SignUpPage signUpPage = new SignUpPage();
    private final LogInPage logInPage = new LogInPage();
    private final UserPage userPage = new UserPage();

    @Test
    void homePageIsAccessible() {
        homePage.open();

        homePage.header.shouldHave(text("Spring social 2 cloud"));
        menu.buttons.shouldHave(size(2));
    }

    @Test
    void usersCanSignUpAndChangePasswords() {

        homePage.open();
        menu.signUp();
        signUpPage.signUp("test", "test password");

        menu.logIn();
        logInPage.logIn("test", "test password");

        userPage.username.shouldHave(text("test"));

        userPage.changePassword("test password", "987654");
        menu.logOut();
        menu.logIn();
        logInPage.logIn("test", "987654");

        userPage.username.shouldHave(text("test"));

    }

}
