/*
    - FIX KUNG SAAN KAPAG ENTER LANG GINAWA NG USER SA MGA USER INPUT
*/


import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        
        double balance = 5000.0;
        boolean isValid = true;

        String pin = "12345"
             , ENTERED_PIN = "";
        
        int choice = 0
          , attempt = 3;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("+--------------------------------------------+");
            System.out.println("|          AUTOMATED TELLER MACHINE          |");
            System.out.println("+--------------------------------------------+\n");
            System.out.print("ENTER YOUR PIN: ");
            ENTERED_PIN = scanner.nextLine();
            System.out.println();

            if (!(ENTERED_PIN.equals(pin))) {
                
                attempt--;

                if (attempt == 0) {
                    System.out.println("\nACCOUNT LOCKED!\n");
                    System.exit(0);
                }
                System.out.printf("INVALID PIN\nYOU ONLY HAVE %d ATTEMPTS LEFT\n\n", attempt); 
            }
            
        } while (!(ENTERED_PIN.equals(pin)));

        do {
            
            choice = getUserChoice(scanner);

            if (choice == 1) displayBalance(scanner, balance);

            else if (choice == 2) balance = deposit(scanner, balance);
            
            else if (choice == 3) balance = withdraw(scanner, balance);    

            else if (choice == 4) pin = ChangePIN(scanner, pin);

            else if (choice == 5) isValid = false;

            else System.out.println("INVALID INPUT\nPLEASE TRY AGAIN!\n");

        } while (isValid);

        scanner.close();
    }

    static int getUserChoice(Scanner scanner) {

        System.out.println("+--------------------------------------------+");
        System.out.println("|          AUTOMATED TELLER MACHINE          |");
        System.out.println("+--------------------------------------------+");
        System.out.println("|                                            |");
        System.out.println("|   [1] CHECK BALANCE    [4] CHANGE PIN      |");
        System.out.println("|                                            |");
        System.out.println("|   [2] DEPOSIT          [5] EXIT            |");
        System.out.println("|                                            |");
        System.out.println("|   [3] WITHDRAW                             |");
        System.out.println("|                                            |");
        System.out.println("+--------------------------------------------+");
        System.out.print("\n\nSELECT: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        
        return choice;
    }

    static void displayBalance(Scanner scanner, double balance) {

        System.out.println("+--------------------------------------------+");
        System.out.printf("|           BALANCE: PHP %-20.2f|\n", balance);        
        System.out.println("+--------------------------------------------+");
        System.out.println("\nPRESS [ENTER] TO CONTINUE");
        scanner.nextLine();
        
    }

    static double deposit(Scanner scanner, double balance) {

        double deposit;
        boolean isValid = true;

        do {
            
            System.out.println("+--------------------------------------------+");
            System.out.println("|          AUTOMATED TELLER MACHINE          |");
            System.out.println("+--------------------------------------------+");
            System.out.println("|                  DEPOSIT                   |");
            System.out.print("\n\nENTER DEPOSIT AMOUNT: ");
            deposit = scanner.nextDouble();
            scanner.nextLine();
            System.out.println();
        
        if (deposit <= 0) System.out.println("DEPOSIT MUST BE ABOVE 0!\nPLEASE TRY AGAIN!");

        else {
            if (deposit % 100 == 0) {
                balance += deposit;
                System.out.println("\nYOU HAVE SUCCESFULLY DEPOSITED PHP " + deposit + "\n\n");
                isValid = false;
            }
            else System.out.println("DEPOSIT AMOUNT MUST BE MULTIPLE OF 100\nPLEASE TRY AGAIN");
        }

        } while (isValid);
        
        return balance;
    }

    static double withdraw(Scanner scanner, double balance) {

        double withdrawAmount;
        boolean isValid = true;
        char confirm = '\0';

        do {

            if (balance > 0) {

                System.out.println("+--------------------------------------------+");
                System.out.println("|          AUTOMATED TELLER MACHINE          |");
                System.out.println("+--------------------------------------------+");
                System.out.println("|                  WITHDRAW                  |");
                System.out.print("\n\nENTER WITHDRAW AMOUNT: ");
                withdrawAmount = scanner.nextDouble();
                scanner.nextLine();
                System.out.println();

                if (withdrawAmount > 10000) System.out.println("MAXIMUM WITHDRAWAL AMOUNT PER TRANSACTION: PHP 10,000\n");

                else if (withdrawAmount > balance) System.out.println("WITHDRAW AMOUNT SHOULD NOT BE GREATER THAN BALANCE\n"); 
                    
                else if (withdrawAmount <= 0) System.out.println("AMOUNT MUST BE ABOVE 0!\nPLEASE TRY AGAIN!");

                else if (withdrawAmount % 100 != 0) System.out.println("AMOUNT MUST BE MULTIPLE OF 100!\n");
                
                else {

                    do {
                        System.out.println("WITHDRAW: PHP " + withdrawAmount);
                        System.out.println("\nCONFIRM [Y/N]: ");
                        confirm = scanner.nextLine().toUpperCase().charAt(0);
                    } while ((confirm != 'Y') && (confirm != 'N'));
                    
                    if (confirm == 'Y') {
                        balance -= withdrawAmount;
                        System.out.println("\nYOU HAVE SUCCESFULLY WITHDRAWED PHP " + withdrawAmount + "\n\n");
                    }
                    else System.out.println("\nTRANSACTION CANCELED\n");
                    isValid = false;

                }
            }
            else {
                System.out.printf("INSUFFICIENT BALANCE!\nYOUR CURRENT BALANCE IS %.2f\n\n", balance);
                isValid = false;
            }
        } while (isValid);

        return balance;
    }

    static String ChangePIN(Scanner scanner, String pin) {

        String CHANGE_PIN = "";
        String ENTERED_PIN = "";

        int attempt = 3;
        int valid = 0;
        int ChangePIN_Valid = 0;
        char confirm = '\0';
        
        System.out.println("+--------------------------------------------+");
        System.out.println("|          AUTOMATED TELLER MACHINE          |");
        System.out.println("+--------------------------------------------+");
        System.out.println("|                 CHANGE PIN                 |");

        do {

            System.out.print("\n\nENTER YOUR CURRENT PIN: ");
            ENTERED_PIN = scanner.nextLine();

            if (!(ENTERED_PIN.equals(pin))) {
                
                attempt--;
                
                if (attempt == 0) {
                    System.out.println("\nYOU HAVE FAILED TO ENTER YOUR PIN!");
                    System.out.println("\nRETURNING TO MAIN MENU . . .\n");
                    valid = 2;
                }
                else System.out.printf("\nINVALID PIN\nYOU ONLY HAVE %d ATTEMPTS LEFT", attempt);
                 
            }
            else valid = 1;

        } while (valid == 0);

        if (valid == 1) {

            do {

                System.out.print("\nENTER YOUR NEW PIN: ");
                CHANGE_PIN = scanner.nextLine();
                
                boolean isDigit = true;
                for (int i = 0; i < CHANGE_PIN.length(); i++) {
                    if (!Character.isDigit(CHANGE_PIN.charAt(i))) {
                        isDigit = false;
                        break;
                    }
                }

                if (CHANGE_PIN.equals(pin)) System.out.println("\nTHIS IS YOUR CURRENT PIN!\nPROVIDE A NEW ONE");

                else if (!isDigit) System.out.println("\nPIN MUST CONTAIN DIGITS ONLY!");

                else if ((CHANGE_PIN.length() >= 7) || (CHANGE_PIN.length() <= 3)) System.out.println("\nPIN MUST BE ATLEAST 4 CHARACTERS OR 6 CHARACTERS MAX!\nPLEASE TRY AGAIN!");
                
                else {

                    do {

                        System.out.printf("\nCONFIRM NEW PIN: %s", CHANGE_PIN);
                        System.out.print("\nYES OR NO [Y/N]: ");
                        confirm = scanner.nextLine().toUpperCase().charAt(0);

                    } while ((confirm != 'Y') && (confirm != 'N'));
                    
                    if (confirm == 'Y') ChangePIN_Valid = 1;

                    else {
                        ChangePIN_Valid = 1;
                        System.out.println("\nYOU DID NOT CONFIRM NEW PIN");
                        System.out.println("\nRETURNING TO MAIN MENU . . .\n");
                        CHANGE_PIN = pin;
                    }
                }

            } while (ChangePIN_Valid == 0);
  
        }

        else CHANGE_PIN = pin;

        return CHANGE_PIN;
    }
    
}