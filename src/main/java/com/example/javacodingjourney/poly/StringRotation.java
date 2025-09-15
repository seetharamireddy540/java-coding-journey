package com.example.javacodingjourney.poly;

import com.example.javacodingjourney.ds.GraphApp;

public class StringRotation {


    public static void main(String[] args) {


        String data = "Gita"; // 1/- > aGit taGi itaG and Gita, aGit

        int size = data.length();
        int noOfRotations = 5;

        if (noOfRotations > size) {
            noOfRotations = noOfRotations % size;
        }
        char dataChar[] = data.toCharArray();
        if (noOfRotations == size) {
            return;
        }



    }
}
