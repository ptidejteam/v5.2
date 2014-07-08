//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Grammar production:
 * f0 -> Type()
 * f1 -> VariableDeclarator()
 * f2 -> ( "," VariableDeclarator() )*
 * f3 -> ";"
 */
public class FieldDeclaration implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public Type f0;
   public VariableDeclarator f1;
   public NodeListOptional f2;
   public NodeToken f3;

   public FieldDeclaration(Type n0, VariableDeclarator n1, NodeListOptional n2, NodeToken n3) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = n3;
   }

   public FieldDeclaration(Type n0, VariableDeclarator n1, NodeListOptional n2) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = new NodeToken(";");
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

