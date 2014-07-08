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
package squad.quality.pqmod.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import squad.quality.AbstractQualityAttribute;
import squad.quality.INominalQualityAttribute;
import squad.quality.IQualityAttribute;

public class Understandability extends AbstractQualityAttribute implements
		IQualityAttribute, INominalQualityAttribute {

	public String concretelyComputeNomValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity entity) {

		final double Aid =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("AID"))
				.compute(anAbstractModel, entity);

		if (Aid <= 0.11) {
			return "N";
		}
		else {
			return "G";
		}
	}
	public String getDefinition() {
		final String def =
			"Degree to which the code source can be understood easily.";
		return def;
	}

}
