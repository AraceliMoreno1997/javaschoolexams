package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Calculator {
    private float resolveParenthesis(String expression) {
        if (expression.charAt(0) == '(') {
            String expressionWithoutParenthesis = expression.substring(1, expression.length() - 1);
            return sum(expressionWithoutParenthesis);
        } else {
            return Float.valueOf(expression);
        }
    }

    private float divide(String expression) {
        List<String> subExpressions = splitConsideringParenthesis(expression, '/');
        int expressionsCount = subExpressions.size();
        float result = resolveParenthesis(subExpressions.get(0));
        for (int i = 1; i < expressionsCount; i++) {
            String subExpression = subExpressions.get(i);
            float numericValue = resolveParenthesis(subExpression);
            result = result / numericValue;
        }
        return result;
    }

    private float multiply(String expression) {
        List<String> subExpressions = splitConsideringParenthesis(expression, '*');
        int expressionsCount = subExpressions.size();
        float result = 1;
        for (int i = 0; i < expressionsCount; i++) {
            String subExpression = subExpressions.get(i);
            float numericValue = divide(subExpression);
            result = result * numericValue;
        }
        return result;
    }

    private float sub(String expression) {
        List<String> subExpressions = splitConsideringParenthesis(expression, '-');
        int expressionsCount = subExpressions.size();
        float result = multiply(subExpressions.get(0));
        for (int i = 1; i < expressionsCount; i++) {
            String subExpression = subExpressions.get(i);
            float numericValue = multiply(subExpression);
            result = result - numericValue;
        }

        return result;
    }

    private float sum(String expression) {
        List<String> subExpressions = splitConsideringParenthesis(expression, '+');
        int expressionsCount = subExpressions.size();
        float result = 0;
        for (int i = 0; i < expressionsCount; i++) {
            String subExpression = subExpressions.get(i);
            float numericValue = sub(subExpression);
            result = result + numericValue;
        }

        return result;
    }

    private List<String> splitConsideringParenthesis(String expression, char operator) {
        List<String> subExpressions = new ArrayList<>();
        int parLevel = 0;
        String subExpression = "";
        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);
            if (character == '(') {
                parLevel = parLevel + 1;
            } else if (character == ')') {
                parLevel = parLevel - 1;
            }

            if (parLevel == 0 && operator == character) {
                if (subExpression == "") {
                    return null;
                }
                subExpressions.add(subExpression);
                subExpression = "";
            } else {
                subExpression = subExpression + character;
            }
        }
        if (parLevel != 0) {
            return null;
        }
        if (subExpression != "") {
            subExpressions.add(subExpression);
        }
        return subExpressions;
    }

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as
     *                  decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is
     *         invalid
     */
    public String evaluate(String statement) {
        if (statement == "") {
            return null;
        }
        try {
            float result = sum(statement);
            if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) {
                return null;
            }
            Locale currentLocale = Locale.getDefault();
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
            otherSymbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("0.####", otherSymbols);
            return df.format(result);
        } catch (Exception e) {
            return null;
        }

    }
}
