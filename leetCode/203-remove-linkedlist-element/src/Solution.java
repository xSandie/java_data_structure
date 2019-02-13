class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val){//因为删除后的头节点可能还与val相等
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
            //断掉关系就会被回收了
        }

        if (head == null){
            //可能把全部节点都删了
            return head;
        }
        //删除列表中间的
        ListNode prev = head;
        while (prev.next != null){
            if (prev.next.val == val){
                //下一个元素要被删除
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else {
                //删除之后prev.next就自然改变了,防止跳过
                prev = prev.next;
            }
        }
        return head;
    }
}