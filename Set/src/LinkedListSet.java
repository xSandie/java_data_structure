public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public void add(E e){
        if (list.contains(e)){
            return;
        }else {
            list.addFirst(e);
        }
    }

    @Override
    public boolean contains(E e){
        return list.contains(e);
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public void remove(E e){
        list.removeElements(e);
    }

}
