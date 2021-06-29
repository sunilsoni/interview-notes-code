package com.interview.notes.code.misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BalancedBracketsUsingStringTest {
    private BalancedBracketsUsingString balancedBracketsUsingString;

    @BeforeEach
    public void setup() {
        balancedBracketsUsingString = new BalancedBracketsUsingString();
    }

    @Test
    public void givenNullInput_whenCheckingForBalance_shouldReturnFalse() {
        boolean result = balancedBracketsUsingString.isBalanced(null);
        assertThat(result).isFalse();
    }

    @Test
    public void givenEmptyString_whenCheckingForBalance_shouldReturnTrue() {
        boolean result = balancedBracketsUsingString.isBalanced("");
        assertThat(result).isTrue();
    }

    @Test
    public void givenInvalidCharacterString_whenCheckingForBalance_shouldReturnFalse() {
        boolean result = balancedBracketsUsingString.isBalanced("abc[](){}");
        assertThat(result).isFalse();
    }

    @Test
    public void givenOddLengthString_whenCheckingForBalance_shouldReturnFalse() {
        boolean result = balancedBracketsUsingString.isBalanced("{{[]()}}}");
        assertThat(result).isFalse();
    }

    @Test
    public void givenEvenLengthString_whenCheckingForBalance_shouldReturnFalse() {
        boolean result = balancedBracketsUsingString.isBalanced("{{[]()}}}}");
        assertThat(result).isFalse();
    }

    @Test
    public void givenEvenLengthUnbalancedString_whenCheckingForBalance_shouldReturnFalse() {
        boolean result = balancedBracketsUsingString.isBalanced("{[(])}");
        assertThat(result).isFalse();
    }

    @Test
    public void givenEvenLengthBalancedString_whenCheckingForBalance_shouldReturnTrue() {
        boolean result = balancedBracketsUsingString.isBalanced("{[()]}");
        assertThat(result).isTrue();
    }

    @Test
    public void givenBalancedString_whenCheckingForBalance_shouldReturnTrue() {
        boolean result = balancedBracketsUsingString.isBalanced("{{[[(())]]}}");
        assertThat(result).isTrue();
    }

    @Test
    public void givenAnotherBalancedString_whenCheckingForBalance_shouldReturnTrue() {
        boolean result = balancedBracketsUsingString.isBalanced("{{([])}}");
        assertThat(result).isTrue();
    }

    @Test
    public void givenUnBalancedString_whenCheckingForBalance_shouldReturnFalse() {
        boolean result = balancedBracketsUsingString.isBalanced("{{)[](}}");
        assertThat(result).isFalse();
    }

}