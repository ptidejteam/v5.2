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
package ptidej.solver.repair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import ptidej.solver.Branching;
import ptidej.solver.Constraint;
import ptidej.solver.Problem;
import ptidej.solver.Solver;
import util.io.ProxyConsole;
import choco.AbstractConstraint;
import choco.ContradictionException;
import choco.integer.IntVar;
import choco.palm.explain.BetterConstraintComparator;
import choco.palm.explain.Explanation;
import choco.palm.explain.PalmConstraintPlugin;
import choco.palm.prop.PalmEngine;
import choco.palm.search.PalmContradiction;

public class CombinatorialAutomaticRepair extends MemoryRepair {
	public CombinatorialAutomaticRepair(final Problem problem) {
		super(problem);
	}

	public ArrayList[] ptidejSelectDecisionToUndo(final Explanation explanation)
		throws PalmContradiction {

		final ArrayList[] re = { new ArrayList(), new ArrayList()};
		final AbstractConstraint ct =
			(AbstractConstraint) Collections.min(
				explanation.toSet(),
				new BetterConstraintComparator());
		final int weight =
			((PalmConstraintPlugin) ct.getPlugIn()).getWeight();

		// For now, I suppose that nextContraint is a string.
		if (weight != 0 && weight < this.getProblem().getMaxRelaxLevel()) {
			final String nextConstraint =
				((Constraint) ct).getNextConstraint();
			if (nextConstraint != null) {
				// Yann 2002/10/12: Weight!
				// I am using a general repair algorithm.
				// This algorithm checks that the constraint
				// being removed can actually be removed
				// (i.e., its weight must be < to the maximum
				// level of relaxation). I am dealing myself
				// with the constraints, thus I must by bypass
				// this consistency check.
				 ((PalmConstraintPlugin) ct.getPlugIn()).setWeight(1);

				// [0] Removing: ~S (~S) // ct, getSymbol(ct.thisConstraint),
				re[0].add(ct);

				final Branching nextBranching =
					((Solver) this.getManager()).getNextBranching();
				String message = nextBranching.getMessage();
				int percentage = nextBranching.getWeight();

				// I get the parameters for the instanciation 
				final String name = ((Constraint) ct).getName();
				final String nextName = name;
				// I keep the same name to know the original constraint.
				final String commande = ((Constraint) ct).getXCommand();
				percentage = percentage / 2;
				percentage = (percentage == 0) ? 1 : percentage;

				final ArrayList args = new ArrayList();
				args.add(nextName);
				args.add(commande);
				for (int i = 0; i < ct.getNbVars(); ++i) {
					args.add((IntVar) ct.getVar(i));
				}
				args.add(new Integer(percentage));
				args.add(((Constraint) ct).getApproximations());

				// I instantiate the next constraint using reflection.
				final Class nClass =
					((Constraint) ct).getNextConstraintConstructor(
						nextConstraint);
				final Constructor[] constructors = nClass.getConstructors();
				final Constructor constr = constructors[0];
				AbstractConstraint nextCt = null;
				try {
					nextCt =
						(AbstractConstraint) constr.newInstance(
							args.toArray());
				}
				catch (IllegalArgumentException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
				catch (InstantiationException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
				catch (IllegalAccessException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
				catch (InvocationTargetException e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}

				message =
					message
						+ "# Solution without constraint:\n#\t"
						+ ((Constraint) ct).getSymbol()
						+ ": "
						+ name
						+ "\n# Constraint replaced with this weaker constraint:\n#\t"
						+ ((Constraint) nextCt).getSymbol()
						+ ": "
						+ nextName
						+ '\n';

				nextBranching.setMessage(message);
				nextBranching.setWeight(percentage);

				//				System.out.println(
				//					"---- \nTrying weaker constraint\n" + message);

				// I post the next constraint.
				re[1].add(nextCt);
			}
			else {
				final Branching nextBranching =
					((Solver) this.getManager()).getNextBranching();
				String message = nextBranching.getMessage();
				message =
					message
						+ "# Solution without constraint:\n#\t"
						+ ((Constraint) ct).getSymbol()
						+ ": "
						+ ((Constraint) ct).getName()
						+ "\n# No more weaker constraint to try.\n";
				nextBranching.setMessage(message);

				re[0].add(ct);
			}
		}
		else if (weight == 0) {
			// If the constraint is of weight 0, I just relax it.
			re[0].add(ct);
		}

		if (re[0].size() == 0 && re[1].size() == 0) {
			final PalmEngine pe =
				(PalmEngine) (this.getProblem()).getPropagationEngine();
			try {
				pe.raiseSystemContradiction();
			}
			catch (final ContradictionException e) {
				//				System.out.println("No more solution found.");
				//				System.out.println("Terminated!");
			}
		}

		return re;
	}
}
