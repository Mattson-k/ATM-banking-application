import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    public String getNewUserUUID() {

        // generate a new uuid for a user
        String uuid;
        Random rng =new Random();  //generate a code
        int len =6;   //length set to 6
        boolean nonUnique;
          //continue looping till we get a unique i.d

        do {
         uuid="";
         for (int c=0;c<len;c++){
             uuid+=((Integer)rng.nextInt(10)).toString();
         }
               //check to make sure its unique
            nonUnique=false;
            for (User u : this.users){
                if(uuid.compareTo(u.getUUID())==0 ){
                    nonUnique= true;
                    break;
                }
            }


        }   while (nonUnique);

        
        return uuid;
    }

         public  String getNewAccountUUID() {
             // generate a new uuid for a user
             String uuid;
             Random rng =new Random();  //generate a code
             int len =10;   //length set to 10
             boolean nonUnique;
             //continue looping till we get a unique i.d

             do {
                 uuid="";
                 for (int c=0;c<len;c++){
                     uuid+=((Integer)rng.nextInt(10)).toString();
                 }
                 //check to make sure its unique
                 nonUnique=false;
                 for (Account a : this.accounts){
                     if(uuid.compareTo(a.getUUID())==0 ){
                         nonUnique= true;
                         break;
                     }
                 }




             }   while (nonUnique);


             return uuid;
         }


    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }


    /**
     *
      * @param FirstName of user
     * @param LastName   of user
     * @param pin   of the user
     * @return   new user object
     */



    public User addUser(String FirstName,String LastName,String pin){
        //create user object and add it to your list
        User newUser=new User(FirstName,LastName,pin,this);
        this.users.add(newUser);

        // create a savings account

        Account newAccount = new Account("Savings", newUser,this);

        // add this account to holder and bank lists
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

         return newUser;

    }

    public User userLogin(String userID,String pin){
        // search through list of users

        for (User u :this.users){
            // check user ID is correct
            if(u.getUUID().compareTo(userID) ==0 && u.validatePin(pin)){
            return u;
            }


        }

     // if we havent found the user   or have incoreect pin
        return null;
    }




}