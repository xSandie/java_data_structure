import java.util.Random;

public class Main {
    private static double testHeap(Integer[] testData, boolean isHeapify){
        long startTime = System.nanoTime();
        if (isHeapify == true){
            MaxHeap<Integer> maxHeap = new MaxHeap<>(testData);
        }else {
            MaxHeap<Integer> maxHeap = new MaxHeap<>();
            for (int num: testData){
                maxHeap.add(num);
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

//        int n = 10;
//
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//
//        Random random = new Random();
//        for (int i = 0; i < n ; i++){
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//        }
//
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++){
//            arr[i] = maxHeap.extractMax();
//        }
//
//        for (int i = 1; i < n; i++){
//            if (arr[i-1] < arr[i]){
//                //说明堆实现有问题
//                throw new IllegalArgumentException("Error");
//            }
//        }
//        System.out.println(arr);
        int n = 100000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++){
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double timeHeapify = testHeap(testData, true);
        double time = testHeap(testData, false);

        System.out.println("heapify:" + timeHeapify);
        System.out.println("without heapify" + time);


    }
}
