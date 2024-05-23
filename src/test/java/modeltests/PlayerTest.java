package modeltests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import seng201.team32.exceptions.NameCharException;
import seng201.team32.models.Player;
import seng201.team32.models.PlayerInventory;

public class PlayerTest {

    @Test
    void testPlayer(){
        Player player = new Player();
        // Test name checking
        assertFalse(player.checkName(""));
        assertFalse(player.checkName("1"));
        assertFalse(player.checkName("12"));
        assertFalse(player.checkName("!"));
        assertFalse(player.checkName("!@#$"));
        assertFalse(player.checkName("hello!"));
        assertFalse(player.checkName("123456789101112131415"));

        assertTrue(player.checkName("TheoCallum"));
        assertTrue(player.checkName("Theo1"));
        assertTrue(player.checkName("Callum2"));

        String testName = "TestName";
        player.setName(testName);
        assertEquals(testName, player.getName());
        assertInstanceOf(PlayerInventory.class, player.getInventory());



    }

}
