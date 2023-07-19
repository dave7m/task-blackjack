package model;

import exception.InvalidMoneyAmountException;

public class Wallet {
    private int money;

    /**
     * @param money: integer
     * @pre money >= 0
     */
    public Wallet(int money) {
        this.money = Math.max(money, 0);
    }

    public Wallet() {
        this.money = 100;
    }

    /**
     * @param amount: int
     * @pre amount >= 0
     */
    public void put(int amount) throws InvalidMoneyAmountException {
        if (amount <= 0) {
            throw new InvalidMoneyAmountException();
        }
        this.money += amount;
    }

    /**
     * Subtracts the amount from the wallet
     *
     * @param amount: integer
     * @throws InvalidMoneyAmountException : if the money in the wallet is insufficient
     * @pre amount >= 0
     */
    public void withdraw(int amount) throws InvalidMoneyAmountException {
        if (this.money < amount || amount == 0) {
            throw new InvalidMoneyAmountException();
        }
        this.money -= amount;
    }

    /**
     * @return int: how much money in this wallet is
     */
    public int getAmount() {
        return this.money;
    }

    /**
     * Empties the Wallet, if the current amount is greater than zero
     */
    public void withdrawAll() {
        if (this.money >= 0) {
            this.money = 0;
        }
    }
}
