import static org.junit.Assert.*;


public class Test {

    TabStack<String> stos = new TabStack<String>();

    @org.junit.Test
    public void testNieuzywanyStos1() throws Exception{
        assertTrue(stos.isEmpty());
    }

    @org.junit.Test(expected = Exception.class)
    public void testNieuzywanyStos2() throws Exception{
        stos.pop();
    }

    @org.junit.Test(expected = Exception.class)
    public void testNieuzywanyStos3() throws Exception{
        stos.top();
    }

    @org.junit.Test
    public void testPush() throws Exception {
        String napis = "napis";
        stos.push(napis);
        assertSame(napis, stos.top());
        assertSame(napis, stos.top());
        assertSame(napis, stos.top());
        assertSame(napis, stos.top());
        assertFalse(stos.isEmpty());

    }

    @org.junit.Test
    public void testPop() throws Exception {
        String napis = "napis";
        stos.push(napis);
        assertSame(napis, stos.pop());
        assertTrue(stos.isEmpty());
    }

    @org.junit.Test
    public void testPop2() throws Exception {
        String napis1 = "napis1";
        String napis2 = "napis2";
        String napis3 = "napis3";
        stos.push(napis1);
        stos.push(napis2);
        stos.push(napis3);
        assertSame(stos.pop(), napis3);
        assertSame(stos.pop(), napis2);
        assertSame(stos.pop(), napis1);
        assertTrue(stos.isEmpty());
    }

    @org.junit.Test
    public void testWstawNull() throws Exception {
        String napis = null;
        stos.push(napis);
        assertNull(stos.pop());
    }

    @org.junit.Test
    public void testStosPoWyjatku() throws Exception {
        String napis = "napis";
        try {
            stos.top();
        }catch (Exception e){ }
        stos.push(napis);
        assertEquals(napis,stos.top());
    }

    @org.junit.Test
    public void testObliczenia() throws Exception {
        ONP onp = new ONP();
        assertTrue(9.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("2+17-10="))));
        assertTrue(21.5 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("5*4+3/2="))));
        assertTrue(114.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("(5+(4*3)+2)*6="))));
    }
    @org.junit.Test
    public void testObliczeniaDodawanie() throws Exception {
        ONP onp = new ONP();
        assertTrue(9.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("2+7="))));
    }
    @org.junit.Test
    public void testObliczeniaPdejmowanie() throws Exception {
        ONP onp = new ONP();
        assertTrue(30.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("40-10="))));
    }
    @org.junit.Test
    public void testObliczeniaMnozenie() throws Exception {
        ONP onp = new ONP();
        assertTrue(12.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("4*3="))));

    }
    @org.junit.Test
    public void testObliczeniaDzielenie() throws Exception {
        ONP onp = new ONP();
        assertTrue(3.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("24/8="))));

    }

    @org.junit.Test
    public void testObliczeniaModulo() throws Exception {
        ONP onp = new ONP();
        assertTrue(2.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("20%9="))));
    }

    @org.junit.Test
    public void testObliczeniaPotegowanie() throws Exception {
        ONP onp = new ONP();
        assertTrue(64.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("4^3="))));

    }

    @org.junit.Test
    public void testObliczeniaPierwiastkowanie() throws Exception {
        ONP onp = new ONP();
        assertTrue(9.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("2p81="))));

    }

    @org.junit.Test
    public void testObliczeniaSilnia() throws Exception {
        ONP onp = new ONP();
        assertTrue(120.0 == Double.parseDouble(onp.obliczONP(onp.przeksztalcNaOnp("5!="))));

    }


}