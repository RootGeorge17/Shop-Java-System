import java.util.Scanner;

public class Perishable extends Accessory {
    protected  boolean isIrritant;
    protected String useByDate;
    protected int volume;

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Is Irritant: " + isIrritant);
        System.out.println("Use ByDate: " + useByDate);
        System.out.println("Volume: " + volume);
    }

    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner);
        this.isIrritant = Boolean.parseBoolean(scanner.next());
        this.useByDate = scanner.next();
        this.volume = Integer.parseInt(scanner.next());
    }
}