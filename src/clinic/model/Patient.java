package clinic.model;

public class Patient {
    private int id;
    private String name;
    private String species;
    private String colour;
    private String gender;
    private String age;

    public Patient(int id, String name, String species, String colour, String gender, String age) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.colour = colour;
        this.gender = gender;
        this.age = age;
    }

    public Patient(String name, String species, String colour, String gender, String age) {
        this.name = name;
        this.species = species;
        this.colour = colour;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", colour='" + colour + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
