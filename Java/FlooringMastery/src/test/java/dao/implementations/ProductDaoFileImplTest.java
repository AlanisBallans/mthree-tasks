package dao.implementations;

import dao.interfaces.ProductDao;
import dto.Product;
import exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoFileImplTest {

    ProductDao testDao;

    ProductDaoFileImplTest() throws PersistenceException {
    }

    @BeforeEach
    void setUp() throws Exception {
        String testFile = "testProducts.txt";
        PrintWriter writer = new PrintWriter(new FileWriter(testFile));
        writer.println("Tile::2.50::4.50");
        writer.println("Wood::3.35::5.75");
        writer.close();
        testDao = new ProductDaoFileImpl(testFile);
    }

    @Test
    void testGetAllProducts() {
        Product cloneProduct1 = new Product("Tile", new BigDecimal("2.50"), new BigDecimal("4.50"));
        Product cloneProduct2 = new Product("Wood", new BigDecimal("3.35"), new BigDecimal("5.75"));

        List<Product> products = testDao.getAllProducts();
        assertTrue(products.contains(cloneProduct1), "Checking the product list contains the first file line");
        assertTrue(products.contains(cloneProduct2), "Checking the product list contains the second file line");
    }
}