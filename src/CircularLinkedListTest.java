import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CircularLinkedListTest {
    CircularLinkedList<Player> testPlayer = new CircularLinkedList<Player>();
    @Test
    public void getSize() {
        assertEquals(0,testPlayer.getSize());
        testPlayer.add(new Player("John"));
        testPlayer.add(new Player("Phil"));
        testPlayer.add(new Player("Dale"));
        assertEquals(3,testPlayer.getSize());
    }
    @Test
    public void get() {
        testPlayer.add(new Player("John"));
        testPlayer.add(new Player("Kat"));
        assertEquals("John",testPlayer.get(0).getName());
        assertThrows(IllegalArgumentException.class,
                () -> testPlayer.get(100));
    }
    @Test
    public void add() {
        testPlayer.add(new Player("katty"));
        assertEquals("katty",testPlayer.get(0).getName());
    }
    @Test
    public void remove() {
        //remove value:new player
        testPlayer.add(new Player("John"));
        testPlayer.add(new Player("Sky"));
        testPlayer.add(new Player("Nha"));
        testPlayer.remove(new Player("Sky"));
        assertEquals("Nha",testPlayer.get(1).getName());
    }
    @Test
    public void testRemove() {
        testPlayer.add(new Player("Jelly"));
        testPlayer.add(new Player("Octa"));
        testPlayer.add(new Player("Fishea"));
        testPlayer.remove(0);
        assertEquals("Octa",testPlayer.get(0).getName());
        assertThrows(IllegalArgumentException.class,
                () -> testPlayer.remove(-1));
        //remove given position
    }
    @Test
    public void iterator() {
        testPlayer.add(new Player("Jelly"));
        testPlayer.add(new Player("Octa"));
        testPlayer.add(new Player("Fishea"));
        Iterator<Player> myIterate = testPlayer.iterator();
        CircularLinkedList<Player> temp = new CircularLinkedList<>();
        Iterator<Player> tempIterate = temp.iterator();
        assertEquals("Jelly",myIterate.next().getName());
    }
}