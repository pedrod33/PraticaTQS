package ua.tqs.stack_proj;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class TqsStackTest 
{

    private LinkedList<String> baseStack = new LinkedList<>();
    private LinkedList<String> filledStack = new LinkedList<>();
    private LinkedList<String> limitedStack = new LinkedList<>();

    private TqsStack baseObj = new TqsStack(baseStack);
    private TqsStack filledObj = new TqsStack(filledStack);
    private TqsStack limitedObj = new TqsStack(limitedStack,2);
    @org.junit.jupiter.api.BeforeEach
    public void init(){
        filledObj.push("valor1");
        filledObj.push("valor2");

        limitedObj.push("valor1");
        limitedObj.push("valor2");
        limitedObj.push("valor3");
        limitedObj.push("valor4");
    }

    @org.junit.jupiter.api.AfterEach
    public void teardown(){
        
    }

    @Test
    public void isEmpty(){
        assertTrue(baseObj.isEmpty());
    }

    @Test
    public void pop(){
        assertEquals("valor2",filledObj.pop());
        assertEquals(1,filledObj.size());
    }

    @Test
    public void popEmpty(){
        assertThrows(NoSuchElementException.class, () -> {
            baseObj.pop();
        });
    }

    @Test 
    public void push2elem(){
        assertEquals(2,filledObj.size());
    }

    @Test
    public void peek(){
        assertEquals("valor2",filledObj.peek());
        assertEquals(2,filledObj.size());
    }

    @Test
    public void peekEmpty(){
        assertThrows(NoSuchElementException.class, () -> {baseObj.pop();});
    }

    @Test
    public void pushLimit(){
        assertEquals(2,limitedObj.size());
        assertEquals(2,filledObj.size());

    }
}
