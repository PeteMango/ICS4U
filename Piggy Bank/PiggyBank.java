/**
 * @Name: Peter Wang
 * @Teacher: Mr.Anandarajan
 * @Task: Piggy Bank in class assignment
 * @Date: 7 January, 2019
 */

/**
 *
 * Imports
 */
import java.util.*;

/**
 * Piggybank class
 */
public class PiggyBank {
    /**
     * @declaration of variables
     */
    private ArrayList<Coin> coins;
    private int NToonie, NLoonie, NQuarter, NDime, NNickel;

    /**
     * @function constructor
     */
    public PiggyBank () {
        NToonie = 0;
        NLoonie = 0;
        NQuarter = 0;
        NDime = 0;
        NNickel = 0;
        coins = new ArrayList<Coin>();
    }

    /**
     * @function constructor with only an arraylist
     * @param newCoin
     */
    public PiggyBank (ArrayList<Coin> newCoin) {
        coins = newCoin;
        NToonie = 0;
        NLoonie = 0;
        NQuarter = 0;
        NDime = 0;
        NNickel = 0;
    }

    /**
     * @return the number of coins currently in the piggy bank
     */
    public int getNumberOfCoins () {
        return coins.size();
    }

    /**
     * @return the total money in the piggy bank
     */
    public double getTotalMoney () {
        double total = 0;
        for(int i = 0; i < coins.size(); i++) {
            total += coins.get(i).getValue();
        }
        return Math.round(total * 100.0) / 100.0;
    }

    /**
     * @Function adds the specified coin into the piggy bank
     * @param coin
     */
    public void addCoin (Coin coin, int amount) {
        for(int i = 0; i < amount; i++) { coins.add(coin); } /* Add coin to the array list */
        if(coin.getType().equalsIgnoreCase("Nickel")) { /* adding nickel to piggy bank */
            NNickel += amount;
        }
        if(coin.getType().equalsIgnoreCase("Dime")) { /* adding dime to piggy bank */
            NDime += amount;
        }
        if(coin.getType().equalsIgnoreCase("Quarter")) { /* adding quarter to piggy bank */
            NQuarter += amount;
        }
        if(coin.getType().equalsIgnoreCase("Loonie")) { /* adding loonie to piggy bank */
            NLoonie += amount;
        }
        if(coin.getType().equalsIgnoreCase("Toonie")) { /* adding toonie to piggy bank */
            NToonie += amount;
        }
    }

    /**
     * @function getter methods
     * @return the amount of each coin in the bank
     */
    public int getToonie () {
        return NToonie;
    } /* returns the number of toonies */
    public int getLoonie () {
        return NLoonie;
    } /* returns the number of loonie */
    public int getQuarter () {
        return NQuarter;
    } /* returns the number of quarter */
    public int getDime () {
        return NDime;
    } /* returns the number of dime */
    public int getNickel () {
        return NNickel;
    } /* returns the number of nickel */

    /**
     * @function remove the specified coin from the piggy bank
     */
    public void removeCoin (Coin coin, int amount) {
        if(!ableToRemove(coin, amount)) System.out.println("There are not enough of this coin type to be removed!");
        if(coin.getType().equalsIgnoreCase("Nickel")) { /* removing nickel from piggy bank */
            NNickel -= amount;
            for(int i = 0; i < amount; i++) coins.remove(coin);
        }
        if(coin.getType().equalsIgnoreCase("Dime")) { /* removing dime from piggy bank */
            NDime -= amount;
            for(int i = 0; i < amount; i++) coins.remove(coin);
        }
        if(coin.getType().equalsIgnoreCase("Quarter")) { /* removing quarter from piggy bank */
            NQuarter -= amount;
            for(int i = 0; i < amount; i++) coins.remove(coin);
        }
        if(coin.getType().equalsIgnoreCase("Loonie")) { /* removing loonie from piggy bank */
            NLoonie -= amount;
            for(int i = 0; i < amount; i++) coins.remove(coin);
        }
        if(coin.getType().equalsIgnoreCase("Toonie")) { /* removing toonie from piggy bank */
            NToonie -= amount;
            for(int i = 0; i < amount; i++) coins.remove(coin);
        }
    }

    /**
     * @param coin
     * @return true if there is enough of that coin in the bank to remove, return false if otherwise
     */
    public boolean ableToRemove (Coin coin, int amount) {
        if(coin.getType().equalsIgnoreCase("Nickel") || coin.getValue() == 0.05) { /* checking if you can remove a nickel from the piggy bank */
            if(NNickel >= amount) return true;
            return false;
        }
        if(coin.getType().equalsIgnoreCase("Dime") || coin.getValue() == 0.1) { /* checking if you can remove a dime from the piggy bank */
            if(NDime >= amount) return true;
            return false;
        }
        if(coin.getType().equalsIgnoreCase("Quarter") || coin.getValue() == 0.25) { /* checking if you can remove a quarter from the piggy bank */
            if(NQuarter >= amount) return true;
            return false;
        }
        if(coin.getType().equalsIgnoreCase("Loonie") || coin.getValue() == 1.0) { /* checking if you can remove a loonie from the piggy bank */
            if(NLoonie >= amount) return true;
            return false;
        }
        if(coin.getType().equalsIgnoreCase("Toonie") || coin.getValue() == 2.0) { /* checking if you can remove a toonie from the piggy bank */
            if(NToonie >= amount) return true;
            return false;
        }
        return false;
    }

    public double min (int a, double b) {
        if(a < b) return a;
        return (int) b;
    }

    /**
     * @param amount
     * @return true if the amount can be made using the coins avaliable, return false if it can't
     */
    public boolean ableToMake (double amount) {
        if(amount >= 2.0) {
            amount -= 2.0 * min (getToonie(), amount / 2.0);
        }
        if(amount >= 1.0) {
            amount -= 1.0 * Math.min(getLoonie(), amount / 1.0);
        }
        if(amount >= 0.25) {
            amount -= 0.25 * Math.min(getQuarter(), amount / 0.25);
        }
        if(amount >= 0.1) {
            amount -= 0.1 * Math.min(getDime(), amount / 0.1);
        }
        if(amount >= 0.05) {
            amount -= 0.05 * Math.min(getNickel(), amount / 0.05);
        }
        if(amount == 0) return true;
        return false;
    }

    /**
     * @param amount of money hoping to make
     * @return closest amount that can be achieved that is lower than amount
     */
    public double closestAbleToMakeLower (double amount) {
        for(double i = amount; i >= 0.0; i -= 0.01) { /* loop through amount lower than the amount */
            i = Math.round(i * 100.0)/100.0;
            if(ableToMake(i) == true) { /* checking if the amount can be achieved */
                return i; /* if yes, return the number */
            }
        }
        return 0; /* if none of the amount can be made, return 0 */
    }
    /**
     * @param amount of money hoping to make
     * @return closest amount that can be achieved that is higher than amount
     */
    public double closesAbleToMakeHigher (double amount) {
        for(double i = amount; i <= getTotalMoney(); i += 0.01) { /* loop through amount higher than the amount */
            i = Math.round(i * 100.0)/100.0;
            if(ableToMake(i) == true) { /* checking if the amount can be achieved */
                return i; /* if yes, return the number */
            }
        }
        return getTotalMoney(); /* if none of the amount can be made, return the total amount of money */
    }

    /**
     * @param amount to be extracted
     * @return true if there are enough money in the piggy bank, and false otherwise
     */
    public boolean haveEnoughMoney (double amount) {
        if(amount > getTotalMoney()) return false; /* check if there are enough money in the bank */
        return true; /* if there arn't enough, return false */
    }

    /**
     * List of Unused Functions
     */
    /**
     * @function take out the money in the bank
     * @param amount
     */
    public void takeoutMoney (double amount) {
        if(amount >= 2.0) {
            int numberToRemove = (int) Math.min(getToonie(), amount / 2.0);
            for(int i = 0; i < numberToRemove; i++) {
                for(int j = 0; j < coins.size(); j++) {
                    if(coins.get(j).getValue() == 2.0) {
                        coins.remove(j);
                        break;
                    }
                }
            }
            NToonie -= numberToRemove;
            amount -= 2.0 * numberToRemove;
        }
        if(amount >= 1.0) {
            int numberToRemove = (int) Math.min(getLoonie(), amount / 1.0);
            for(int i = 0; i < numberToRemove; i++) {
                for(int j = 0; j < coins.size(); j++) {
                    if(coins.get(j).getValue() == 1.0) {
                        coins.remove(j);
                        break;
                    }
                }
            }
            NLoonie -= numberToRemove;
            amount -= 1.0 * numberToRemove;
        }
        if(amount >= 0.25) {
            int numberToRemove = (int) Math.min(getQuarter(), amount / 0.25);
            for(int i = 0; i < numberToRemove; i++) {
                for(int j = 0; j < coins.size(); j++) {
                    if(coins.get(j).getValue() == 0.25) {
                        coins.remove(j);
                        break;
                    }
                }
            }
            NQuarter -= numberToRemove;
            amount -= 0.25 * numberToRemove;
        }
        if(amount >= 0.1) {
            int numberToRemove = (int) Math.min(getDime(), amount / 0.1);
            for(int i = 0; i < numberToRemove; i++) {
                for(int j = 0; j < coins.size(); j++) {
                    if(coins.get(j).getValue() == 0.1) {
                        coins.remove(j);
                        break;
                    }
                }
            }
            NDime -= numberToRemove;
            amount -= 0.1 * numberToRemove;
        }
        if(amount >= 0.05) {
            int numberToRemove = (int) Math.min(getNickel(), amount / 0.05);
            for(int i = 0; i < numberToRemove; i++) {
                for(int j = 0; j < coins.size(); j++) {
                    if(coins.get(j).getValue() == 0.05) {
                        coins.remove(j);
                        break;
                    }
                }
            }
            NNickel -= numberToRemove;
            amount -= 0.05 * numberToRemove;
        }
    }

    /**
     * @function adding a specific amount to the bank
     * @param amount
     */
    public void addAmount (double amount) {
        double C[] = {2.0, 1.0, 0.25, 0.1, 0.05}; /* create an array to store the coin values */
        for(int i = 0; i < C.length;) { /* loop through the avaliable coins */
            if(amount == 0) break; /* if the exact amount is made, break */
            if(C[i] == 2.0 && amount >= 2.0) {
                amount -= C[i];
                NToonie++;
                coins.add(new Toonie());
                continue;
            } /* add toonie */
            i++;
            if(C[i] == 1.0 && amount >= 1.0)  {
                NLoonie++;
                amount -= C[i];
                coins.add(new Loonie());
                continue;
            } /* add loonie */
            i++;
            if(C[i] == 0.25 && amount >= 0.25) {
                amount -= C[i];
                NQuarter++;
                coins.add(new Quarter());
                continue;
            } /* add quarter */
            i++;
            if(C[i] == 0.1 && amount >= 0.1) {
                amount -= C[i];
                NDime++;
                coins.add(new Dime());
                continue;
            } /* add dime */
            i++;
            if(C[i] == 0.05 && amount >= 0.05) {
                amount -= C[i];
                NNickel++;
                coins.add(new Nickel());
                continue;
            } /* add nickel */
        }
    }

    /**
     * @function sort the array list of coins
     */
    public void sortPiggyBank () {
        Collections.sort(coins, new Comparator<Coin>() { /* Comparator */
            public int compare (Coin a, Coin b) {
                return Double.compare(a.getValue(), b.getValue());
            }
        });
    }

    /**
     * @function construtor with parameter
     * */
    public PiggyBank (ArrayList<Coin> newCoin, int newToonie, int newLoonie, int newQuarter, int newDime, int newNickel) {
        NToonie = newToonie;
        NLoonie = newLoonie;
        NQuarter = newQuarter;
        NDime = newDime;
        NNickel = newNickel;
        coins = newCoin;
        for (int i = 0; i < newToonie; i++) {
            newCoin.add(new Toonie());
        }
        for (int i = 0; i < newLoonie; i++) {
            newCoin.add(new Loonie());
        }
        for (int i = 0; i < newQuarter; i++) {
            newCoin.add(new Quarter());
        }
        for (int i = 0; i < newDime; i++) {
            newCoin.add(new Dime());
        }
        for (int i = 0; i < newNickel; i++) {
            newCoin.add(new Nickel());
        }
    }
}
