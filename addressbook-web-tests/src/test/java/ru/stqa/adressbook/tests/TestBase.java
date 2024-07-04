package ru.stqa.adressbook.tests;

import ru.stqa.adressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    protected static ApplicationManager app;


    @BeforeEach
    public void setUp() {
       if (app==null) {
           app=new ApplicationManager();
       }
        app.init(System.getProperty("browser","firefox"));

    }

}
