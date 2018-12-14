public interface Stack<Element> {
    int getSize();
    boolean isEmpty();
    void push(Element e);
    Element pop();
    Element peek();
}
