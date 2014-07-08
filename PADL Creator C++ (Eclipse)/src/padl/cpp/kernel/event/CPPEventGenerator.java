/* (c) Copyright 2008 and following years, Yann-Ga�l Gu�h�neuc,
 * �cole Polytechnique de Montr�al.
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
package padl.cpp.kernel.event;

import padl.cpp.kernel.ICPPClass;
import padl.cpp.kernel.ICPPGhost;
import padl.cpp.kernel.ICPPMemberClass;
import padl.cpp.kernel.ICPPMemberGhost;
import padl.cpp.kernel.IDestructor;
import padl.cpp.kernel.IEnum;
import padl.cpp.kernel.IEnumValue;
import padl.cpp.kernel.IGlobalField;
import padl.cpp.kernel.IGlobalFunction;
import padl.cpp.kernel.IMemberStructure;
import padl.cpp.kernel.IStructure;
import padl.cpp.kernel.IUnion;
import padl.cpp.visitor.ICPPWalker;
import padl.event.EventGenerator;
import padl.kernel.IContainer;

public class CPPEventGenerator extends EventGenerator implements ICPPWalker {
	private static final long serialVersionUID = 2115411573715818526L;
	private static CPPEventGenerator UniqueInstance;
	public static CPPEventGenerator getInstance() {
		if (CPPEventGenerator.UniqueInstance == null) {
			CPPEventGenerator.UniqueInstance = new CPPEventGenerator();
		}
		return CPPEventGenerator.UniqueInstance;
	}

	protected CPPEventGenerator() {
	}

	@Override
	public void close(final ICPPClass aCPPClass) {
		this.close((IContainer) aCPPClass);
	}

	@Override
	public void close(final ICPPGhost aCPPGhost) {
		this.close((IContainer) aCPPGhost);
	}

	@Override
	public void close(final ICPPMemberClass aCPPMemberClass) {
		this.close((IContainer) aCPPMemberClass);
	}

	@Override
	public void close(final ICPPMemberGhost aCPPMemberGhost) {
		this.close((IContainer) aCPPMemberGhost);
	}

	@Override
	public void close(final IDestructor aDestructor) {
	}

	@Override
	public void close(final IEnum anEnum) {
		this.close((IContainer) anEnum);
	}

	@Override
	public void close(final IGlobalField aGlobalField) {
	}

	@Override
	public void close(IGlobalFunction aGlobalFunction) {
	}

	@Override
	public void close(IMemberStructure aMemberStructure) {
		this.close((IContainer) aMemberStructure);
	}

	@Override
	public void close(final IStructure aStruct) {
		this.close((IContainer) aStruct);
	}

	@Override
	public void close(final IUnion anUnion) {
		this.close((IContainer) anUnion);
	}

	@Override
	public String getName() {
		return "C++ Event Generator";
	}

	@Override
	public void open(final ICPPClass aCPPClass) {
		this.genetateEventForEntity(aCPPClass);
		this.open((IContainer) aCPPClass);
	}

	@Override
	public void open(final ICPPGhost aCPPGhost) {
		this.genetateEventForEntity(aCPPGhost);
		this.open((IContainer) aCPPGhost);
	}

	@Override
	public void open(final ICPPMemberClass aCPPMemberClass) {
		this.genetateEventForEntity(aCPPMemberClass);
		this.open((IContainer) aCPPMemberClass);
	}

	@Override
	public void open(final ICPPMemberGhost aCPPMemberGhost) {
		this.genetateEventForEntity(aCPPMemberGhost);
		this.open((IContainer) aCPPMemberGhost);
	}

	@Override
	public void open(final IDestructor aDestructor) {
		this.genetateEventForElement(aDestructor);
	}

	@Override
	public void open(final IEnum anEnum) {
		this.genetateEventForEntity(anEnum);
		this.open((IContainer) anEnum);
	}

	@Override
	public void open(final IGlobalField aGlobalField) {
		this.genetateEventForElement(aGlobalField);
	}

	@Override
	public void open(final IGlobalFunction aGlobalFunction) {
		this.genetateEventForElement(aGlobalFunction);
	}

	@Override
	public void open(IMemberStructure aMemberStructure) {
		this.genetateEventForEntity(aMemberStructure);
		this.open((IContainer) aMemberStructure);
	}

	@Override
	public void open(final IStructure aStruct) {
		this.genetateEventForEntity(aStruct);
		this.open((IContainer) aStruct);
	}

	@Override
	public void open(final IUnion anUnion) {
		this.genetateEventForEntity(anUnion);
		this.open((IContainer) anUnion);
	}

	@Override
	public void visit(final IEnumValue anEnumValue) {
	}
}
