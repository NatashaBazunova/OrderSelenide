package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }
    @Test
    public void shouldSubmitRequest() {
        $("[data-test-id=name] input").setValue("Наталья");
        $("[data-test-id=phone] input").setValue("+78956345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
//
    }

    @Test
    public void shouldSubmitRequestWithFullName() {
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+78956345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldSubmitFullNameWithHyphen() {
        $("[data-test-id=name] input").setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input").setValue("+78956345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    public void shouldSubmitRequestUkrNumber() {
        $("[data-test-id=name] input").setValue("Иванов-Сидоров Иван");
        $("[data-test-id=phone] input").setValue("+30666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}


