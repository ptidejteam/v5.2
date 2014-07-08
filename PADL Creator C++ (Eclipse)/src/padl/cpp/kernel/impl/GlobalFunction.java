package padl.cpp.kernel.impl;

import java.util.Iterator;
import padl.cpp.kernel.IGlobalFunction;
import padl.cpp.util.EmptyIterator;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentOfEntity;
import padl.kernel.IConstituentOfOperation;
import padl.kernel.IElement;
import padl.kernel.IFilter;
import padl.kernel.IFirstClassEntity;
import padl.kernel.exception.ModelDeclarationException;
import padl.kernel.impl.Method;

class GlobalFunction extends Method implements IGlobalFunction {
	private static final long serialVersionUID = 135094587167318982L;

	private IElement attachedElement;
	private char[] returnType;

	public GlobalFunction(final char[] anID, final char[] aName) {
		super(anID);
		this.setName(aName);
	}
	// Yann 2014/06/20: What a story!
	// Because I forgot to override this method, the cloning
	// of GlobalFunction would throw an exception because the
	// FirstClassEntity.addConstituent(IConstituent) method 
	// would be used, which forbids adding to FirstClassEntity
	// anything but IConstituentOfEntity and IRelatonship...
	// First, this exception was silently discarded, see 
	// padl.kernel.impl.FirstClassEntity.performCloneSession(),
	// then, the forwarded exception was not logged because
	// Eclipse (through EclipseCPPParserCaller) would close
	// the normal and error output streams given to it!...
	// So, now, everyting is working as expected and logs
	// cannot be closed anymore, see util.io.UnclosablePrintWriter.
	@Override
	public void addConstituent(final IConstituent aConstituent) {
		if (aConstituent instanceof IConstituentOfOperation) {
			this.addConstituent((IConstituentOfOperation) aConstituent);
		}
		else {
			super.addConstituent(aConstituent);
		}
	}
	@Override
	public void addConstituent(final IConstituentOfEntity anElement) {
		throw new ModelDeclarationException(this.getClass().getName()
				+ " cannot accept elements");
	}
	@Override
	public void addConstituent(final IConstituentOfOperation aConstituent) {
		this.container.directlyAddConstituentWithUniqueID(aConstituent);
	}
	public void addInheritedEntity(final IFirstClassEntity anEntity) {
		throw new ModelDeclarationException(this.getClass().getName()
				+ " cannot accept entities");
	}
	@Override
	public void attachTo(final IElement anElement) {
		this.attachedElement = anElement;
	}
	@Override
	public void detach() {
		this.attachedElement = null;
	}
	@Override
	public IElement getAttachedElement() {
		return this.attachedElement;
	}
	@Override
	public String getCallDeclaration() {
		return this.getDisplayName();
	}
	@Override
	public String getDisplayReturnType() {
		return String.valueOf(this.returnType);
	}
	@Override
	public IFirstClassEntity getInheritedEntityFromID(final char[] anID) {
		return null;
	}
	@Override
	public IFirstClassEntity getInheritedEntityFromName(final char[] aName) {
		return null;
	}
	@Override
	public Iterator<?> getIteratorOnInheritedEntities() {
		return new EmptyIterator<>();
	}
	@Override
	public Iterator<?> getIteratorOnInheritedEntities(final IFilter aFilter) {
		return new EmptyIterator<>();
	}
	@Override
	public Iterator<?> getIteratorOnInheritingEntities() {
		return new EmptyIterator<>();
	}
	@Override
	public Iterator<?> getIteratorOnInheritingEntities(final IFilter aFilter) {
		return new EmptyIterator<>();
	}
	public int getNumberOfInheritedEntities() {
		return 0;
	}
	public int getNumberOfInheritingEntities() {
		return 0;
	}
	public String getPurpose() {
		return "";
	}
	@Override
	public char[] getReturnType() {
		return this.returnType;
	}
	@Override
	public boolean isAboveInHierarchy(final IFirstClassEntity anEntity) {
		return false;
	}
	@Override
	public void removeInheritedEntity(final IFirstClassEntity anEntity) {
	}
	@Override
	public void setPurpose(final String aPurpose) {
	}
	@Override
	public void setReturnType(final char[] aType) {
		this.returnType = aType;
	}
}
