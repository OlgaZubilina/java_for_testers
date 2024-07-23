package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {
    @Test
    void canRegisterUser() {
        String url = "";
        var username = CommonFunctions.randomString(10);
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email, "password");//создать адрес на почтовым сервере
        app.session().createAccount(username, email);// заполняем форму создания и отправляем
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));//ждем почту
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
           url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
        app.session().submitRegistration(url,username);//проходим по ссылке и завершаем регистрацию пользователя
        app.http().login(username,"password");//проверяем что пользователь может залогиниться
        Assertions.assertTrue(app.http().isLoggedIn());
    }


}

