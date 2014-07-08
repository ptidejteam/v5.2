/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
package mendel.metric;

import mendel.model.IEntity;
import mendel.model.JClassEntity;
import mendel.model.JInterfaceEntity;


/*
 * Deviant Visitor, using RTTI rather than double-dispatch by Element-Visitor
 */
public abstract class DispatchMetric implements IEntityMetric {
	
	public String compute(IEntity entity) {
		if (entity instanceof JClassEntity)
			return compute((JClassEntity) entity);
		if (entity instanceof JInterfaceEntity)
			return compute((JInterfaceEntity) entity);
		return "";
	}


	public String compute(JClassEntity entity) {
		return "";
	}
	
	public String compute(JInterfaceEntity entity) {
		return "";
	}
	
}
