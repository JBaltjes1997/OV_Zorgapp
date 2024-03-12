import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consult implements Comparable<Consult> {
    LocalDate date;
    String description;
    String type;

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public Consult(LocalDate date, String despription, String type) {
        this.date = date;
        this.description = despription;
        this.type = type;
    }

    public static String makeDentistConsult() {
        ArrayList<String> optionsD = new ArrayList<>();
        optionsD.add("Standaard - €20,00");
        optionsD.add("Simpel - €30,00");
        optionsD.add("Complex - €55,00");
        for (String option : optionsD) {
            System.out.format("Optie %d: %s \n", optionsD.indexOf(option) + 1, option);
        }
        System.out.println("Welke type consult is het?: ");
        int choice = scanner.nextInt();
        return optionsD.get(choice-1);
    }

    public static String makeDoctorConsult() {
        ArrayList<String> optionsDoc = new ArrayList<>();
        optionsDoc.add("Standaard - €21,50");
        optionsDoc.add("Uitgebreid - €43,00");
        for (String option : optionsDoc) {
            System.out.format("Optie %d: %s \n", optionsDoc.indexOf(option) + 1, option);
        }
        System.out.println("Welke type consult is het?: ");
        int choice = scanner.nextInt();
        return optionsDoc.get(choice-1);
    }

    public static String makePhysioConsult() {
        ArrayList<String> optionsPh = new ArrayList<>();
        optionsPh.add("Standaard - €17,50");
        optionsPh.add("Kort - €22,50");
        optionsPh.add("Uitgebreid - €45,00");
        for (String option : optionsPh) {
            System.out.format("Optie %d: %s \n", optionsPh.indexOf(option) + 1, option);
        }
        System.out.println("Welke type consult is het?: ");
        int choice = scanner.nextInt();
        System.out.println("Word er gebruik gemaakt van faciliteiten? (ja/nee)");
        String ans = scanner.next();
        if(ans.equalsIgnoreCase("ja")){
            return optionsPh.get(choice-1) + " + Faciliteiten - €5,00";
        }
        return optionsPh.get(choice-1);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Consult o) {
        return getDate().compareTo(o.getDate());
    }
}
