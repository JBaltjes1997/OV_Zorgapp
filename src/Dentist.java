import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Dentist extends User{
    public Dentist(int id, String name) {
        super(id, name);
    }

    @Override
    boolean canSeeBMI(User user) {
        return false;
    }

    @Override
    boolean canSeeLungCapacity(User user) {
        return false;
    }

    @Override
    boolean canMakeConsult() {
        return true;
    }

    @Override
    boolean canMakeDentistConsult() {
        return true;
    }

    @Override
    boolean canMakeDoctorConsult() {
        return false;
    }

    @Override
    boolean canMakePhysioConsult() {
        return false;
    }
}
