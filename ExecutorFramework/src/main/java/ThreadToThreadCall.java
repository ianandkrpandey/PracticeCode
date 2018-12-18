
public class ThreadToThreadCall {
public static void main(String[] args) {
	Thread t = new Thread(new ThreadA());
	t.start();
	
}
}
class ThreadA implements Runnable{

	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println(i);
		}
		Thread t = new Thread(new ThreadB());
		t.start();
		
		
	}}
class ThreadB implements Runnable{
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("a"+i);
		}
		
	}
}
