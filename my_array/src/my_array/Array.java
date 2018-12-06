package my_array;

public class Array {
	
	private	int[] data;
	private int size;//数组有值元素的个数
	/**
	 * 
	 * @param capacity
	 * 容量
	 * 构造函数，传入数组的容量构造Array
	 */
	public Array(int capacity){
		data= new int[capacity];
		size = 0 ;
	}
	//无参数构造函数
	public Array(){
		this(10);
	}

	public int getSize(){
		return size;
	}

	public int getCapacity(){
		return data.length;
	}

	public Boolean isEmpty(){
		return size == 0;
	}
	//向末尾添加一个元素
	public void addLast(int e){
		add(size, e);
	}
	//向数组起始位置添加一个元素
	public void addFirst(int e){
		add(0, e);
	}
	//向指定位置添加元素
	public void add(int index,int e){
		if(size==data.length){
			throw new IllegalArgumentException("Array is full");
		}
		if(index <0 || index>size){
			throw new IllegalArgumentException("Index is wrong");
		}
		//每个元素向后移动一个位置
		for (int i = size - 1; i >= index ; i--) {
			data[i + 1] = data[i];
		}
		data[index]=e;
		size++;
	}

	@Override//覆盖父类的方法
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append(String.format("Array:size = %d , capacity = %d\n", size,data.length));
	}
}
