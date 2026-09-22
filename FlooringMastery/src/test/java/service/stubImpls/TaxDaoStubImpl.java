package service.stubImpls;

import dao.interfaces.TaxDao;
import dto.Tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxDaoStubImpl implements TaxDao {

    Tax onlyTax;

    public TaxDaoStubImpl() {
        onlyTax = new Tax("CA", "California", new BigDecimal("2.25"));
    }


    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> taxes = new ArrayList<>();
        taxes.add(onlyTax);
        return taxes;
    }
}
