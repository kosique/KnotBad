package de.diezwei.knotbad.szenarios;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import de.diezwei.knotbad.parser.Parser;

public class ScenarioSteps
{
	private String input;
	private Parser parser;
	private List<String> result;

	@Given("a new parser instance")
	public void inputString()
	{
		this.parser = new Parser();
	}

	@When("the input reads $input")
	public void input(@Named("input") String input)
	{
		this.input = input;
	}

	@When("the user runs the parser")
	public void parse()
	{
		//		this.result = this.parser.parse(input);
	}

	@Then("the computed result should be $result")
	public void checkResult(@Named("result") int result)
	{
		System.out.println("Result should be " + result);
	}
}
