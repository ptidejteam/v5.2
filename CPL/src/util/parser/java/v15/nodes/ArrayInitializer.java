//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Grammar production:
 * f0 -> "{"
 * f1 -> [ VariableInitializer() ( "," VariableInitializer() )* ]
 * f2 -> [ "," ]
 * f3 -> "}"
 */
public class ArrayInitializer implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeToken f0;
   public NodeOptional f1;
   public NodeOptional f2;
   public NodeToken f3;

   public ArrayInitializer(NodeToken n0, NodeOptional n1, NodeOptional n2, NodeToken n3) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = n3;
   }

   public ArrayInitializer(NodeOptional n0, NodeOptional n1) {
      this.f0 = new NodeToken("{");
      this.f1 = n0;
      this.f2 = n1;
      this.f3 = new NodeToken("}");
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

