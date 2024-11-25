import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class practiceFormAutotests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
// To block pop-up banners
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
// General information
        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("ivanov@example.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("8999456743");
// Calendar
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("4");
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $(".react-datepicker__day--016").click();
// Subjects
        $("#subjectsInput").setValue("English").pressEnter();
// Hobbies
        $("#hobbiesWrapper").$(byText("Reading")).click();
// Picture
        $("#uploadPicture").uploadFromClasspath("imageForTest.jpg");
// Current Address
        $("#currentAddress").setValue("Some address!");
//State and City
        $("#state").click();
        $(byText("Rajasthan")).click();
        $("#city").click();
        $(byText("Jaiselmer")).click();
// Submit button
        $("#submit").click();

// Submitting the form
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table").shouldHave(text("Student Name")).shouldHave(text("Alex Ivanov"));
        $(".table").shouldHave(text("Student Email")).shouldHave(text("ivanov@example.com"));
        $(".table").shouldHave(text("Gender")).shouldHave(text("Female"));
        $(".table").shouldHave(text("Mobile")).shouldHave(text("8999456743"));
        $(".table").shouldHave(text("Date of Birth")).shouldHave(text("16 May,2000"));
        $(".table").shouldHave(text("Subjects")).shouldHave(text("English"));
        $(".table").shouldHave(text("Hobbies")).shouldHave(text("Reading"));
        $(".table").shouldHave(text("Picture")).shouldHave(text("imageForTest.jpg"));
        $(".table").shouldHave(text("Address")).shouldHave(text("Some address!"));
        $(".table").shouldHave(text("State and City")).shouldHave(text("Rajasthan Jaiselmer"));
    }
}