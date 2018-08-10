package greeting;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreetingsTest {
    private Greetings great;
    
    @Before
    public void setUP(){
        great = new Greetings();
    }

    @Test
    public void greeting() {
        assertArrayEquals(new String[]{""}, great.toString());
    }

    private void assertArrayEquals(String[] strings, String s) {
    }

    @Test
    public void greeting1() {
        assertFalse("false", false ,great.greeting(java.util.Optional.of("")));
    }

    private void assertFalse(String aFalse, boolean b, Greeting greeting) {
    }
}