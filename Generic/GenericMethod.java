package Generic;

class Box<T> {
    private T ob;
    
    public T getOb() {
        return ob;
    }
    
    public void setOb(T ob) {
        this.ob = ob;
    }
}
public class GenericMethod {
    public static <T extends Number> void swapBox(Box<T> a, Box<T> b) {
        T aVal = a.getOb();
        T bVal = b.getOb();

        a.setOb(bVal);
        b.setOb(aVal);
    }
    public static void main(String[] args) {
        Box<Integer> abox = new Box<>();
        abox.setOb(99);

        Box<Integer> bbox = new Box<>();
        bbox.setOb(55);

        System.out.println(abox.getOb() + " & " + bbox.getOb());
        swapBox(abox, bbox);
        System.out.println(abox.getOb() + " & " + bbox.getOb());

    }
    
}
