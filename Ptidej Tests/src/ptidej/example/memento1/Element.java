package ptidej.example.memento1;

public abstract class Element implements AbstractDocument, AbstractElement {
	public void print() {
		System.out.println(this.getClass());
	}
}
