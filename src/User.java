import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User{

  private final String FirstName;  //first name of user
private final String LastName;   // last name of user
private final String uuid;  //the i.d of the user   universal unique identifier

private byte[] pinHash;  // the md5 hash of the users pin-number

private final ArrayList<Account> accounts; //list of accounts for this user

  /**
   *
   * @param FirstName  the users first name
   * @param LastName   the users last name
   * @param pin        the users pin-number for the account
   * @param theBank     the bank object that the user is a customer of
   */

  // constructor for user
  public User(String FirstName, String LastName, String pin, Bank theBank){
    // set users name

    this.FirstName=FirstName;
    this.LastName=LastName;

    // store the pin  using the md5 hash but not exact value for security reasons  we will use the message digest class

    try {
      MessageDigest md =MessageDigest.getInstance("md5");
      this.pinHash= md.digest(pin.getBytes()); // this will get the byte data adn digest it and return a different  array of bytes to store in pin hash
    } catch (NoSuchAlgorithmException e) {
      System.err.println("error caught no such algorithm exception");   // print out an error
      e.printStackTrace();
      System.exit(1);
    }

    //get uuid unique universal i.d for the user
    this.uuid=theBank.getNewUserUUID();       // we write the method in bank to do that

// create empty lists of accounts
    this.accounts=new ArrayList<Account>();

    // print log message  to know our uuid
    System.out.printf(" Our Highly Esteemed Customer %s, %s with ID %s created. \n", LastName,FirstName,this.uuid);



    }

  /**
   *   add an account for the user!!
   * @param anAcct  the account to add
   */
  public void addAccount(Account anAcct)
  {
    this.accounts.add(anAcct);
  }

     //return user uuid
  public String getUUID()
  {                                               
    return this.uuid;
  }

  public boolean validatePin(String aPin) {

    try {
      MessageDigest md = MessageDigest.getInstance("md5");
      return  MessageDigest.isEqual(md.digest(aPin.getBytes()),this.pinHash);
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Error, caught NoSuchAlgorithmException");
      e.printStackTrace();
      System.exit(1);
    }

      return  false;
  }



  public String getFirstName(){
    return this.FirstName;

  }

  /**
   *   print summaries for the accounts of this user
   */

  public  void printAccountsSummary () {
        System.out.printf("\n\n%s's accounts summary\n", this.FirstName);
         for (int a=0;a< this.accounts.size();a++){
           System.out.printf("%d) %s\n",a+1,this.accounts.get(a).getSummaryLine());

         }
           System.out.println();
  }

  public  int numAccounts(){
    return this.accounts.size();

  }

  public void printAcctHistory(int acctIdx){
      this.accounts.get(acctIdx).printTransHistory();
    
  }

  /**
   *    get the account balance of a particular account
   * @param acctIdx     the index of account to use
   * @return    account balance
   */
  public double getAcctBalance(int acctIdx){
    return this.accounts.get(acctIdx).getBalance();


  }

  /**
   *  get uuid of  a particular account
   * @param acctIdx   the index of the account to use
   *
   * @return  the uuid of the account
   */
  public String getAcctUUID(int acctIdx){
    return this.accounts.get(acctIdx).getUUID();


  }

  /**
   * add a transaction to a particular account
   * @param acctIdx  the index of the account
   * @param amount  the amount of the transaction
   *
   * @param memo    memo of transaction
   */
  public void addAcctTransaction(int acctIdx,double amount,String memo){
       this.accounts.get(acctIdx).addTransaction(amount,memo);
       

  }


}

