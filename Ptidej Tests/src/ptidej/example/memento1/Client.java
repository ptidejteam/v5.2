package ptidej.example.memento1;

public class Client {
	public static void main(final String[] args) {
		final Client client = new Client();

		final Document document = new Document();
		document.addElement(new Title());
		document.addElement(new Paragraph());

		// Remember previous state. The nice thing about this
		// implementation is that the internal state of the
		// Document class is never exposed, not even through the
		// DocumentMemento interface, beacause a private inner class
		// realises the concrete implementation of the memento.
		client.previousDocumentMemento = document.createMemento();

		document.addElement(new Title());
		document.addElement(new ParaIndent());
		document.addElement(new ParaIndent());

		System.out
			.println("-- FIRST PRINTALL -------------------------------------");
		document.printAll();
		System.out
			.println("-- SECOND PRINTALL AFTER UNDO -------------------------");
		document.setMemento(client.previousDocumentMemento);
		document.printAll();
	}
	private DocumentMemento previousDocumentMemento;
}
