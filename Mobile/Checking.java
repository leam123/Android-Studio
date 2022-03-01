/**
**  Author: Leamor T. Garcia
**  Date: 
**
**/
import java.lang.*;

public class Checking extends BankAccount{
    protected double minimum;
    protected double charge;
    
    public Checking(int acctnum,String name,double bal,double minimum,double charge){
        super(acctnum,name,bal);
        this.minimum = minimum;
        this.charge = charge;
    }
    
    public boolean cash_check(){
        boolean ok = false;
        if(bal<minimum){
            double x = bal - charge;
            setBal(x);
            ok = true;
        }
        return ok;
    }
    public void print(){
        System.out.println("Minimum: " + minimum);
        System.out.println("Charge: " + charge);
    }
}