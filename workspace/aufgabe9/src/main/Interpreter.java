package main;
import memory.Variables;
import parser.Builder;

public class Interpreter implements Builder<Double> {

    public Double add(Double left, Double right) {
        return left + right;
    }

    public Double sub(Double left, Double right) {
        return left - right;
    }

    public Double mul(Double left, Double right) {
        return left * right;
    }

    public Double div(Double left, Double right) {
        return left / right;
    }

    public Double negate(Double expression) {
        return -expression;
    }

    public Double number(String number) {
        return Double.valueOf(number);
    }

    public Double let(String id, Double expression) {
        Variables.store(id, expression);
        return expression;
    }

    public Double id(String id) {
        return Variables.load(id);
    }
}
