/*
 * (c) Copyright 2001-2006 Yann-Ga�l Gu�h�neuc,
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
package padl.test.setter;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.kernel.Constants;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.IMethod;
import padl.kernel.IPackage;
import padl.kernel.IParameter;
import padl.kernel.impl.Factory;
import util.io.ProxyConsole;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2006/11/28
 */
public class TestGetter extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	public TestGetter(final String name) {
		super(name);
	}
	public void setUp() {
		if (TestGetter.IdiomLevelModel == null) {
			try {
				final char[] entityName = "A".toCharArray();
				final IFirstClassEntity entity =
					Factory.getInstance().createClass(entityName, entityName);

				final char[] parameterName = "a".toCharArray();
				final IParameter aParameter1 =
					Factory.getInstance().createParameter(
						entity,
						parameterName,
						Constants.CARDINALITY_ONE);
				final IMethod aGetter =
					Factory.getInstance().createMethod(
						"get".toCharArray(),
						"get".toCharArray());
				aGetter.setReturnType(entityName);
				aGetter.addConstituent(aParameter1);

				final IParameter aParameter2 =
					Factory.getInstance().createParameter(
						entity,
						parameterName,
						Constants.CARDINALITY_ONE);
				final IMethod aSetter =
					Factory.getInstance().createMethod(
						"set".toCharArray(),
						"set".toCharArray());
				aSetter.addConstituent(aParameter2);

				entity.addConstituent(aGetter);
				entity.addConstituent(aSetter);

				final IPackage aPackage =
					Factory.getInstance().createPackage("p".toCharArray());
				aPackage.addConstituent(entity);

				final ICodeLevelModel aCodeLevelModel =
					Factory.getInstance().createCodeLevelModel("Model");
				aCodeLevelModel.addConstituent(aPackage);

				TestGetter.IdiomLevelModel =
					(IIdiomLevelModel) new AACRelationshipsAnalysis()
						.invoke(aCodeLevelModel);
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testClass() {
		Assert.assertEquals(
			"Class A exists",
			1,
			TestGetter.IdiomLevelModel.getNumberOfTopLevelEntities());
	}
	public void testMethods() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestGetter.IdiomLevelModel
				.getTopLevelEntityFromID("A");
		Assert.assertEquals(
			"Getter and setter exist",
			2,
			firstClassEntity.getNumberOfConstituents());
	}
	public void testParameters() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestGetter.IdiomLevelModel
				.getTopLevelEntityFromID("A");
		final IMethod getter =
			(IMethod) firstClassEntity.getConstituentFromID("get");
		Assert.assertEquals(
			"Parameter exists",
			1,
			getter.getNumberOfConstituents());
	}
}
