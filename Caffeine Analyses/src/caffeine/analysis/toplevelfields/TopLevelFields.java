/*
 * (c) Copyright 2002-2003 Yann-Ga�l Gu�h�neuc,
 * �cole des Mines de Nantes and Object Technology International, Inc.
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
package caffeine.analysis.toplevelfields;

/**
 * @version 0.1
 * @author	Yann-Ga�l Gu�h�neuc
 */
public class TopLevelFields {
	private ContainerObject container;
	private ContainedObject object1;
	private ContainedObject object2;

	public static void main(String[] args) {
		final TopLevelFields tlf = new TopLevelFields();
		tlf.setUp();
		tlf.operation();
		System.exit(0);
	}

	public TopLevelFields() {
	}
	public void setUp() {
		this.object1 = new ContainedObject();
		this.object2 = new ContainedObject();
		this.container = new ContainerObject(this.object1, this.object2);
	}
	public void operation() {
		this.container.operation();
		this.object1.operation();
		this.object2.operation();
	}
}