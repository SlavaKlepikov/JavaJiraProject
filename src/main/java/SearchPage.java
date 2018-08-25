import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

class SearchPage {


    void searchButton(){
        $(By.cssSelector("[class='aui-item aui-button aui-button-subtle search-button']")).click(); }

    void selectProject(String request){
        $(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow")).click();
        $(By.id("searcher-pid-input")).setValue(request);
        $(By.cssSelector("label[title='" +request+"']")).click(); }

    void clickSaveAsButton(){
        $(By.className("filter-operations")).shouldBe(Condition.visible, Condition.enabled).click(); }

    void enterFilterName(String request){
        $(By.id("save-filter-dialog")).shouldBe(Condition.visible);
        $(By.id("filterName")).shouldBe(Condition.visible).shouldBe(Condition.enabled).setValue(request); }


    void fiterProject(){
        $(By.cssSelector("button[data-id='project']")).click();
    }

    void clickSearchProjectButton(){
        if ($(By.cssSelector("span.aui-icon.icon-close")).isDisplayed()){
            $(By.cssSelector("span.aui-icon.icon-close")).click();}
        else{}
        if($(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow")).isDisplayed()){ }
        else {
            $(By.xpath("//a[contains(@class, 'switcher-item active ')]")).shouldBe(Condition.visible).shouldBe(Condition.enabled).click(); }
    }

    void selectProjectQAAUTO6(String request){
        $(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow")).click();
        $(By.id("searcher-pid-input")).setValue(request);
        $(By.cssSelector("label[title='QAAUTO-6']")).click();
        $(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow")).click();}

    public void clickSubmitFilterName(){ $(By.cssSelector(".aui-button.submit")).click(); }

    void fiterTypeIssue(){
        $(By.cssSelector("button[data-id='issuetype']")).click();
    }

    void selectQAAUTO6Profect(){
        $(By.cssSelector("input[value='11400']")).click();
    }

    void selectEpicFilter(){
        $(By.cssSelector("input[value='10000']")).shouldBe(Condition.visible).click();
    }

    void clickFindFiltersButton(){
        $(By.cssSelector(".find-filters")).click(); }

    void clickButtonChangeViews(){
        $(".aui-buttons").click();}
    void clickDetailView(){
        $("a.aui-list-item-link[data-layout-key='split-view'").click(); }
    void clickListView(){
        $("a.aui-list-item-link[data-layout-key='list-view']").click(); }

    void searchResultsContains(String request){
    $(".focused").shouldHave(attribute("data-key")).shouldHave(text(request));
    }

    List<SelenideElement> issueListContentType(){
    return $(".list-content").$$("img");}


}
