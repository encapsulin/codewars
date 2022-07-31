/*
DESCRIPTION:
Snail Sort
Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.

array = [[1,2,3],
         [4,5,6],
         [7,8,9]]
snail(array) #=> [1,2,3,6,9,8,7,4,5]
For better understanding, please follow the numbers of the next array consecutively:

array = [[1,2,3],
         [8,9,4],
         [7,6,5]]
snail(array) #=> [1,2,3,4,5,6,7,8,9]

NOTE: The idea is not sort the elements from the lowest value to the highest; the idea is to traverse the 2-d array in a clockwise snailshell pattern.

NOTE 2: The 0x0 (empty matrix) is represented as en empty array inside an array [[]].
 */
package codewars;

import java.util.ArrayList;

public class Snail {

    public static int[] snail(int[][] array) {
        int arrayLength = array.length;

        ArrayList<Integer> listResult = new ArrayList<>();
        if(array[0].length == 0)
            return list2array(listResult);

        int directionInc = 1;

        int iMax = arrayLength - 1;
        int iMin = 0;
        int iDirection = directionInc;

        int jMax = arrayLength - 1;
        int jMin = 1;
        int jDirection = directionInc;

        int iColumn = 0;
        int iRow = 0;

        while (iMax >= iMin || jMax >= jMin) {

            if (iDirection == directionInc) {
                while (iColumn <= iMax) {
                    listResult.add(array[iRow][iColumn]);
                    iColumn++;
                }
                iMax--;
                iColumn--;
                iRow++;
            } else {
                while (iColumn >= iMin) {
                    listResult.add(array[iRow][iColumn]);
                    iColumn--;
                }
                iMin++;
                iColumn++;
                iRow--;
            }
            iDirection = -iDirection;

            //////////////////////////////////
            if (jDirection == directionInc) {
                while (iRow <= jMax) {
                    listResult.add(array[iRow][iColumn]);
                    iRow++;
                }
                jMax--;
                iRow--;
                iColumn--;
            } else {
                while (iRow >= jMin) {
                    listResult.add(array[iRow][iColumn]);
                    iRow--;
                }
                jMin++;
                iRow++;
                iColumn++;
            }
            jDirection = -jDirection;

        }

        return list2array(listResult);
    }

    static int[] list2array(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            array[i] = list.get(i);
        return array;
    }

}
