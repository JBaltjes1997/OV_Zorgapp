import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.round;
import static java.time.format.DateTimeFormatter.ofPattern;

class Patient {
    int       id;
   String    surname;
   String    firstName;
   LocalDate dateOfBirth;
    double length;
    double weight;

    ArrayList<Medication> medication;

    double lungCapacity;

    ArrayList<Consult> consults;

    public Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double length, double weight, ArrayList<Medication> medication, double lungCapacity, ArrayList<Consult> consults) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.length = length;
        this.weight = weight;
        this.medication = medication;
        this.lungCapacity = lungCapacity;
        this.consults = consults;
    }

    public Patient() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMedication(ArrayList<Medication> medication) {
        this.medication = medication;
    }

    String getSurname() {
        return surname;
    }

    String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Medication> getMedication() {
        return medication;
    }

    public double getLungCapacity() {
        return lungCapacity;
    }

    public void setLungCapacity(double lungCapacity) {
        this.lungCapacity = lungCapacity;
    }

    public double getLength() {
        return length;
    }

    public double getWeight() {
        return weight;
    }

    public ArrayList<Consult> getConsults() {
        return consults;
    }

    public void setConsults(ArrayList<Consult> consults) {
        this.consults = consults;
    }

    void viewData(User user) {
        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
        System.out.format("%-17s %s\n", "Voornaam:", firstName);
        System.out.format("%-17s %s\n", "Geboortedatum:", dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.format("%-17s %s\n", "Leeftijd:", this.calcAge());
        if (user.canSeeBMI(user)) {
            System.out.format("%-17s %.2f %s\n", "Lengte:", length, "m");
            System.out.format("%-17s %.1f %s\n", "Gewicht:", weight, "kg");
            System.out.format("%-17s %.1f %s\n", "BMI: ", this.calculateBMI(), "kg/m²");
        }
        if (user.canSeeLungCapacity(user)) {
            System.out.format("%-17s %.2f %s\n", "Long inhoud:", lungCapacity, "l");
        }
        System.out.println("Medicatie: ");
        if (medication.isEmpty()) {
            System.out.print("Heeft geen toegekende medicatie \n");
        }
        for (Medication med : medication) {
            System.out.println("Naam: " + med.getName() + ", Hoeveelheid: " + med.getQuantity() + " mg" + ", Vervaldatum " + med.getExpirintDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        System.out.println("Consulten: ");
        if (consults.isEmpty()) {
            System.out.print("Heeft geen toegekende consulten \n");
        }
        Collections.sort(consults);
        for (Consult consult : consults) {
            System.out.println("Datum: " + consult.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                    ", Beschrijving: " + consult.getDescription() + ", Type: " + consult.getType());
        }
    }

    void viewName(){
        System.out.format("===== Patiënt id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
    }

    double calculateBMI(){
        return this.weight / (this.length * this.length );
    }

    int calcAge(){
        int age = LocalDate.now().getYear() - this.dateOfBirth.getYear();
        if (LocalDate.now().getMonthValue() <= this.dateOfBirth.getMonthValue() && LocalDate.now().getDayOfMonth() < this.dateOfBirth.getDayOfMonth() ){
            return age -1;
        }
        return age;
    }

    String fullName() {
        return String.format("%s %s [%s]", firstName, surname, dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }


}
