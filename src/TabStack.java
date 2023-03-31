import java.util.LinkedList;
import java.util.ListIterator;

public class TabStack<T>
{
    private LinkedList <T> list;

    public TabStack() { list = new LinkedList<T>(); }

    public void push(T s) { list.addLast(s); }

    public T pop() throws IllegalArgumentException
    {
        if(!list.isEmpty())
            return list.removeLast();
        else
            throw new IllegalArgumentException("Pobierasz z pustego stosu!");
    }

    public T top() throws IllegalArgumentException {
        if(list.getLast() == null) {
            throw new IllegalArgumentException("Stos jest pusty");
        }

        return list.getLast();
    }

    public boolean isEmpty() { return list.isEmpty(); }

    public int getSize() { return list.size(); }

    public T showValue(int i)
    {
        if(i < list.size() && i >= 0)
            return list.get(i);
        else
            throw new IllegalArgumentException("Niepoprawny indeks tablicy! Podano indeks: " + Integer.toString(i));
    }

    public String wyswietlElementy()
    {
        String str = "";
        ListIterator<T> iterator = list.listIterator(0);
        while(iterator.hasNext())
            str += iterator.next() + " ";
        return str;
    }

    public String toString() { return wyswietlElementy(); }

}
