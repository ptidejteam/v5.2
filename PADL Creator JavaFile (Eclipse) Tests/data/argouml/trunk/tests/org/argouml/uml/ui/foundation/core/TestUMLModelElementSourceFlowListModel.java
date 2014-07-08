// $Id$
// Copyright (c) 1996-2006 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.uml.ui.foundation.core;

import org.argouml.model.Model;
import org.argouml.uml.ui.AbstractUMLModelElementListModel2Test;

/**
 * @since Oct 30, 2002
 * @author jaap.branderhorst@xs4all.nl
 */
public class TestUMLModelElementSourceFlowListModel
    extends AbstractUMLModelElementListModel2Test {

    /**
     * The number of elements used in the tests.
     */
    private static final int NO_OF_ELEMENTS = 10;

    /**
     * Constructor for TestUMLModelElementSourceFlowListModel.
     * @param arg0 is the name of the test case.
     */
    public TestUMLModelElementSourceFlowListModel(String arg0) {
        super(arg0);
    }

    /**
     * @see org.argouml.uml.ui.AbstractUMLModelElementListModel2Test#buildElement()
     */
    protected void buildElement() {
        setElem(Model.getCoreFactory().createClass());
    }

    /**
     * @see org.argouml.uml.ui.AbstractUMLModelElementListModel2Test#buildModel()
     */
    protected void buildModel() {
        setModel(new UMLModelElementSourceFlowListModel());
    }

    /**
     * @see org.argouml.uml.ui.AbstractUMLModelElementListModel2Test#fillModel()
     */
    protected Object[] fillModel() {
        Object[] ext = new Object[NO_OF_ELEMENTS];
        for (int i = 0; i < NO_OF_ELEMENTS; i++) {
            ext[i] = Model.getCoreFactory().createFlow();
            Model.getCoreHelper().addSourceFlow(getElem(), ext[i]);
        }
        return ext;
    }

    /**
     * @see org.argouml.uml.ui.AbstractUMLModelElementListModel2Test#removeHalfModel(Object[])
     */
    protected void removeHalfModel(Object[] elements) {
        for (int i = 0; i < NO_OF_ELEMENTS / 2; i++) {
            Model.getCoreHelper().removeSourceFlow(getElem(), elements[i]);
        }
    }

}
