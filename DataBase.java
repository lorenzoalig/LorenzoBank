/**
 * This class serves as a database for all bank accounts created. It works
 * with as an object vector and includes the following methods:
 * 
 *      - Initializing the vector
 *      - Creating and inserting accounts
 *      - Locating an account with a code (returns Account object)
 *      - Deleting an account
 *      - Rearranging files in the database (repositioning the vector to fill null elements)
 *      - Sorting the vector
 *      - Printing the vector (to see all database)
 *      
 * Disclaimer: This is not the final version. Its purpose is for testing only. Further documentation to be added.
 * 
 * @lorenzoalig
 * @01.07.25
 */

public class DataBase
{
    // Database vector and index attributes
    private Account[] accounts;
    private int index;
    
    // Constructor
    public DataBase(){
        this.index = 0;
        this.accounts = null;  
    }
    
    // Getter for a copy of the database vector for iteration
    public Account[] getAccounts(){
        Account[] copyOfAccountFiles = new Account[this.index];      
        for(int i = 0; i < this.index; i++){          
            copyOfAccountFiles[i] = this.accounts[i];
        }        
        return copyOfAccountFiles;
    }
    
    // Getter for the index
    public int getIndex(){
        return this.index;
    }
    
    // Intialize the database vector
    public void initializeDatabase(int size){      
        this.accounts = new Account[size];  
    }
    
    // Insert an account on the database
    public boolean insertAccount(Account account){
        if(this.index >= accounts.length){            
            return false;      
        } else{          
            this.accounts[index] = account;
            this.index++;           
            return true;
        }        
    }
    
    // Locate an account on the database via code
    public Account locateAccount(int code){     
        int accountCode;        
        for(int i = 0; i < this.accounts.length; i++){           
            if(this.accounts[i] != null){           
                accountCode = this.accounts[i].getCode();
                
                if(accountCode == code){                
                    return this.accounts[i];                
                }
            }
        }      
        return null;
    }
    
    // System.out.println("\nError: Account does not exist.");

    // Locate an account on the database via code
    public Account locateAccount(String username){        
        String accountName;
        
        for(int i = 0; i < this.accounts.length; i++){            
            if(this.accounts[i] != null){               
                accountName = this.accounts[i].getUsername();
                
                if(username.equals(accountName)){              
                    return this.accounts[i];              
                }
            }
        }       
        return null;
    }
    
    // Delete an account from the database via code
    public boolean deleteAccount(int code){        
        Account aux = locateAccount(code);       
        for(int i = 0; i < this.index; i++){          
            if(this.accounts[i] == aux){                  
                    this.accounts[i] = null;
                    return true;                 
            }
        }        
        return false;
    }
    
    // Rearrange database to fill null elements
    public void rearrangeFiles(){        
        for(int i = 0; i < this.index; i++){          
            if(this.accounts[i] == null){              
                    for(int j = i; j < this.index; j++){                        
                        this.accounts[j] = this.accounts[j + 1];                      
                    }                   
                    this.index--;
            }
        }
    }
    
    // Sort accounts in database by code
    public void sortFilesByCode(){        
        for(int i = 0; i < this.accounts.length; i++){            
            for(int j = i + 1; j < this.accounts.length; j++){                
                Account temp;
                
                if(accounts[j].getCode() < accounts[i].getCode()){
                    temp = accounts[i];
                    accounts[i] = accounts[j];
                    accounts[j] = temp;
                }                
            }
        }
    }
    
    // Print entire database
    public void printDatabase(){      
        for(int i = 0; i < this.accounts.length; i++){            
            if(this.accounts[i] == null){                
                System.out.println("Account{NULL, Position:" + (i + 1) + "}");                
            }else {            
                System.out.println(this.accounts[i].toString());          
            }
        }
    }
    
    // Checks the account vector to see if username is already taken. If so, returns false.
    public boolean checkUsername(String username){                
        String accountName;        
        for(int i = 0; i < this.index; i++){                       
            accountName = this.accounts[i].getUsername();
            
            if(username.equals(accountName))             
                return false;      
        }        
        return true;
    }
    
    // Validate account login attempt
    public boolean validateLogin(String username, String password){        
        String accountPassword;
        
        if(checkUsername(username)){            
            return false;         
        } else {         
            accountPassword = locateAccount(username).getPassword();
            
            if(password.equals(accountPassword)){                               
                return true;              
            } else {                              
                return false;                
            }
        }
    }
}