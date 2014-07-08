/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.crosstabs.base;

import java.io.Serializable;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;

/**
 * Base read-only implementation of {@link net.sf.jasperreports.crosstabs.JRCrosstabCell JRCrosstabCell}.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseCrosstabCell.java,v 1.1 2008/09/29 16:22:09 guehene Exp $
 */
public class JRBaseCrosstabCell implements JRCrosstabCell, Serializable
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected Integer width;
	protected Integer height;
	protected String rowTotalGroup;
	protected String columnTotalGroup;
	protected JRCellContents contents;
	
	protected JRBaseCrosstabCell()
	{
	}
	
	public JRBaseCrosstabCell(JRCrosstabCell crosstabCell, JRBaseObjectFactory factory)
	{
		factory.put(crosstabCell, this);
		
		this.width = crosstabCell.getWidth();
		this.height = crosstabCell.getHeight();
		
		this.rowTotalGroup = crosstabCell.getRowTotalGroup();
		this.columnTotalGroup = crosstabCell.getColumnTotalGroup();
		
		this.contents = factory.getCell(crosstabCell.getContents());
	}

	public String getRowTotalGroup()
	{
		return this.rowTotalGroup;
	}

	public String getColumnTotalGroup()
	{
		return this.columnTotalGroup;
	}

	public JRCellContents getContents()
	{
		return this.contents;
	}

	public Integer getHeight()
	{
		return this.height;
	}

	public Integer getWidth()
	{
		return this.width;
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		JRBaseCrosstabCell clone = null;

		try
		{
			clone = (JRBaseCrosstabCell)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
		
		if (this.contents != null)
		{
			clone.contents = (JRCellContents)this.contents.clone();
		}

		return clone;
	}
}
