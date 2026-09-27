package dao.implementations;

import dao.interfaces.TaxDao;
import dto.Tax;
import exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TaxDaoFileImplTest {

    TaxDao testDao;

    TaxDaoFileImplTest() throws PersistenceException {
    }

    @BeforeEach
    void setUp() throws Exception {
        String testFile = "testTaxes.txt";
        PrintWriter writer = new PrintWriter(new FileWriter(testFile));
        writer.println("TX::Texas::4.45");
        writer.println("CA::California::5.25");
        writer.close();
        testDao = new TaxDaoFileImpl(testFile);
    }

    @Test
    void testGetAllTaxes() {
        Tax cloneTax1 = new Tax("TX", "Texas", new BigDecimal("4.45"));
        Tax cloneTax2 = new Tax("CA", "California", new BigDecimal("5.25"));

        List<Tax> taxes = testDao.getAllTaxes();
        assertTrue(taxes.contains(cloneTax1), "Checking the tax list contains the first file line");
        assertTrue(taxes.contains(cloneTax2), "Checking the tax list contains the second file line");
    }
}