package javaandbd;

/**
 * Created by P.Xorygij on 27.07.17.
 */
public class Player {
    private String name;
    private int age;
    private int number;
    private String position;
    private String club;
    private String summary;

    Player(String newName, int newAge, int newNumber, String newPosition, String newClub, String newSummary){
        name = newName;
        age = newAge;
        number = newNumber;
        position = newPosition;
        club = newClub;
        summary = newSummary;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
