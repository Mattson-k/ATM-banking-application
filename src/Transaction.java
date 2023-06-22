import java.util.Date;

public class Transaction {
    private double amount;   // amount with  transaction

    private Date timestamp;  //date of transaction

    private String memo;       // memo for this transaction

    private  Account inAccount;  //account in which its performed



    // create a new transaction
    //amount transacted
    //the account the transaction belongs to
    public Transaction(double amount, Account inAccount){
        this.amount=amount;
        this.inAccount =inAccount;
        this.timestamp=new Date();
        this.memo="";
        
        
    }

     public Transaction(double amount, String memo,Account inAccount){
        // call the two-arg constructor first
         this(amount, inAccount);
         //set memo
         this.memo =memo;








     }


}
