public class Main {

    public static void main(String[] args) {
            BST<Integer> bst = new BST<>();
            int[] nums = {5,3,6,8,4,2};

            for (int num:nums){
                bst.add(num);
            }

//            bst.preOrder();
//            System.out.println();
            bst.inOrder();//结果正好是 从小到大 排序后 的结果
//            System.out.println();
//            bst.postOrder();
//            bst.preOrderNR();
//            bst.levelOrder();
    }
}
