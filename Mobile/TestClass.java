public class TestClass{
    public static void main(String[] args){
        InterestChecking intcheck = new InterestChecking(160265749,"Leamor Garcia",15000.9090,500.00,50.00,25.10,500.00,250.00);
        intcheck.print();
        System.out.println("\n");
        
        /*try{
            intcheck.deposit(5000.90);
            intcheck.deposit(-5000.90); //exception
        }catch(AccountException ae){
            System.out.println(ae);
        }
        intcheck.print();*/
    }
}