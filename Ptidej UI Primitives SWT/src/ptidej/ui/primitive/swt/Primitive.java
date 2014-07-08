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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import ptidej.ui.RGB;
import ptidej.ui.event.MouseEvent;

public abstract class Primitive implements ptidej.ui.primitive.IPrimitive {
	private Point origin;
	private Dimension dimension;
	private final RGB color;
	private Point destination;
	private final Device device;
	private final GC graphics;
	private boolean isSelected;

	/*
	 * An helper method to convert RGB instances into Color instances.
	 * This method is memory-efficient...
	 */
	private static final Map colors = new HashMap();
	public static Color convertColor(final Device device, final RGB rgbColor) {

		if (Primitive.colors.containsKey(rgbColor)) {
			return (Color) Primitive.colors.get(rgbColor);
		}
		else {
			final Color newColor =
				new Color(
					device,
					rgbColor.getRed(),
					rgbColor.getGreen(),
					rgbColor.getBlue());
			Primitive.colors.put(rgbColor, newColor);
			return newColor;
		}
	}

	protected Primitive(
		final Device device,
		final GC graphics,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		this.device = device;
		this.graphics = graphics;
		this.origin = origin;
		this.dimension = dimension;
		this.color = color;

		this.computeDestination();
	}
	public final void build() {
		// throw new RuntimeException("The build() method of ptidej.primitive.awt.Primitive must not be called.");
	}
	private void computeDestination() {
		this.destination =
			new Point(this.origin.x + this.dimension.width, this.origin.y
					+ this.dimension.height);
	}
	public final Point getDestination() {
		return this.destination;
	}
	public final Device getDevice() {
		return this.device;
	}
	public final Dimension getDimension() {
		return this.dimension;
	}
	public final GC getGraphics() {
		return this.graphics;
	}
	public String getName() {
		return "ptidej.ui.primitive.swt.Primitive (" + this.getClass() + ')';
	}
	public final Point getPosition() {
		return this.origin;
	}
	public RGB getRGBColor() {
		return this.color;
	}
	public Color getSWTColor() {
		return Primitive.convertColor(this.getDevice(), this.color);
	}
	public boolean isNameShowable() {
		return false;
	}
	public boolean isSelected() {
		return this.isSelected;
	}
	public boolean processMouseEvent(final MouseEvent me) {
		return false;
	}
	public void setDimension(final Dimension dimension) {
		this.dimension = dimension;
		this.computeDestination();
	}
	public void setPosition(final Point position) {
		this.origin = position;
		this.computeDestination();
	}
	public void setSelected(final boolean isSelected) {
		this.isSelected = isSelected;
	}
}
