/*
 * Created on 2005-08-26
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package dram.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * @author rachedsa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FileComparaisonInc {
	private FileReader rd;
	private BufferedReader buf;
	private FileOutputStream fos;
	private OutputStreamWriter osw;
	private File outputFile;

	public FileComparaisonInc() {
		try {
			this.rd =
				new FileReader("C:\\Documents and Settings\\rachedsa\\Bureau\\diffJunit\\diff4.txt");

			this.buf = new BufferedReader(this.rd);

			this.outputFile =
				new File("C:\\Documents and Settings\\rachedsa\\Bureau\\diffJunit\\diff4Mod.txt");
			this.fos = new FileOutputStream(this.outputFile);
			this.osw = new OutputStreamWriter(this.fos);
			modif();
		}
		catch (Exception ex) {
			System.err.println("Syntax error line ");
			ex.printStackTrace();

		}
	}

	public void modif() {
		try {
			String chaine = null;
			String token1;
			String token2;
			String token3;
			String token4;
			String token5;
			String token6;

			String record;
			int time = 0;
			String ss = "";
			while ((chaine = this.buf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(chaine, "|");
				st.nextToken();
				token1 = st.nextToken();
				token2 = st.nextToken();
				token3 = st.nextToken();
				token4 = st.nextToken();
				token5 = st.nextToken();
				token6 = st.nextToken();

				if (ss.equals(token1)) {

				}
				else {
					time = time + 1;
					ss = token1;
				}

				if (token2.equals("constructorEntry0")
					|| token2.equals("constructorExit0")) {
					token5 = "1";
					record =
						token2
							+ "|"
							+ token3
							+ "|"
							+ token4
							+ "|"
							+ token5
							+ "|"
							+ time
							+ "|"
							+ '\n';
				}
				else {
					st.nextToken();
					token6 = "1";
					record =
						token2
							+ "|"
							+ token3
							+ "|"
							+ token4
							+ "|"
							+ token5
							+ "|"
							+ token6
							+ "|"
							+ time
							+ "|"
							+ '\n';
				}
				this.osw.write(record);
				this.osw.flush();
			}
		}
		catch (Exception ex) {
			System.err.println("Syntax error line ");
			ex.printStackTrace();

		}
	}
	public static void main(final String[] args) {
		new FileComparaisonInc();
	}
}
