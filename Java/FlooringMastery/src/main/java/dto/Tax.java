package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Tax {
    private String stateAbr;
    private String state;
    private BigDecimal taxRate;

    public Tax(String stateAbr, String state, BigDecimal taxRate) {
        this.stateAbr = stateAbr;
        this.state = state;
        this.taxRate = taxRate;
    }

    public String getStateAbr() {
        return stateAbr;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(stateAbr, tax.stateAbr) && Objects.equals(state, tax.state) && Objects.equals(taxRate, tax.taxRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateAbr, state, taxRate);
    }

    @Override
    public String toString() {
        return "Tax{" +
                "stateAbr='" + stateAbr + '\'' +
                ", state='" + state + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}
