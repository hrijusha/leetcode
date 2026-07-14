class MedianFinder {
    PriorityQueue<Integer> rightHeap;
    PriorityQueue<Integer> leftHeap;

    public MedianFinder() {
        leftHeap = new PriorityQueue<>(Collections.reverseOrder());
        rightHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        leftHeap.add(num);
        rightHeap.add(leftHeap.poll());
        if (leftHeap.size() < rightHeap.size()) {
            leftHeap.add(rightHeap.poll());
        }
    }

    public double findMedian() {
        if (leftHeap.size() > rightHeap.size()) {
            return leftHeap.peek();
        } else {
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        }

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */