/*DESCRIPTION:
        Lyrics...
        Pyramids are amazing! Both in architectural and mathematical sense. If you have a computer, you can mess with pyramids even if you are not in Egypt at the time. For example, let's consider the following problem. Imagine that you have a pyramid built of numbers, like this one here:

        /3/
        \7\ 4
        2 \4\ 6
        8 5 \9\ 3
        Here comes the task...
        Let's say that the 'slide down' is the maximum sum of consecutive numbers from the top to the bottom of the pyramid. As you can see, the longest 'slide down' is 3 + 7 + 4 + 9 = 23

        Your task is to write a function that takes a pyramid representation as argument and returns its' largest 'slide down'. For example:

        * With the input `[[3], [7, 4], [2, 4, 6], [8, 5, 9, 3]]`
        * Your function should return `23`.
        */

package codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PyramidsSlideDown2 {

    static int[][] pyramidD;

    static List<List<Node>> allCombinatios;

    static int MAX_RECURSION_LENGTH = 22;

    //faster, because of recursion limit
    public static int longestSlideDown(int[][] pyramid) {
        System.out.println(Arrays.deepToString(pyramid));
        pyramidD = pyramid;

        List<Node> bestCombination = new ArrayList<>();
        Node bestNode = new Node(0, pyramid[0][0]);
        bestCombination.add(bestNode);

        for(int i = 0; i< pyramid.length-1; i++) {
            long time1 = System.currentTimeMillis();

            allCombinatios = new ArrayList<>();
            allCombinationsForParentNode(i, bestNode.index, new ArrayList<>());

            System.out.println("allCombinationsForParentNode():"+(System.currentTimeMillis() - time1));
            time1 = System.currentTimeMillis();

            bestNode = getBestNodeFrom(allCombinatios);

            System.out.println("getBestNodeFrom():"+(System.currentTimeMillis() - time1));
            time1 = System.currentTimeMillis();

            bestCombination.add(bestNode);

            System.out.println(i + ") " + (System.currentTimeMillis() - time1));
        }

        return calcPathSum(bestCombination);
    }

    static void allCombinationsForParentNode(int row, int column, List<Node> path) {
        row++;
        if (row >= pyramidD.length || path.size() >= MAX_RECURSION_LENGTH) {
//            System.out.println(path);
            allCombinatios.add(path);
            return;//nodes;
        }

        Node nodeLeft = new Node(column, pyramidD[row][column]);
        List<Node> pathL = new ArrayList<>(path);
        pathL.add(nodeLeft);
        allCombinationsForParentNode(row, nodeLeft.index, pathL);

        Node nodeRight = new Node(column+1, pyramidD[row][column + 1]);
        List<Node> pathR = new ArrayList<>(path);
        pathR.add(nodeRight);
        allCombinationsForParentNode(row , nodeRight.index, pathR);

    }

    static class Node {
        int index = 0;
        int value = 0;

        public Node() {
            this.index = 0;
            this.value = 0;
        }

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "idx=" + index +
                    ", val=" + value +
                    '}';
        }
    }

    static int calcPathSum(List<Node> list){
//        System.out.println(list);
        return list.stream().mapToInt(n->n.value).sum();
    }

    private static Node getBestNodeFrom(List<List<Node>> allCombinatios) {
        int indexBest = 0;
        int index = 0;
        int maxSum = 0;
        for(List<Node> list: allCombinatios){
            int sum = calcPathSum(list);
            if(maxSum < sum){
                maxSum = sum;
                indexBest = index;
            }
            index++;
        }
        return allCombinatios.get(indexBest).get(0);
    }

    //very slow
    public static int longestSlideDown_AllCombinations(int[][] pyramid) {
        int sum = 0;
        int index = 0;
        for (int iRow = 0; iRow < pyramid.length; iRow++) {
            int max = 0;
            int maxIndex = 0;

            for (int jCol = 0; jCol < pyramid[iRow].length; jCol++) {
                if (jCol - index  >=0 && jCol - index <= 1 && max < pyramid[iRow][jCol]) {
                    max = pyramid[iRow][jCol];
                    maxIndex = jCol;
                }
                System.out.print(pyramid[iRow][jCol] + " ");
            }
            System.out.println(":"+max);
            index = maxIndex;
            sum += max;
        }
        return sum;
    }
}
