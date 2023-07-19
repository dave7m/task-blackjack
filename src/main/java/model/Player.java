package model;


import exception.InvalidMoneyAmountException;

public class Player extends Person{
    private final Wallet wallet;
    private Bet bet;
    private int playerNumber;

    public Player(){
        wallet = new Wallet(100);
    }

    /**
     * @return the balance of the player's wallet
     */
    public int balance(){
        return wallet.getAmount();
    }

    /**
     * withdraws a certain amount of money from the player's wallet
     * @param amount: int
     * @pre amount >= 0
     * @throws InvalidMoneyAmountException: if not enough money or invalid amount
     */
    public void withdraw(int amount) throws InvalidMoneyAmountException {
        wallet.withdraw(amount);
    }

    /**
     * Adds a certain amount of money to the Player's wallet. If trying to add a negative number, it ends the program
     * @param amount: integer, should be positive
     */
    public void receiveMoney(int amount) throws InvalidMoneyAmountException {
        wallet.put(amount);
    }

    /**
     * Converts an array of all possible hand values to an appropriate String
     *
     * @return String
     */
    public String getAllHandValues() {
        // The returned array's length is either 1 value or 2 values. Does not count an ace as 11 if it busts the hand,
        // only as 1. hand.getAllValues() returns an array of possible hand values. E.g. having an EIGHT and an ACE
        // results in [9, 19] (8+1 and 8+11), having an EIGHT and a NINE results in [17] (8+9).
        int[] allValues = hand.getAllValues();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(");
        stringBuilder.append(allValues[0]);
        if(allValues.length == 2){
            stringBuilder.append(", ");
            stringBuilder.append(allValues[1]);
        }
        stringBuilder.append(")");
        return " ".repeat(8 - stringBuilder.length()) + stringBuilder;
    }

    /**
     * @return Bet: current bet
     */
    public Bet getBet(){return bet;}

    /**
     * allows setting amount of current bet
     * @param bet: Bet with value of current bet
     */
    public void setBet(Bet bet){this.bet = bet;}

    /**
     * allows setting amount of current bet to entire amount of money that the player has
     */
    public void setBetAllIn() {
        this.bet = new Bet(this.wallet.getAmount());
    }

    /**
     * @return current playerNumber
     */
    public int getPlayerNumber(){return playerNumber;}

    /**
     * assigns a new number to a player
     * @param newPlayerNumber: integer
     */
    public void setPlayerNumber(int newPlayerNumber){this.playerNumber = newPlayerNumber;}
}
