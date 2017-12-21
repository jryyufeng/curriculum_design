package Shuju;

//����
public class Queue<T>{
	private int max;
	private T[] ary;
	private int front;//��ͷָ�� ֻ��ȡ�����ݵ�λ��
	private int rear;//��βָ�룬ֻ�ǲ���λ��
	private int nItems;//ʵ�����ݸ���
	
	//���е�ʵ��
	public Queue(int size){
		this.max=size;
		ary=(T[])new Object[max];
		front=0;
		rear=-1;
		nItems=0;
	}
	//�����β
	public void insert(T t){
		if(rear==max-1){
			rear=-1;
		}
		ary[++rear]=t;
		nItems++;
	}
	//�Ƴ���ͷ
	public T remove(){
		T temp=ary[front++];
		if(front==max){
			front=0;//���е�β����ͷ��ʼ
		}
		nItems--;
		return temp;
	}
	//�鿴��ͷ			
	public T peek(){
		return ary[front]; 
	}
	//�п�
	public boolean isEmpty(){
		return nItems==0;
	}
	//��������
	public boolean isFull(){
		return nItems==max;
	}
	//����ʵ�ʴ�С
	public int size(){
		return nItems;
	}
}