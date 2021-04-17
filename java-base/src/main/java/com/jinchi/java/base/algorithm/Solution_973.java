package com.jinchi.java.base.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2020/11/10
 */
public class Solution_973 {

    public static void main(String[] args) {

        int[][] point = new int[][]{{-5, 4}, {-6, -5}, {4, 6}};

        kClosest(point, 2);
    }


    public static int[][] kClosest(int[][] points, int K) {
        Map<int[], Integer> distanceMap = new HashMap<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(K, (o1, o2) -> distanceMap.get(o2).compareTo(distanceMap.get(o1)));
        for (int i = 0; i < points.length; i++) {
            int currDistance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            System.out.println(currDistance);
            int[] key = new int[]{points[i][0], points[i][1]};
            distanceMap.put(key, currDistance);

            if (maxHeap.size() == K) {
                if (distanceMap.get(maxHeap.peek()).compareTo(currDistance) > 0) {
                    maxHeap.poll();
                    maxHeap.offer(key);
                }
            } else if (maxHeap.size() < K) {
                maxHeap.offer(key);
            }
        }
        return maxHeap.toArray(new int[][]{});
    }
}
