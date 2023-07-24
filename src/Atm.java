import java.util.Scanner;

public class Atm {

//


    public static void main(String[] args) {
     //initialise scanner
        Scanner sc =new Scanner(System.in) ;

        // initialise  bank

         Bank theBank =new Bank("CentenaryBank") ;

         // add user to bank which also creates a savings account
        User aUser = theBank.addUser("Kigonya","Timothy","5417");

        // add a fixed deposit account for our user
        Account newAccount =new Account("Fixed Deposit Account",aUser,theBank);
            aUser.addAccount(newAccount);
            theBank.addAccount (newAccount);

            User curUser;
            while (true){
                //stay in login prompt till its succesful
                 curUser =Atm.mainMenuPrompt(theBank, sc);
                 //stay in main menu till user quits
                 Atm.printUserMenu(curUser, sc);



            }



        }

    /**
     *        initialise the main menu prompt
     * @param theBank
     * @param sc
     * @return
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc ) {
            //inits
        String userID;
        String pin;
        User authUser;
        //prompt user for user id  and pin combo till correct one is reached

      do {
          System.out.printf("\n\n welcome to %s\n\n" ,theBank.getName());
           System.out.print("Enter user ID:");
           userID=sc.nextLine();
           System.out.print("Enter pin: ");
           pin =sc.nextLine();

           //  try to get user object corresponding to id and pin combo
           
              authUser= theBank.userLogin(userID,pin);
              if(authUser==null){
                System.out.println("incorrect id/pin combination." +"please try again ");

              }

      }   while (authUser==null);  //continue looping till succesful login

      return authUser;


    }

    /**
     *
     * @param theUser    display user menu and make prompts
     * @param sc
     */
    public static void printUserMenu(User theUser, Scanner sc){
     //print a summary of the users accounts
        theUser.printAccountsSummary();
        // initialise
        int choice;
        //user menu
        do {
            System.out.printf("welcome %s, what would you like to do?\n",theUser.getFirstName());
            System.out.println("  1) Show account transaction history");
            System.out.println("  2) Withdraw");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println("ENTER CHOICE") ;
            choice= sc.nextInt();

           if (choice <1 || choice > 5 ){
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
            case 5:
                sc.nextInt();
                 break;
        }
         //redisplay this menu unless the user wants to quit
         if(choice!=5){
              Atm.printUserMenu(theUser,sc);
         }


    }

    /**
     * show the transaction history for an account
     * @param theUser  the logged-in user object
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

    /**
     *      process transferring funds from one account to another
     * @param theUser the logged-in user object
     * @param sc the scanner object used for user input
     */


public  static void transferFunds(User theUser, Scanner sc){

  //inits

  int fromAcct;
   int toAcct;
   double amount;
   double acctBal;

   // get the account to transfer from
    do {
        System.out.print("Enter the number (1-%d) of the account \n" + "to transfer from: ");
             fromAcct= sc.nextInt()-1;
             if(fromAcct< 0 || fromAcct >=theUser.numAccounts()){
                 System.out.println("invalid account. please try again. ");
        }

    }   while (fromAcct< 0 || fromAcct >=theUser.numAccounts());
            acctBal=theUser.getAcctBalance (fromAcct);

            //get account to transfer to

    do {
        System.out.printf("Enter the number (1-%d) of the account \n" + "to transfer to: ", theUser.numAccounts());
        toAcct= sc.nextInt()-1;
        if(toAcct< 0 || toAcct >=theUser.numAccounts())
            System.out.println("invalid account. please try again. ");


    }   while (toAcct< 0 || fromAcct >=theUser.numAccounts());

       //get the amount to transfer
    do {
        System.out.printf("Enter the amount to transfer (max shs%.02f): shs",acctBal );
        amount=sc.nextDouble();
        if (amount<0){
            System.out.println("amount must be greater than zero,");

        } else if (amount>acctBal) {System.out.printf("amount must not be greater than\n" + "balance of shs%.02f.\n",acctBal);

        }


    }   while(amount< 0 || amount > acctBal);

           //do the transfer

  theUser.addAcctTransaction(fromAcct,-1*amount, String.format("transfer to account %s",theUser.getAcctUUID(toAcct)));


    theUser.addAcctTransaction(toAcct,amount,String.format("transfer to account %s",theUser.getAcctUUID(fromAcct)));





}


    /**
     * process a fund withdraw from an account
     * @param theUser the logged-in user object
     * @param sc  the scanner object user for user input
     */

public static void withdrawFunds(User theUser ,Scanner sc){

     //inits
     String memo;
     int fromAcct;
     int toAcct;
     double amount;
     double acctBal;

     // get the account to transfer from
     do {
         System.out.printf("Enter the number (1-%d) of the account \n" + "to withdraw from: ", theUser.numAccounts());
         fromAcct= sc.nextInt()-1;
         if(fromAcct< 0 || fromAcct >theUser.numAccounts());{
             System.out.println("invalid account. please try again. ");
         }

     }   while (fromAcct< 0 || fromAcct >theUser.numAccounts());
     acctBal=theUser.getAcctBalance (fromAcct);


     //get the amount to transfer
     do {
         System.out.printf("Enter the amount to withdraw (min shs%.02f): shs",acctBal );
         amount=sc.nextDouble();
         if (amount<0){
             System.out.println("amount must be greater than zero,");

         } 


     }   while(amount< 0);


      //gobble up the rest of the previous input
      sc.nextInt();

      //get a memo
     System.out.print("Enter a memo:  ");
    memo=sc.nextLine();

     // do the withdrawal
   theUser.addAcctTransaction(fromAcct,-1*amount,memo);




 }

    /**
     * process a fund deposit to an account
     * @param theUser the logged-in user object
     * @param sc the scanner object used for the user input
     */




   public static void depositFunds(User theUser,Scanner sc){

       //inits
       int toAcct;
       double amount;
       double acctBal;


       // get the account to transfer to
       do {
           System.out.printf("Enter the number (1-%d) of the account\n" + "to deposit in: ",theUser.numAccounts());
           toAcct= sc.nextInt()-1;
           if(toAcct< 0 || toAcct >=theUser.numAccounts()){
               System.out.println("invalid account. please try again. ");
           }

       }   while (toAcct < 0 || toAcct >=theUser.numAccounts());
       acctBal=theUser.getAcctBalance (toAcct);


       //get the amount to transfer
       do {
           System.out.printf("Enter the amount to transfer (min shs%.02f): shs",acctBal );
           amount=sc.nextDouble();
           if (amount<0){
               System.out.println("amount must be greater than zero,");

           }

       }   while(amount<0);


       //guzzle up the rest of the previous input
       sc.nextInt();

       //get a memo
       System.out.print("Enter a memo:  ");
      String memo= sc.nextLine();

       // do the deposit
       theUser.addAcctTransaction(toAcct,amount,memo);


   }


    }