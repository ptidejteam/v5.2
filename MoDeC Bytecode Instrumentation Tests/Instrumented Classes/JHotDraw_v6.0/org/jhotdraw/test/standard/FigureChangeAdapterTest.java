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
package org.jhotdraw.test.standard;

import org.jhotdraw.standard.FigureChangeAdapter;
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
 * TestCase FigureChangeAdapterTest is generated by
 * JUnitDoclet to hold the tests for FigureChangeAdapter.
 * @see org.jhotdraw.standard.FigureChangeAdapter
 */
// JUnitDoclet end javadoc_class
public class FigureChangeAdapterTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private FigureChangeAdapter figurechangeadapter;
	// JUnitDoclet end class

	/**
	 * Constructor FigureChangeAdapterTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public FigureChangeAdapterTest(String name) {
		// JUnitDoclet begin method FigureChangeAdapterTest
		super(name);
		// JUnitDoclet end method FigureChangeAdapterTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public FigureChangeAdapter createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new FigureChangeAdapter();
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
		figurechangeadapter = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		figurechangeadapter = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method figureInvalidated()
	/**
	 * Method testFigureInvalidated is testing figureInvalidated
	 * @see org.jhotdraw.standard.FigureChangeAdapter#figureInvalidated(org.jhotdraw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureInvalidated()
	public void testFigureInvalidated() throws Exception {
		// JUnitDoclet begin method figureInvalidated
		// JUnitDoclet end method figureInvalidated
	}

	// JUnitDoclet begin javadoc_method figureChanged()
	/**
	 * Method testFigureChanged is testing figureChanged
	 * @see org.jhotdraw.standard.FigureChangeAdapter#figureChanged(org.jhotdraw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureChanged()
	public void testFigureChanged() throws Exception {
		// JUnitDoclet begin method figureChanged
		// JUnitDoclet end method figureChanged
	}

	// JUnitDoclet begin javadoc_method figureRemoved()
	/**
	 * Method testFigureRemoved is testing figureRemoved
	 * @see org.jhotdraw.standard.FigureChangeAdapter#figureRemoved(org.jhotdraw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureRemoved()
	public void testFigureRemoved() throws Exception {
		// JUnitDoclet begin method figureRemoved
		// JUnitDoclet end method figureRemoved
	}

	// JUnitDoclet begin javadoc_method figureRequestRemove()
	/**
	 * Method testFigureRequestRemove is testing figureRequestRemove
	 * @see org.jhotdraw.standard.FigureChangeAdapter#figureRequestRemove(org.jhotdraw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureRequestRemove()
	public void testFigureRequestRemove() throws Exception {
		// JUnitDoclet begin method figureRequestRemove
		// JUnitDoclet end method figureRequestRemove
	}

	// JUnitDoclet begin javadoc_method figureRequestUpdate()
	/**
	 * Method testFigureRequestUpdate is testing figureRequestUpdate
	 * @see org.jhotdraw.standard.FigureChangeAdapter#figureRequestUpdate(org.jhotdraw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureRequestUpdate()
	public void testFigureRequestUpdate() throws Exception {
		// JUnitDoclet begin method figureRequestUpdate
		// JUnitDoclet end method figureRequestUpdate
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
