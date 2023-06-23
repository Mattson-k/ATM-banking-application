import java.util.ArrayList;

public class Account {
    private String name; //name of account

    private String uuid;  //user account i.d

    private User holder;   //user object that hold this account

    private ArrayList<Transaction> transactions;


    //  this will entail the name of the account,
    //  the user object that holds the account,
     //the bank that issues the account
   public Account(String name, User holder,Bank theBank)  {

       //set name of account and holder
       this.name=name;
       this.holder=holder;

       // get new uuid for account
       this.uuid=theBank.getNewAccountUUID();

       // initialise transactions
       this.transactions=new ArrayList<Transaction>();



   }


    public String getUUID() {
       return  this.uuid;
    }

    /**
     * get summary line for the account
     * @return
     */
    public String getSummaryLine(){
         //get balance of account
           double balance=this.getBalance();
           //format summary line depending if balance is negative

           if(balance >= 0){
               return String.format("%s : shs%.02f : %s", this.uuid, balance,this.name);


           } else {


               return String.format("%s : shs(%.02f) : %s", this.uuid, balance,this.name);

           }




       }

       public double getBalance(){
        double balance =0;
        for (Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;

       }

    /**
     *    print transaction history of the account
     */

    public void printTransHistory(){
         System.out.printf("\n Transaction history for account %s\n", this.uuid   );
        for(int t=this.transactions.size()-1; t>=0;t--)  //start with last index in the transaction and keep looping while t is greater and equal too 0 and keep reducing
        {
           System.out.printf(this.transactions.get(t).getSummaryLine());

        }
        System.out.println();

      }


}


