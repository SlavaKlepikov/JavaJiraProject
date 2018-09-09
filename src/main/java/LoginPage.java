import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


    class LoginPage {

        String jsessionCookie;
        void enterLogin(String login){
         $("#login-form-username").shouldBe(visible).setValue(login);
        }

        void enterPassword(String password){
            $("#login-form-password").setValue(password);
        }


        void submitButton(){
            $("#login-form-submit").click();}

        public WebElement errorMessegeFailLogin(){
           return $("div.aui-message.error");
            }
    }

