import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

abstract class User {
    String userName;
    int userID;

    public User(int id, String name) {
        this.userID = id;
        this.userName = name;
    }

    String getUserName() {
        return userName;
    }

    int getUserID() {
        return userID;
    }

    abstract boolean canSeeBMI(User user);
    abstract boolean canSeeLungCapacity(User user);

    abstract boolean canMakeConsult();

    abstract boolean canMakeDentistConsult();

    abstract boolean canMakeDoctorConsult();

    abstract boolean canMakePhysioConsult();
}
