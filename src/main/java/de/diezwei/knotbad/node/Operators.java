package de.diezwei.knotbad.node;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import de.diezwei.knotbad.exception.UnexpectedKnotBadException;
import de.diezwei.knotbad.parser.token.AssocType;

public class Operators
{
	private final Map<String, Integer> precedences = new HashMap<>();
	private final Map<String, AssocType> assocTypes = new HashMap<>();
	private final Map<String, Class<? extends Operator>> classTypes = new HashMap<>();

	private final Map<String, Class<? extends Function>> functionTypes = new HashMap<>();

	private static Operators instance = new Operators();

	private Operators()
	{
		super();

		final Reflections reflections = new Reflections(
				new ConfigurationBuilder()
						.setScanners(new SubTypesScanner())
						.setUrls(ClasspathHelper.forPackage("de.diezwei.knotbad.operator"))
						.filterInputsBy(new FilterBuilder().exclude(NullOperator.class.getName() + ".class"))
				);

		final Set<Class<? extends Operator>> operatorTypes = reflections.getSubTypesOf(Operator.class);

		for (final Class<? extends Operator> operatorType : operatorTypes)
		{
			try
			{
				if (!Modifier.isAbstract(operatorType.getModifiers()))
				{
					register(operatorType.newInstance());
				}
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				throw new UnexpectedKnotBadException("Cannot instantiate operator " + operatorType);
			}
		}

		final Reflections reflections1 = new Reflections(
				new ConfigurationBuilder()
						.setScanners(new SubTypesScanner())
						.setUrls(ClasspathHelper.forPackage("de.diezwei.knotbad.operator"))
						.filterInputsBy(new FilterBuilder().exclude(NullFunction.class.getName() + ".class"))
				);

		final Set<Class<? extends Function>> functionTypes = reflections1.getSubTypesOf(Function.class);

		for (final Class<? extends Function> functionType : functionTypes)
		{
			try
			{
				if (!Modifier.isAbstract(functionType.getModifiers()))
				{
					register(functionType.newInstance());
				}
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				throw new UnexpectedKnotBadException("Cannot instantiate operator " + functionType);
			}
		}

	}

	private void register(Operator operator)
	{
		this.precedences.put(operator.getLiteral(), operator.getPrecedence());
		this.assocTypes.put(operator.getLiteral(), operator.getAssocType());
		this.classTypes.put(operator.getLiteral(), operator.getClass());
	}

	private void register(Function function)
	{
		this.functionTypes.put(function.getLiteral(), function.getClass());
		this.precedences.put(function.getLiteral(), function.getPrecedence());
		this.assocTypes.put(function.getLiteral(), function.getAssocType());
	}

	public static Operators getInstance()
	{
		return instance;
	}

	public AssocType getAssocType(String literal)
	{
		return assocTypes.get(literal);
	}

	public int getPrecedence(String literal)
	{
		return precedences.get(literal);
	}

	public Class<? extends Operator> getOperatorClass(String literal)
	{
		return classTypes.get(literal);
	}

	public boolean isFunction(String word)
	{
		return (this.functionTypes.containsKey(word));
	}

	public Class<? extends Function> getFunctionClass(String literal)
	{
		final Class<? extends Function> type = functionTypes.get(literal);

		if (type == null)
		{
			return NullFunction.class;
		}

		return type;
	}
}
