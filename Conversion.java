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
