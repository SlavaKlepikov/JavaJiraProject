import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.security.acl.Group;
import java.util.List;
import java.util.Set;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestJira {

    private static LoginPage loginPage;
    private static SearchPage searchPage;
    private static DashboardPage dashboardPage;
    private static ManageFiltersPages manageFiltersPages;



    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void setupSuite(String browser) {
      loginPage = new LoginPage();
      searchPage = new SearchPage();
      dashboardPage = new DashboardPage();
      manageFiltersPages = new ManageFiltersPages();
      Configuration.browser = browser;
      open(LoadProperties.getPropValue("urlJira"));
      loginPage.enterLogin(LoadProperties.getPropValue("login"));
      loginPage.enterPassword(LoadProperties.getPropValue("password"));
      loginPage.submitButton();
      dashboardPage.atDashboardPage();
      loginPage.jsessionCookie = getWebDriver().manage().getCookieNamed("JSESSIONID").getValue();
      getWebDriver().manage().getCookieNamed("JSESSIONID").getValue();
      getWebDriver().quit();}


   @BeforeMethod (alwaysRun = true)
   public void setupMethod() {
       open(LoadProperties.getPropValue("urlJira"));
       Cookie ck = new Cookie("JSESSIONID", loginPage.jsessionCookie);
       WebDriverRunner.getWebDriver().manage().addCookie(ck);
       open(LoadProperties.getPropValue("urlJiraDashboard"));
   }

    @Test (groups = "login")
    public void testDashboardPage(){
        dashboardPage.atDashboardPage();
    }

    @Test(dataProvider = "login-provider", dependsOnGroups = "login")
    public void testLoginMethod(String login,String password, String urlJira) {
        open(urlJira);
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.submitButton();
        dashboardPage.atDashboardPage();
    }

    @DataProvider(name="login-provider")
    public Object[][] dataProviderMethod(){
      Object [][] data =  new Object[2][3];
      data[0][0]=LoadProperties.getPropValue("login");
      data[0][1]=LoadProperties.getPropValue("password");
      data[0][2]=LoadProperties.getPropValue("urlJira");

      data[1][0]=LoadProperties.getPropValue("login2");
      data[1][1]=LoadProperties.getPropValue("password2");
      data[1][2]=LoadProperties.getPropValue("urlJira2");
        return data;
    }

    @Test (dependsOnGroups = "login",groups ="regression")
    public void CheckingProjectFilterEpicType()  {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickSearchProjectButton();
        searchPage.selectProject("QAAUTO-6");
        searchPage.searchResultsContains("QAAUT6");
        searchPage.fiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.fiterTypeIssue();
        searchPage.clickButtonChangeViews();
        searchPage.clickDetailView();
        searchPage.checkTypeOfFirstPositionInIssueList("Epic");
        List<SelenideElement> listImg= searchPage.fullListSelenideElementsImg();
        for (WebElement element: listImg) {
            Assert.assertEquals(element.getAttribute("alt"), "Epic"); }
    }

    @Test (dependsOnGroups = "login",groups ="regression")
    public void testSaveFilter(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickFindFiltersButton();
        manageFiltersPages.clickMyButton();
        manageFiltersPages.deleteFilterIfExist("1 testSaveFilter");
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        searchPage.searchResultsContains("QAAUT6");
        searchPage.clickSaveAsButton();
        searchPage.enterFilterName("1 testSaveFilter");
        searchPage.clickSubmitFilterName();
        searchPage.clickFindFiltersButton();
        manageFiltersPages.clickMyButton();
        manageFiltersPages.checkAvailabilityFilter("1 testSaveFilter");
        manageFiltersPages.deleteFilterIfExist("1 testSaveFilter");}


    @Test (dependsOnGroups = "login",groups ="regression" )
    public void testJiraCoreHelpPageOpenNewTab() {
        dashboardPage.atDashboardPage();
        String handleDashboard= getWebDriver().getWindowHandle();
        dashboardPage.clickHelpMenu();
        dashboardPage.clickJiraCoreHelp();
        Set<String> handles = getWebDriver().getWindowHandles();
        Assert.assertEquals(handles.size(),2);
        handles.remove(handleDashboard);
        String handleJavaCoreHelp=handles.iterator().next();
        getWebDriver().switchTo().window(handleJavaCoreHelp);
        dashboardPage.jiraCoreHelpPage();
  }

    @Test(dependsOnGroups = "login", groups = "negative")
    public void testLoginNegativeMethod() {
        open(LoadProperties.getPropValue("urlJira"));
        loginPage.enterLogin(LoadProperties.getPropValue("loginFail"));
        loginPage.enterPassword(LoadProperties.getPropValue("passwordFail"));
        loginPage.submitButton();
        Assert.assertEquals(loginPage.errorMessegeFailLogin().getText(),"Sorry, your username and password are incorrect - please try again.");
        }

   @AfterMethod
   public void tearDown()
   {
       getWebDriver().quit();
   }
}

