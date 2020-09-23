package ru.vasiliydz.javahomework.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.vasiliydz.javahomework.Account;
import ru.vasiliydz.javahomework.Customer;

public class CustomerTest {

    @Test
    public void fullName_whenNamesAreNotEmpty() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin");
        String expectedFullName = "Vasya Pupkin";
        // when
        String actualFullName = customer.fullName();
        // then
        Assert.assertEquals(expectedFullName, actualFullName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fullName_excepts_whenNameIsNull() {
        new Customer(null, "Pupkin");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fullName_excepts_whenLastNameIsNull() {
        new Customer("Vasya", null);
    }

    @Test
    public void openAccount_returnsTrue_whenItDoesNotExist() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", null);
        // when
        boolean isSuccess = customer.openAccount(1);
        // then
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void openAccount_returnsFalse_whenItExists() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", new Account(1));
        // when
        boolean isSuccess = customer.openAccount(2);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void openAccount_returnsFalse_whenItIsOpenedTwice() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", null);
        customer.openAccount(1);
        // when
        boolean isSuccess = customer.openAccount(2);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void closeAccount_returnsFalse_whenItDoesNotExist() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", null);
        // when
        boolean isSuccess = customer.closeAccount();
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void closeAccount_returnsTrue_whenItExists() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", new Account(1));
        // when
        boolean isSuccess = customer.closeAccount();
        // then
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void closeAccount_returnsFalse_whenItIsClosedTwice() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", new Account(1));
        customer.closeAccount();
        // when
        boolean isSuccess = customer.closeAccount();
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void withdrawFromCurrentAccount_returnsFalse_whenAccountDoesNotExist() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", null);
        // when
        boolean isSuccess = customer.withdrawFromCurrentAccount(10);
        // then
        Assert.assertFalse(isSuccess);
    }

    @Test
    public void addMoneyToCurrentAccount_returnsFalse_whenAccountDoesNotExist() {
        // given
        Customer customer = new Customer("Vasya", "Pupkin", null);
        // when
        boolean isSuccess = customer.addMoneyToCurrentAccount(10);
        // then
        Assert.assertFalse(isSuccess);
    }

}