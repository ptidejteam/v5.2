//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Grammar production:
 * f0 -> InstanceOfExpression()
 * f1 -> ( ( "==" | "!=" ) InstanceOfExpression() )*
 */
public class EqualityExpression implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public InstanceOfExpression f0;
   public NodeListOptional f1;

   public EqualityExpression(InstanceOfExpression n0, NodeListOptional n1) {
      this.f0 = n0;
      this.f1 = n1;
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

