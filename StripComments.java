/*
https://www.codewars.com/kata/51c8e37cee245da6b40000bd
DESCRIPTION:
Complete the solution so that it strips all text that follows any of a set of comment markers passed in. Any whitespace at the end of the line should also be stripped out.

Example:

Given an input string of:

apples, pears # and bananas
grapes
bananas !apples
The output expected would be:

apples, pears
grapes
bananas
*/
package codewars;

import java.util.Arrays;

public class StripComments {
    public static String stripComments(String text, String[] commentSymbols) {
        System.out.println(text);
        System.out.println(Arrays.toString(commentSymbols));

        StringBuilder stringBuilder = new StringBuilder();
        boolean bComment = false;

        for(Character c: text.toCharArray()){
            boolean bIsCommentChar = Arrays.stream(commentSymbols).anyMatch(s->s.equals(c.toString()));
            boolean bIsEOL = c.equals('\n');
            if(bIsCommentChar)
                bComment = true;
            if(bIsEOL) {
                bComment = false;
                String str = rStrip(stringBuilder.toString());
                stringBuilder = new StringBuilder(str);
            }
            if(!bComment)
                stringBuilder.append(c);

        }

        return rStrip(stringBuilder.toString());
    }

    static String rStrip(String str){
        return str.replaceAll(" +$", "");
    }
}
