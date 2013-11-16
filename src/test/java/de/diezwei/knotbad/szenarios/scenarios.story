Scenario:  Solving simple number

Given an input reading 1
Then the solver calculates an result of 1

Scenario:  Solving smaller expressiong

Given an input reading 1 + 2
Then the solver calculates an result of 3  

Given an input reading 1 - 2
Then the solver calculates an result of -1  

Given an input reading 1 + 2 * 3
Then the solver calculates an result of 7  

Given an input reading 7 / 2
Then the solver calculates an result of 3.5  

Given an input reading 10! / 9!
Then the solver calculates an result of 10




Scenario: Solving expressions with braces 

Given an input reading (1+2)*3
Then the solver calculates an result of 9

Given an input reading (((1+2)*(3+4)) - 11)!
Then the solver calculates an result of 3628800

Given an input reading (((1+2)!)+(1+2!)+1)!
Then the solver calculates an result of 3628800




Scenario: Solving expressions with functions
  
Given an input reading min(1,2)
Then the solver calculates an result of 1

Given an input reading min(1+2,2)
Then the solver calculates an result of 2

Given an input reading min(10+10+10,2*(3*4))
Then the solver calculates an result of 24

Given an input reading min(10+10+10,2*(3*4)) - 10
Then the solver calculates an result of 14

Given an input reading (min(10+10+10,2*(3*4)) - 14)!
Then the solver calculates an result of 3628800 




Scenario: Overloaded -

Given an input reading 5 - 2
Then the solver calculates an result of 3
  
Given an input reading 5 * 2
Then the solver calculates an result of 10
  
Given an input reading -5 * 2
Then the solver calculates an result of -10
  
Given an input reading -5 * -2
Then the solver calculates an result of 10
  
Given an input reading 5-2
Then the solver calculates an result of 3
  
Given an input reading 5+-2
Then the solver calculates an result of 3
  