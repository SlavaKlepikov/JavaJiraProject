import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

 class DashboardPage {

    void issueButton() {
        $(By.id("find_link")).click();
    }

    void searchOfIssues() {
        $("#issues_new_search_link_lnk").click();
    }
}