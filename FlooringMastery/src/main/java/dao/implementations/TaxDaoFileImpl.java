package dao.implementations;

import dao.interfaces.TaxDao;
import dto.Product;
import dto.Tax;
import exceptions.PersistenceException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class TaxDaoFileImpl implements TaxDao {

    private final String TAX_FILE;
    private final String DELIMITER = "::";

    private Map<String, Tax> allTaxes = new HashMap<>();

    public TaxDaoFileImpl() throws PersistenceException {
        TAX_FILE = "taxes.txt";
        loadFile();
    }

    public TaxDaoFileImpl(String fileName) throws PersistenceException {
        TAX_FILE = fileName;
        loadFile();
    }

    private void loadFile() throws PersistenceException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(TAX_FILE));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("Tax file not found", e);
        }

        while (scanner.hasNext()) {
            String taxLine = scanner.nextLine();
            String[] taxDetails = taxLine.split(DELIMITER);

            String stateAbr = taxDetails[0];
            String state = taxDetails[1];
            BigDecimal taxRate = new BigDecimal(taxDetails[2]);

            Tax tax = new Tax(stateAbr, state, taxRate);

            allTaxes.put(stateAbr, tax);
        }
    }

    @Override
    public List<Tax> getAllTaxes() {
        return new ArrayList<>(allTaxes.values());
    }
}
