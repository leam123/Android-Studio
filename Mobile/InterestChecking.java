/**
**  Author: Leamor T. Garcia
**  Date: 
**
**/
import java.lang.*;

public class InterestChecking extends Checking{
    private double intrate;
    private double minint;
    private double moncharge;
    
    public InterestChecking(int acctnum,String name,double bal,double minimum,double charge,double intrate,double minint,double moncharge){
        super(acctnum,name,bal,minimum,charge);
        this.intrate = intrate;
        this.minint = minint;
        this.moncharge = moncharge;
    }
    public boolean interest(){
        boolean ok = false;
        if(bal> minint){
            double x = bal + intrate;
            setBal(x);
            ok = true;
        }else{
            double x = bal - moncharge;
            setBal(x);
            ok = true;
        }
        return ok;
    }
    public void print(){
        System.out.println("Accout Number: " + acctnum + "\nName: " + name + "\nBalance: " + bal);
        System.out.println("Minimum: " + minimum);
        System.out.println("Charge: " + charge);
        System.out.println("Annual Interest Rate: " + intrate);
        System.out.println("Minimum balance: " + minint);
        System.out.println("Monthly fee: " + moncharge);
    }
}