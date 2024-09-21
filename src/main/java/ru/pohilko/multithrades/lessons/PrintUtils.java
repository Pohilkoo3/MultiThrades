package ru.pohilko.multithrades.lessons;

public class PrintUtils {


    public static void printTemplate(String template, Object args){
        System.out.println(template.formatted(args));
    }
}
