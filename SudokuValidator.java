package codewars;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuValidator {

    static Set<Integer> aSet = new HashSet<>();

    public static boolean check(int[][] sudoku) {
        return checkRows(sudoku)
                && checkColumns(sudoku)
                && checkCells3x3(sudoku);
    }

    public static void setInit() {
        IntStream.range(1, 10).forEach(i -> aSet.add(i));
    }

    public static boolean checkRows(int[][] sudoku) {

        for (int i = 0; i < sudoku.length; i++) {
            setInit();
            for (int j = 0; j < sudoku.length; j++)
                aSet.remove(sudoku[i][j]);
            if (!aSet.isEmpty())
                return false;
        }

        return true;
    }

    public static boolean checkColumns(int[][] sudoku) {

        for (int i = 0; i < sudoku.length; i++) {
            setInit();
            for (int j = 0; j < sudoku.length; j++)
                aSet.remove(sudoku[j][i]);
            if (!aSet.isEmpty())
                return false;
        }

        return true;
    }


    public static boolean checkCells3x3(int[][] sudoku) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!checkCells3x3_ixj(i, j, sudoku))
                    return false;
            }
        }
        return true;
    }

    public static boolean checkCells3x3_ixj(int i_offset, int j_offset, int[][] sudoku) {
        setInit();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                aSet.remove(sudoku[3 * i_offset + i][3 * j_offset + j]);
            }
        }
        return aSet.isEmpty();
    }

}