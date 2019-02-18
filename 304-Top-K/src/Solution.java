import java.util.*;
//O(nlogm)
class Solution {
    private class Freq{
        int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }
    }

    //改变java类的比较方式，可以写成匿名类传入，匿名类可以捕获该类中的所有静态变量
    private class FreqComparator implements Comparator<Freq>{
        //比较器作为优先队列的参数传入
        @Override
        public int compare(Freq a, Freq b){
            return a.freq - b.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else{
                map.put(num, 1);
            }
        }

        PriorityQueue<Freq> queue = new PriorityQueue<>(new FreqComparator());
        for(int key: map.keySet()){
            if(queue.size() < k){
                //还未存够k个元素
                queue.add(new Freq(key, map.get(key)));
            }else if(map.get(key) > queue.peek().freq){
                //当前元素频次更高
                queue.remove();
                queue.add(new Freq(key, map.get(key)));
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while(!queue.isEmpty()){
            res.add(queue.remove().e);
        }
        return res;
    }
}