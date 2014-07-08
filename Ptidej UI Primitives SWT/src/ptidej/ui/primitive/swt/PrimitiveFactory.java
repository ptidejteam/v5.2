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
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.ui.primitive.swt;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import ptidej.ui.RGB;
import ptidej.ui.primitive.IAggregationSymbol;
import ptidej.ui.primitive.IArrowSymbol;
import ptidej.ui.primitive.IAssociationSymbol;
import ptidej.ui.primitive.IButton;
import ptidej.ui.primitive.ICompositionSymbol;
import ptidej.ui.primitive.IDottedLine;
import ptidej.ui.primitive.IDottedSquareLine;
import ptidej.ui.primitive.IDottedTriangle;
import ptidej.ui.primitive.IDoubleSquareLine;
import ptidej.ui.primitive.IInheritanceSymbol;
import ptidej.ui.primitive.ILabel;
import ptidej.ui.primitive.ILine;
import ptidej.ui.primitive.IPlainSquareLine;
import ptidej.ui.primitive.IPlainTriangle;
import ptidej.ui.primitive.IPrimitiveFactory;
import ptidej.ui.primitive.IRectangle;

public class PrimitiveFactory implements IPrimitiveFactory {
	private static Map UniqueInstance = new HashMap(1);
	// Yann 2002/12/16: Multipe-singletons!
	// The PrimitiveFactory for SWT is a bit particular.
	// I want one and only one instance of PrimitiveFactory
	// associated with a given graphic context, but I can
	// have multiple instances of graphic contexts and thus
	// multiple instances of PrimitiveFactory: one per
	// graphic context.
	public static IPrimitiveFactory getInstance(
		final Device device,
		final GC graphics) {

		if (PrimitiveFactory.UniqueInstance.get(graphics) == null) {
			final PrimitiveFactory primitiveFactory = new PrimitiveFactory();
			primitiveFactory.setDevice(device);
			primitiveFactory.setGraphics(graphics);
			PrimitiveFactory.UniqueInstance.put(graphics, primitiveFactory);
		}

		return (IPrimitiveFactory) PrimitiveFactory.UniqueInstance
			.get(graphics);
	}
	private Device device;

	private GC graphics;
	public final IAggregationSymbol createAggregationSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new AggregationSymbol(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public final IArrowSymbol createArrowSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new ArrowSymbol(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public final IAssociationSymbol createAssociationSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new AssociationSymbol(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public final IButton createButton(
		final String label,
		final Point position,
		final boolean centerText,
		final RGB color) {

		return new Button(
			this.device,
			this.graphics,
			label,
			position,
			centerText,
			color);
	}
	public final IButton createButton(
		final String label,
		final Point position,
		final Dimension dimension,
		final boolean centerText,
		final RGB color) {

		return new Button(
			this.device,
			this.graphics,
			label,
			position,
			dimension,
			centerText,
			color);
	}
	public final ICompositionSymbol createCompositionSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new CompositionSymbol(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public IDoubleSquareLine createDottedDoubleSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new DottedDoubleSquareLine(
			this.getDevice(),
			this.getGraphics(),
			origin,
			dimension,
			color);

	}
	public final IDottedLine createDottedLine(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new DottedLine(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public final IDottedSquareLine createDottedSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new DottedSquareLine(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public final IDottedTriangle createDottedTriangle(
		final Point origin,
		final int symbolDirection,
		final RGB color) {

		return new DottedTriangle(
			this.device,
			this.graphics,
			origin,
			symbolDirection,
			color);
	}
	public final ILabel createLabel(
		final String label,
		final Point position,
		final Dimension dimension,
		final RGB color) {

		return new Label(
			this.device,
			this.graphics,
			label,
			position,
			dimension,
			color);
	}
	public final ILine createLine(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new Line(this.device, this.graphics, origin, dimension, color);
	}
	public IDoubleSquareLine createPlainDoubleSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new PlainDoubleSquareLine(
			this.getDevice(),
			this.getGraphics(),
			origin,
			dimension,
			color);
	}
	public final IPlainSquareLine createPlainSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new PlainSquareLine(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public final IPlainTriangle createPlainTriangle(
		final Point origin,
		final int symbolDirection,
		final RGB color) {

		return new PlainTriangle(
			this.device,
			this.graphics,
			origin,
			symbolDirection,
			color);
	}
	public final IRectangle createRectangle(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		return new Rectangle(
			this.device,
			this.graphics,
			origin,
			dimension,
			color);
	}
	public final Device getDevice() {
		return this.device;
	}
	public final GC getGraphics() {
		return this.graphics;
	}
	public final void setDevice(final Device device) {
		this.device = device;
	}

	public final void setGraphics(final GC graphics) {
		this.graphics = graphics;
	}
	public IInheritanceSymbol createInheritanceSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		// TODO Auto-generated method stub
		return null;
	}
}
