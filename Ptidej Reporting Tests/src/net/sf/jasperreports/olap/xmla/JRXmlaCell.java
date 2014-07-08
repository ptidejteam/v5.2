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
package net.sf.jasperreports.olap.xmla;

import net.sf.jasperreports.olap.result.JROlapCell;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRXmlaCell.java,v 1.1 2008/09/29 16:21:36 guehene Exp $
 */
public class JRXmlaCell implements JROlapCell
{
	
	private final Object value;
	private final String formattedValue;

	public JRXmlaCell(Object value, String formattedValue)
	{
		this.value = value;
		this.formattedValue = formattedValue;
	}
	
	public String getFormattedValue()
	{
		return this.formattedValue;
	}

	public Object getValue()
	{
		return this.value;
	}

	public boolean isError()
	{
		//TODO 
		return false;
	}

	public boolean isNull()
	{
		return this.value == null;
	}

}
