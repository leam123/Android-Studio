/**
**  Author: Leamor T. Garcia
**  Date: 
**
**/
import java.lang.*;
import java.util.*;
public class BankAccount{
    protected int acctnum;
    protected String name;
    protected double bal;
    public BankAccount(int acctnum,String name,double bal){
        this.acctnum = acctnum;
        this.name = name;
        this.bal = bal;
    }
    //getter
    public int getAcctNum(){
        return acctnum;
    }
    public String getName(){
        return name;
    }
    public double getBal(){
        return bal;
    }
    //setter
    public void setAcctNum(int acctnum){
        this.acctnum = acctnum;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBal(double bal){
        this.bal = bal;
    }
    //functions
    public boolean deposit(double amount) throws AccountException{
        boolean ok = true;
        if(amount<=0){
            ok = false;
            throw new AccountException("Invalid amount");
        }else
            bal += amount;
        return ok;
    }
    public void balance(){
        System.out.println("Balance: " + bal);
    }
    public void print(){
        System.out.println("Accout Number: " + acctnum + "\nName: " + name + "\nBalance: " + bal);
    }
}