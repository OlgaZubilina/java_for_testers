package ru.stqa.geometry.figures;

import java.util.Objects;

public class Triangle {
    double a;
    double b;
    double c;


    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a<=0 || b<=0 ||c<=0) {
            throw new IllegalArgumentException("Сторона треугольника не может быть меньше или равна нулю");
        }
        if ((a+b)<=c ||(b+c)<=a ||(c+a)<=b){
            throw new IllegalArgumentException("Сумма двух сторон треугольника не может быть меньше или равна третьей стороне");
        }

    }

    public double perimeter() {
        double p = (this.a + this.b + this.c);
        return p;


    }

    public double area() {
        double S = Math.sqrt(((this.perimeter() / 2) * ((this.perimeter() / 2) - this.a) * ((this.perimeter() / 2) - this.b) * ((this.perimeter() / 2) - this.c)));
        return S;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)||
                (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)||
                (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)||
                (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)||
                (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)||
                (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}