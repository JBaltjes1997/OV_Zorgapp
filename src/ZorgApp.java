import java.io.IOException;
import java.util.ArrayList;

class ZorgApp {
    public static void main(String[] args) throws IOException {
        Administration administration = new Administration();
        administration.setCurrentUser();
        administration.setCurrentPatient();
        administration.menu();
    }
}
