/*
 * (c) Copyright 2001-2004 Yann-Ga�l Gu�h�neuc,
 * University of Montr�al.
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
package ptidej.solver.approximation;

/**
 * @author	Yann-Ga�l Gu�h�neuc
 * @since	2006/08/16
 */
public class TSE07ExtensibleStrictInheritanceApproximations implements
		IApproximations {

	private static final String[] APPROXIMATIONS =
		{ "ptidej.solver.constraint.repository.StrictInheritanceConstraint",
				"ptidej.solver.constraint.repository.StrictInheritancePathConstraint" };

	private static TSE07ExtensibleStrictInheritanceApproximations UniqueInstance;
	public static TSE07ExtensibleStrictInheritanceApproximations getDefaultApproximations() {
		if (TSE07ExtensibleStrictInheritanceApproximations.UniqueInstance == null) {
			TSE07ExtensibleStrictInheritanceApproximations.UniqueInstance =
				new TSE07ExtensibleStrictInheritanceApproximations();
		}
		return TSE07ExtensibleStrictInheritanceApproximations.UniqueInstance;
	}

	private TSE07ExtensibleStrictInheritanceApproximations() {
	}
	public String[] getApproximations() {
		return TSE07ExtensibleStrictInheritanceApproximations.APPROXIMATIONS;
	}
}
