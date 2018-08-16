import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


    class LoginPage {
        void enterLogin(String login){
            $("#login-form-username").shouldBe(visible).setValue(login);
        }

        void enterPassword(String password){
            $("#login-form-password").setValue(password);
        }


        void submitButton(){
            $("#login-form-submit").click();

        }

    }

