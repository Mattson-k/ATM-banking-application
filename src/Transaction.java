import java.util.Date;

public class Transaction {
    private final double amount;   // amount with  transaction

    private final Date timestamp;  //date of transaction

    private String memo;       // memo for this transaction

    private final Account inAccount;  //account in which its performed


    /**
     *          // create a new transaction
     * @param amount  //amount transacted
     * @param inAccount  //the account the transaction belongs to
     */
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

    /**
     * get the amount of the transaction
     * @return the amount
     */
      public double getAmount(){
           return this.amount;
      }

    /**
     *      get a string summarising the transaction
     * @return    string summary
     */

      public String getSummaryLine() {
          if (this.amount >= 0) {
              return String.format("%s : shs%.02f : %s", this.timestamp.toString(), this.amount, this.memo);


          }

          else {
              return String.format("%s : shs(%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);
          }



      }


}       
