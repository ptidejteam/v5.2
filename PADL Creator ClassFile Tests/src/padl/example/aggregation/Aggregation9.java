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
package padl.example.aggregation;

/**
 * @version	0.1
 * @author 	Yann-Ga�l Gu�h�neuc
 * 
 * One container aggregation relationship with A through
 * methods addToA(), getA(), and removeFromA().
 * 
 * Three use relationships with A through parameters
 * and return types of methods addToA(), getA(), and removeFromA().
 */
public class Aggregation9 {
	private A[] a;
	void addToA(final int index, final A newA) {
		this.a[index] = newA;
	}
	A getA(final int index) {
		return this.a[index];
	}
	void removeFromA(final A newA) {
	}
}
