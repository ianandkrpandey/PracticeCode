
public class ThreadCalling {
public static void main(String[] args) {
	long a= System.currentTimeMillis();
	Mythread t1 = new Mythread();
	t1.start();
	Mythread2 t2 = new Mythread2();
	Thread t = new Thread(t2);
	t.start();
	long b = System.currentTimeMillis()-a;
	System.out.println("Time taken---"+b);
}
static class Mythread extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println(i);
		}
	}
}
static class Mythread2 implements Runnable{

	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("a");
		}
	}
	
}
}
