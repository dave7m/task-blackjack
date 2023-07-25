package model;

import exception.InvalidMoneyAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
    Player aPlayer;

    @BeforeEach
    void setUp() {
        aPlayer = new Player();
        aPlayer.addCardToHand(new Card(Rank.KING, Suit.DIAMOND));
    }
    @Test
    void withdrawValidAmount() throws InvalidMoneyAmountException {
        aPlayer.withdraw(40);
        assertEquals(60, aPlayer.balance());
    }

    @Test
    void withdrawTooMuch() {
        assertThrows(InvalidMoneyAmountException.class, () -> aPlayer.withdraw(101));
    }

    @Test
    void withdraw0() {
        assertThrows(InvalidMoneyAmountException.class, () -> aPlayer.withdraw(0));
    }

}