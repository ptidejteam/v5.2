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
package pom.metrics.repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethodInvocation;
import padl.kernel.IOperation;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

public class RFC extends AbstractMetric implements IMetric, IUnaryMetric {
	public String getDefinition() {
		return "Response for class: a count of the number of methods of an entity and the number of methods of other entities that are invoked by the methods of the entity.";
	}
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		final Set setOfCalledMethods = new HashSet();
		final Iterator iteratorOnDeclaredMethods =
			super.classPrimitives.listOfDeclaredMethods(anEntity).iterator();
		while (iteratorOnDeclaredMethods.hasNext()) {
			final IOperation operation =
				(IOperation) iteratorOnDeclaredMethods.next();
			final Iterator iteratorOnCalledMethods =
				operation.getIteratorOnConstituents(IMethodInvocation.class);
			while (iteratorOnCalledMethods.hasNext()) {
				final IMethodInvocation methodInvocation =
					(IMethodInvocation) iteratorOnCalledMethods.next();
				final IOperation calledMethod =
					methodInvocation.getCalledMethod();
				if (calledMethod != null) {
					setOfCalledMethods.add(calledMethod.getDisplayName());
				}
			}
		}
		return setOfCalledMethods.size();
	}
}
