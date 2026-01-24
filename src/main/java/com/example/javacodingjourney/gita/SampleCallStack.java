package com.example.javacodingjourney.gita;

public class SampleCallStack {

    public static void main(String[] args) {
        SampleCallStack callStack = new SampleCallStack();
        callStack.methodA(5);

    }

    public int methodA(int a) {
        int b = methodB(a);
        return b + b;
    }

    public int methodB(int a) {
        return a + a;
    }
}
