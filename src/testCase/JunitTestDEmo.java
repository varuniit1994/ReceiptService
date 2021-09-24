package testCase;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.ReceiptService;
import service.Product;

class JunitTestDEmo {
	
	ReceiptService receiptService = new ReceiptService();
	 
	@Test
	void testTotalInvoice1() {
		List<Product> productList = getProductList1();
		receiptService.calculateReceipt(productList);
		assertEquals(getPriceOfEachProductInList1(), productList.stream().map(Product::getTotalPrice).collect(Collectors.toList()));
        assertEquals(BigDecimal.valueOf(330.83).setScale(2), receiptService.getFinalAmount());
        assertEquals(BigDecimal.valueOf(15.00).setScale(2), receiptService.getTotalTax());
	}

	@Test
	void testTotalInvoice2() {
		List<Product> productList = getProductList2();
		receiptService.calculateReceipt(productList);
		assertEquals(getPriceOfEachProductInList2(), productList.stream().map(Product::getTotalPrice).collect(Collectors.toList()));
        assertEquals(BigDecimal.valueOf(646.10).setScale(2), receiptService.getFinalAmount());
        assertEquals(BigDecimal.valueOf(75.60).setScale(2), receiptService.getTotalTax());
	}
	
	private List<Product> getProductList1() {
		 List<Product> productList1 = new ArrayList<Product>();
	        productList1.add(new Product("book", 1, new BigDecimal("124.99"), new BigDecimal("10"), new BigDecimal("5"), false, true));
	        productList1.add(new Product("music CD", 1, new BigDecimal("149.99"), new BigDecimal("10"), new BigDecimal("5"), false, false));
	        productList1.add(new Product("chocolate bar", 1, new BigDecimal("40.85"), new BigDecimal("10"), new BigDecimal("5"), false, true));
		return productList1;
	}
	
	private List<BigDecimal> getPriceOfEachProductInList1(){
		List<BigDecimal> expectedPriceOfEachProductInList1 = new ArrayList<>();
        expectedPriceOfEachProductInList1.add(new BigDecimal("124.99"));
        expectedPriceOfEachProductInList1.add(new BigDecimal("164.99"));
        expectedPriceOfEachProductInList1.add(new BigDecimal("40.85"));
		return expectedPriceOfEachProductInList1;
	}
	
	private List<Product> getProductList2(){
        List<Product> productList2 = new ArrayList<>();
        productList2.add(new Product("imported chocolates", 1, new BigDecimal("100.00"), new BigDecimal("10"), new BigDecimal("5"), true, true));
        productList2.add(new Product("imported perfume", 1, new BigDecimal("470.50"), new BigDecimal("10"), new BigDecimal("5"), true, false));
		return productList2;
	}
	
	private List<BigDecimal> getPriceOfEachProductInList2(){
		List<BigDecimal> expectedPriceOfEachProductInList2 = new ArrayList<>();
        expectedPriceOfEachProductInList2.add(new BigDecimal("105.00"));
        expectedPriceOfEachProductInList2.add(new BigDecimal("541.10"));
		return expectedPriceOfEachProductInList2;
	}
	

}
