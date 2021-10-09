import java.util.*;
public class Piggybank_Main {
    static ArrayList<Coin> coin;
    static String welcome = "Welcome to your Piggybank!";
    static String command = "Here are the list of commands that you can do with this application (Enter the number before the command): \n" +
        "0: Exit the program. \n" +
        "1: Put specific coins in the bank. \n" +
        "2: Extract an exact amount from the bank. \n" +
        "3: Show the number of each coin in the bank. \n" +
        "4: Print out the amount of total money in the bank.";
    static String addCoin = "Here are the list of coins that you can add to the bank: (Enter the number before the command): \n" +
        "0: Return to previous menu \n" +
        "1: Toonie \n" +
        "2: Loonie \n" +
        "3: Quarter \n" +
        "4: Dime \n" +
        "5: Nickel";
    static String amountEachCoin = "Here are the list of coins that you can inquire in the bank: (Enter the number before the command): \n" +
            "0: Return to previous menu \n" +
            "1: Toonie \n" +
            "2: Loonie \n" +
            "3: Quarter \n" +
            "4: Dime \n" +
            "5: Nickel";
    static String addAmount = "Please enter the specific amount in dollars ($)";
    static String amount = "Please enter the amount of that coin: ";
    static Scanner sc = new Scanner (System.in); /* declare scanner to read in user input */
    public static void main (String args[]) {
        System.out.println(welcome); /* print out the welcoming message */
        PiggyBank bank = new PiggyBank(); /* create and initialize the piggy bank */

        /**
         * @testing methods
         */
//        bank.addCoin(new Toonie(), 5);
//        bank.addCoin(new Dime(), 5);
//        bank.addCoin(new Nickel(), 5);
//        System.out.println(bank.getToonie()); /* testing getter method */
//
//        System.out.println(bank.getTotalMoney()); /* testing total money method */
//

//        System.out.println(bank.ableToMake(10.6)); /* testing if we are able to make a certain amount method */
//
//        bank.takeoutMoney(10.0); /* testing the removing money from bank method */
//        System.out.println(bank.getTotalMoney());

//        bank.addCoin(new Toonie(), 10);
//        System.out.println(bank.ableToMake(3.0));


        while(true) {
            System.out.println(command); /* print out the instructions line */
            int com = sc.nextInt(); /* scan in the user's command */
            if(com == 0) {
                System.out.println("Thank you for using the program!");
                System.exit(0);
            } /* exiting the program */
            else if(com == 1) {
                System.out.println(addCoin);
                int coman = sc.nextInt();
                if(coman == 0) {
                    continue;
                }
                System.out.println(amount);
                int amnt = sc.nextInt();
                if(coman == 1) {
                    bank.addCoin(new Toonie(), amnt);
                }
                else if(coman == 2) {
                    bank.addCoin(new Loonie(), amnt);
                }
                else if(coman == 3) {
                    bank.addCoin(new Quarter(), amnt);
                }
                else if(coman == 4) {
                    bank.addCoin(new Dime(), amnt);
                }
                else if(coman == 5) {
                    bank.addCoin(new Nickel(), amnt);
                }
            } /* adding coins to the bank */
            else if(com == 2) {
                System.out.println("Please enter the amount of money that you want to extract in dollars");
                double amnt = sc.nextDouble();
                if(!bank.haveEnoughMoney(amnt)) System.out.println("Sorry, there are not enough money in your bank.");
                else if(!bank.ableToMake(amnt)) {
                    System.out.println("The amount cannot be created with the amount present in your bank. \n" +
                            "However, you can make $" + bank.closesAbleToMakeHigher(amnt) + " with the coins in your bank. They will owe you $" + (bank.closesAbleToMakeHigher(amnt)-amnt + "\n" +
                            "Or you can make $" + bank.closestAbleToMakeLower(amnt)) + " with the coins in your bank. You will owe them $" + (amnt-bank.closestAbleToMakeLower(amnt)));
                }
                else {
                    bank.takeoutMoney(amnt);
                    System.out.println("$" + amnt + " was taken out of your bank. Your new balance is: $" + bank.getTotalMoney());
                }
            } /* extracting coins from the bank */
            else if(com == 3) {
                System.out.println("There are " + bank.getToonie() + " toonies in your piggybank.");
                System.out.println("There are " + bank.getLoonie() + " loonies in your piggybank.");
                System.out.println("There are " + bank.getQuarter() + " quarters in your piggybank.");
                System.out.println("There are " + bank.getDime() + " dimes in your piggybank.");
                System.out.println("There are " + bank.getNickel() + " nickels in your piggybank.");
            } /* Show the number of each coin in the bank */
            else if(com == 4) {
                System.out.println("The total amount of money in the bank is: $" + bank.getTotalMoney());
            } /* print out the total amount of money in the piggy bank */
        } /* continuously looping until the user wants to exit the program */
    }
}
