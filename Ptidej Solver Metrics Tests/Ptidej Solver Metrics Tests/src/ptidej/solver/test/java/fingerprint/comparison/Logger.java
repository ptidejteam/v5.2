/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
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
package ptidej.solver.test.java.fingerprint.comparison;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ptidej.solver.fingerprint.Rule;

/**
 * @author Jean-Yves Guyomarc'h
 * @since  2004/09/19
 */
public class Logger {
	private class VarLog {
		final private String name;
		final private double reduction;
		final private int size;

		public VarLog(
			final String name,
			final int size,
			final double reduction) {
			this.name = name;
			this.size = size;
			this.reduction = reduction;
		}
		public String getName() {
			return this.name;
		}
		public double getReduction() {
			return this.reduction;
		}
		public int getSize() {
			return this.size;
		}
	}

	public static final int WITH_RULE = 0;
	public static final int WITHOUT_RULE = 1;

	public static void main(final String args[]) {
		final Logger test = new Logger("TestLog");
		test.setMode(0);
		test.setStart(0);
		test.setILMCreation(10000);
		test.setProblemCreation(30000);
		test.setEndProcess(60000);
		test.setRole(null);
		test.setDomainSize(200);
		test.setNumSolutions(2000);
		test.setGhostedSolutions(1500);
		test.addVar("Var1", 100, 50.26546);
		test.addVar("Var2", 50, 75.654654);
		test.setMode(1);
		test.setStart(0);
		test.setILMCreation(10000);
		test.setProblemCreation(30000);
		test.setEndProcess(100000);
		test.setRole(null);
		test.setDomainSize(200);
		test.setNumSolutions(5000);
		test.setGhostedSolutions(4500);
		test.addVar("Var1", 200, 0);
		test.addVar("Var2", 200, 0);
		test.print();
	}

	private int[] domainSize = new int[2];
	private long[] endProcess = new long[2];
	private int[] ghostSol = new int[2];
	private long[] ilmCreation = new long[2];
	private int[] numSol = new int[2];
	private long[] problemCreation = new long[2];
	private Rule role;
	private int ruleMode;
	private long[] start = new long[2];
	private String testTitle;
	private ArrayList[] var = new ArrayList[2];

	public Logger(final String testTitle) {
		this.testTitle = testTitle;
		this.var[0] = new ArrayList();
		this.var[1] = new ArrayList();
		this.ruleMode = 0;
		this.role = null;
	}
	public void addVar(
		final String name,
		final int size,
		final double reduction) {

		this.var[ruleMode].add(new VarLog(name, size, reduction));
	}
	private String computeTime(final long start, final long end) {
		final long t = end - start;
		final long min = (t / 1000) / 60;
		final long sec = (t / 1000) - (60 * min);

		final StringBuffer buffer = new StringBuffer();
		buffer.append(min);
		buffer.append('\'');
		buffer.append(sec);

		return buffer.toString();
	}
	public void print() {
		try {
			final StringBuffer fileName = new StringBuffer();
			fileName.append("rsc/");
			fileName.append(this.testTitle);
			fileName.append(".txt");
			final PrintWriter out =
				new PrintWriter(
					new BufferedWriter(new FileWriter(fileName.toString())));

			out.println(testTitle);
			out.println();
			out.println(role);

			out.print("Step\tILMCreation\tProblemCreation\tSolver\tTotal\n");
			out.print("Ruled\t");
			out.print(this.computeTime(start[0], ilmCreation[0]));
			out.print('\t');
			out.print(this.computeTime(ilmCreation[0], problemCreation[0]));
			out.print('\t');
			out.print(this.computeTime(problemCreation[0], endProcess[0]));
			out.print('\t');
			out.println(this.computeTime(start[0], endProcess[0]));
			out.print("No Rule\t");
			out.print(this.computeTime(start[1], ilmCreation[1]));
			out.print('\t');
			out.print(this.computeTime(ilmCreation[1], problemCreation[1]));
			out.print('\t');
			out.print(this.computeTime(problemCreation[1], endProcess[1]));
			out.print('\t');
			out.println(this.computeTime(start[1], endProcess[1]));
			out.println();

			out.print("Var\tDomainNoRule\tDomainRuled\tReduction\n");
			for (int i = 0; i < var[0].size(); i++) {
				final VarLog vr = (VarLog) var[0].get(i);
				final VarLog vnr = (VarLog) var[1].get(i);

				out.print(vr.getName());
				out.print('\t');
				out.print(vnr.getSize());
				out.print('\t');
				out.print(vr.getSize());
				out.print('\t');
				out.print((int) vr.getReduction());
				out.print("%\n");
			}
			out.println();

			out.print("Solutions\tTotal\tGhosted\tValabe\n");
			out.print("Ruled\t");
			out.print(this.numSol[0]);
			out.print('\t');
			out.print(this.ghostSol[0]);
			out.print('\t');
			out.println(this.numSol[0] - this.ghostSol[0]);

			out.print("No rule\t");
			out.print(this.numSol[1]);
			out.print('\t');
			out.print(this.ghostSol[1]);
			out.print('\t');
			out.println(this.numSol[1] - this.ghostSol[1]);

			out.flush();
			out.close();

		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	public void setDomainSize(final int i) {
		this.domainSize[ruleMode] = i;
	}
	public void setEndProcess(final long l) {
		this.endProcess[ruleMode] = l;
	}
	public void setGhostedSolutions(final int i) {
		this.ghostSol[ruleMode] = i;
	}
	public void setILMCreation(final long l) {
		this.ilmCreation[ruleMode] = l;
	}
	public void setMode(final int ruleMode) {
		this.ruleMode = ruleMode;
	}
	public void setNumSolutions(final int i) {
		this.numSol[ruleMode] = i;
	}
	public void setProblemCreation(final long l) {
		this.problemCreation[ruleMode] = l;
	}
	public void setRole(final Rule role) {
		this.role = role;
	}
	public void setStart(final long rootTime) {
		this.start[ruleMode] = rootTime;
	}
}
