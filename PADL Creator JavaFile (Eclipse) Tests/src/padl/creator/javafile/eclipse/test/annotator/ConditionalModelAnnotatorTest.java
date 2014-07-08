package padl.creator.javafile.eclipse.test.annotator;

import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IMethod;
import padl.statement.kernel.ISwitchInstruction;
import util.io.ProxyConsole;

public class ConditionalModelAnnotatorTest extends TestCase {

	public ConditionalModelAnnotatorTest(final String name) {
		super(name);

	}

	public void testLoc() {
		final String sourcePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] javaFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/annotator/" };
		final String classPathEntry = "";
		final ICodeLevelModel javaModel =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourcePath,
				classPathEntry,
				javaFiles);
		final IClass entity =
			(IClass) javaModel
				.getTopLevelEntityFromID("padl.example.annotator.E");
		final IMethod m1 =
			(IMethod) entity.getConstituentFromName("main".toCharArray());
		final IMethod m2 =
			(IMethod) entity.getConstituentFromName("method1".toCharArray());

		try {
			int nbConditionalStatements =
				m1.getNumberOfConstituents(Class
					.forName("padl.kernel.impl.Conditional"));
			Assert.assertEquals(1, nbConditionalStatements);
			nbConditionalStatements =
				m2.getNumberOfConstituents(Class
					.forName("padl.kernel.impl.Conditional"));
			Assert.assertEquals(3, nbConditionalStatements);
			final Iterator iter =
				m2.getConcurrentIteratorOnConstituents(Class
					.forName("padl.kernel.impl.SwitchInstruction"));
			while (iter.hasNext()) {
				final ISwitchInstruction instruction =
					(ISwitchInstruction) iter.next();
				ProxyConsole
					.getInstance()
					.debugOutput()
					.println(instruction.toString());
			}
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
