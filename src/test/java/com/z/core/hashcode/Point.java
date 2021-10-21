package com.z.core.hashcode;

import java.util.Collection;
import java.util.HashSet;

public class Point {
    private int x;

    private int y;

    public Point(int x, int y) {

        super();

        this.x = x;

        this.y = y;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(x, y);
//    }

    public static void main(String[] args) {

        Point p1 = new Point(3, 3);

        Point p2 = new Point(5, 5);

        Point p3 = new Point(3, 3);

        Collection<Point> collection = new HashSet<Point>();

        collection.add(p1);

        collection.add(p2);

        collection.add(p3);

        System.out.println(collection.size());//3，因为HashSet中不会保存重复的对象，每添加一个元素，先判断，再添加，如果已经存在，那么就不在添加，无序的！
    }
}
