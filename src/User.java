import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User{
private String FirstName;  //first name of user
private String LastName;   // last name of user
private String uuid;  //the i.d of the user   universal unique identifier

private byte pinHash[];  // the md5 hash of the users pin-number

private ArrayList<Account> accounts; //list of accounts for this user


  // constructor for user
  public User(String FirstName, String LastName, String pin, Bank theBank){
    // set users name

    this.FirstName=FirstName;
    this.LastName=LastName;

    // store the pin md5 hash but not exact value for security reasons

    try {
      MessageDigest md =MessageDigest.getInstance("md5");
      this.pinHash= md.digest(pin.getBytes());
    } catch (NoSuchAlgorithmException e) {
      System.err.println("error caught no such algorithm exception");
      e.printStackTrace();
      System.exit(1);
    }

    //get uuid unique universal i.d for the user
    this.uuid=theBank.getNewUserUUID();

// create empty lists of accounts
    this.accounts=new ArrayList<Account>();

    // print log message
    System.out.printf("new user %s, %s with ID %s created. \n", LastName,FirstName,this.uuid);



    }

  public void addAccount(Account anAcct) {
    this.accounts.add(anAcct);
  }

     //return user uuid
  public String getUUID() {
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

}