/*
 * (c) Copyright 2000-2002 Yann-Ga�l Gu�h�neuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Ga�l Gu�h�neuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.viewer.extension.repository;

import infovis.Graph;
import infovis.Table;
import infovis.column.StringColumn;
import infovis.graph.DefaultGraph;
import infovis.graph.io.GraphReaderFactory;
import infovis.graph.visualization.MatrixVisualization;
import infovis.io.AbstractReader;
import infovis.io.AbstractReaderFactory.AbstractCreator;
import infovis.panel.ControlPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import padl.visitor.IWalker;
import ptidej.viewer.IRepresentation;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.extension.IViewerExtension;
import util.help.IHelpURL;
import util.io.ProxyConsole;

/**
 * Example of graph visualization with an adjacency matrix.
 * Inspired from MatrixExample from InfoVis by Jean-Daniel Fekete.
 * 
 * @author 	Yann-Ga�l Gu�h�neuc
 */
public final class InfoVisMatrix extends JFrame implements IViewerExtension,
		IHelpURL {

	private static final long serialVersionUID = 1L;
	/**
	 * Return the nth <code>StringColumn</code> from the specified table.
	 *
	 * @param t the table.
	 * @param index the index
	 *
	 * @return the nth <code>StringColumn</code> from the specified table.
	 */
	private static StringColumn getStringColumn(final Table t, int index) {
		StringColumn ret = null;
		for (int i = 0; i < t.getColumnCount(); i++) {
			ret = StringColumn.getColumn(t, i);
			if (ret != null && !ret.isInternal() && index-- == 0)
				return ret;
		}
		return null;
	}

	public InfoVisMatrix() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(final WindowEvent e) {
				InfoVisMatrix.this.setVisible(false);
			}
			public void windowClosing(final WindowEvent e) {
				this.windowClosed(e);
			}
		});
	}
	private void createFrame(final IRepresentation aRepresentation) {
		this.setVisible(false);
		this.setTitle(this.getName());
		this.getContentPane().removeAll();

		final IWalker walker =
			new InfoVisMatrixGenerator(aRepresentation
				.getSourceGraph()
				.getVisibleElements());
		aRepresentation.getSourceModel().walk(walker);

		final String path =
			System.getProperty("user.dir") + File.separatorChar + "Temp.graph";
		try {
			// TODO Use ProxyDisk
			final FileWriter writer = new FileWriter(path);
			writer.write(walker.getResult().toString());
			writer.close();

			// First, I add the new reader to the GraphReaderFactory.
			GraphReaderFactory.sharedInstance().add(
				new AbstractCreator(".graph") {
					public AbstractReader doCreate(
						String name,
						Table table,
						boolean decompress) throws IOException,
							FileNotFoundException {
						return new CSVGraphReader(
							open(name, decompress),
							name,
							GraphReaderFactory.getGraph(table));
					}
				});
			// Second, I create an empty graph.
			final Graph g = new DefaultGraph();
			// Third, I create a reader on the graph with the generated file.
			final AbstractReader reader =
				GraphReaderFactory.createReader(path, g);

			// Last, I load the file and creates the view.
			if (reader.load()) {
				final MatrixVisualization visualization =
					new MatrixVisualization(g);
				visualization.setVertexLabelColumn(InfoVisMatrix
					.getStringColumn(g.getEdgeTable(), 0));

				final ControlPanel control =
					(ControlPanel) visualization.createDefaultControls();

				this.getContentPane().add(control);
				this.pack();
				this.setVisible(true);
			}
		}
		catch (final IOException ioe) {
			ioe.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
	public String getName() {
		return "Adjacency matrix (InfoVis)";
	}
	public String getHelpURL() {
		return "http://ivtk.sourceforge.net/";
	}
	public void invoke(final IRepresentation aRepresentation) {
		this.createFrame(aRepresentation);
	}
	public void sourceModelChanged(final SourceAndGraphModelEvent aViewerEvent) {
		this.sourceModelAvailable(aViewerEvent);
	}
	public void sourceModelAvailable(SourceAndGraphModelEvent aViewerEvent) {
		if (this.isVisible()) {
			this.createFrame(aViewerEvent.getRepresentation());
		}
	}
	public void sourceModelUnavailable() {
		// Nothing to do...
	}
}
