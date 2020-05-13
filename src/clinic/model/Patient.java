package clinic.model;

public class Patient {
    private int id;
    private String animal_name;
    private String species;
    private String colour;
    private String gender;
    private int age;


    public Patient(int id, String name, String species, String colour, String gender, int age) {
        this.id = id;
        this.animal_name = name;
        this.species = species;
        this.colour = colour;
        this.gender = gender;
        this.age = age;
    }

    public Patient(String name, String species, String colour, String gender, int age) {
        this.animal_name = name;
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

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + animal_name + '\'' +
                ", species='" + species + '\'' +
                ", colour='" + colour + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
