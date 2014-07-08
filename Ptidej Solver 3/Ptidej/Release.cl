// (c) Copyright 2000-2002 Yann-Ga�l Gu�h�neuc,
// Ecole des Mines de Nantes and Object Technology International, Inc.
// 
// Use and copying of this software and preparation of derivative works
// based upon this software are permitted. Any copy of this software or
// of any derivative work must include the above copyright notice of
// Yann-Ga�l Gu�h�neuc, this paragraph and the one after it.
// 
// This software is made available AS IS, and THE AUTHOR DISCLAIMS
// ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
// PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
// LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
// EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
// NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGES.
// 
// All Rights Reserved.

[ptidejVersion() : string
	->	"1.0.1"
]
[ptidejReleaseDate() : string
	->	"July 11, 2005"
]
[ptidejInfo() : string
	->	"++ Ptidej: Pattern Trace Identification Detection and Enhancement for Java\n++ Constraint Programming for Design Patterns and Design Defects Identification"
]

[showPtidejLicense() : void
    ->  printf(
    		"~A \n++ Ptidej Solver v~A (~A), Copyright (c) 2001-2004 Y.-G. Gu�h�neuc\n",
    		ptidejInfo(),
    		ptidejVersion(),
    		ptidejReleaseDate())
]

(showPtidejLicense())
