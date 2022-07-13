package com.jinchi.java.base.algorithm;

import java.util.Stack;

class Solution_735 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            int asteroid = asteroids[i];
            asteroidCollision(stack,asteroid);
        }

        int[] result = new int[stack.size()];
        int j = result.length - 1;
        while (!stack.isEmpty()) {
            result[j] = stack.pop();
            j--;
        }
        return result;
    }

    private void asteroidCollision(Stack<Integer> stack, int asteroid) {
        if (stack.isEmpty()) {
            stack.push(asteroid);
        } else {
            int stackAsteroid = stack.peek();
            if(stackAsteroid > 0){
                if(asteroid > 0){
                    stack.push(asteroid);
                }else{
                    if(stackAsteroid + asteroid < 0){
                        stack.pop();
                        asteroidCollision(stack, asteroid);
                    }else if(stackAsteroid + asteroid == 0){
                        stack.pop();
                    }
                }
            }else{
                stack.push(asteroid);
            }
        }
    }
}