package com.z.designpatterns.structure.adapter.example.electric;

public class Celectric implements Voltage {

    private Computer computer;

    private int input;
    private int output = 100;

    public Celectric(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void work(int voltage) {
        computer.work(output);
    }

    public static void main(String[] args) {
        Computer computer = new Computer();
//        computer.work();
    }

}
