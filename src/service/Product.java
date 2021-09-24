package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static service.ReceiptService.DECIMAL_PLACES;
public class Product {

	private final String name;
	private final int qty;
	private final BigDecimal price;
	private final BigDecimal basicTaxPercent;
	private final BigDecimal importDutyTaxPercent;
	private final boolean isImported;
	private final boolean isBasicTaxExempted;
	private BigDecimal totalPrice;
	private BigDecimal totalTaxAmount=new BigDecimal("0.00");
	
	public Product(String name, int qty, BigDecimal price, BigDecimal basicTaxPercent, BigDecimal importDutyTaxPercent,
			boolean isImported, boolean isBasicTaxExempted) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.basicTaxPercent = basicTaxPercent;
		this.importDutyTaxPercent = importDutyTaxPercent;
		this.isImported = isImported;
		this.isBasicTaxExempted = isBasicTaxExempted;
		this.totalPrice=price.multiply(BigDecimal.valueOf(qty));
		
		if(!isBasicTaxExempted||isImported) {
			calculateTotalPriceAndTax();
		}
	}

	private void calculateTotalPriceAndTax() {
		// TODO Auto-generated method stub
		if(!isBasicTaxExempted) {
			  totalTaxAmount = totalTaxAmount.add(getTotalTaxAmount(basicTaxPercent));
		}
		if(isImported) {
			   totalTaxAmount = totalTaxAmount.add(getTotalTaxAmount(importDutyTaxPercent));
		}
		
		 totalTaxAmount = BigDecimal.valueOf(Math.ceil(totalTaxAmount.doubleValue() * 20) / 20);
		 totalTaxAmount = totalTaxAmount.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
	     totalPrice = totalPrice.add(totalTaxAmount);
	}

	private BigDecimal getTotalTaxAmount(BigDecimal taxPercent) {	
		return ((price.multiply(taxPercent)).divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(qty));
	}
	
	 public BigDecimal getTotalPrice() {
	        return totalPrice;
	    }

	    public BigDecimal getTotalTaxAmount() {
	        return totalTaxAmount;
	    }
		
}
