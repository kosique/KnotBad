package de.diezwei.knotbad.szenarios;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import de.diezwei.knotbad.Solver;
import de.diezwei.knotbad.exception.VariableNotBoundException;

public class ScenarioSteps
{
    private Solver solver;

    @Given("an input reading $input")
    public void inputString(@Named("input") String input)
    {
        this.solver = new Solver(input);
    }

    @When("the variable $variable is bound to $value")
    public void bindVariable(@Named("variable") String variable, @Named("value") double value)
    {
        this.solver.bind(variable, value);
    }

    @Then("the solver calculates an result of $result")
    public void checkResult(@Named("result") double result)
    {
        assertThat(solver.solve(), equalTo(result));
    }

    @Then("the solver throws an exception")
    public void checkResult()
    {
        try
        {
            solver.solve();
            Assert.fail("Expected exception not thrown");
        }
        catch (final VariableNotBoundException e)
        {
            return;
        }
    }
}
