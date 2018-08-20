import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestJira {

    private static LoginPage loginPage = new LoginPage();
    private static SearchPage searchPage = new SearchPage();
    private static DashboardPage dashboardPage = new DashboardPage();


   @BeforeSuite
   public void setup() {
       Configuration.browser = "chrome";
       open(LoadProperties.getPropValue("urlJira"));
       loginPage.enterLogin(LoadProperties.getPropValue("login"));
       loginPage.enterPassword(LoadProperties.getPropValue("password"));
   }
    @BeforeMethod
       public void setupCookie() {
           String jsessionCookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("JSESSIONID").getValue();
           Cookie ck = new Cookie("JSESSIONID", jsessionCookie);
           WebDriverRunner.getWebDriver().manage().addCookie(ck);
       }


    @Test
    public void testMainPage(){
        $(".aui-page-header-main").shouldHave(visible, text("System Dashboard"));
    }


    @Test
    public void CheckingProjectFilterEpicType()  {
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.selectProject("QAAUTO-6");
        searchPage.fiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.fiterTypeIssue();
        searchPage.clickButtonChangeViews();
        searchPage.clickDetailView();

        List<SelenideElement> listImg= $(".list-content").$$("img");
        for (WebElement element: listImg) {
            Assert.assertEquals(element.getAttribute("alt"), "Epic");}
    }

   @AfterMethod
   public void tearDown()
   {
       WebDriverRunner.getWebDriver().quit();
   }

}

