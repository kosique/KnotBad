package de.diezwei.knotbad.szenarios;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;

import de.diezwei.knotbad.Solver;

public class ScenarioSteps
{
    private Solver solver;

    @Given("an input reading $input")
    public void inputString(@Named("input") String input)
    {
        this.solver = new Solver(input);
    }

    @Then("the solver calculates an result of $result")
    public void checkResult(@Named("result") double result)
    {
        assertThat(solver.solve(), equalTo(result));
    }
}
