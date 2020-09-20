package ru.vasiliydz.javahomework.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.vasiliydz.javahomework.Customer;

public class CustomerTest {

    @Test
    public void test() {
        Customer vasya = new Customer("Vasya", "Pupkin");
        boolean actual;

        // Test1: полное имя
        Assert.assertEquals("Vasya Pupkin", vasya.fullName());

        // Test2: работа с несуществующим аккаунтом
        actual = vasya.closeAccount();
        Assert.assertFalse("closeAccount() failed: account doesn't exist", false);
        actual = vasya.addMoneyToCurrentAccount(10);
        Assert.assertFalse("addMoneyToCurrentAccount() failed: account doesn't exist", false);
        actual = vasya.withdrawFromCurrentAccount(10);
        Assert.assertFalse("withdrawFromCurrentAccount() failed: account doesn't exist", false);

        // Test3: создание аккаунта
        actual = vasya.openAccount(10);
        Assert.assertTrue("openAccount() failed: it should return true", actual);

        // Test4: повторное создание аккаунта
        actual = vasya.openAccount(20);
        Assert.assertFalse("openAccount() failed: account already created", actual);

        // Test5: работа с аккаунтом
        actual = vasya.addMoneyToCurrentAccount(10);
        Assert.assertTrue("addMoneyToCurrentAccount() failed: it should return true", actual);
        actual = vasya.withdrawFromCurrentAccount(7);
        Assert.assertTrue("withdrawFromCurrentAccount() failed: it should return true", actual);

        // Test6: удаление аккаунта
        actual = vasya.closeAccount();
        Assert.assertTrue("closeAccount() failed: it should return true", actual);

        // Test7: повторное удаление аккаунта
        actual = vasya.closeAccount();
        Assert.assertFalse("closeAccount() failed: account already closed", actual);
    }
}