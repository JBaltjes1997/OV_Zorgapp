public class Doctor extends User{
    public Doctor(int id, String name) {
        super(id, name);
    }

    @Override
    boolean canSeeBMI(User user) {
        return true;
    }

    @Override
    boolean canSeeLungCapacity(User user) {
        return true;
    }

    @Override
    boolean canMakeConsult() {
        return true;
    }

    @Override
    boolean canMakeDentistConsult() {
        return false;
    }

    @Override
    boolean canMakeDoctorConsult() {
        return true;
    }

    @Override
    boolean canMakePhysioConsult() {
        return false;
    }
}
