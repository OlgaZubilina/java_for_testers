package ru.stqa.adressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.stqa.adressbook.common.CommonFunctions;
import ru.stqa.adressbook.model.ContactData;
import ru.stqa.adressbook.model.GroupData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    @Parameter(names={"--type", "-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String output;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-n"})
    int count;
    
    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
              .addObject(generator)
                .build()
               .parse(args);
        generator.run();
    }
    private void run ()throws IOException {
        var data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(output),data);
    } else throw new  IllegalArgumentException ("Неизвестный формат данных"+format);}
    
    private Object generate(){
        if ("groups".equals(type))
        return generateGroups();
    else if ("contacts".equals(type))
        return generateContacts();
    else {throw new IllegalArgumentException("Неизвестный тип данных" + type);}}

    private Object generateData(Supplier<Object>dataSupplier){
        Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
        var result = new ArrayList<Object>();
        for (int i =0;i<count;i++)
        {result.add(dataSupplier.get()); }
        return result;
    }

    private Object generateGroups() {
        return  generateData(()->new GroupData()
                .withName(CommonFunctions.randomString( 10))
                .withHeader(CommonFunctions.randomString( 10))
                .withFooter(CommonFunctions.randomString(10)));

    }

    private Object generateContacts() {
        return generateData(()->new ContactData()

                        .withFirstname(CommonFunctions.randomString(10))
                        .withLastname(CommonFunctions.randomString(10))
                        .withAdress(CommonFunctions.randomString(10))
                        .withPhoto(CommonFunctions.randomFile("src/test/resources/images"))
                .withHome(CommonFunctions.randomInteger(10)));

    }

}
