package com.jinchi.java.base.algorithm;

import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {
    private Queue<Integer> queue;
    private Integer size;
    private Double total;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.queue = new LinkedList<Integer>();
        this.size = size;
        this.total = 0D;
    }

    public double next(int val) {
        if (queue.size() < size) {
            queue.offer(val);
            total += val;
        } else {
            Integer first = queue.poll();
            total -= first;
            queue.offer(val);
            total += val;
        }
        return total / queue.size();
    }
}