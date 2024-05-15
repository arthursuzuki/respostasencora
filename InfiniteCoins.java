import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InfiniteCoins implements SetInterface<Element> {
    private List<Element> list = new ArrayList<>();

    /**
    * Adds an element to the set if it's not already present.
    * 
    * @param e the element to add
    * @return true if this set did not already contain the specified element
    */
    @Override
    public boolean add(Element e) {
        if (contains(e)) {
            return false;
        } else {
            list.add(e);
            return true;
        }
    }

    /**
    * Adds all of the elements in the specified set to this set if they're not already present.
    * 
    * @param s set containing elements to be added to this set
    * @return true if this set changed as a result of the call
    */
    @Override
    public boolean addAll(SetInterface<Element> s) {
        boolean changed = false;
        for (Element elem : s.toArray()) {
            if (!contains(elem)) {
                list.add(elem);
                changed = true;
            }
        }
        return changed;
    }

    /**
    * Returns true if this set contains the specified element.
    * 
    * @param e element whose presence in this set is to be tested
    * @return true if this set contains the specified element
    */
    @Override
    public boolean contains(Element e) {
        return list.contains(e);
    }

    /**
    * Compares the specified set with this set for equality.
    * 
    * @param s the set to be compared for equality with this set
    * @return true if the specified set is equal to this set
    */
    @Override
    public boolean equals(SetInterface<Element> s) {
        if (size() != s.size()) {
            return false;
        }
        for (Element elem : s.toArray()) {
            if (!contains(elem)) {
                return false;
            }
        }
        for (Element elem : toArray()) {
            if (!s.contains(elem)) {
                return false;
            }
        }
        return true;
    }

    /**
    * Returns an iterator over the elements in this set.
    * 
    * @return an iterator over the elements in this set
    */
    @Override
    public java.util.Iterator<Element> iterator() {
        return list.iterator();
    }

    /**
    * Removes the specified element from this set if it is present.
    * 
    * @param e object to be removed from this set, if present
    * @return true if this set contained the specified element
    */
    @Override
    public boolean remove(Element e) {
        return list.remove(e);
    }

    /**
    * Returns the number of elements in this set.
    * 
    * @return the number of elements in this set
    */
    @Override
    public int size() {
        return list.size();
    }

    /**
    * Returns an array containing all of the elements in this set.
    * 
    * @return an array containing all of the elements in this set
    */
    @SuppressWarnings("unchecked")
    @Override
    public Element[] toArray() {
        return (Element[]) list.toArray(new Element[0]);
    }

    /**
    * Calculates all the possible combinations of quarters, dimes, nickels, and pennies to represent the given value in cents.
    * 
    * @param n the value in cents to represent
    * @return a list of arrays representing the combinations of coins
    */
    public static List<int[]> makeChange(int n) {
        List<int[]> result = new ArrayList<>();
        makeChangeHelper(n, 0, 0, 0, result);
        return result;
    }

    /**
    * Helper method to calculate all the possible combinations of quarters, dimes, nickels, and pennies.
    * 
    * @param n the value in cents to represent
    * @param quarters the number of quarters used in the current combination
    * @param dimes the number of dimes used in the current combination
    * @param nickels the number of nickels used in the current combination
    * @param result the list to store the combinations
    */
    private static void makeChangeHelper(int n, int quarters, int dimes, int nickels, List<int[]> result) {
        if (n == 0) {
            result.add(new int[]{quarters, dimes, nickels, 0});
            return;
        }

        for (int q = 0; q <= n / 25; q++) {
            for (int d = 0; d <= (n - q * 25) / 10; d++) {
                for (int ni = 0; ni <= (n - q * 25 - d * 10) / 5; ni++) {
                    int p = n - q * 25 - d * 10 - ni * 5;
                    result.add(new int[]{q, d, ni, p});
                }
            }
        }
    }

    /**
    * Main method to test the makeChange function.
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o valor: ");
        int n = scanner.nextInt();
        List<int[]> result = makeChange(n);
        for (int[] coins : result) {
            System.out.println(java.util.Arrays.toString(coins));
        }
        scanner.close();
    }
}

class Element {
    private int value;

    /**
    * Constructs an element with the given value.
    * 
    * @param value the value of the element
    */
    public Element(int value) {
        this.value = value;
    }

    /**
    * Returns the value of the element.
    * 
    * @return the value of the element
    */
    public int getValue() {
        return value;
    }

    /**
    * Compares this element to the specified object.
    * 
    * @param obj the object to compare
    * @return true if the specified object is equal to this element
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Element element = (Element) obj;
        return value == element.value;
    }

    /**
    * Returns the hash code value for this element.
    * 
    * @return the hash code value for this element
    */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

/**
* Interface.
*/
interface SetInterface<T> {
    boolean add(T element);
    boolean addAll(SetInterface<T> elements);
    boolean contains(T element);
    boolean equals(SetInterface<T> s);
    java.util.Iterator<T> iterator();
    boolean remove(T element);
    int size();
    T[] toArray();
}
