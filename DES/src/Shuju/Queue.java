package Shuju;

//队列
public class Queue<T>{
	private int max;
	private T[] ary;
	private int front;//对头指针 只是取出数据的位置
	private int rear;//队尾指针，只是插入位置
	private int nItems;//实际数据个数
	
	//队列的实现
	public Queue(int size){
		this.max=size;
		ary=(T[])new Object[max];
		front=0;
		rear=-1;
		nItems=0;
	}
	//插入队尾
	public void insert(T t){
		if(rear==max-1){
			rear=-1;
		}
		ary[++rear]=t;
		nItems++;
	}
	//移除队头
	public T remove(){
		T temp=ary[front++];
		if(front==max){
			front=0;//队列到尾，从头开始
		}
		nItems--;
		return temp;
	}
	//查看队头			
	public T peek(){
		return ary[front]; 
	}
	//判空
	public boolean isEmpty(){
		return nItems==0;
	}
	//队列判满
	public boolean isFull(){
		return nItems==max;
	}
	//队列实际大小
	public int size(){
		return nItems;
	}
}