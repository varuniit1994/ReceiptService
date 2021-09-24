package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ReceiptService {

	   public static final int DECIMAL_PLACES = 2;
	    private BigDecimal finalAmount = new BigDecimal("0.00");
	    private BigDecimal totalTax = new BigDecimal("0.00");

	    public BigDecimal getFinalAmount() {
	        return finalAmount;
	    }

	    public BigDecimal getTotalTax() {
	        return totalTax;
	    }

	    /* Calculate the final invoice */
	    public void calculateReceipt(List<Product> productList) {
	        productList.forEach(product -> {
	            finalAmount = finalAmount.add(product.getTotalPrice());
	            totalTax = totalTax.add(product.getTotalTaxAmount());
	        });

	        finalAmount = finalAmount.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);

	        totalTax = BigDecimal.valueOf(Math.ceil(totalTax.doubleValue() * 20) / 20);
	        totalTax = totalTax.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
	    }
	 
}
