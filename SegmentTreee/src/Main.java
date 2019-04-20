public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-8261,2300,-1429,6274,9650,-3267,1414,-8102,6251,-5979,-5291,-4616,-4703,123};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        }//匿名类
        );
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(2, 5));
    }
}
