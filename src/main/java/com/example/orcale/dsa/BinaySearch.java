package com.example.orcale.dsa;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class BinaySearch {

    public static void main(String[] args) {


        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 1; i <= 100; i++) {
            maxHeap.add(i);
            if (maxHeap.size() > 3) {
                maxHeap.poll();
            }
        }

        System.out.println("Rest: " + maxHeap.peek());
    }
    public int countVowels(String data){
        int count = 0;
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for(Character c : data.toCharArray()){ // O(n)
            if(vowels.contains(c)){ // O (1)
                count++;
            }
        }
        return count; // O(n)
    }
    public int largerNumber(int[] data) {
        int largest = data[0];
        for( int i =1 ; i< data.length; i++){
            if(data[i] > largest){
                largest = data[i];
            }
        }
        return largest;
    }
    public int[] reverse(int[] data){
        int count = 0;
        int start = 0;
        int end = data.length - 1;
        while (start < end){
            int temp = data[start];
            data[start] = data[end];
            data[end] = temp;
            start++;
            end--;
        }
        return data;
    }

}
