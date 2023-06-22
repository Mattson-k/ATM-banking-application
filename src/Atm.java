import java.util.Scanner;

public class Atm {




    public static void main(String[] args) {
     //initialise scanner
        Scanner sc =new Scanner(System.in) ;

        // initialise  bank

         Bank theBank =new Bank("Centenary Bank") ;

         // add user to bank which also creates a savings account
        User aUser = theBank.addUser("Kigonya","Timothy","1234");

        // add a checking account for our user
        Account newAccount =new Account("Checking",aUser,theBank);
            aUser.addAccount(newAccount);
            theBank.addAccount (newAccount);







    }
    }