import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


    class LoginPage {

        String jsessionCookie;

        @Step("Enter Login")
        void enterLogin(String login){
         $("#login-form-username").shouldBe(visible).setValue(login);
        }

        @Step("Enter Password")
        void enterPassword(String password){
            $("#login-form-password").setValue(password);
        }

        @Step("Submit Button")
        void submitButton(){
            $("#login-form-submit").click();}

        public WebElement errorMessegeFailLogin(){
           return $("div.aui-message.error");
            }
    }

