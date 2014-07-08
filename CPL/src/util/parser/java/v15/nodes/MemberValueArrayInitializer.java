//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Grammar production:
 * f0 -> "{"
 * f1 -> MemberValue()
 * f2 -> ( "," MemberValue() )*
 * f3 -> [ "," ]
 * f4 -> "}"
 */
public class MemberValueArrayInitializer implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public NodeToken f0;
   public MemberValue f1;
   public NodeListOptional f2;
   public NodeOptional f3;
   public NodeToken f4;

   public MemberValueArrayInitializer(NodeToken n0, MemberValue n1, NodeListOptional n2, NodeOptional n3, NodeToken n4) {
      this.f0 = n0;
      this.f1 = n1;
      this.f2 = n2;
      this.f3 = n3;
      this.f4 = n4;
   }

   public MemberValueArrayInitializer(MemberValue n0, NodeListOptional n1, NodeOptional n2) {
      this.f0 = new NodeToken("{");
      this.f1 = n0;
      this.f2 = n1;
      this.f3 = n2;
      this.f4 = new NodeToken("}");
   }

   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
}

