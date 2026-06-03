package com.example.javacodingjourney.neetcode;

import java.util.Arrays;

public class ProductSelf {

    public static void main(String[] args){
        int[] nums = {1,2,3,4};

        System.out.println(Arrays.toString(productExceptSelf(nums)));

    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result,1);
        int pre =1, post =1;
        for(int i=0;i<n;i++) {
            result[i] = pre;
            pre *= nums[i];
        }
        for(int i = n-1;i>=0;i--){
            result[i] *= post;
            post *= nums[i];
        }
        return result;
    }
}
