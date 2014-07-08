/**
 * Copyright (c) 2008 Simon Denier
 */
package mendel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mendel.model.IEntity;


/**
 * @author Simon Denier
 * @since Feb 18, 2008
 *
 */
public class EntityRepository implements IRepository {

	private Map repository;
	
	public EntityRepository() {
		this.repository = new HashMap();
	}
	
	public void registerEntity(String qualifiedName, IEntity entity) {
		if( hasEntityRegistered(qualifiedName) ) // TODO: log
			System.err.println("Warning: overriding entity in repository");
		this.repository.put(qualifiedName, entity);
	}
	
	public IEntity getEntity(String qualifiedName) {
		return (IEntity) this.repository.get(qualifiedName);
	}
	
	public boolean hasEntityRegistered(String qualifiedName) {
		return this.repository.containsKey(qualifiedName);
	}

	public Iterator iterator() {
		return this.repository.values().iterator();
	}

	public int size() {
		return this.repository.size();
	}

	/* (non-Javadoc)
	 * @see coverage.IRepository#getAllEntities()
	 */
	public Collection getAllEntities() {
		return this.repository.values();
	}
	
}
