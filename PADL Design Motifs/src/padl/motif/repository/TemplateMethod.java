package padl.motif.repository;

import padl.kernel.IClass;
import padl.kernel.IMethod;
import padl.motif.models.BehaviouralMotifModel;
import util.multilingual.MultilingualManager;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class TemplateMethod extends BehaviouralMotifModel implements Cloneable {
	private static final char[] ABSTRACT_CLASS = "AbstractClass".toCharArray();
	private static final char[] CONCREATE_CLASS = "ConcreateClass"
		.toCharArray();
	private static final char[] PRIMITIVE_OPERATION = "PrimitiveOperation"
		.toCharArray();
	private static final long serialVersionUID = 8909819592555162188L;
	private static final char[] TEMPLATE_METHOD = "TemplateMethod"
		.toCharArray();

	public TemplateMethod() {
		super(TemplateMethod.TEMPLATE_METHOD);

		final IClass abstractClass =
			this.getFactory().createClass(
				TemplateMethod.ABSTRACT_CLASS,
				TemplateMethod.ABSTRACT_CLASS);
		abstractClass.setAbstract(true);
		final IMethod templateMethod =
			this.getFactory().createMethod(
				TemplateMethod.TEMPLATE_METHOD,
				TemplateMethod.TEMPLATE_METHOD);
		templateMethod.setAbstract(true);
		abstractClass.addConstituent(templateMethod);
		final IMethod primitiveOperation =
			this.getFactory().createMethod(
				TemplateMethod.PRIMITIVE_OPERATION,
				TemplateMethod.PRIMITIVE_OPERATION);
		primitiveOperation.setAbstract(true);
		abstractClass.addConstituent(primitiveOperation);

		final IClass concreateClass =
			this.getFactory().createClass(
				TemplateMethod.CONCREATE_CLASS,
				TemplateMethod.CONCREATE_CLASS);
		concreateClass.addInheritedEntity(abstractClass);

		final IMethod concreatePrimitiveOperation =
			this.getFactory().createMethod(
				TemplateMethod.PRIMITIVE_OPERATION,
				TemplateMethod.PRIMITIVE_OPERATION);
		concreateClass.addConstituent(concreatePrimitiveOperation);

		this.addConstituent(abstractClass);
		this.addConstituent(concreateClass);
	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", Flyweight.class);
	}

	public char[] getName() {
		return TemplateMethod.TEMPLATE_METHOD;
	}
}
