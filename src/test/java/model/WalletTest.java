package model;

import exception.InvalidMoneyAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet();
    }

    @Test
    void setWallet() {
        wallet = new Wallet(5000);
        assertEquals(5000, wallet.getAmount());
    }

    @Test
    void setWalletNegative() {
        wallet = new Wallet(-100);
        assertEquals(0, wallet.getAmount());
    }

    @Test
    void setWalletZero() {
        wallet = new Wallet(0);
        assertEquals(0, wallet.getAmount());    }

    @Test
    void putPositive() {
        try {
            wallet.put(5000);
        } catch (InvalidMoneyAmountException e) {
            fail();
        }
        assertEquals(5100, wallet.getAmount());
    }

    @Test
    void putNegative() {
        assertThrows(InvalidMoneyAmountException.class, () -> wallet.put(-5000));
    }

    @Test
    void putZero() {
        assertThrows(InvalidMoneyAmountException.class, () -> wallet.put(0));
    }

    @Test
    void withdraw() {
        try {
            wallet.withdraw(70);
        } catch (InvalidMoneyAmountException e) {
            fail();
        }
        assertEquals(30, wallet.getAmount());
    }

    @Test
    void withdrawTooMuch() {
        assertThrows(InvalidMoneyAmountException.class, () -> wallet.withdraw(101));
    }

    @Test
    void withdrawZero() {
        assertThrows(InvalidMoneyAmountException.class, () -> wallet.withdraw(0));
    }

    @Test
    void getAmount() {
        assertEquals(100, wallet.getAmount());
    }

    @Test
    void getAmount0() {
        try {
            wallet.withdraw(wallet.getAmount());
        } catch (InvalidMoneyAmountException e) {
            fail();
        }
        assertEquals(0, wallet.getAmount());
    }
}
