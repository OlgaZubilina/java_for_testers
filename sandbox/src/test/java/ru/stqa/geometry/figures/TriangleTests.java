package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test // Тест на проверку вычисления периметра
    void canCalculatePerimeter() {
        var s = new Triangle(5., 6., 7.);
        double result = s.perimeter();
        Assertions.assertEquals(18, result);
    }

    @Test // Тест на проверку вычисления площади
    void canCalculateArea() {
        var s = new Triangle(5.0, 6.0, 7.0);
        double Area = s.area();

        Assertions.assertEquals(15, Math.ceil(Area));
    }

    @Test //Тест на исключение отрицательных сторон треугольника и сторон, равных нулю
    void canValidateSide () {
        try {
            new Triangle(-1, 3, 4);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
        try {
            new Triangle(3, -1, 4);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
        try {
            new Triangle(5, 2, -1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
        try {
            new Triangle(0, 3, 4);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
        try {
            new Triangle(3, 0, 4);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
        try {
            new Triangle(5, 2, 0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test // Тест на проверку существования треугольника
     void canTriangleExist () {
         try {
             new Triangle(3, 2, 1);
             Assertions.fail();
         } catch (IllegalArgumentException exception) {
             //ok
         }
         try {
             new Triangle(2, 3, 1);
             Assertions.fail();
         } catch (IllegalArgumentException exception) {
             //ok
         }
         try {
             new Triangle(2, 1, 3);
             Assertions.fail();
         } catch (IllegalArgumentException exception) {
             //ok
         }
         try {
             new Triangle(4, 2, 1);
             Assertions.fail();
         } catch (IllegalArgumentException exception) {
             //ok
         }
         try {
             new Triangle(2, 4, 1);
             Assertions.fail();
         } catch (IllegalArgumentException exception) {
             //ok
         }
         try {
             new Triangle(1, 2, 4);
             Assertions.fail();
         } catch (IllegalArgumentException exception) {
             //ok
         }
     }

     @Test //Сравнение треугольников
    void equalityTriangle(){
        var t0 = new Triangle(3.0,4.0,5.0);
        var t1 = new Triangle(3.0,4.0,5.0);
        var t2 = new Triangle(3.0,5.0,4.0);
        var t3 = new Triangle(5.0,3.0,4.0);
        var t4 = new Triangle(5.0,4.0,3.0);
        var t5 = new Triangle(4.0,5.0,3.0);
        var t6 = new Triangle(4.0,3.0,5.0);
        Assertions.assertTrue(t0.equals(t1));
        Assertions.assertTrue(t0.equals(t2));
        Assertions.assertTrue(t0.equals(t3));
        Assertions.assertTrue(t0.equals(t4));
        Assertions.assertTrue(t0.equals(t5));
        Assertions.assertTrue(t0.equals(t6));


    }



}









