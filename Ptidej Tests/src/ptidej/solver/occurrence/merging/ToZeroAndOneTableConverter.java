/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
 * University of Montreal.
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
package ptidej.solver.occurrence.merging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import util.io.ProxyDisk;

public class ToZeroAndOneTableConverter {
	private static final FilenameFilter FILTER = new FilenameFilter() {
		public boolean accept(final File aPath, final String aName) {
			return aName.endsWith(".csv");
		}
	};

	public static void main(final String[] args) {
		final String originPath = "rsc/AP + DP/";
		final String destinationPath = "rsc/AP + DP (All Roles)/";

		final ToZeroAndOneTableConverter converter =
			new ToZeroAndOneTableConverter();
		final Set setOfAllRoles = converter.buildSetOfAllRoles(originPath);
		converter
			.convertFilesInWith(originPath, destinationPath, setOfAllRoles);
	}

	private Set buildSetOfAllRoles(final String aRootPath) {
		final Set generalSetOfRoles = new TreeSet();
		final String[] fileNames =
			new File(aRootPath).list(ToZeroAndOneTableConverter.FILTER);
		for (int i = 0; i < fileNames.length; i++) {
			final String filePath = aRootPath + fileNames[i];
			generalSetOfRoles.addAll(this.extractSetOfRolesFrom(filePath));
		}
		return generalSetOfRoles;
	}
	private void convertFilesInWith(
		final String anOriginPath,
		final String aDestinationPath,
		final Set aSetOfAllRoles) {

		try {
			final String[] fileNames =
				new File(anOriginPath).list(ToZeroAndOneTableConverter.FILTER);
			for (int i = 0; i < fileNames.length; i++) {
				final String fileName = fileNames[i];
				Iterator iterator;

				System.out.print("Converting ");
				System.out.println(fileNames[i]);

				// Open output file and write its header.
				final Writer writer =
					ProxyDisk.getInstance().fileAbsoluteOutput(
						aDestinationPath
								+ fileName.replaceAll(".csv", ".allroles.csv"));
				writer.write(';');
				iterator = aSetOfAllRoles.iterator();
				while (iterator.hasNext()) {
					final String role = (String) iterator.next();
					writer.write(role);
					if (iterator.hasNext()) {
						writer.write(';');
					}
				}
				writer.write('\n');

				// Read each line of the input file and write the corresponding 
				// classname and 1s and 0s depending on the roles that it plays.
				final LineNumberReader reader =
					new LineNumberReader(
						new FileReader(anOriginPath + fileName));
				String line;
				while ((line = reader.readLine()) != null) {
					writer.write(line.substring(0, line.indexOf(';')));
					iterator = aSetOfAllRoles.iterator();
					while (iterator.hasNext()) {
						final String role = (String) iterator.next();
						// If the role exist on the line, then 1 is written, 0 else.
						if (line.indexOf(role) > 0) {
							writer.write("1");
						}
						else {
							writer.write("0");
						}
						if (iterator.hasNext()) {
							writer.write(';');
						}
					}
					writer.write('\n');
				}
				reader.close();
			}
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	private Set extractSetOfRolesFrom(final String aFilePath) {
		final Set setOfRoles = new HashSet();
		try {
			final LineNumberReader reader =
				new LineNumberReader(new FileReader(aFilePath));
			String line;
			while ((line = reader.readLine()) != null) {
				final StringTokenizer tokenizer =
					new StringTokenizer(line, ";");
				// Skip the name of the class.
				tokenizer.nextToken();
				while (tokenizer.hasMoreTokens()) {
					setOfRoles.add(tokenizer.nextToken());
				}
			}
			reader.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
		return setOfRoles;
	}
}
