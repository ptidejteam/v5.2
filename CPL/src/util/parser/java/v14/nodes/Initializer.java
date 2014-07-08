//
// Generated by JTB 1.2.2
//

package util.parser.java.v14.nodes;

/**
 * Grammar production:
 * f0 -> [ "static" ]
 * f1 -> Block()
 */
public class Initializer implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeOptional f0;
   public Block f1;

   public Initializer(NodeOptional n0, Block n1) {
      this.f0 = n0;
      this.f1 = n1;
   }

   public void accept(util.parser.java.v14.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v14.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

