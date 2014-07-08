/**
 * @author Mathieu Lemoine
 * @created 2008-10-27 (月)
 *
 * Licensed under 4-clause BSD License:
 * Copyright © 2009, Mathieu Lemoine
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    This product includes software developed by Mathieu Lemoine and contributors.
 *  * Neither the name of Mathieu Lemoine nor the
 *    names of contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY Mathieu Lemoine ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Mathieu Lemoine BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jct.test.rsc.jct.kernel.impl;


import jct.test.rsc.jct.kernel.IJCTElement;
import jct.test.rsc.jct.kernel.IJCTIdentifiable;
import jct.test.rsc.jct.kernel.IJCTRootNode;
import jct.test.rsc.jct.kernel.IJCTSelector;


/**
 * Interface representing a selector.
 * 
 * Default implementation for {@link jct.test.rsc.jct.kernel.IJCTSelector}
 *
 * @author Mathieu Lemoine
 */
abstract class JCTSelector<Element extends IJCTIdentifiable, Component extends IJCTElement> extends JCTSourceCodePart<Component> implements IJCTSelector<Element>
{
    JCTSelector(final IJCTRootNode aRootNode)
    {
        super(aRootNode);
    }
    
    /**
     * Returns true iff o is a selector on an equal element
     */
    @Override
    public boolean equals(final Object o)
    {
        if(!(o instanceof IJCTSelector)) return false;
        if(null == this.getElement() && null == ((IJCTSelector)o).getElement()) return true;
        if(null == this.getElement() || null == ((IJCTSelector)o).getElement()) return false;
        
        return ((IJCTSelector)o).getElement().equals(this.getElement());
    }
    
    /**
     * Returns {@code this.getElement().hashCode()}
     */
    @Override
    public int hashCode()
    {
        return this.getElement().hashCode();
    }
    

}
