public class Pair {
    StringBuilder string = new StringBuilder();
    int k;
    
    public Pair(int k, String str) {
        string.append(str);
        this.k = k;
    }
    
    public void inAdd(String b) {
        k++;
        string.append(" ");
        string.append(b);
    }
    
    public int intOut() {
        return k;
    }
    public String strOut() {
        return string.toString();
    }
    
    public String toString() {
        return Integer.toString(k) + " " + string.toString();
    }
    
}
