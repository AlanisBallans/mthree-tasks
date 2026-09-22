package dao.implementations;

import dao.interfaces.ProductDao;
import dto.Product;
import exceptions.PersistenceException;

import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class ProductDaoFileImpl implements ProductDao {

    private final String PRODUCT_FILE;
    private final String DELIMITER = "::";

    private Map<String, Product> allProducts = new HashMap<>();

    public ProductDaoFileImpl() throws PersistenceException {
        PRODUCT_FILE = "products.txt";
        try {
            loadFile();
        } catch (Exception e) {
            throw new PersistenceException("Product file could not be loaded", e);
        }
    }

    public ProductDaoFileImpl(String fileName) throws PersistenceException {
        PRODUCT_FILE = fileName;
        try {
            loadFile();
        } catch (Exception e) {
            throw new PersistenceException("Product file could not be loaded", e);
        }
    }

    private void loadFile() throws Exception {
        Scanner scanner = new Scanner(new FileReader(PRODUCT_FILE));
        while (scanner.hasNext()) {
            String productLine = scanner.nextLine();
            String[] productDetails = productLine.split(DELIMITER);

            String productType = productDetails[0];
            BigDecimal costPerSquareFoot = new BigDecimal(productDetails[1]);
            BigDecimal laborCostPerSquareFoot = new BigDecimal(productDetails[2]);

            Product product = new Product(productType,costPerSquareFoot,laborCostPerSquareFoot);

            allProducts.put(productType, product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(allProducts.values());
    }
}
