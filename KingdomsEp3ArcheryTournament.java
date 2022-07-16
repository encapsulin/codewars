package codewars;

import java.util.*;
import java.util.stream.Collectors;

public class KingdomsEp3ArcheryTournament {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(KingdomsEp3ArcheryTournament.countAndSort(new String[]{
                "z*B", "*a*", "**z"})));// return [b, z, a]
    }

    public static String[] countAndSort(String[] target) {
        printArray(target);
        Map<Character, Arrow> map = new LinkedHashMap<>();

        int iRow = 0;
        int iRowScore = 0;

        int iTargetSizeDiv2 = target.length / 2 + 1;

        for (String sRow : target) {

            if (++iRow <= iTargetSizeDiv2)
                iRowScore++;
            else
                iRowScore--;

            int iCol = 0;
            int iColScore = 0;
            for (Character cCol : sRow.toCharArray()) {

                if (++iCol <= iTargetSizeDiv2)
                    iColScore++;
                else
                    iColScore--;

                int iCellScore = Math.min(iRowScore, iColScore);

                if (cCol.equals('*')) {
                    System.out.print(".  ");
                    continue;
                } else
                    System.out.print(cCol + "" + iCellScore + " ");


                Arrow arrow = map.get(Character.toLowerCase(cCol));
                if (arrow == null)
                    arrow = new Arrow();

                arrow.setChar(cCol, iCellScore);

                map.put(arrow.ch, arrow);

            }
            System.out.println();
        }

        List<Arrow> listChar = new ArrayList<>();
        map.entrySet().forEach(o -> {
            listChar.add(o.getValue());
        });

        return list2array(listChar);
    }

    public static String[] list2array(List<Arrow> listChar) {

        //System.out.println(listChar);
        Comparator<Arrow> cmp = Comparator
                .comparing(Arrow::getWeight)
                .thenComparing(Arrow::getCount, Comparator.reverseOrder())
                .thenComparing((a, b) -> Character.compare(a.ch, b.ch));
        listChar.sort(cmp);
        System.out.println(listChar);

        List<String> list = listChar.stream()
                .map(a -> a.ch.toString()).collect(Collectors.toList());

        String[] result = new String[list.size()];
        int i = 0;
        for (String s : list)
            result[i++] = s;

        printArray(result);
        return result;
    }

    public static class Arrow {
        Character ch = null;
        Integer iCount = 0;
        Integer iCountUpperCase = 0;
        Integer iWeight = 0;

        public void setChar(Character ch, Integer iWeight) {
            this.ch = ch;
            this.iCount++;
            this.iWeight += iWeight;

            if (Character.isUpperCase(ch)) {
                this.ch = Character.toLowerCase(ch);
                this.iCount++;
                this.iCountUpperCase++;
                this.iWeight += iWeight;
            }
        }

        public Integer getCount() {
            return iCount;
        }

        public Integer getCountUpper() {
            return iCountUpperCase;
        }

        public Integer getWeight() {
            return iWeight;
        }

        @Override
        public String toString() {
            return "{" +
                    ch +
                    ", iCount=" + iCount +
                    ", iCountUpper=" + iCountUpperCase +
                    ", iWeight=" + iWeight +
                    '}';
        }
    }

    public static void printArray(String[] args) {
        for (String s : args)
            System.out.print('"' + s + "\", ");
        System.out.println();
    }

}
