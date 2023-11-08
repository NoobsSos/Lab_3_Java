package Task.Exception;

public class BillAlreadyPaid extends Exception {
    public BillAlreadyPaid() {
        super("Bill is already paid. You can't modify it.");
    }
}
