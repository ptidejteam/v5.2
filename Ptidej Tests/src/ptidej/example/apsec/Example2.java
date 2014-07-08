/*
 * (c) Copyright 2001-2004 Yann-Ga�l Gu�h�neuc,
 * University of Montr�al.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.example.apsec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2004/07/24
 */
public class Example2 {
	public static void main(final String[] args) {
		final Example2 example2 = new Example2();
		example2.addA(new A());
		// ...
	}

	private final List listOfAs = new ArrayList();
	public void addA(final A a) {
		this.listOfAs.add(a);
	}
	public A getA(final int index) {
		return (A) this.listOfAs.remove(index);
	}
	public void removeA(final A a) {
		this.listOfAs.remove(a);
	}
}
