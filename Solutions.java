import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solutions {

    String str = "{\n"
            + "    Money Heist Info\n"
            + "    {\n"
            + "        The most important character is the Professor\n"
            + "    }\n"
            + "    {\n"
            + "        Another character is that of Berlin\n"
            + "        {\n"
            + "            Berlin is in charge of the money heist\n"
            + "        }\n"
            + "    }\n"
            + "    {\n"
            + "        Another character is that of Moscow\n"
            + "        {\n"
            + "            Moscow is Denver's dad\n"
            + "        }\n"
            + "    }\n"
            + "    {\n"
            + "        Another character is that of Rio\n"
            + "        {\n"
            + "            Rio is a programmer\n"
            + "            {\n"
            + "                Rio is also a decent hacker\n"
            + "                {\n"
            + "                    Rio is quite happy to be a part of the heist\n"
            + "                }\n"
            + "            }\n"
            + "        }\n"
            + "    }\n"
            + "    {\n"
            + "        Another character is that of Denver\n"
            + "    }\n"
            + "}";

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int m1;
        int m2;
        double resultRes;
        int length = result.length;

        for (int i = 0; i < nums1.length; i++) {
            result[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            result[nums1.length + i] = nums2[i];
        }

        Arrays.sort(result);

        if (result.length % 2 == 0) {
            m1 = result[length / 2 - 1];
            System.out.println(length / 2);
            m2 = result[length / 2];
            resultRes = (m1 + m2) / 2;

        } else {
            resultRes = result[length / 2];
        }

        return resultRes;

    }

    public static String longestPalindrome(String s) {
        if(s == null || s.length()==0) return "";
        if(s.equals(new StringBuilder(s).reverse().toString())) return s;
        char[] arr = s.toCharArray();
        char letter;
        String res = String.valueOf(s.charAt(0));
        if(s.length() >= 2) {


            for (int i = 0; i < arr.length; i++) {
                letter = arr[i];
                StringBuilder cs = new StringBuilder();
                for (int x = i + 1; x < arr.length; x++) {
                    String temp;
                    if (letter == arr[x]) {
                        temp = s.substring(i, x + 1);

                        cs = cs.append(temp).reverse();
                        if (temp.equals(new StringBuilder(temp).reverse().toString()) && res.length() < temp.length()) {
                            res = temp;
                            cs.setLength(0);
                        }

                    }
                }
            }
        }
        return res;

    }

    public static String convert(String s, int numRows) {
        String ans="";
        if(numRows==1) return s;
        for (int i=0;i<numRows;i++) {
            int incr=2*(numRows-1);
            for (int j=i;j<s.length();j+=incr) {
                ans+=s.charAt(j);
                if(i>0 && i<numRows-1 && j+incr-(2*i)<s.length()) ans+=s.charAt(j+incr-(2*i));
            }
        }
        return ans;
    }

    public static int reverse(int x){
        String temp = String.valueOf(x);
        StringBuilder str;
        int result = 0;
        if(!Character.isDigit(temp.charAt(0))){
            char first = temp.charAt(0);
            str =  new StringBuilder(temp.substring(1));
            str.append(first);
            str = new StringBuilder(str).reverse();
        }else {
            str = new StringBuilder(temp).reverse();
        }

        try{
            result = Integer.parseInt(str.toString());
        }
        catch (NumberFormatException e){
            return result;
        }

        return result;
    }

    public static Map<Character, Integer> countLetters(String word){

        if(word == null){
            return null;
        }
        char[] arr = word.toCharArray();
        Map<Character,Integer> counting = new HashMap<>();
        for(int x = 0; x<arr.length;x++){
            int count = counting.getOrDefault(arr[x],0);
            counting.put(arr[x], ++count);
        }

        return counting;
    }

    public static int[][] rotate(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for(int i = first; i < last; ++i) {
                int offset = i - first;
                int top = matrix[first][i]; // save top
                // left -> top
                matrix[first][i] = matrix[last-offset][first];

                // bottom -> left
                matrix[last-offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
        return matrix;
    }

    public static int getMax(int a, int b) {
        int c = a - b;
        int k = (c >> 31) & 0x1;
        int max = a - k * c;
        return max;
    }

    public static String restoreString(String s, int[] indices) {
        char[] arr = s.toCharArray();
        char[] result = new char[arr.length];
        for(int x = 0; x<indices.length; x++){
            result[indices[x]] = arr[x];
        }

        return String.valueOf(result);
    }

    public static int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        int n = points.length;
        for(int i=1;i<n;i++){
            int[] cur = points[i];
            int[] prev = points[i-1];
            int curVal = Math.max(Math.abs(cur[0] - prev[0]), Math.abs(cur[1] - prev[1]));
            ans += curVal;
        }
        return ans;
    }

    public static String longestPalindrome2(String s){
        if(s.length() <2) return s;
        char[] arr = s.toCharArray();
        Map<Character, Integer> chars = new HashMap<>();
        StringBuilder str;
        StringBuilder result = new StringBuilder(s.charAt(0));
        for(int x = 0; x<arr.length; x++){
            if(chars.containsKey(arr[x])){
                str = new StringBuilder(s.substring(chars.get(arr[x]),x+1));
                result = str.reverse();
                if(str.equals(result)) return result.toString();
            }
            chars.put(arr[x],x);

        }

        return result.toString();
    }

    public static int[] solution(String transfersTo, int[] transValues) {
        char[] bankLetters = transfersTo.toCharArray();
        int initialBalanceForBankA = 0;
        int initialBalanceForBankB = 0;
        int currentBalanceForBankA = 0;
        int currentBalanceForBankB = 0;
        int[] result = new int[2];

        for (int x = 0; x < bankLetters.length; x++) {
            //this means transfer B -> A
            if (bankLetters[x] == 'A') {
                //add current balance A and remove current balance of B
                currentBalanceForBankA += transValues[x];
                currentBalanceForBankB -= transValues[x];
                if (currentBalanceForBankB < 0) {
                    initialBalanceForBankB += Math.abs(currentBalanceForBankB);
                    currentBalanceForBankB = 0;

                }
            }
            //this means transfer A -> B
            if (bankLetters[x] == 'B') {
                //add current balance A and remove current balance of B
                currentBalanceForBankA -= transValues[x];
                currentBalanceForBankB += transValues[x];
                if (currentBalanceForBankA < 0) {
                    initialBalanceForBankA += Math.abs(currentBalanceForBankA);
                    currentBalanceForBankA = 0;

                }
            }

        }

        result[0] = initialBalanceForBankA;
        result[1] = initialBalanceForBankB;

        return result;
    }

    public static List<String> layeredString(String string, int layer) {

        String[] arr = string.split("\\n");
        List<String> result = new ArrayList<>();

        int level = 0;
        for (int x = 0; x < arr.length - 1; x++) {
            if (arr[x].trim().equals("{")) {
                level++;
                if (level == layer && !arr[x + 1].trim().equals("{") && !arr[x + 1].trim().equals("}"))
                    result.add(arr[x+1].trim());
            }
            if (arr[x].trim().equals("}"))
                level--;

        }
        return result;
    }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        final Set<String> dictionary = new HashSet<>(Arrays.asList(words));
        final List<String> answer = new ArrayList<>();
        for (final String word : words) {
            final int length = word.length();
            final boolean[] dp = new boolean[length + 1];
            dp[0] = true;
            for (int i = 1; i <= length; ++i) {
                for (int j = (i == length ? 1 : 0); !dp[i] && j < i; ++j) {
                    dp[i] = dp[j] && dictionary.contains(word.substring(j, i));
                }
            }
            if (dp[length]) {
                answer.add(word);
            }
        }
        return answer;
    }

    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i=0; i<heights.length-1; i++) {
            if (heights[i] >= heights[i+1]) continue;
            bricks -= heights[i+1] - heights[i];
            pq.add(heights[i+1] - heights[i]);

            if (bricks < 0) {
                bricks += pq.poll();
                if (ladders > 0) ladders--;
                else return i;
            }
        }

        return heights.length - 1;
    }

    static void selectionSort(int[] arr){
        for(int x = 0; x<arr.length; x++){
            int smallIndex = x;
            int temp;

            for(int j = x+1; j<arr.length; j++){
                if(arr[j] < arr[smallIndex]) {
                    smallIndex = j;
                }
            }
            temp = arr[x];
            arr[x] = arr[smallIndex];
            arr[smallIndex] = temp;
        }

    }

    static void insertionSort(int[] arr){

        for(int x = 1; x<arr.length; x++ ){
            for(int j = x; j>0 && arr[j] < arr[j-1]; j--){
                int temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;


            }

        }




    }
}

/*
You are given a list of N transfers (numbered from 0 to N−1)
        Result array should be returned as an array of integers.

        Examples:

        1. Given R = "BAABA" and V = [2, 4, 1, 1, 2], the function should return [2, 4]. The bank accounts’ balances after each transfer are shown in the following table:


                               | A | B
        ------------------------+---+---
        initial balance        | 2 | 4
        transfer 2 from A to B | 0 | 6
        transfer 4 from B to A | 4 | 2
        transfer 1 from B to A | 5 | 1
        transfer 1 from A to B | 4 | 2
        transfer 2 from B to A | 6 | 0

        2. Given R = "ABAB" and V = [10, 5, 10, 15], the function should return [0, 15].

        3. Given R = "B" and V = [100], the function should return [100, 0].

        Write an efficient algorithm for the following assumptions:

        string R and array V are both of length N;
        N is an integer within the range [1..100,000];
        each element of array V is an integer within the range [1..10,000];
        string R consists only of the characters "A" and/or "B".
*/