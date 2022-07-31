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