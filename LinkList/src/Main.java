public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i=0;i < 5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2,666);
        System.out.println("2位置添加后，3号元素" + linkedList);

        linkedList.remove(2);
        System.out.println("2位置删除后，3号元素" + linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

    }
}
