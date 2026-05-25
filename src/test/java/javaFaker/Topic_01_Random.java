package javaFaker;

import java.util.Calendar;
import java.util.Random;

public class Topic_01_Random {
    public static void main (String[] args ){
        Random rand= new Random();

        System.out.println("automation"+rand.nextInt(999999)+"@mail.com");
        System.out.println("automation"+rand.nextInt(999999)+"@mail.com");
        System.out.println("automation"+rand.nextInt(999999)+"@mail.com");
        System.out.println("automation"+rand.nextInt(999999)+"@mail.com");
        System.out.println("automation"+rand.nextInt(999999)+"@mail.com");

        System.out.println(getRandomEmail());
        System.out.println(getRandomNumber());
        System.out.println(getRandomNumber(100,1000));
      }

    public static int getRandomNumber(){
        int uLimit=99;
        int lLimit=999999;
        Random rand= new Random();

        return lLimit + rand.nextInt(uLimit-lLimit);
    }

    public static int getRandomNumber(int maximum, int minimum){
        Random rand= new Random();
        return minimum + rand.nextInt(maximum-minimum);
    }

    public static String getRandomEmail(){
        return "Auto"+ getRandomNumberByDateTime()+"@live.com";
    }

    public static long getRandomNumberByDateTime(){
        return Calendar.getInstance().getTimeInMillis()%100000;
    }
}
