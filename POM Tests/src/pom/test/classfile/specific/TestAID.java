/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
 * University of Montreal.
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
package pom.test.classfile.specific;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.impl.Factory;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;

public class TestAID extends TestCase {
	private static MetricsRepository metrics;
	private static ICodeLevelModel model = null;
	private static final String root =
		"../POM Tests/data/Metric Specific for Java/bin/pom/test/rsc/specific/testAID/";
	public TestAID(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();
		if (TestAID.model == null) {
			TestAID.model =
				Factory.getInstance().createCodeLevelModel("Test.TestMetrics");
			TestAID.model.create(new CompleteClassFileCreator(
				new String[] { root }));

			TestAID.metrics = MetricsRepository.getInstance();
		}
	}
	public void testRef() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testAID.TestChild00");

		Assert.assertEquals(1d, ((IUnaryMetric) metrics.getMetric("AID"))
			.compute(model, firstClassEntity), 0d);
	}
	public void testAncestorExtend01() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestAID.model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testAID.TestAChild00");

		Assert.assertEquals(5d, ((IUnaryMetric) metrics.getMetric("AID"))
			.compute(model, firstClassEntity), 0d);
	}
	public void testAncestorImplement01() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestAID.model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testAID.TestBChild00");

		Assert.assertEquals(1.9375d, ((IUnaryMetric) metrics.getMetric("AID"))
			.compute(model, firstClassEntity), 0d);
	}
	public void testAncestorExtendImplement01() {
		final IFirstClassEntity firstClassEntity =
			(IFirstClassEntity) TestAID.model
				.getTopLevelEntityFromID("pom.test.rsc.specific.testAID.TestCChild00");

		Assert.assertEquals(2.9375d, ((IUnaryMetric) metrics.getMetric("AID"))
			.compute(model, firstClassEntity), 0d);
	}
}
