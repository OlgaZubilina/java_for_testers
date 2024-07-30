package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.regex.Pattern;

@Nested
class UserCreationTests extends TestBase
{
    DeveloperMailUser user;
    @Test
    void canRegisterUserApi2() {
String url = "";

        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
        app.session().createAccount(user.name(), email);

        // заполняем форму создания и отправляем
        var message = app.developerMail().receive(user, Duration.ofSeconds(60));//ждем почту

        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(message);
        if (matcher.find()) {
            url = message.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
        app.session().submitRegistration(url, user.name());//проходим по ссылке и завершаем регистрацию пользователя
        app.http().login(user.name(),password);//проверяем что пользователь может залогиниться
        Assertions.assertTrue(app.http().isLoggedIn());
    }

@Test
void canAddUser(){
        app.rest().addUser(new UserData().withUsername("testuser457776").withEmail("testuser777745766@localhost"));
}



   /* @AfterEach
void deleteMailUser(){
     app.developerMail().deleteUser(user);
    }*/
}
