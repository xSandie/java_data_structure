package my_array;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Array<Integer> arr = new Array(10);//int类型包装类
		for (int i = 0; i < 10; i++) {
			arr.addLast(i);
		}
		arr.add(1,100);
		System.out.println(arr);

		arr.remove(2);
		arr.remove(3);
		System.out.println(arr);
	}

}
