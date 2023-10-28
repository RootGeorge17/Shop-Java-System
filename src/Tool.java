import java.util.Scanner;

public class Tool extends ShopItem{
    protected int timesBorrowed;
    protected boolean onLoan;
    protected int weight;

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Times Borrowed: " + timesBorrowed);
        System.out.println("On Loan: " + onLoan);
        System.out.println("Weight: " + weight);
    }

    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner);
        this.timesBorrowed = Integer.parseInt(scanner.next());
        this.onLoan = Boolean.parseBoolean(scanner.next());
        this.weight = Integer.parseInt(scanner.next());
    }

}