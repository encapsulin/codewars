import java.util.*;

public class Autodigigram {
  
   static Map<Integer, Integer> map ;

    public static boolean validate(int base, int width, int[] list) {

        step1Count(base, width, list);

        for (int i = 0; i < list.length; i++) {
            if (step2Match(i, list[i]) == false)
                return false;
        }
        return true;
    }

    private static void step1Count(int base, int width, int[] list) {
        map = new HashMap<>();
        for (Integer iList : list) {
            String sInt = adjustWidth(iList, base, width);
            for (Character c : sInt.toCharArray()) {
                Integer i = Integer.valueOf(String.valueOf(c), base);
                Integer count = map.get(i);
                map.put(i, (count == null) ? 1 : count + 1);
            }
        }
    }

    private static String adjustWidth(Integer iList, int base, int width) {
        String s = Integer.toString(iList, base);
        int diff = width - s.length();
        while(diff-- >0)
            s = "0" + s;
        return s;
    }

    private static boolean step2Match(int i, int listI) {
        Integer iMap = map.get(Integer.valueOf(i));
        if (iMap != null && iMap != listI)
            return false;

        return true;
    }
}
