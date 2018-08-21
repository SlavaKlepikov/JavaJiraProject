import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

 class DashboardPage {

    void clickIssueButton() {
        $(By.id("find_link")).click();
    }

    void clickSearchOfIssues() {
        $("#issues_new_search_link_lnk").click();
    }
}