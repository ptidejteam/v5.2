//
// Generated by JTB 1.2.2
//

package util.parser.java.v15.nodes;

/**
 * Represents an grammar optional node, e.g. ( A )? or [ A ]
 */
public class NodeOptional implements Node {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public NodeOptional() {
      this.node = null;
   }

   public NodeOptional(Node n) {
      addNode(n);
   }

   public void addNode(Node n)  {
      if ( this.node != null)                // Oh oh!
         throw new Error("Attempt to set optional node twice");

      this.node = n;
   }
   public void accept(util.parser.java.v15.visitors.Visitor v) {
      v.visit(this);
   }
   public Object accept(util.parser.java.v15.visitors.ObjectVisitor v, Object argu) {
      return v.visit(this,argu);
   }
   public boolean present()   { return this.node != null; }

   public Node node;
}

