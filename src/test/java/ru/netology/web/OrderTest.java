package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    @Test
    public void shouldNotSubmitEnglishName() {
        $("[data-test-id=name] input").setValue("Ivanov Ian");
        $("[data-test-id=phone] input").setValue("+30666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldNotSubmitWrongPhone() {
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("80666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldNotSubmitWithoutAgreement() {
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+70666345792");
//        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    public void shouldSubmitWithLongName() {
        $("[data-test-id=name] input").setValue("Ивановаыпаывплыопмиукшгпгщшиппмлывоирфпжшпоимиыуыпшгмыитвлаопгш Иванцволапсадлоывамишапмишпмшпиглпшдгфрымшпашгйн");
        $("[data-test-id=phone] input").setValue("+70666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    public void shouldNotSubmitShortPhone() {
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+706663492");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    public void shouldSubmitWithThreeNames() {
        $("[data-test-id=name] input").setValue("Иванов Иван Иванович");
        $("[data-test-id=phone] input").setValue("+70666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    public void shouldSubmitWithLetter() {
        $("[data-test-id=name] input").setValue("Ивайнова Алина");
        $("[data-test-id=phone] input").setValue("+70666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Disabled
    @Test
    public void shouldNotSubmitWithLetter() {
        $("[data-test-id=name] input").setValue("Иванова Алёна");
        $("[data-test-id=phone] input").setValue("+70666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    public void shouldNotSubmitEmptyFieldName() {
        $("[data-test-id=name] input").setValue(" ");
        $("[data-test-id=phone] input").setValue("+70666345792");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    public void shouldNotSubmitEmptyFieldPhone() {
        $("[data-test-id=name] input").setValue("Иванов Иван ");
        $("[data-test-id=phone] input").setValue(" ");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }


}


