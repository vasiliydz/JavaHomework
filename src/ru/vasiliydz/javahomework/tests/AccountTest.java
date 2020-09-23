package ru.vasiliydz.javahomework.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.vasiliydz.javahomework.Account;

public class AccountTest {

    @Test
    public void withdraw_returnsFalse_whenBalanceIsZero() {
        // given
        Account account = new Account(1, 0);
        double amount = 5;
        // when
        boolean isSuccess = account.withdraw(amount);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void withdraw_returnsFalse_whenAmountIsZero() {
        // given
        Account account = new Account(1, 10);
        double amount = 0;
        // when
        boolean isSuccess = account.withdraw(amount);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void withdraw_returnsFalse_whenAmountIsNegative() {
        // given
        Account account = new Account(1, 10);
        double amount = -5;
        // when
        boolean isSuccess = account.withdraw(amount);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void withdraw_returnsTrue_whenBalanceIsEnoughToWithdraw() {
        // given
        Account account = new Account(1, 10);
        double amount = 5;
        // when
        boolean isSuccess = account.withdraw(amount);
        // then
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void withdraw_returnsFalse_whenBalanceIsNotEnoughToWithdraw() {
        // given
        Account account = new Account(1, 10);
        double amount = 15;
        // when
        boolean isSuccess = account.withdraw(amount);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void add_returnsFalse_whenAmountIsZero() {
        // given
        Account account = new Account(1);
        double amount = 0;
        // when
        boolean isSuccess = account.add(amount);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void add_returnsFalse_whenAmountIsNegative() {
        // given
        Account account = new Account(1);
        double amount = -10;
        // when
        boolean isSuccess = account.add(amount);
        //then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void add_returnsTrue_whenAmountIsPositive() {
        // given
        Account account = new Account(1);
        double amount = 10;
        // when
        boolean isSuccess = account.add(amount);
        // then
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void addingAndWithdrawingMoney() {
        // given
        Account account = new Account(1);
        double amount = 10;
        account.add(amount);
        // when
        boolean isSuccess = account.withdraw(amount);
        // then
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void withdrawMoneyTwice_secondWithdrawingIsUnsuccessful() {
        // given
        Account account = new Account(1, 15);
        account.withdraw(10);
        double amount = 10;
        // when
        boolean isSuccess = account.withdraw(amount);
        // then
        Assert.assertFalse(isSuccess);
    }

}