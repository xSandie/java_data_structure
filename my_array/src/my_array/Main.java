package my_array;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Array<Integer> arr = new Array(20);//int类型包装类
		for (int i = 0; i < 10; i++) {
			arr.addLast(i);
		}
		System.out.println(arr);
	}

}
