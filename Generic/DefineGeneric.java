package Generic;

class DBox<L, R>{
    private L l;
    private R r;

    DBox(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public String toString() {
        return l + " & " + r;
    }

}  
public class DefineGeneric {
    public static void main(String[] args) {
        DBox<String, Integer> abox = new DBox<>("Apple", 25);
        DBox<String, Integer> obox = new DBox<>("Orange", 13);

        DBox<DBox<String, Integer>, DBox<String, Integer>> ddbox = new DBox<>(abox, obox);
        System.out.println(ddbox);
        
        
    }
    
}
