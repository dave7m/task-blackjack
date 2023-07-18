package model;

import java.util.Objects;

public class Bet {

    private final int bet;

    public Bet(int bet) {
        this.bet = bet;
    }

    public int getAmount() {
        return bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet1 = (Bet) o;
        return bet == bet1.bet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet);
    }
}
