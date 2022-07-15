package codewars;

public class CardGame {

    public static void main(String... args) {
        System.out.println(new CardGame().getClass().getSimpleName());

        System.out.println(cardGame(12L));//9
        System.out.println(cardGame(8589934590L));//8589934558
        System.out.println(cardGame(966634542008741760L));// exp:18148209712745445
        //in:243216925379399104 exp:243216925379399030
    }

    public static long cardGame(long n) {
        long iAliceSum = 0;
        boolean bAlice = true;

        while (n > 0) {

            long iDecrement = 1;
            if (n % 2 == 0 && ((long) n / 2) % 2 != 0 || n == 4)  {
                iDecrement = (long) n / 2;
            }

            n -= iDecrement;

            if (bAlice)
                iAliceSum+= iDecrement;

            bAlice = !bAlice;
        }

        return iAliceSum;
    }

}
