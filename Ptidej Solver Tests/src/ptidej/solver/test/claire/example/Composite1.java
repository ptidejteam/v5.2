/*
 * (c) Copyright 2001, 2002 Yann-Ga�l Gu�h�neuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Ga�l Gu�h�neuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.solver.test.claire.example;

import junit.framework.Assert;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.motif.IDesignMotifModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceGenerator;
import ptidej.solver.test.claire.Primitive;
import util.io.ProxyConsole;

public class Composite1 extends Primitive {
	private static int NumberOfExpectedSolutions;
	private static Occurrence[] FoundSolutions;
	private static Occurrence[] ExpectedSolutions;

	public Composite1(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (Composite1.FoundSolutions == null) {
			final IDesignMotifModel pattern =
				(IDesignMotifModel) padl.motif.repository.Composite.class
					.newInstance();

			try {
				final ICodeLevelModel codeLevelModel =
					Factory.getInstance().createCodeLevelModel(
						"ptidej.example.composite1");
				codeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../Ptidej Tests/bin/ptidej/example/composite1/" }));

				final IIdiomLevelModel idiomLevelModel =
					(IIdiomLevelModel) new AACRelationshipsAnalysis()
						.invoke(codeLevelModel);

				// Expected solutions.
				Composite1.ExpectedSolutions =
					SolutionReader.getExpectedSolutions(
						"Composite1",
						idiomLevelModel);
				Composite1.NumberOfExpectedSolutions =
					SolutionReader.getExpectedNumberOfSolutions(
						"Composite1",
						idiomLevelModel);

				// Solutions found.
				Composite1.FoundSolutions =
					this.testDesignPattern(
						Composite1.class,
						Primitive.ALL_SOLUTIONS,
						pattern.getName(),
						idiomLevelModel,
						OccurrenceGenerator.SOLVER_COMBINATORIAL_AUTOMATIC,
						OccurrenceGenerator.PROBLEM_AC4);
			}
			catch (final CreationException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			Composite1.NumberOfExpectedSolutions,
			Composite1.FoundSolutions.length);
	}
	public void testSolutions() {
		for (int i = 0; i < Composite1.NumberOfExpectedSolutions; i++) {
			Assert.assertEquals(
				"",
				Composite1.ExpectedSolutions[i],
				Composite1.FoundSolutions[i]);
		}
	}
	//	public void testComponentSolutionComponent() {
	//		final int[] solutionWithAbstractDocument = new int[] { 0, 3, 6, 9 };
	//		for (int i = 0; i < solutionWithAbstractDocument.length; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[solutionWithAbstractDocument[i]],
	//				"Component",
	//				"ptidej.example.composite1.AbstractDocument");
	//		}
	//
	//		final int[] solutionWithElement =
	//			new int[] { 1, 2, 4, 5, 7, 8, 10, 11 };
	//		for (int i = 0; i < solutionWithAbstractDocument.length; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[solutionWithElement[i]],
	//				"Component",
	//				"ptidej.example.composite1.Element");
	//		}
	//	}
	//	public void testCompositeSolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Composite",
	//				"ptidej.example.composite1.Document");
	//		}
	//	}
	//	public void testLeaf1SolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Leaf-1",
	//				"ptidej.example.composite1.IndentedParagraph");
	//		}
	//	}
	//	public void testLeaf2SolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Leaf-2",
	//				"ptidej.example.composite1.Paragraph");
	//		}
	//	}
	//	public void testLeaf3SolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Leaf-3",
	//				"ptidej.example.composite1.Title");
	//		}
	//	}
	//	public void testLeaf4SolutionComponent() {
	//		final int[] solutionWithAbstractDocument = new int[] { 0, 3, 6, 9 };
	//		for (int i = 0; i < solutionWithAbstractDocument.length; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[solutionWithAbstractDocument[i]],
	//				"Leaf-4",
	//				"ptidej.example.composite1.Element");
	//		}
	//	}
	//	public void testSolutionPercentage() {
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			12,
	//			Composite1.FoundSolutions[0].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			12,
	//			Composite1.FoundSolutions[1].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			6,
	//			Composite1.FoundSolutions[2].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			3,
	//			Composite1.FoundSolutions[3].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			3,
	//			Composite1.FoundSolutions[4].getPercentage());
	//		for (int i = 5; i < 11; i++) {
	//			Composite1.assertEquals(
	//				"Distorted solution percentage",
	//				1,
	//				Composite1.FoundSolutions[i].getPercentage());
	//		}
	//	}
}
