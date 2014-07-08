/*
 * @(#)Test.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	(c) by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */
package org.jhotdraw.test.figures;

import org.jhotdraw.figures.NumberTextFigure;
import junit.framework.TestCase;
// JUnitDoclet begin import
// JUnitDoclet end import

/*
 * Generated by JUnitDoclet, a tool provided by
 * ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org
 * and www.objectfab.de for informations about
 * the tool, the licence and the authors.
 */

// JUnitDoclet begin javadoc_class
/**
 * TestCase NumberTextFigureTest is generated by
 * JUnitDoclet to hold the tests for NumberTextFigure.
 * @see org.jhotdraw.figures.NumberTextFigure
 */
// JUnitDoclet end javadoc_class
public class NumberTextFigureTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private NumberTextFigure numbertextfigure;
	// JUnitDoclet end class

	/**
	 * Constructor NumberTextFigureTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public NumberTextFigureTest(String name) {
		// JUnitDoclet begin method NumberTextFigureTest
		super(name);
		// JUnitDoclet end method NumberTextFigureTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public NumberTextFigure createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new NumberTextFigure();
		// JUnitDoclet end method testcase.createInstance
	}

	/**
	 * Method setUp is overwriting the framework method to
	 * prepare an instance of this TestCase for a single test.
	 * It's called from the JUnit framework only.
	 */
	protected void setUp() throws Exception {
		// JUnitDoclet begin method testcase.setUp
		super.setUp();
		numbertextfigure = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		numbertextfigure = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method overlayColumns()
	/**
	 * Method testOverlayColumns is testing overlayColumns
	 * @see org.jhotdraw.figures.NumberTextFigure#overlayColumns()
	 */
	// JUnitDoclet end javadoc_method overlayColumns()
	public void testOverlayColumns() throws Exception {
		// JUnitDoclet begin method overlayColumns
		// JUnitDoclet end method overlayColumns
	}

	// JUnitDoclet begin javadoc_method setValue()
	/**
	 * Method testSetGetValue is testing setValue
	 * and getValue together by setting some value
	 * and verifying it by reading.
	 * @see org.jhotdraw.figures.NumberTextFigure#setValue(int)
	 * @see org.jhotdraw.figures.NumberTextFigure#getValue()
	 */
	// JUnitDoclet end javadoc_method setValue()
	public void testSetGetValue() throws Exception {
		// JUnitDoclet begin method setValue getValue
		int[] tests = { Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE };

		for (int i = 0; i < tests.length; i++) {
			numbertextfigure.setValue(tests[i]);
			assertEquals(tests[i], numbertextfigure.getValue());
		}
		// JUnitDoclet end method setValue getValue
	}

	// JUnitDoclet begin javadoc_method getRepresentingFigure()
	/**
	 * Method testGetRepresentingFigure is testing getRepresentingFigure
	 * @see org.jhotdraw.figures.NumberTextFigure#getRepresentingFigure()
	 */
	// JUnitDoclet end javadoc_method getRepresentingFigure()
	public void testGetRepresentingFigure() throws Exception {
		// JUnitDoclet begin method getRepresentingFigure
		// JUnitDoclet end method getRepresentingFigure
	}

	// JUnitDoclet begin javadoc_method testVault
	/**
	 * JUnitDoclet moves marker to this method, if there is not match
	 * for them in the regenerated code and if the marker is not empty.
	 * This way, no test gets lost when regenerating after renaming.
	 * <b>Method testVault is supposed to be empty.</b>
	 */
	// JUnitDoclet end javadoc_method testVault
	public void testVault() throws Exception {
		// JUnitDoclet begin method testcase.testVault
		// JUnitDoclet end method testcase.testVault
	}

}
