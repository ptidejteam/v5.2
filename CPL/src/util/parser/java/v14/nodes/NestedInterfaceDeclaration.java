//
// Generated by JTB 1.2.2
//

package util.parser.java.v14.nodes;

/**
 * Grammar production:
 * f0 -> ( "static" | "abstract" | "final" | "public" | "protected" | "private" | "strictfp" )*
 * f1 -> UnmodifiedInterfaceDeclaration()
 */
public class NestedInterfaceDeclaration implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeListOptional f0;
   public UnmodifiedInterfaceDeclaration f1;

   public NestedInterfaceDeclaration(NodeListOptional n0, UnmodifiedInterfaceDeclaration n1) {
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

