/*
 * (c) Copyright 2008 and following years, Julien Tanteri, University of
 * Montreal.
 * 
 * Use and copying of this software and preparation of derivative works based
 * upon this software are permitted. Any copy of this software or of any
 * derivative work must include the above copyright notice of the author, this
 * paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS ALL
 * WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, AND NOT
 * WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY LIABILITY FOR DAMAGES
 * RESULTING FROM THE SOFTWARE OR ITS USE IS EXPRESSLY DISCLAIMED, WHETHER
 * ARISING IN CONTRACT, TORT (INCLUDING NEGLIGENCE) OR STRICT LIABILITY, EVEN IF
 * THE AUTHOR IS ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package jct.test.rsc.snpsht.utils.diffutils.patch.context;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ContextFileWriter implements IContextFileWriter {
	private BufferedWriter writer;
	private String lastLine;
	private int currentLineNumber;

	public ContextFileWriter(OutputStream source) {
		this.currentLineNumber = 0;
		this.writer = new BufferedWriter(new OutputStreamWriter(source));
	}

	@Override
	public String getLastLine() {
		return this.lastLine;
	}

	@Override
	public int getLineNumber() {
		return this.currentLineNumber;
	}

	@Override
	public void writeLine(String toWrite) throws IOException {
		this.lastLine = toWrite;
		this.writer.write(toWrite + "\n", 0, toWrite.length() + 1);
		this.currentLineNumber++;
	}

	@Override
	public void close() throws IOException {
		this.writer.close();
	}

}
