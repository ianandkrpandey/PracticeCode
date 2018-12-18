

public class InnerClassExample {
public static void main(String[] args) {
	OuterClass out = new OuterClass();
	out.outerMethod();
	
	
}
}
class OuterClass{
	class innerClass{
		public void innerMethod() {
			System.out.println("Inner Body");
		}
	}
	public void outerMethod() {
		innerClass in = new innerClass();
		in.innerMethod();
		System.out.println("Outer Body");
	}
}
