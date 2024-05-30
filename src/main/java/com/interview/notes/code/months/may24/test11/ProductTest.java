package com.interview.notes.code.months.may24.test11;


enum Product {
    MYPRODUCT("MYPRODUCT", 123);

    private String productName;
    private Integer productId;

    private Product(String productName, Integer productId) {
        this.productName = productName;
        this.productId = productId;
    }

    protected String getMatchValue(Account account) {
        if (account == null || account.getProduct() == null || account.getProduct().getProductId() == null) {
            return "";
        }

        Integer productIdFromAccount = account.getProduct().getProductId();
        for (Product product : Product.values()) {
            if (productIdFromAccount.equals(product.getProductId())) {
                return product.getProductName();
            }
        }
        return "";
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductId() {
        return productId;
    }
}

public class ProductTest {
/*
    @Test
    public void testGetMatchValue_ProductFound() {
        Account account = new Account(new Product("MYPRODUCT", 123));
        assertEquals("MYPRODUCT", Product.MYPRODUCT.getMatchValue(account));
    }

    @Test
    public void testGetMatchValue_ProductNotFound() {
        Account account = new Account(new Product("UNKNOWN_PRODUCT", 456));
        assertEquals("", Product.MYPRODUCT.getMatchValue(account));
    }

    @Test
    public void testGetMatchValue_NullProduct() {
        Account account = new Account(null);
        assertEquals("", Product.MYPRODUCT.getMatchValue(account));
    }

    @Test
    public void testGetMatchValue_NullProductId() {
        Account account = new Account(new Product(null, null));
        assertEquals("", Product.MYPRODUCT.getMatchValue(account));
    }*/
}

class Account {
    private Product product;

    public Account(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
