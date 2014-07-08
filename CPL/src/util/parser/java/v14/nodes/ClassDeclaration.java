//
// Generated by JTB 1.2.2
//

package util.parser.java.v14.nodes;

/**
 * Grammar production:
 * f0 -> ( "abstract" | "final" | "public" | "strictfp" )*
 * f1 -> UnmodifiedClassDeclaration()
 */
public class ClassDeclaration implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeListOptional f0;
   public UnmodifiedClassDeclaration f1;

   public ClassDeclaration(NodeListOptional n0, UnmodifiedClassDeclaration n1) {
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

