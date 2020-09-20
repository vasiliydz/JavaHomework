package ru.vasiliydz.javahomework.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.vasiliydz.javahomework.Account;

public class AccountTest {

    @Test
    public void test() {
        Account account = new Account(1);
        boolean actual;

        // Test1: снимаем деньги с нулевого счёта
        actual = account.withdraw(10);
        Assert.assertFalse("withdraw() failed: withdraw from account with zero balance", actual);

        // Test2: добавляем 0 рублей
        actual = account.add(0);
        Assert.assertFalse("add() failed: add(0) should return false", actual);

        // Test3: добавляем отрицательное число
        actual = account.add(-20);
        Assert.assertFalse("add() failed: add(< 0) should return false", actual);

        // Test4: добавляем положительное число
        actual = account.add(20);
        Assert.assertTrue("add() failed: add(> 0) should return true", actual);

        // Test5: снимаем 0 рублей
        actual = account.withdraw(0);
        Assert.assertFalse("withdraw() failed: withdraw(0) should return false", actual);

        // Test6: снимаем отрицательное число
        actual = account.withdraw(-15);
        Assert.assertFalse("withdraw() failed: withdraw(< 0) should return true", actual);

        // Test7: снимаем достаточное количество денег
        actual = account.withdraw(15);
        Assert.assertTrue("withdraw() failed: balance is enough but false returned", actual);

        // Test8: снимаем недостаточное количество денег
        actual = account.withdraw(10);
        Assert.assertFalse("withdraw() failed: balance is not enough but true returned", actual);
    }
}