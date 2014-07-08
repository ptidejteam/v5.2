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
package org.jhotdraw.test.util;

// JUnitDoclet begin import
import org.jhotdraw.util.ColorMap;
import junit.framework.TestCase;
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
 * TestCase ColorMapTest is generated by
 * JUnitDoclet to hold the tests for ColorMap.
 * @see org.jhotdraw.util.ColorMap
 */
// JUnitDoclet end javadoc_class
public class ColorMapTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private ColorMap colormap;
	// JUnitDoclet end class

	/**
	 * Constructor ColorMapTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public ColorMapTest(String name) {
		// JUnitDoclet begin method ColorMapTest
		super(name);
		// JUnitDoclet end method ColorMapTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public ColorMap createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new ColorMap();
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
		colormap = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		colormap = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method size()
	/**
	 * Method testSize is testing size
	 * @see org.jhotdraw.util.ColorMap#size()
	 */
	// JUnitDoclet end javadoc_method size()
	public void testSize() throws Exception {
		// JUnitDoclet begin method size
		// JUnitDoclet end method size
	}

	// JUnitDoclet begin javadoc_method color()
	/**
	 * Method testColor is testing color
	 * @see org.jhotdraw.util.ColorMap#color(int)
	 */
	// JUnitDoclet end javadoc_method color()
	public void testColor() throws Exception {
		// JUnitDoclet begin method color
		// JUnitDoclet end method color
	}

	// JUnitDoclet begin javadoc_method name()
	/**
	 * Method testName is testing name
	 * @see org.jhotdraw.util.ColorMap#name(int)
	 */
	// JUnitDoclet end javadoc_method name()
	public void testName() throws Exception {
		// JUnitDoclet begin method name
		// JUnitDoclet end method name
	}

	// JUnitDoclet begin javadoc_method colorIndex()
	/**
	 * Method testColorIndex is testing colorIndex
	 * @see org.jhotdraw.util.ColorMap#colorIndex(java.awt.Color)
	 */
	// JUnitDoclet end javadoc_method colorIndex()
	public void testColorIndex() throws Exception {
		// JUnitDoclet begin method colorIndex
		// JUnitDoclet end method colorIndex
	}

	// JUnitDoclet begin javadoc_method isTransparent()
	/**
	 * Method testIsTransparent is testing isTransparent
	 * @see org.jhotdraw.util.ColorMap#isTransparent(java.awt.Color)
	 */
	// JUnitDoclet end javadoc_method isTransparent()
	public void testIsTransparent() throws Exception {
		// JUnitDoclet begin method isTransparent
		// JUnitDoclet end method isTransparent
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
