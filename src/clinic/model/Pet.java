package model;

public class Pet {
    //pet name
    private String petName;
    //gender
    private String gender;
    //pedigree
    private String animalPedigree;
    //dar nesugalvojau
    //species
    //pet breed
    private String petBreed;
    //pet weight
    private int weight;



    private String physicalActivity;

//    public Pet(String petName, String gender, String animalPedigree,
//               String animalSpecie, int weight, String petBreed) {
//
//    }

    public Pet(String petName, String gender, String animalPedigree, String physicalActivity, String petBreed, int weight) {
        this.petName = petName;
        this.gender = gender;
        this.animalPedigree = animalPedigree;
        this.petBreed = petBreed;
        this.weight = weight;
        this.physicalActivity = physicalActivity;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAnimalPedigree() {
        return animalPedigree;
    }

    public void setAnimalPedigree(String animalPedigree) {
        this.animalPedigree = animalPedigree;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {

        this.petBreed = petBreed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {

        this.weight = weight;
    }
    public String getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(String physicalActivity) {
        this.physicalActivity = physicalActivity;
    }
}
