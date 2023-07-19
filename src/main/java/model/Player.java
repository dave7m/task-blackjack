package model;


import exception.InvalidMoneyAmountException;

public class Player extends Person{
    private int wallet;
    private int bet;
    private int playerNumber;

    public Player(){
        wallet = 100;
    }

    /**
     * @return the balance of the player's wallet
     */
    public int balance(){
        return this.wallet;
    }

    /**
     * withdraws a certain amount of money from the player's wallet
     */
    public void withdraw(int amount) throws InvalidMoneyAmountException {
        if (this.wallet < amount || amount == 0) {
            throw new InvalidMoneyAmountException();
        }
        this.wallet -= amount;
    }

    /**
     * Adds a certain amount of money to the Player's wallet. If trying to add a negative number, it throws an exception
     */
    public void receiveMoney(int amount) throws InvalidMoneyAmountException {
        if (amount <= 0) {
            throw new InvalidMoneyAmountException();
        }
        this.wallet += amount;    }

    public int getBet(){return bet;}
    public void setBet(int bet){this.bet = bet;}
    public int getPlayerNumber(){return playerNumber;}
    public void setPlayerNumber(int newPlayerNumber){this.playerNumber = newPlayerNumber;}
}
