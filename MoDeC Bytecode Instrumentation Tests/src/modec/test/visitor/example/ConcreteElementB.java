package modec.test.visitor.example;

/**
 * @(#)Text6.java
 *
 *
 * @author 
 * @version 1.00 2007/3/16
 */

public class ConcreteElementB implements Element {

	private String name;
	
    public ConcreteElementB() {
    	this.name = "ConcreteElementB";
    }
    
     public void acceptVisitor(Visitor v)
     {
     	v.visitConcreteElementB(this);
     }
     
     public String getName()
     {
     	return this.name;
     }
    
    
}