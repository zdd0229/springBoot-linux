package com.z.suanfa;

import java.util.Arrays;

public class MainTest {

    public static void main(String[] args) {
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
        };

        char[][] board2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "AAB";
        String word2 = "ABCESEEEFS";

        MainTest solution = new MainTest();
        boolean exist = solution.exist(board2, word2);

        System.out.println(exist);
    }

    public boolean exist(char[][] board, String word) {

        char[] words = word.toCharArray();
        int[][] path = new int[word.length()][2];
        for (int k = 0; k < path.length; k++) {
            Arrays.fill(path[k], -1);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {


                if (dfs(path, i, j, words, board, 0)) {
                    for (int[] ints : path) {
                        System.out.println(Arrays.toString(ints));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int[][] path, int i, int j, char[] words, char[][] board, int deep) {

        if (deep > words.length - 1) return true;
        if (board.length <= i || board[0].length <= j || i < 0 || j < 0) return false;
        //如果路径交叉，直接返回false
        if (cross(path, i, j, deep)) return false;
        if (board[i][j] != words[deep]) return false;

//        System.out.println(String.format("我现在在第%s层（%s），此时的i=%s，j=%s", deep, words[deep], i, j));
        path[deep][0] = i;
        path[deep][1] = j;

//        char temp = board[i][j];
//        board[i][j] = '#';
        //下
        boolean res = dfs(path, i + 1, j, words, board, deep + 1)
                //上
                || dfs(path, i - 1, j, words, board, deep + 1)
                //左
                || dfs(path, i, j - 1, words, board, deep + 1)
                //右
                || dfs(path, i, j + 1, words, board, deep + 1);

//        board[i][j] = temp;


        return res;

    }

    //校验是否有路径交叉
    private boolean cross(int[][] path, int i, int j, int deep) {
        for (int k = 0; k < deep; k++) {
            int[] ints = path[k];
            if (ints[0] == i && ints[1] == j) return true;
        }
        return false;
    }

}
