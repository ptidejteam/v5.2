//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Grammar production:
 * f0 -> StatementExpression()
 * f1 -> ( "," StatementExpression() )*
 */
public class StatementExpressionList implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public StatementExpression f0;
   public NodeListOptional f1;

   public StatementExpressionList(StatementExpression n0, NodeListOptional n1) {
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

