/* (c) Copyright 2011 and following years, Aminata SABAN�,
 * �Cole Polytechnique de Montr�al.
 * 
 * @author: Aminata SABAN�
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
package padl.creator.javafile.eclipse.test.basic;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;

public class LightModelCreatorTest extends TestCase {
	public LightModelCreatorTest(final String aName) {
		super(aName);
	}

	//creation from some java files list
	public void testCreateModelFromJavaFilesList() {

		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";

		final String[] listOfFiles =
			new String[] {
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member1/MemberGhostExample.java",
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member2/",
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member3/MemberGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final int classesNumberExpected = 4;
		final int classesNumber =
			model.getNumberOfTopLevelEntities(IClass.class);

		Assert.assertEquals(classesNumberExpected, classesNumber);
	}

	//creation from all the source code
	public void testCreateModelFromJavaSourceCode() {

		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/";

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry);

		final int minimalClassesNumberExpected = 5;
		final int classesNumber =
			model.getNumberOfTopLevelEntities(IClass.class);

		Assert.assertTrue(minimalClassesNumberExpected < classesNumber);

	}
}
