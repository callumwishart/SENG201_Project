package gameplaytests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team32.exceptions.FullCartException;
import seng201.team32.models.gameplay.Cart;
import seng201.team32.models.resources.Resource;
import seng201.team32.models.resources.Wood;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private static Cart testCart;

    @BeforeEach
    void setup(){
        CartTest.testCart = new Cart(5, 1, new Wood());
    }

    @Test
    void testCart() throws FullCartException {
        assertEquals(new Wood(), testCart.getResource());
        assertEquals(0, testCart.getCargoSlotsFilled());
        testCart.fillCart(new Wood());
        assertEquals(1, testCart.getCargoSlotsFilled());
        for (int i = 0; i < 4; i++){
            testCart.fillCart(new Wood());
        }
        assertTrue(testCart.isFull());
        assertThrows(FullCartException.class, () -> testCart.fillCart(new Wood()));
        testCart.incrementDistance();
        assertEquals(1, testCart.getDistance());
        testCart.setFinished(true);
        assertTrue(testCart.isFinished());
    }

}
