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
package caffeine.example.arctest;

import caffeine.Caffeine;
import caffeine.Constants;

/**
 * @author	Yann-Ga�l Gu�h�neuc
 * @version 0.2
 */
public final class CaffeineLauncher {
	public static void main(final String[] args) {
		Caffeine
			.getUniqueInstance()
			.start(
				"../Caffeine/Rules/OnlyStaticEvents.pl",
				"../Caffeine/cfparse.jar;../Caffeine/javassist.jar;../Caffeine/bin;../Caffeine Examples/bin",
				"caffeine.example.arctest.ArcTest",
				new String[] { "caffeine.example.arctest.*", },
				Constants.GENERATE_METHOD_ENTRY_EVENT
					| Constants.GENERATE_PROGRAM_END_EVENT,
				new String[][] {
					new String[] {
						"caffeine.example.arctest.ArcTest",
						"caffeine.example.arctest.ArcControls",
						"controls" },
					new String[] {
						"caffeine.example.arctest.ArcTest",
						"caffeine.example.arctest.ArcCanvas",
						"canvas" }
		});
	}
}