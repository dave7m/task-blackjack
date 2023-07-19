package model;


import exception.InvalidMoneyAmountException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player aPlayer;

    @BeforeEach
    void setUp() {
        aPlayer = new Player();
        aPlayer.addCardToHand(new Card(Rank.KING, Suit.DIAMOND));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCardToHand() {
        aPlayer.addCardToHand(new Card(Rank.TWO, Suit.DIAMOND));
        aPlayer.addCardToHand(new Card(Rank.QUEEN, Suit.DIAMOND));
        assertEquals(22, aPlayer.getHandValue());
        List<Card> cardList = aPlayer.hand.getCards();
        assertTrue(cardList.get(0).equals(new Card(Rank.KING, Suit.DIAMOND)));
        assertTrue(cardList.get(1).equals(new Card(Rank.TWO, Suit.DIAMOND)));
        assertTrue(cardList.get(2).equals(new Card(Rank.QUEEN, Suit.DIAMOND)));
    }

    @Test
    void balance() {
        assertEquals(100, aPlayer.balance());
    }

    @Test
    void withdrawValidAmount() {
        assertDoesNotThrow(() -> aPlayer.withdraw(40));
        try {
            aPlayer.withdraw(40);
            assertEquals(20, aPlayer.balance());
        } catch (InvalidMoneyAmountException e) {
            fail();
        }
    }

    @Test
    void withdrawTooMuch() {
        assertThrows(InvalidMoneyAmountException.class, () -> aPlayer.withdraw(101));
    }

    @Test
    void withdraw0() {
        assertThrows(InvalidMoneyAmountException.class, () -> aPlayer.withdraw(0));
    }


    @Test
    void receiveMoney() {
        try {
            aPlayer.receiveMoney(50);
        } catch (InvalidMoneyAmountException e) {
            fail();
        }
        assertEquals(150, aPlayer.balance());
    }

    @Test
    void receiveMoney0() {
        assertThrows(InvalidMoneyAmountException.class, () -> aPlayer.receiveMoney(0));
    }

    @Test
    void receiveMoneyNegative() {
        assertThrows(InvalidMoneyAmountException.class, () -> aPlayer.receiveMoney(-50));
    }

    @Test
    void getAllHandValues() {
        assertEquals("    (10)", aPlayer.getAllHandValues());
        aPlayer.addCardToHand(new Card(Rank.TWO, Suit.DIAMOND));
        assertEquals("    (12)", aPlayer.getAllHandValues());
        aPlayer.addCardToHand(new Card(Rank.ACE, Suit.DIAMOND));
        assertEquals("    (13)", aPlayer.getAllHandValues());
        Player bPlayer = new Player();
        bPlayer.addCardToHand(new Card(Rank.ACE, Suit.CLUBS));
        assertEquals(" (1, 11)", bPlayer.getAllHandValues());
    }

    @Test
    void testPlayerNo() {
        aPlayer.setPlayerNumber(3);
        assertEquals(3, aPlayer.getPlayerNumber());
    }

    @Test
    void testBet() {
        aPlayer.setBet(new Bet(100));
        assertEquals(100, aPlayer.getBet().getAmount());
    }

    @Test
    void testBetEquality() {
        assertEquals(new Bet(200), new Bet(200));
    }

    @Test
    void testBetInequality() {
        assertNotEquals(new Bet(100), new Bet(200));
    }
}