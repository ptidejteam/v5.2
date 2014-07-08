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
package ptidej.solver.branching;

import ptidej.solver.Branching;
import ptidej.solver.Problem;
import ptidej.solver.Variable;
import util.io.ProxyConsole;
import choco.ContradictionException;
import choco.integer.IntVar;
import choco.integer.var.IntDomain;
import choco.palm.explain.Explanation;
import choco.palm.integer.PalmIntDomain;
import choco.util.IntIterator;

public class AutomaticBranching extends Branching {
	public Object selectBranchingObject() {
		final Problem pb = (Problem) this.extender.getManager().getProblem();
		this.setSolutionNumber(this.getSolutionNumber() + 1);
		this.printOutSolution(
			pb,
			this.getMessage(),
			this.getSolutionNumber(),
			this.getWeight(),
			this.getXCommand());
		final Explanation expl = pb.makeExplanation();

		// Yann 2007/03/30: Variables...
		// I make sure there is at least one variable.
		// In case the user is trying to call the solver
		// on an empty problem.
		if (pb.getVars().length > 0) {
			final IntVar last = pb.getIntVar(pb.getVars().length - 1);

			for (int i = 0; i < pb.getVars().length; i++) {
				((Variable) pb.getIntVar(i)).self_explain(
					PalmIntDomain.DOM,
					expl);
			}

			final IntDomain domain = last.getDomain();
			if (domain.getSize() != 0) {
				final IntIterator iterator = domain.getIterator();
				while (iterator.hasNext()) {
					final int value = iterator.next();
					pb.post(pb.neq(last, value), expl);
				}
			}
			else {
				pb.post(pb.neq(last, last.getValue()), expl);
			}
		}

		try {
			pb.propagate();
		}
		catch (final ContradictionException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		// TODO: What is this return for?
		return this.getNextBranching();
	}
}
