import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.time.format.DateTimeFormatter.*;

/**
 * class Administration represents the core of the application by showing
 * the main menu, from where all other functionality is accessible, either
 * directly or via sub-menus.
 * An Administration instance needs a User as input, which is passed via the
 * constructor to the data member 'currentUser'.
 * The patient data is available via the data member currentPatient.
 */

class Administration {
    static final int STOP = 0;
    static final int VIEW_CURRENT_PATIENT_DATA = 1;
    static final int VIEW_ALL_PATIENTS = 2;
    static final int SWITCH_CURRENT_PATIENT = 3;
    static final int ALTER_PATIENT_DATA = 4;
    static final int ADD_NEW_PATIENT = 5;
    static final int DELETE_PATIENT = 6;
    static final int ADD_MEDICATION = 7;
    static final int ALTER_MEDICATION = 8;
    static final int ALTER_LUNG_CAPACITY = 9;
    static final int SEARCH_FOR_PATIENT = 10;
    static final int SHOW_BM_IGRAPH = 11;
    static final int CREATE_NEW_CONSULT = 12;
    static final int SWITCH_USER = 13;

    Patient currentPatient;            // The currently selected patient
    User currentUser;                  // the current user of the program.

    ArrayList<Patient> patients = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Medication> medications = new ArrayList<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    String DEFAULT_ERROR = "Vul aub een heel getal in";
    String DEFAULT_WRITING_ERROR = "Vul een ',' en geen '.' in aub ";
    String DEFAULT_DATE_ERROR = "Vul aub een geldige datum in";
    String DEFAULT_AUTHORISATION_MESSAGE = "U bent niet ge-authoriseerd";

    /**
     * Constructor
     */
    Administration() throws IOException {
        patients.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 9, 5), 1.77, 87, new ArrayList<>(), 5.5, new ArrayList<>()));
        patients.add(new Patient(2, "Van Boxtel", "Loes", LocalDate.of(1974, 3, 21), 1.69, 71, new ArrayList<>(), 6.9, new ArrayList<>()));
        patients.add(new Patient(3, "Vissers", "Simone", LocalDate.of(1997, 7, 11), 1.71, 65, new ArrayList<>(), 4.6, new ArrayList<>()));
        patients.add(new Patient(4, "van de Boogaard", "Jan", LocalDate.of(1959, 11, 11), 1.79, 81, new ArrayList<>(), 5.9, new ArrayList<>()));
        patients.add(new Patient(5, "Kooimans", "Peer", LocalDate.of(1983, 10, 9), 1.85, 89, new ArrayList<>(), 5.3, new ArrayList<>()));
        patients.add(new Patient(6, "Schouten", "Hennie", LocalDate.of(1939, 2, 15), 1.72, 95, new ArrayList<>(), 6.3, new ArrayList<>()));

        medications.add(new Medication("Coffeïne", 0, LocalDate.of(2025, 9, 5)));
        medications.add(new Medication("Ibuprofen", 0, LocalDate.of(2025, 9, 5)));
        medications.add(new Medication("Jodium", 0, LocalDate.of(2025, 9, 5)));
        medications.add(new Medication("Nicotine", 0, LocalDate.of(2025, 9, 5)));
        medications.add(new Medication("Thymol", 0, LocalDate.of(2025, 9, 5)));


        patients.get(0).getMedication().add(new Medication(medications.get(0).getName(), medications.get(0).getQuantity(), medications.get(0).getExpirintDate()));
        patients.get(0).getMedication().add(new Medication(medications.get(1).getName(), medications.get(1).getQuantity(), medications.get(0).getExpirintDate()));

        patients.get(1).getMedication().add(new Medication(medications.get(0).getName(), medications.get(0).getQuantity(), medications.get(0).getExpirintDate()));

        patients.get(5).getMedication().add(new Medication(medications.get(4).getName(), medications.get(0).getQuantity(), medications.get(0).getExpirintDate()));

        patients.get(1).getConsults().add(new Consult(LocalDate.of(2023, 10,24), "3e SOA test", "Standaard - €20"));
        patients.get(1).getConsults().add(new Consult(LocalDate.of(2023, 12,5), "4e SOA test", "Standaard - €17,50"));
        patients.get(1).getConsults().add(new Consult(LocalDate.of(2023, 1,5), "1e SOA test", "Standaard - €21,50"));
        patients.get(1).getConsults().add(new Consult(LocalDate.of(2023, 6,5), "2e SOA test", "Kort - €22,50"));

        patients.get(2).getConsults().add(new Consult(LocalDate.of(2024, 1,2), "Ernstige kater", "Uitgebreid - €45,00"));

        users.add(new Dentist(1, "Ben L. Salomon"));
        users.add(new Doctor(2, "Florence Nightingale"));
        users.add(new Pharmacist(3, "Walter White"));
        users.add(new Physiotherapist(4, "Per Henrik Ling"));
    }

    void setCurrentUser() {
        try {
            for (User user : users) {
                System.out.format("Gebruikers id=%d: \n", user.getUserID());
                System.out.format("%-17s \n\n", user.getUserName());
            }
            System.out.print("\nGeef gebruiker's id: ");

            int user_choice = scanner.nextInt();
            while (!user_exists(user_choice)) {
                System.out.println("Er is geen gebruiker met dit ID");
                System.out.print("Geef gebruiker's id: ");
                user_choice = scanner.nextInt();
            }
            for (User user : users) {
                if (user_choice == user.getUserID()) {
                    currentUser = user;
                }
            }
        } catch (Exception e) {
            System.out.println(DEFAULT_ERROR);
            scanner.nextLine();
            setCurrentUser();
        }
    }

    void setCurrentPatient() {
        try {
            for (Patient patient : patients) {
                System.out.format("Patiënt id=%d: %s %s\n", patient.getId(), patient.getFirstName(), patient.getSurname());
            }
            System.out.print("\nWie is de huidige patiënt: ");

            int user_choice = scanner.nextInt();
            while (patient_exists(user_choice)) {
                System.out.println("Er is geen patiënt met dit ID");
                System.out.print("Geef patiënt id: ");
                user_choice = scanner.nextInt();
            }
            for (Patient patient : patients) {
                if (user_choice == patient.getId()) {
                    currentPatient = patient;
                }
            }
        } catch (Exception e) {
            System.out.println(DEFAULT_ERROR);
            scanner.nextLine();
            setCurrentPatient();
        }
    }

    void viewPatientName() {
        for (Patient patient : patients) {
            patient.viewName();
        }
    }

    void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    boolean user_exists(int user_choice) {
        for (User user : users) {
            if (user_choice == user.getUserID()) {
                return true;
            }
        }
        return false;
    }

    boolean patient_exists(int patient_choice) {
        for (Patient patient : patients) {
            if (patient_choice == patient.getId()) {
                return false;
            }
        }
        return true;
    }

    void alterCurrentPatient() {
        try {
            viewPatientName();

            System.out.print("Patiënt id: ");
            int patient_choice = scanner.nextInt();

            while (patient_exists(patient_choice)) {
                System.out.println("Er is geen patiënt met dit ID");
                System.out.print("Geef patiënt ID: ");
                patient_choice = scanner.nextInt();
            }
            for (Patient patient : patients) {
                if (patient_choice == patient.getId()) {
                    patient.viewData(currentUser);
                    setCurrentPatient(patient);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(DEFAULT_ERROR);
            scanner.nextLine();
            alterCurrentPatient();
        }
    }

    void showOptions() {
        System.out.println("1. Patiënt voornaam");
        System.out.println("2. Patiënt achternaam");
        System.out.println("3. Patiënt lengte");
        System.out.println("4. Patiënt gewicht");
        System.out.println("5. Patiënt geboortedatum");
        System.out.println("0. Einde");
        System.out.print("Geef keuze: ");
    }

    void alterPatientFirstname() {
        System.out.print("Geef patiënt voornaam: ");
        currentPatient.setFirstName(scanner.next());
    }

    void alterPatientLastname() {
        System.out.print("Geef patiënt achternaam: ");
        currentPatient.setSurname(scanner.next());
    }

    void alterPatientHeight() {
        try {
            System.out.print("Geef patiënt lengte: ");
            currentPatient.setLength(scanner.nextDouble());
        } catch (Exception e) {
            System.out.println(DEFAULT_WRITING_ERROR);
            scanner.nextLine();
            alterPatientHeight();
        }
    }

    void alterPatientWeight() {
        try {
            System.out.print("Enter patient weight: ");
            currentPatient.setWeight(scanner.nextDouble());
        } catch (Exception e) {
            System.out.println(DEFAULT_WRITING_ERROR);
            scanner.nextLine();
            alterPatientWeight();
        }
    }

    void alterPatientDateOfBirth() {
        try {
            System.out.print("Geef patiënt geboorte dag: ");
            int day = scanner.nextInt();
            System.out.print("Geef patiënt geboorte maand(in getallen): ");
            int month = scanner.nextInt();
            System.out.print("Geef patiënt geboorte jaar: ");
            int year = scanner.nextInt();

            LocalDate new_birthday = LocalDate.of(year, month, day);
            currentPatient.setDateOfBirth(new_birthday);
        } catch (DateTimeException e) {
            System.out.println(DEFAULT_DATE_ERROR);
            alterPatientDateOfBirth();
        }
    }

    void alterPatientData() {
        System.out.println("Let op, u past de gegevens van de huidige patiënt aan");
        System.out.println("Wat wilt u aanpassen?: ");
        showOptions();
        int user_choice = scanner.nextInt();
        while (user_choice > 0) {
            if (user_choice == 1) {
                alterPatientFirstname();

            } else if (user_choice == 2) {
                alterPatientLastname();

            } else if (user_choice == 3) {
                alterPatientHeight();

            } else if (user_choice == 4) {
                alterPatientWeight();

            } else if (user_choice == 5) {
                alterPatientDateOfBirth();
            }
            System.out.println("Wilt u nog wat aanpassen?: ");
            showOptions();
            user_choice = scanner.nextInt();
        }
    }

    void addNewPatient() {
        try {
            Patient patient = new Patient();
            patient.setId(patients.get(patients.size() - 1).getId() + 1);

            System.out.print("Geef patiënt voornaam: ");
            patient.setFirstName(scanner.next());
            System.out.print("Geef patiënt achtenaam: ");
            patient.setSurname(scanner.next());
            System.out.print("Geef patiënt lengte: ");
            patient.setLength(scanner.nextDouble());
            System.out.print("Geef patiënt gewicht: ");
            patient.setWeight(scanner.nextDouble());

            System.out.print("Geef geboortedatum van patiënt, als (dd-mm-jjjj): ");
            String datum = scanner.next();
            DateTimeFormatter dateFormat = ofPattern("d-M-yyyy");
            LocalDate birthdate = LocalDate.parse(datum, dateFormat);
            patient.setDateOfBirth(birthdate);
            patient.setMedication(new ArrayList<>());
            patient.setLungCapacity(0);

            patients.add(patient);
        } catch (InputMismatchException e) {
            System.out.println(DEFAULT_WRITING_ERROR);
            scanner.nextLine();
            addNewPatient();
        } catch (DateTimeException e) {
            System.out.println(DEFAULT_DATE_ERROR);
            scanner.nextLine();
            addNewPatient();
        }
    }

    void deletePatient() {
        viewPatientName();
        System.out.print("Geef het ID van de te verwijderen patiënt: ");
        int patient_choice = scanner.nextInt();
        while (patient_exists(patient_choice)) {
            System.out.println("Er is geen patiënt met dit ID");
            System.out.print("Geef patiënt ID: ");
            patient_choice = scanner.nextInt();
        }
        for (Patient patient : patients) {
            if (patient_choice == patient.getId()) {
                patients.remove(patient);
                break;
            }
        }
    }

    void showMedOptions() {
        System.out.println("\nMogelijke medicatie: ");
        for (Medication med : medications) {
            System.out.format("id=%d naam: %s, ", medications.indexOf(med) + 1, med.getName());
        }
    }

    boolean checkUserIsAuthorised() {
        return currentUser.getClass() == Dentist.class || currentUser.getClass() == Doctor.class;
    }
    void addMedication() {
        try {
            if (!checkUserIsAuthorised()) {
                System.out.println("Je bent niet ge-authriseerd");
            } else {
                ArrayList<Medication> user_medications = currentPatient.getMedication();
                showMedOptions();

                System.out.print("\nSelecteer medicatie id: ");
                int user_choice = scanner.nextInt();
                if (user_medications.contains(medications.get(user_choice - 1))) {
                    System.out.println("Patient heeft deze medicatie al");
                }
                while (user_choice > 0) {
                    System.out.print("Hoeveel miligram wilt u toevoegen?: ");
                    int quantity_choice = scanner.nextInt();
                    System.out.print("Wat is de verval datum?: ");
                    String datum = scanner.next();
                    DateTimeFormatter dateFormat = ofPattern("d-M-yyyy");
                    LocalDate expiringDate = LocalDate.parse(datum, dateFormat);
                    user_medications.add(new Medication(medications.get(user_choice - 1).getName(), quantity_choice, expiringDate));
                    System.out.println("Geef het id van een andere medicatie wilt toevoegen, of druk op 0 om te stoppen: ");
                    showMedOptions();
                    user_choice = scanner.nextInt();
                }
            }

            } catch(IndexOutOfBoundsException e){
                System.out.println("Medicatie met dit id bestaat niet");
                scanner.nextLine();
                addMedication();
            } catch(InputMismatchException ignored){
                System.out.println(DEFAULT_ERROR);
                scanner.nextLine();
                addMedication();
            } catch(DateTimeException e){
                System.out.println(DEFAULT_DATE_ERROR);
                scanner.nextLine();
                addMedication();
            }
        }

    void alterMedication(){
        try {
            if (!checkUserIsAuthorised()) {
                System.out.println(DEFAULT_AUTHORISATION_MESSAGE);
            } else {
                ArrayList<Medication> medication = currentPatient.getMedication();
                if (medication.isEmpty()) {
                    System.out.println("Deze patiënt heeft geen toegekende medicijnen");
                }

                System.out.println("Patiënt medicatie: ");
                for (Medication med : medication) {
                    System.out.format("id=%d Naam: " + med.getName() + " Hoeveelheid: " + med.getQuantity() + " gram\n", medication.indexOf(med) + 1);
                }
                System.out.print("\nGeef aub het id van de medicatie die u wilt wijzigen: ");

                int choice = scanner.nextInt();
                while (choice > 0) {
                    System.out.println("Druk op 0 als u het medicijn wilt verwijderen");
                    System.out.print("Wat moet de hoeveelheid zijn?: ");
                    int new_choice = scanner.nextInt();
                    if (new_choice > 0) {
                        medication.get(choice - 1).setQuantity(new_choice);
                    } else {
                        medication.remove(choice - 1);
                    }
                    System.out.print("Selecteer het id van een andere medicatie die u wilt aanpassen, of druk op 0 om te stoppen: ");
                    choice = scanner.nextInt();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nDe patiënt heeft deze medicatie niet.\n");
            scanner.nextLine();
            alterMedication();
        } catch (InputMismatchException ignored) {
            System.out.println("\n" + DEFAULT_WRITING_ERROR + "\n");
            scanner.nextLine();
            alterMedication();
        }
    }

    void alterLungCapacity() {
        try {
            if (currentUser.getClass() != Physiotherapist.class) {
                System.out.println(DEFAULT_AUTHORISATION_MESSAGE);
            } else {
                System.out.print("Geef patiënt longinhoud: ");
                currentPatient.setLungCapacity(scanner.nextDouble());
            }
        } catch (InputMismatchException e) {
            System.out.println(DEFAULT_WRITING_ERROR);
            scanner.nextLine();
            alterLungCapacity();
        }
    }

    void searchPatient() {
        System.out.print("Geef patiënt naam: ");
        ArrayList<Patient> thisArray = new ArrayList<>();
        String name = scanner.next();
        for (Patient patient : patients) {
            if (patient.getFirstName().equalsIgnoreCase(name)) {
                thisArray.add(patient);
            }
        }
        if (thisArray.isEmpty()) {
            System.out.format("patiënt met naam %s gevonden", name);
        } else {
            setCurrentPatient(thisArray.get(0));
        }
    }

    void showBMIgraph() {
        try {
            System.out.println("Wat is het gewicht van de huidige patiënt?: ");
            double bmi1 = currentPatient.calculateBMI();
            double weight = scanner.nextDouble();
            currentPatient.setWeight(weight);
            double bmi2 = currentPatient.calculateBMI();
            String String1 = "laatste keer: " + LocalDate.of(2023, 6,5).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String String2 = "deze keer:    " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.format("%-15s", String1);
            for (int num = 0; num < bmi1; num += 1) {
                System.out.print('▮');
            }
            System.out.format("%-12.1f", bmi1);
            System.out.format("\n%-15s", String2);
            for (int num = 0; num < bmi2; num += 1) {
                System.out.print("▮");
            }
            System.out.format("%-12.1f", bmi2);
            System.out.print("\n");
            System.out.format("%-23s","");
            for(int i = 0; i <= 35; i+=5){
                System.out.format("%-8d",i);
            }
            System.out.print("\n");
        }
        catch(Exception e){
                System.out.println(DEFAULT_ERROR);
                showBMIgraph();
            }
        }

    void createNewConsult() {
        try {
            if (!currentUser.canMakeConsult()) {
                System.out.println(DEFAULT_AUTHORISATION_MESSAGE);
            } else {
                System.out.print("Wat is de datum van het consult, als (dd-mm-jjjj): ");
                String datum = scanner.next();
                DateTimeFormatter dateFormat = ofPattern("d-M-yyyy");
                LocalDate date = LocalDate.parse(datum, dateFormat);
                System.out.print("Welke klachten heeft de patiënt: ");
                String reason = scanner.next();
                if (currentUser.canMakeDentistConsult()) {
                    currentPatient.getConsults().add(new Consult(date, reason, "Tandarts " + Consult.makeDentistConsult()));
                } else if (currentUser.canMakePhysioConsult()) {
                    currentPatient.getConsults().add(new Consult(date, reason, "Fysiotherapeut " + Consult.makePhysioConsult()));
                } else if (currentUser.canMakeDoctorConsult()) {
                    currentPatient.getConsults().add(new Consult(date, reason, "Huisarts " + Consult.makeDoctorConsult()));
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(DEFAULT_WRITING_ERROR);
            scanner.nextLine();
            createNewConsult();
        } catch (DateTimeException e) {
            System.out.println(DEFAULT_DATE_ERROR);
            scanner.nextLine();
            createNewConsult();
        }
    }

    void menu() {
        boolean nextCycle = true;
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            if(currentUser.getClass().equals(Dentist.class)){
                System.out.format("Huidige gebruiker: id:%d Naam:%s %s\n", currentUser.getUserID(), currentUser.getUserName(), currentUser.getClass().toString().replace("class Dentist", "Beroep: Tandarts"));
            } else if (currentUser.getClass().equals(Doctor.class)){
                System.out.format("Huidige gebruiker: id:%d Naam:%s %s\n", currentUser.getUserID(), currentUser.getUserName(), currentUser.getClass().toString().replace("class Doctor", "Beroep: Huisarts"));
            } else if (currentUser.getClass().equals(Pharmacist.class)){
                System.out.format("Huidige gebruiker: id:%d Naam:%s %s\n", currentUser.getUserID(), currentUser.getUserName(), currentUser.getClass().toString().replace("class Pharmacist", "Beroep: Apotheker"));
            } else if (currentUser.getClass().equals(Physiotherapist.class)){
                System.out.format("Huidige gebruiker: id:%d Naam:%s %s\n", currentUser.getUserID(), currentUser.getUserName(), currentUser.getClass().toString().replace("class Physiotherapist", "Beroep: Fysiotherapeut"));
            }
            System.out.format("Huidige patiënt: %s\n", currentPatient.fullName());

            System.out.format("%d:  STOP\n", STOP);
            System.out.format("%d:  Zie huidige patiënt data\n", VIEW_CURRENT_PATIENT_DATA);
            System.out.format("%d:  Zie alle patiënten\n", VIEW_ALL_PATIENTS);
            System.out.format("%d:  Verander van huidige patiënt\n", SWITCH_CURRENT_PATIENT);
            System.out.format("%d:  Verander patiënt data\n", ALTER_PATIENT_DATA);
            System.out.format("%d:  Nieuwe patiënt toevoegen\n", ADD_NEW_PATIENT);
            System.out.format("%d:  Verwijder patiënt\n", DELETE_PATIENT);
            System.out.format("%d:  Voeg medicatie toe aan patiënt\n", ADD_MEDICATION);
            System.out.format("%d:  Wijzig medicatie van patiënt\n", ALTER_MEDICATION);
            System.out.format("%d:  Wijzig longinhoud van patiënt\n", ALTER_LUNG_CAPACITY);
            System.out.format("%d:  Zoek naar specifieke patiënt\n", SEARCH_FOR_PATIENT);
            System.out.format("%d:  Toon BMI grafiek\n", SHOW_BM_IGRAPH);
            System.out.format("%d:  Maak nieuw consult\n", CREATE_NEW_CONSULT);
            System.out.format("%d:  Verander van gebruiker \n", SWITCH_USER);

            try {
                System.out.print("Geef keuze: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case STOP:
                        nextCycle = false;
                        break;

                    case VIEW_CURRENT_PATIENT_DATA:
                        currentPatient.viewData(currentUser);
                        break;

                    case VIEW_ALL_PATIENTS:
                        viewPatientName();
                        break;

                    case SWITCH_CURRENT_PATIENT:
                        alterCurrentPatient();
                        break;

                    case ALTER_PATIENT_DATA:
                        alterPatientData();
                        break;

                    case ADD_MEDICATION:
                        addMedication();
                        break;

                    case ADD_NEW_PATIENT:
                        addNewPatient();
                        break;

                    case ALTER_MEDICATION:
                        alterMedication();
                        break;

                    case DELETE_PATIENT:
                        deletePatient();
                        break;

                    case SWITCH_USER:
                        setCurrentUser();
                        break;

                    case ALTER_LUNG_CAPACITY:
                        alterLungCapacity();
                        break;

                    case SEARCH_FOR_PATIENT:
                        searchPatient();
                        break;

                    case SHOW_BM_IGRAPH:
                        showBMIgraph();
                        break;

                    case CREATE_NEW_CONSULT:
                        createNewConsult();
                        break;

                    default:
                        System.out.println(DEFAULT_ERROR);
                        break;
                }
            } catch (Exception e) {
                System.out.println(DEFAULT_ERROR);
                scanner.nextLine();
                menu();
            }
        }
    }
}
