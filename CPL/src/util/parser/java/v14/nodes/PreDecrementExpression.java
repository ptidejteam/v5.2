//
// Generated by JTB 1.2.2
//

package util.parser.java.v14.nodes;

/**
 * Grammar production:
 * f0 -> "--"
 * f1 -> PrimaryExpression()
 */
public class PreDecrementExpression implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeToken f0;
   public PrimaryExpression f1;

   public PreDecrementExpression(NodeToken n0, PrimaryExpression n1) {
      this.f0 = n0;
      this.f1 = n1;
   }

   public PreDecrementExpression(PrimaryExpression n0) {
      this.f0 = new NodeToken("--");
      this.f1 = n0;
   }

   public void accept(util.parser.java.v14.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v14.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

