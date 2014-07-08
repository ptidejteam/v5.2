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

// JUnitDoclet begin import
import org.jhotdraw.figures.ScribbleTool;
import org.jhotdraw.test.JHDTestCase;
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
 * TestCase ScribbleToolTest is generated by
 * JUnitDoclet to hold the tests for ScribbleTool.
 * @see org.jhotdraw.figures.ScribbleTool
 */
// JUnitDoclet end javadoc_class
public class ScribbleToolTest
// JUnitDoclet begin extends_implements
extends JHDTestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private ScribbleTool scribbletool;
	// JUnitDoclet end class

	/**
	 * Constructor ScribbleToolTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public ScribbleToolTest(String name) {
		// JUnitDoclet begin method ScribbleToolTest
		super(name);
		// JUnitDoclet end method ScribbleToolTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public ScribbleTool createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new ScribbleTool(getDrawingEditor());
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
		scribbletool = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		scribbletool = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method activate()
	/**
	 * Method testActivate is testing activate
	 * @see org.jhotdraw.figures.ScribbleTool#activate()
	 */
	// JUnitDoclet end javadoc_method activate()
	public void testActivate() throws Exception {
		// JUnitDoclet begin method activate
		// JUnitDoclet end method activate
	}

	// JUnitDoclet begin javadoc_method deactivate()
	/**
	 * Method testDeactivate is testing deactivate
	 * @see org.jhotdraw.figures.ScribbleTool#deactivate()
	 */
	// JUnitDoclet end javadoc_method deactivate()
	public void testDeactivate() throws Exception {
		// JUnitDoclet begin method deactivate
		// JUnitDoclet end method deactivate
	}

	// JUnitDoclet begin javadoc_method mouseDown()
	/**
	 * Method testMouseDown is testing mouseDown
	 * @see org.jhotdraw.figures.ScribbleTool#mouseDown(java.awt.event.MouseEvent, int, int)
	 */
	// JUnitDoclet end javadoc_method mouseDown()
	public void testMouseDown() throws Exception {
		// JUnitDoclet begin method mouseDown
		// JUnitDoclet end method mouseDown
	}

	// JUnitDoclet begin javadoc_method mouseDrag()
	/**
	 * Method testMouseDrag is testing mouseDrag
	 * @see org.jhotdraw.figures.ScribbleTool#mouseDrag(java.awt.event.MouseEvent, int, int)
	 */
	// JUnitDoclet end javadoc_method mouseDrag()
	public void testMouseDrag() throws Exception {
		// JUnitDoclet begin method mouseDrag
		// JUnitDoclet end method mouseDrag
	}

	// JUnitDoclet begin javadoc_method mouseUp()
	/**
	 * Method testMouseUp is testing mouseUp
	 * @see org.jhotdraw.figures.ScribbleTool#mouseUp(java.awt.event.MouseEvent, int, int)
	 */
	// JUnitDoclet end javadoc_method mouseUp()
	public void testMouseUp() throws Exception {
		// JUnitDoclet begin method mouseUp
		// JUnitDoclet end method mouseUp
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
