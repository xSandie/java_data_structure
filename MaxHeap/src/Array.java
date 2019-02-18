
public class Array<Element> {//代表支持泛型，数据类型是Element，存储引用
	
	private	Element[] data;
	private int size;//数组有值元素的个数
	/**
	 * 
	 * @param capacity
	 * 容量
	 * 构造函数，传入数组的容量构造Array
	 */
	public Array(int capacity){
		data= (Element[]) new Object[capacity];
		size = 0 ;
	}
	public Array(Element[] arr){
		data = (Element[]) new Object[arr.length];
		for (int i = 0; i < arr.length; i++){
			data[i] = arr[i];
		}
		size = arr.length;
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

	public boolean isEmpty(){
		return size == 0;
	}
	//向末尾添加一个元素
	public void addLast(Element e){
		add(size, e);
	}
	//向数组起始位置添加一个元素
	public void addFirst(Element e){
		add(0, e);
	}
	//向指定位置添加元素
	public void add(int index,Element e){
		if(size==data.length){
			resize(2*data.length);
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

	//获取index索引位置的元素
	public Element get(int index){
		if(index<0||index>=size){
			throw new IllegalArgumentException("Index is Illegal");
		}
		return data[index];
	}

	//数组元素更新
	public void set(int index,Element e){
		if(index<0||index>=size){
			throw new IllegalArgumentException("Index is Illegal");
		}
		data[index]=e;
	}

	//查找是否包含某个元素
	public boolean contains(Element e){
		for (int i=0;i<size;i++){
			if(data[i].equals(e)){
				return true;
			}
		}
		return false;
	}

	//找出对应元素的索引
	public int find(Element e){
		for(int i=0;i<size;i++){
			if (data[i].equals(e)){//使用值比较
				return i;
			}
		}
		return -1;//返回无效索引
	}

	//删除index位置元素
	public Element remove(int index){
		if(index<0||index>=size){
			throw new IllegalArgumentException("Index is Illegal");
		}
		Element ret=data[index];//返回值
		for (int i = index + 1; i < size; i++) {
			data[i-1]=data[i];//index被后面的覆盖
		}
		size--;
		//使data[size]能够被垃圾回收
		data[size]=null;
		if (size == data.length/4 && data.length/4!=0){
			resize(data.length/2);
		}
		return ret;
	}
	//删除最开始和最末尾元素
	public Element removeFirst(){
		return remove(0);
	}
	public Element removeLast(){
		return remove(size-1);
	}

	//删除指定值的元素
	public void removeElement(Element e){
		int index=find(e);
		if(index != -1){
			remove(index);
		}
	}

	public void swap(int i, int j){
		if (i < 0 || i >= size || j < 0 || j >= size){
			throw new IllegalArgumentException("Index is illegal");
		}
		Element temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	@Override//覆盖父类的方法,定义类在打印输出时输出什么代码
	public String toString(){
		// Returns a string representation of the object. 
		// In general, the toString method returns a string that "textually represents" this object. 
		// The result should be a concise but informative representation that is easy for a person to read. 
		// It is recommended that all subclasses override this method.
		StringBuilder res = new StringBuilder();//Constructs a string builder with no characters in it and an initial capacity of 16 characters.
		res.append(String.format("Array:size = %d , capacity = %d\n", size,data.length));
		res.append('[');
		for (int i = 0; i < size; i++) {
			res.append(data[i]);
			if(i!=size-1){//不是最后一个元素
				res.append(", ");
			}
		}
		res.append(']');
		return res.toString();//这里调用的是StringBuilder的toString方法，让他变成字符串

	}

	private void resize(int newCapacity){
		Element[] newData = (Element[])new Object[newCapacity];//强制类型转化成Element，因为不支持new 泛型,getCapacity获取的是data.length，所以不需要再手动改变capacity
		for(int i=0;i<size;i++){
			newData[i]=data[i];
		}
		data=newData;
	}

}
