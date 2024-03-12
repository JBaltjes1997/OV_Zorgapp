import java.time.LocalDate;

public class Medication {
    String name;
    int quantity;
    LocalDate expirintDate;


    public Medication(String name, int quantity, LocalDate expirintDate) {
        this.name = name;
        this.quantity = quantity;
        this.expirintDate = expirintDate;
    }

    public String getName() {
        return name;
    }

    void showMeds() {
        System.out.format("%-17s %s\n", "Naam:", name);
        System.out.format("%-17s %s\n", "Hoeveelheid:", quantity);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirintDate() {
        return expirintDate;
    }

    public void setExpirintDate(LocalDate expirintDate) {
        this.expirintDate = expirintDate;
    }


}
