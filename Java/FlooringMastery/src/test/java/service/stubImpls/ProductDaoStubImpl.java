package service.stubImpls;

import dao.interfaces.ProductDao;
import dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoStubImpl implements ProductDao {

    Product onlyProduct;

    public ProductDaoStubImpl() {
        onlyProduct = new Product("Tile", new BigDecimal("225.50"), new BigDecimal("4.45"));
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(onlyProduct);
        return products;
    }
}
