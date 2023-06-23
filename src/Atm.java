import java.util.Scanner;

public class Atm {




    public static void main(String[] args) {
     //initialise scanner
        Scanner sc =new Scanner(System.in) ;

        // initialise  bank

         Bank theBank =new Bank("CentenaryBank") ;

         // add user to bank which also creates a savings account
        User aUser = theBank.addUser("Kigonya","Timothy","1234");

        // add a checking account for our user
        Account newAccount =new Account("Checking",aUser,theBank);
            aUser.addAccount(newAccount);
            theBank.addAccount (newAccount);

            User curUser;
            while (true){
                //stay in login prompt till its succesful
                 curUser =Atm.mainMenuPrompt(theBank, sc);

                 Atm.printUserMenu(curUser, sc);



            }







        }

    /**
     * 
     * @param theBank
     * @param sc
     * @return
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc ) {

        String userID;
        String pin;
        User authUser;
        //prompt user for id/pin combo till correct one is reached

      do {
          System.out.printf("welcome to %s" ,theBank.getName());
           System.out.print("Enter user ID:");
           userID=sc.nextLine();
           System.out.print("Enter pin: ");
           pin =sc.nextLine();

           //  tyr to get user object corresponding to id and pin combo
           
              authUser= theBank.userLogin(userID,pin);
              if(authUser==null){
                System.out.println("incorrect id/pin combination." +"please try again ");


              }




      }   while (authUser==null);  //continue looping till succesful login

      return authUser;


    }
    
    public static void printUserMenu(User theUser, Scanner sc){
     //print a summary of the users accounts
        theUser.printAccountsSummary();
        // initialise
        int choice;
        do {
            System.out.printf("welcome %s, what would you like to do");
            theUser.getFirstName();
            System.out.println("  1) show account transaction history");
            System.out.println("  2) withdrwaw");
            System.out.println("  3)  deposit");
            System.out.println("  4) transfer");
            System.out.println("  5) quit");
            System.out.println();
            System.out.println("ENTER CHOICE") ;
            choice= sc.nextInt();

           if (choice<1 || choice > 5 ){
               System.out.println("INVALID CHOICE PLEASE CHOOSE 1-5");

           }
        } while ( choice < 1 || choice > 5);


     // process the choice

        switch (choice){

            case 1:
                Atm.showTransferHistory(theUser,sc);
                break;
            case 2:
                Atm.withdrawFunds(theUser,sc);
                break;
            case 3:
                Atm.depositFunds(theUser,sc);
                break;
            case 4:
                Atm.transferFunds(theUser,sc);
                break;

        }
         //redisplay this menu unless the user wants to quit
         if(choice!=5){
              Atm.printUserMenu(theUser,sc);
         }


    }

    /**
     * show the transaction history for an account
     * @param theUser  the logged in user object
     *                 
     * @param sc       the scanner object used for the user
     */

    public  static void showTransferHistory(User theUser, Scanner sc){
        int theAcct;
        // get account whose transactions history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +" whose transactions you want to see:",theUser.numAccounts());
            theAcct=sc.nextInt()-1;
            if(theAcct<0 || theAcct>= theUser.numAccounts()){
                System.out.println("Invalid account please try again");

            }

        }   while (theAcct<0 || theAcct>= theUser.numAccounts());
            //print the transaction history

        theUser.printAcctHistory (theAcct);


    }









    }