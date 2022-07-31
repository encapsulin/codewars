/*
DESCRIPTION:
Alice and Bob are playing a game. There are n(1≤n≤1018)n (1 \leq n \leq 10^{18})n(1≤n≤10 
18
 ) cards on the table. The aim of the game is to collect the most cards. The rules:

if the number of cards is even, the players can either take half of the cards from the stack, or only 1 card -- as they choose;
if the number of cards is odd, the players must take 1 card.
Alice starts the game.

Return the maximum number of cards Alice can collect, if Bob plays optimally (tries to get as many cards as possible for himself).

Example
When there are 10 cards, Alice can collect maximum 8, like this:

1. Alice takes half of the 10 cards     (now has: 5)          -->  5 cards left
2. Bob must take 1 card                 (now has: 1)          -->  4 cards left
3. Alice takes half of the 4 cards      (now has: 5 + 2 = 7)  -->  2 cards left
4. Bob takes 1 (or half of the 2 cards) (now has: 1 + 1 = 2)  -->  1 card left
5. Alice takes the last card            (now has: 7 + 1 = 8)  -->  game over!

Thus, Alice has 8 cards and Bob has 2
*/

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
