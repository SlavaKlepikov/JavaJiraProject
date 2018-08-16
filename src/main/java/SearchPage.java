import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

class SearchPage {


    void searchButton(){
        $(By.cssSelector("[class='aui-item aui-button aui-button-subtle search-button']")).click(); }

    void selectProject(String request){
        $(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow")).click();
        $(By.id("searcher-pid-input")).setValue(request);
        $(By.cssSelector("label[title='" +request+"']")).click(); }

    void saveAsButton(){
        $(By.cssSelector(".aui-button.aui-button-light.save-as-new-filter")).click(); }

    void enterFilterName(String request){
        $(By.id("filterName")).setValue(request); }


    void fiterProject(){
        $(By.cssSelector("button[data-id='project']")).click();
    }

    void fiterTypeIssue(){
        $(By.cssSelector("button[data-id='issuetype']")).click();
    }

    void selectQAAUTO6Profect(){
        $(By.cssSelector("input[value='11400']")).click();
    }

    void selectEpicFilter(){
        $(By.cssSelector("input[value='10000']")).click();
    }

    void clickButtonChangeViews(){
        $(".aui-buttons").click();}
    void clickDetailView(){
        $("a.aui-list-item-link[data-layout-key='split-view'").click(); }
    void clickListView(){
        $("a.aui-list-item-link[data-layout-key='list-view']").click(); }
}
