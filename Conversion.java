/*
DESCRIPTION:
Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral representation of that integer.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.
*/

public class Conversion {

   public static String solution(int n) {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        do {
            sb.append(int2roman(n % 10, level++));
            n /= 10;
        } while (n >= 1);

        return sb.reverse().toString();
    }

  
    public static String int2roman(int n, int level) {
        StringBuilder sb = new StringBuilder();
        Character[][] map = {
            {'I', 'V', 'X'}
            , {'X', 'L', 'C'}
            , {'C', 'D', 'M'}
            , {'M'}
        };
      
        if (n <= 3) {
            while (n-- > 0)
                sb.append(map[level][0]);
        } else if (n == 4) {
            sb.append(map[level][1]);
            sb.append(map[level][0]);
        } else if (n <= 8) {
            while (n-- - 5 > 0)
                sb.append(map[level][0]);
            sb.append(map[level][1]);
        } else if (n == 9) {
            sb.append(map[level][2]);
            sb.append(map[level][0]);
        }
        return sb.toString();
    }
  
}
