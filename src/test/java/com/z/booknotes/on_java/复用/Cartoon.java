package com.z.booknotes.on_java.复用;

class Art {
    {
        System.out.println("Art");
    }

    Art() {
        System.out.println("Art constructor");
    }
}

class Drawing extends Art {
    {
        System.out.println("Drawing");
    }

    Drawing() {
        System.out.println("Drawing constructor");
    }
}

public class Cartoon extends Drawing {
    {
        System.out.println("Cartoon");
    }

    public Cartoon() {
        System.out.println("Cartoon constructor");
    }

    public static void main(String[] args) {
        Cartoon x = new Cartoon();
    }
}
