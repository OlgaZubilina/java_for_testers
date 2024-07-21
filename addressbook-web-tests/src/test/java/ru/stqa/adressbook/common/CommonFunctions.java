package ru.stqa.adressbook.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString (int n){
        var rnd = new Random();
        Supplier<Integer>randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i->'a'+i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String randomInteger (int n){

        String result = "";
        for (int i =0 ; i < n; i++)
        {  var rnd = new Random().nextInt(9);
     result = result + rnd;}
        return result;
    }
    public static String randomFile (String dir){
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir,fileNames[index]).toString();

    }
}
