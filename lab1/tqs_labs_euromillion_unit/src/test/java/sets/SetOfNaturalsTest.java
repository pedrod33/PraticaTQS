/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;
    private SetOfNaturals setF;
    
    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
        setF = SetOfNaturals.fromArray(new int[]{1, 2, 3, 4});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
        
    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
        assertThrows(IllegalArgumentException.class, () -> setB.add(10));

    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");
        assertFalse(setB.intersects(setF));
    }

    @Test
    public void testAllPositive(){
        boolean res = true;
        Iterator<Integer> it = setD.iterator();
        int sz = setD.size();
        for (int i = 0 ; i < sz ; i++){
            if(it.next()<=0){
                res=false;
            }
        }
        assertTrue(res);
    }
    
    @Test
    public void testAllDiferent(){
        int sz = setD.size();
        Iterator<Integer> it = setD.iterator();
        Set<Integer> st = new HashSet<Integer>();
        for (int i = 0 ; i < sz ; i++){
            st.add(it.next());
        }
        assertEquals(sz, st.size());
    }

    @Test
    public void testFromArray(){
        int[] elems = new int[]{10, 20, -30};
        int[] elems2 = new int[]{1, 2, -3};
        int[] elemsrep = new int[]{10, 20, 10};
        assertThrows(IllegalArgumentException.class, () -> setA = SetOfNaturals.fromArray(elems));
        assertThrows(IllegalArgumentException.class, () -> setA = SetOfNaturals.fromArray(elems2));
        assertThrows(IllegalArgumentException.class, () -> setA = SetOfNaturals.fromArray(elemsrep));
    }

    @Test
    public void testIntersectWithIntersection(){
        assertTrue(setB.intersects(setD));
        assertTrue(setC.intersects(setB));
    }


    @Test
    public void testContains(){
        assertTrue(setB.contains(10));
        assertFalse(setB.contains(11));
    }

    @Test
    public void testContainsFalse(){
        assertFalse(setA.contains(10));
        assertFalse(setB.contains(3));        
    }
}
