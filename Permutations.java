/*
https://www.codewars.com/kata/5254ca2719453dcc0b00027d/java
DESCRIPTION:
In this kata you have to create all permutations of a non empty input string and remove duplicates, if present. 
This means, you have to shuffle all letters from the input in all possible orders.
/*

package codewars;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Permutations {

    static Set<String> set = new HashSet<>();
    public static List<String> singlePermutations(String s) {
        set = new HashSet<>();
        System.out.println(s);
        solve(s);
        List<String> list = set.stream().collect(Collectors.toList());
        return list;
    }

    public static void solve(String str_){
        set.add(str_);

        for(int i=0; i< str_.length()-1; i++){
            byte[] str_array = str_.getBytes();

            byte tmp = str_array[i];
            str_array[i] = str_array[i+1];
            str_array[i+1] = tmp;

            String s = new String(str_array);
            if(set.add(s))
                solve(s);
        }
    }
}
