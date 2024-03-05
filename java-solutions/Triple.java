import java.util.ArrayList;
import java.util.List;

public class Triple {
    List<Integer> stringNum = new ArrayList<>();
    List<Integer> inStrNum = new ArrayList<>();
    int k;
    
    public Triple(int k, int strN, int inStrN) {
        stringNum.add(strN);
        inStrNum.add(inStrN);
        this.k = k;
    }
    
    public void inAdd(int ad1, int ad2) {
        k++;
        stringNum.add(ad1);
        inStrNum.add(ad2);
    }
    
    public String pair(int i) {
        return stringNum.get(i) + ":" + inStrNum.get(i);
    }
    
    public int count() {
        return k;
    }
    
    public int sLength() {
        return stringNum.size();
    }
}
