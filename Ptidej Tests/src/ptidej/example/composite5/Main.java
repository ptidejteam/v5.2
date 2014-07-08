/*
 * (c) Copyright 2001, 2002 Yann-Ga�l Gu�h�neuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Ga�l Gu�h�neuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.example.composite5;

public class Main {
	public static void main(final String[] args) {
		final Document document = new Document();
		document.addElement(new Title());
		document.addElement(new Paragraph());
		document.addElement(new Title());
		document.addElement(new IndentedParagraph());
		document.addElement(new IndentedParagraph());
		document.addElement(new SpecialIndentedParagraph(document));

		final int sw = 5;
		switch (sw) {
			case 1 :
				document.printAll();
				break;
			case 2 :
				document.getClass();
				break;
			case 3 :
				document.printAll();

				break;
			case 4 :
				document.hashCode();

				break;
			case 5 :
				document.print();
				break;
			default :
				System.out.println("");
				break;
		}

		document.printAll();
	}
}
