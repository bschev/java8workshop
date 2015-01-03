package java8ws.utils;

public class Person {

    private String name;

    private int age;

    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

    public Person(final String aName, final int aAge, final Gender aGender) {
        name = aName;
        age = aAge;
        gender = aGender;
    }

    public String getName() {
        return name;
    }

    public void setName(final String aName) {
        name = aName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int aAge) {
        age = aAge;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender aGender) {
        gender = aGender;
    }
}
