package com.kanezi.springsocial2cloud.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserPage {
    private final SelenideElement passwordChangeTab = $("#user_segment a.item[data-tab='change_password']");

    private final SelenideElement oldPasswordInput = $("#oldPassword");
    private final SelenideElement newPasswordInput = $("#password");
    private final SelenideElement newPasswordConfirmInput = $("#passwordConfirm");

    private final SelenideElement submitButton = $("input[type='submit']");

    public final SelenideElement username = $("div.content a.header");


    public void changePassword(String oldPassword, String newPassword) {
        passwordChangeTab.click();

        oldPasswordInput.setValue(oldPassword);
        newPasswordInput.setValue(newPassword);
        newPasswordConfirmInput.setValue(newPassword);

        submitButton.click();
    }

}
