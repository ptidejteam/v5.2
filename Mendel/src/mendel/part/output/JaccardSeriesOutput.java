/**
 * Copyright (c) 2008 Simon Denier
 */
package mendel.part.output;

import java.util.List;
import java.util.Vector;

import mendel.Util;
import mendel.family.Family;
import mendel.family.FamilyHomogeneity;
import mendel.family.JaccardDistance;
import mendel.family.Prototype;
import mendel.family.FamilyHomogeneity.PrototypeTag;
import mendel.model.JClassEntity;
import mendel.part.AbstractPart;


/**
 * Input: Family
 * Output: String
 * 
 * @author Simon Denier
 * @since Sep 10, 2008
 *
 */
public class JaccardSeriesOutput extends AbstractPart {
	
	private FamilyHomogeneity tool = new FamilyHomogeneity();
	
	private JaccardDistance jac = new JaccardDistance();

	/* (non-Javadoc)
	 * @see mendel.IPart#compute(java.lang.Object)
	 */
	public Object compute(Object data) {
		Family family = (Family) data;
		StringBuffer result = new StringBuffer(family.parent().getBaseName());
		List<JClassEntity> children = family.children();
		Vector<String> jaccards = new Vector<String>(children.size());
		Prototype prototype = family.getPrototype("prototype");
		for (JClassEntity child : children) {
			result.append(", " + child.getBaseName());
			jaccards.add( this.jac.compute(child) );
		}
		PrototypeTag tag = this.tool.tag(family, prototype);
		result.append("\n" + tag + ", ");
		Util.join(jaccards, ", ", result);
		return result.toString();
	}

}
