public class Pharmacist extends User{
    public Pharmacist(int id, String name) {
        super(id, name);
    }

    @Override
    boolean canSeeBMI(User user) {
        return false;
    }

    @Override
    boolean canSeeLungCapacity(User user) {
        return true;
    }

    @Override
    boolean canMakeConsult() {
        return false;
    }

    @Override
    boolean canMakeDentistConsult() {
        return false;
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
