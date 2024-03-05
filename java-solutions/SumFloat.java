public class SumFloat {
    public static void main(final String[] args) {
        float sum = 0;
        for (int i = 0; i < args.length; i++) {
            int firstnum = -1;
            int lastnum = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(args[i].charAt(j))) {
                    if (firstnum == -1) {
                        firstnum = j;
                    }
                    lastnum = j;
                } else{
                    if (firstnum != -1) {
                        sum += Float.parseFloat(args[i].substring(firstnum, lastnum + 1));
                        firstnum = -1;
                        lastnum = 0;
                    }
                }
            }
            if (firstnum != -1) {
                sum += Float.parseFloat(args[i].substring(firstnum, lastnum + 1));
            }
            firstnum = -1;
            lastnum = 0;
        }
        System.out.println(sum);
    }
}
