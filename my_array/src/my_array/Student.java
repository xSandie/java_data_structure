package my_array;

public class Student {

    private String name;
    private int score;

    public Student(String studentName,int studentScore){
        name = studentName;
        score = studentScore;
    }

    @Override
    public String toString(){
        //它返回对象的一个String表示
        return String.format("Student(name:%s ,score:%d)",name,score);//Returns a formatted string using the specified format string and arguments.
    }

    public static void main(String[] args){
        Array<Student> arr=new Array<>();
        arr.addLast(new Student("Alice",100));
        arr.addLast(new Student("slice",100));
        arr.addLast(new Student("Alce",90));
        System.out.println(arr);
        String str=arr.toString();
        System.out.println(str);
        
    }
}
