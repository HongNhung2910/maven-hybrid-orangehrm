package javaFaker;



import net.datafaker.Faker;


public class Topic_02_FakerNet {
    public static void main(String[] args){
        Faker faker= new Faker();
        System.out.println(faker.internet().emailAddress());

        System.out.println(faker.address().state());
        System.out.println("Password"+faker.credentials().password(10,15,true,true,true));

        System.out.println("Random"+faker.number().randomDigit());

        System.out.println(faker.business().creditCardType());
        System.out.println(faker.business().creditCardExpiry());
        System.out.println(faker.business().creditCardNumber());
    }
}
