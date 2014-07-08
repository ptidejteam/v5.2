//
// Generated by JTB 1.2.2
//

package util.parser.java.v14.nodes;

/**
 * Grammar production:
 * f0 -> "this" Arguments() ";"
 *       | [ PrimaryExpression() "." ] "super" Arguments() ";"
 */
public class ExplicitConstructorInvocation implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeChoice f0;

   public ExplicitConstructorInvocation(NodeChoice n0) {
      this.f0 = n0;
   }

   public void accept(util.parser.java.v14.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v14.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

