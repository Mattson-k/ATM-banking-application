import java.util.ArrayList;

public class Account {
    private final String name; //name of account

    private final String uuid;  //user account i.d

    private final User holder;   //user object that holds this account

    private final ArrayList<Transaction> transactions;

                        //Constructor for Account class

    /**
     *
     * @param name     this will entail the name of the account,
     * @param holder    the user object that holds the account,
     * @param theBank   the bank that issues the account
     */


   public Account(String name, User holder,Bank theBank)  {

       //set name of account and holder
       this.name=name;
       this.holder=holder;

       // get new uuid for account
       this.uuid=theBank.getNewAccountUUID();  // we write the method in bank to do that

       // initialise transactions
       this.transactions=new ArrayList<Transaction>();

       


   }


    public String getUUID()
    {
       return  this.uuid;
    }

    /**
     * get summary line for the account
     * @return    summary for account
     */
    public String getSummaryLine(){
         //get balance of account
           double balance=this.getBalance();
           //format summary line depending on if balance is negative or positive

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
        for(int t=this.transactions.size()-1; t>=0;t--)  //start with last index in the transaction and keep looping while t is greater and equal to ZERO and keep reducing
        {
           System.out.println(this.transactions.get(t).getSummaryLine());

        }
        System.out.println();

      }

    /**
     * add a new transaction in this account
     * @param amount the amount transacted
     * @param memo  the transaction memo
     */
    public  void  addTransaction(double amount , String memo){
         // create new transaction object and add it to our list
          
        Transaction newTrans= new Transaction(amount,memo,this);
        this.transactions.add(newTrans);


      }

}


