package codewars;

import java.util.*;

public class ClosestWeight {

    public static void main(String... args) {
        System.out.println(new ClosestWeight().getClass().getSimpleName());
        String s = "103 123 4444 99 2000";
        String r = "";
//        s = "";
        s = "403749 18 278325 97 304194 119 58359 165 144403 128 38";
        r = "[[11, 5, 119], [11, 9, 128]]";

//        s = "22 22 12 12 10 10 0 0";
//        r = "[[0, 6, 0], [[0, 7, ]0]]";
        System.out.println("s:" + s);
        System.out.println("expected:" + r);
        System.out.println("actual:" + Arrays.deepToString(closest(s)));

    }

    public static int weight(String s) {

        int sum = s.chars().map(i->i-'0').sum();
        sum = 0;
        for (Character c : s.toCharArray())
            sum += c - '0';
        return sum;
    }


    public static int[][] closest(String strng) {

        List<Entry> list = new ArrayList<>();
        int i = 0;
        for (String s : strng.split(" ")) {
            list.add(new Entry(s, i++, weight(s)));
        }

        list.sort((l1, l2) -> l1.weight - l2.weight);

        int mindiff = Integer.MAX_VALUE;
        for ( i = 0; i < list.size() - 1; i++) {
            int diff = list.get(i + 1).weight - list.get(i).weight;
            mindiff = (mindiff > diff) ? diff: mindiff;
        }

        for ( i = 0; i < list.size() - 1; i++) {
            int diff = list.get(i + 1).weight - list.get(i).weight;
            if (mindiff == diff) {
                int[][] intReturn = new int[2][3];
                intReturn[0][0] = list.get(i).weight;
                intReturn[0][1] = list.get(i).index;
                intReturn[0][2] = list.get(i).i;
//                System.out.println(intReturn[0]);

                intReturn[1][0] = list.get(i+1).weight;
                intReturn[1][1] = list.get(i+1).index;
                intReturn[1][2] = list.get(i+1).i;
                return intReturn;
            }
        }
        return new int[][]{};
    }

    static class Entry {
        //        String s;
        int i;
        int weight;
        int index;

        public Entry(String s, int i, int weight) {
            this.i = Integer.parseInt(s);
            this.weight = weight;
            this.index = i;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "i=" + i +
                    ", weight=" + weight +
                    ", index=" + index +
                    '}';
        }
    }
}
