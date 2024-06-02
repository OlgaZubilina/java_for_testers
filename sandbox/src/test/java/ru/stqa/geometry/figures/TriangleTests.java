package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

public class TriangleTests {
@Test
    void canCalculatePerimeter (){
var s = new Triangle(5.,6.,7.);
double result = s.perimeter();
        Assertions.assertEquals(18,result);}
@Test
void canCalculateArea(){
    var s = new Triangle(5.0,6.0,7.0);
double Area = s.area();

    Assertions.assertEquals(15, Math.ceil(Area));
}}