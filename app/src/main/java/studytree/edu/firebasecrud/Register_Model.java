package studytree.edu.firebasecrud;

public class Register_Model {

    private String firstName;
    private String lastName;
    private String age;
    private String city;
    private String key;

    public Register_Model(){

    }

    public Register_Model(String firstName, String lastName, String age, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
    }

    public Register_Model(String firstName, String lastName, String age, String city, String key) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
