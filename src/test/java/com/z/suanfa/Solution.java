package com.z.suanfa;

public class Solution {

    public static void main(String[] args) {
        int[][] demo = new int[30][40];
        for (int i = 0; i < demo.length; i++) {
            for (int j = 0; j < demo[i].length; j++) {
                demo[i][j] = shuweixiangjia(i) + shuweixiangjia(j);
            }
        }
        for (int i = 0; i < demo.length; i++) {
            for (int j = 0; j < demo[i].length; j++) {
                if (demo[i][j] < 12)
                    System.out.print("\033[30;4m" + String.format("%5s", demo[i][j] + "*") + "\033[0m");
                else
                    System.out.print(String.format("%5s", demo[i][j]));

            }
            System.out.println("\n");
        }
    }

    private static int shuweixiangjia(int num) {
        String nu = Integer.toString(num);
        char[] chars = nu.toCharArray();
        int res = 0;
        for (char aChar : chars) {
            int i = Integer.parseInt(String.valueOf(aChar));
            res += i;
        }
        return res;
    }

}
