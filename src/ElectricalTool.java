import java.util.Scanner;

public class ElectricalTool extends Tool
{
    protected boolean rechargeble;
    protected String power;
   
    public Boolean getRechargeble()
    {
        return this.rechargeble;
    }

    public String getPower()
    {
        return this.power;
    }
    
    public void setRechargeble(boolean rechargeble)
    {
        this.rechargeble = rechargeble;
    }
    
    public void setPower(String power)
    {
        this.power = power;
    }
    
    @Override
    public void printDetails(){
        super.printDetails();
        System.out.println(getRechargeble());
        
    }
    
    @Override
    public void readData(Scanner scanner) {
        super.readData(scanner);
        setRechargeble(scanner.nextBoolean());
        setPower(scanner.next());
    }
}
