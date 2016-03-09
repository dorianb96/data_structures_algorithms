package data_structures.Stack;

public class StackCalculator {
    /**
     * PSEUDOCODE:
     * stack = empty
     * for each element of expression
     *   if element is number, push it on stack and continue
     *   else pop two elements from stack, perform operation and push result back on stack
     * end for
     * if stack size different from 1, write error
     * else syso stack.pop()
     * @param input the expression for calculation
     * @return result of calculation
     */
    public String calculate(String input){
        // we get the initial expression and split it by spaces
        String[] expression = input.split(" ");
        // also make the stack
        StackObject<Integer> stack = new StackObject<>();
        // for every element in the original expression
        for (String elem : expression){
            System.out.println("pushing " + elem);
            // if the expressed value is integer, push it to the stack
            try{
                Integer num = Integer.parseInt(elem);
                stack.push(num);
            }
            // if the value isn't an integer, than it is a mathematical expression
            // now we must evaluate the expression
            catch (Exception e){
                // if there are less than two integers in the stack
                // throw an error
                if (stack.size() < 2){
                    throw new Error("Error with input");
                }
                // otherwise evaluate the result from last two integers and the expression
                // the evaluate is a helper method
                int result = evaluate(stack.pop(), elem, stack.pop());
                // then push the result back on stack for further evaluation
                stack.push(result);
            }
        }
        // if all that finishes an we still haven't emptied the stack
        // throw a new error
        if (stack.size() != 1){
            throw new Error("Some error with input");
        }

        return ("The result is " + stack.pop());
    }

    public Integer evaluate(Integer num1, String expression, Integer num2){
        switch (expression) {
            case "+":
                return num2 + num1;
            case "-":
                return num2 - num1;
            case "/":
                if (num1 == 0) {
                    throw new NumberFormatException("Can't divide with zero");
                }
                return num2 / num1;
            case "*":
                return num2 * num1;
            case "%":
                return num2 % num1;
            default:
                throw new NumberFormatException("Not supported expression");
        }

    }
}
