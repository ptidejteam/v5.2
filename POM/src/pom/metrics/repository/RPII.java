package pom.metrics.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

/**
 * RPII - Relative Number of Internal Inheritance Relationships
 * 
 * The following metric is related to packages, and is based
 * on the paper "Butterflies: A Visual Approach to Characterize Packages",
 * by Ducasse, Lanza and Ponisio.
 * 
 * @author Karim DHAMBRI
 * @since  2005/??/?? 
 * 
 * @author Duc-Loc Huynh
 * @since  2005/08/18
 * 
 * @author Yann
 * 
 * Modifications made to fit the new architecture
 */
public class RPII extends AbstractMetric implements IMetric, IUnaryMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		final double eip =
			super.getUnaryMetricInstance("EIP").compute(
				anAbstractModel,
				firstClassEntity);
		final double piir =
			super.getUnaryMetricInstance("PIIR").compute(
				anAbstractModel,
				firstClassEntity);
		if (piir + eip == 0) {
			return 0;
		}
		else {
			return piir / (piir + eip);
		}
	}
	public String getDefinition() {
		final String def = "PIIR divided by the sum of PIIR and EIP.";
		return def;
	}
}
