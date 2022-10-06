package com.company.proautomation.ui.pages.constants;

/**
 * @author vsanap
 * @created on 30-Sep-22
 * @project pro-automation
 */
public class ConfirmPopupConstants {
    private static String CONFIRM_MESSAGE_LOCATOR = "//div[contains(text(), \"%s\")]";
    public static String CONFIRM_MESSAGE =
            String.format(CONFIRM_MESSAGE_LOCATOR, "Thanks for submitting the form");
}
