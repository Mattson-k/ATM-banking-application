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

}
