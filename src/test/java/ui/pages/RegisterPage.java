package ui.pages;

import com.microsoft.playwright.Page;

import ui.data.RegisterUserData;

public class RegisterPage {

    private final Page page;

    public RegisterPage(Page page) {
        this.page = page;
    }

    public void openRegistrationForm() {
        page.click("text=Register");
    }

    public void enterFirstName(String firstName) {
        page.locator("#customer\\.firstName")
                .fill(firstName);
    }

    public void enterLastName(String lastName) {
        page.locator("#customer\\.lastName")
                .fill(lastName);
    }

    public void enterStreet(String street) {
        page.locator("#customer\\.address\\.street")
                .fill(street);
    }

    public void enterCity(String city) {
        page.locator("[id='customer.address.city']")
                .fill(city);
    }

    public void enterState(String state) {
        page.locator("[id='customer.address.state']")
                .fill(state);
    }

    public void enterZipCode(String zipCode) {
        page.locator("[id='customer.address.zipCode']")
                .fill(zipCode);
    }

    public void enterPhoneNumber(String phoneNumber) {
        page.locator("[id='customer.phoneNumber']")
                .fill(phoneNumber);
    }

    public void enterSSn(String ssn) {
        page.locator("[id='customer.ssn']")
                .fill(ssn);
    }

    public void enterUsername(String username) {
        page.locator("[id='customer.username']")
                .fill(username);
    }

    public void enterPassword(String password) {
        page.locator("[id='customer.password']")
                .fill(password);
    }

    public void enterRepeatedPassword(String repeatedPassword) {
        page.locator("[id='repeatedPassword']")
                .fill(repeatedPassword);
    }

    public void clickRegister() {
        page.locator("[value='Register']")
                .click();
    }

    public boolean isUserRegistered(String username) {
       return getWelcomeMessage().contains(username);

    }
    public String getWelcomeMessage(){
       return page.locator(".title").innerText();
       
    }
    public void registerUser(RegisterUserData userData){
        
        enterFirstName(userData.getFirstName());
        enterLastName(userData.getLastName());
        enterStreet(userData.getStreet());
        enterCity(userData.getCity());
        enterState(userData.getState());
        enterZipCode(userData.getZipCode());
        enterPhoneNumber(userData.getPhoneNumber());
        enterSSn(userData.getSsn());
        enterUsername(userData.getUsername());
        enterPassword(userData.getPassword());
        enterRepeatedPassword(userData.getRepeatedPassword());

    }
}
