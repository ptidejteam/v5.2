//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Grammar production:
 * f0 -> ReferenceType()
 *       | PrimitiveType()
 */
public class Type implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeChoice f0;

   public Type(NodeChoice n0) {
      this.f0 = n0;
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

