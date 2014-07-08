/*
 * Writen in CLAIRE by
 * @author Yann-Ga�l Gu�h�neuc
 * Translated and adapted from CLAIRE version to JAVA by
 * @author Iyadh Sidhom
 * @author Salim Bensemmane
 * @author Fay�al Skhiri
 *
 * (c) Copyright 2000-2004 Yann-Ga�l Gu�h�neuc,
 */
package ptidej.solver.solver;

import java.util.ArrayList;

import ptidej.solver.AssignVariable;
import ptidej.solver.Problem;
import ptidej.solver.Solver;
import ptidej.solver.branching.InteractiveBranching;
import ptidej.solver.repair.SimpleInteractiveRepair;

//init constructeur
public class SimpleInteractiveSolver extends Solver {
	public SimpleInteractiveSolver(final Problem pb) {
		super(pb);
		final ArrayList list = new ArrayList();
		list.add(new AssignVariable());
		list.add(new InteractiveBranching());
		this.attachPalmBranchings(list);
		this.attachPalmRepair(new SimpleInteractiveRepair(pb));
	}
}