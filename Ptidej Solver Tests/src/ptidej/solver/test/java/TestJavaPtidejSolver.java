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
package ptidej.solver.test.java;

import junit.framework.Test;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2004/09/19
 */
public final class TestJavaPtidejSolver extends junit.framework.TestSuite {
	public static Test suite() {
		final TestJavaPtidejSolver suite = new TestJavaPtidejSolver();

		suite.addTest(ptidej.solver.test.java.combinatorial.TestSuite.suite());
		suite.addTest(ptidej.solver.test.java.example.TestSuite.suite());
		suite.addTest(ptidej.solver.test.java.simple.TestSuite.suite());
		
		return suite;
	}
	public TestJavaPtidejSolver() {
	}
	public TestJavaPtidejSolver(final Class theClass) {
		super(theClass);
	}
	public TestJavaPtidejSolver(final String name) {
		super(name);
	}
}
