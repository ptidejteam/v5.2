/*
 * (c) Copyright 2004 S�bastien Robidoux, Ward Flores,
 * Universit� de Montr�al
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 * Created 2004/08/10
 */
package padl.cpp.kernel.impl;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import padl.cpp.kernel.IStructure;
import padl.kernel.IElement;
import padl.kernel.IEntityMarker;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IOperation;
import padl.kernel.impl.FirstClassEntity;

class Structure extends FirstClassEntity implements IEntityMarker, IStructure {
	private static final long serialVersionUID = -6073309025082506778L;

	private boolean forceAbstract = false;
	private List<IFirstClassEntity> friendClassList =
		new ArrayList<IFirstClassEntity>();
	private List<IOperation> friendFunctionList = new ArrayList<IOperation>();
	@SuppressWarnings("rawtypes")
	private final List<?> listOfSuperInterfaces = new ArrayList();

	public Structure(final char[] anID) {
		super(anID);
	}
	public void addFriendClass(final IFirstClassEntity aClass) {
		if (aClass != null) {
			this.friendClassList.add(aClass);
		}
	}
	public void addFriendFunction(final IOperation aFunction) {
		if (aFunction != null) {
			this.friendFunctionList.add(aFunction);
		}
	}
	public void attachTo(final IElement anElement) {
	}
	public void detach() {
	}
	public IElement getAttachedElement() {
		return null;
	}
	//	public Structure(final String anID, final IEntity inheritedEntity) {
	//		super(anID);
	//		try {
	//			this.addInheritedEntity(inheritedEntity);
	//		}
	//		catch (final ModelDeclarationException e) {
	//			// No error can occur
	//		}
	//	}
	// Yann 2002/07/29: Thought...
	// I think the following method is not consistent with the idea of
	// vetoable property. I guess a better implementation would be for
	// the PClass class to register itself as a listener on the list
	// of inherited entities and to veto any addition to the list when
	// the list already contains one entity.
	//	public void addInherits(final Entity aPEntity)
	//		 {
	//
	//		if (this.listOfInheritedEntities().size() > 0) {
	//			StringBuffer buffer = new StringBuffer();
	//			buffer.append("Only single inheritance supported for class\n");
	//			buffer.append(this);
	//			buffer.append(" -|>- ");
	//			buffer.append(this.listOfInheritedEntities().(0));
	//			buffer.append("\nCannot attach new super-class\n");
	//			buffer.append(aPEntity);
	//			throw new ModelDeclarationException(buffer.toString());
	//		}
	//
	//		super.addInherits(aPEntity);
	//	}
	//	public void addImplementedEntity(final IEntity anEntity)
	//		 {
	//
	//		if (this.listOfSuperInterfaces.contains(anEntity)) {
	//			throw new ModelDeclarationException(
	//				anEntity.getID()
	//					+ " is already implemented by "
	//					+ this.getID());
	//		}
	//
	//		this.listOfSuperInterfaces.add(anEntity);
	//		((Entity) anEntity).addInheritingEntity(this);
	//	}
	//	public void assumeAllInterfaces() {
	//		try {
	//			final Iterator iterator = this.listOfSuperInterfaces.iterator();
	//			while (iterator.hasNext()) {
	//				this.assumeInterface((Interface) iterator.next());
	//			}
	//		}
	//		catch (final ModelDeclarationException e) {
	//			// No ModelDeclarationException can occur.
	//		}
	//	}
	//	public void assumeInterface(final IInterface anInterface)
	//		 {
	//
	//		if (!this.listOfSuperInterfaces.contains(anInterface)) {
	//			throw new ModelDeclarationException(
	//				anInterface.getID()
	//					+ " is not implemented by "
	//					+ this.getID());
	//		}
	//
	//		final Iterator iterator = anInterface.listOfConstituents().iterator();
	//		while (iterator.hasNext()) {
	//			try {
	//				final Element orgElement = (Element) iterator.next();
	//				orgElement.startCloneSession();
	//				final Element dupElement = (Element) orgElement.getClone();
	//				orgElement.endCloneSession();
	//				dupElement.setAbstract(false);
	//				dupElement.attachTo(orgElement);
	//				this.addConstituent(dupElement);
	//			}
	//			catch (final ModelDeclarationException e) {
	//				// In case of duplicated element.
	//			}
	//		}
	//	}
	//	public IEntity getImplementedEntity(final String anEntityName) {
	//		final Iterator iterator = this.listOfImplementedEntities().iterator();
	//		while (iterator.hasNext()) {
	//			final Entity implementedEntity = (Entity) iterator.next();
	//			if (implementedEntity.getName().equals(anEntityName)) {
	//				return implementedEntity;
	//			}
	//		}
	//
	//		return null;
	//	}
	public boolean isForceAbstract() {
		return this.forceAbstract;
	}
	public List<?> listOfImplementedEntities() {
		return this.listOfSuperInterfaces;
	}
	//	public void performCloneSession() {
	//		super.performCloneSession();
	//
	//		// Duplicate implementation hierarchy.
	//		final Structure clonedPStructure = (Structure) this.getClone();
	//		clonedPStructure.listOfSuperInterfaces =
	//			new ArrayList(this.listOfSuperInterfaces.size());
	//		final Iterator iterator = this.listOfSuperInterfaces.iterator();
	//		while (iterator.hasNext()) {
	//			final Entity currentInterface = (Entity) iterator.next();
	//			// Yann: The followind lines are not needed anymore?
	//			// if (currentPInterface.isCloned()) {
	//			// tmpObject.removeShouldImplement(currentPInterface);
	//			// try {
	//			// Yann 2001/07/31: Hack!
	//			// The following test is only needed when cloning
	//			// a subList of the padlModel.
	//			// A better and *cleaner* algorithm must be
	//			// implemented eventually.
	//			if (currentInterface.getClone() != null) {
	//				clonedPStructure.listOfSuperInterfaces.add(
	//					(Entity) currentInterface.getClone());
	//			}
	//		}
	//	}
	//	public void removeImplementedEntity(final IEntity anEntity) {
	//		this.listOfSuperInterfaces.remove(anEntity);
	//	}
	public void setAbstract(final boolean aBoolean) {
		this.forceAbstract = aBoolean;
		super.setAbstract(aBoolean);
	}
	public void setVisibility(final int visibility) {
		super.setVisibility(this.isForceAbstract() ? visibility
				| Modifier.ABSTRACT : visibility);
	}
	public String toString() {
		final StringBuffer codeEq = new StringBuffer();
		codeEq.append(super.toString());

		codeEq.append("\n\tFriends with functions:");
		final Iterator<IOperation> iteratorOnFriendFunctions =
			this.friendFunctionList.iterator();
		while (iteratorOnFriendFunctions.hasNext()) {
			final IOperation iOperation = iteratorOnFriendFunctions.next();
			codeEq.append('\t');
			codeEq.append(iOperation.getName());
			codeEq.append('\n');
		}

		codeEq.append("\n\tFriends with first-class entities:");
		final Iterator<IFirstClassEntity> iteratorOnFriendClasses =
			this.friendClassList.iterator();
		while (iteratorOnFriendClasses.hasNext()) {
			final IFirstClassEntity iOperation = iteratorOnFriendClasses.next();
			codeEq.append("\t\t");
			codeEq.append(iOperation.getName());
			codeEq.append('\n');
		}

		return codeEq.toString();
	}
}
