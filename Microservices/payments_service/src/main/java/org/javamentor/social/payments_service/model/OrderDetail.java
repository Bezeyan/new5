package org.javamentor.social.payments_service.model;

import java.util.Locale;

public class OrderDetail {
    private String productName;
    private float subtotal;
    private float shipping;
    private float tax;
    private float total;

    public OrderDetail(String productName, String subtotal,
                       String shipping, String tax, String total) {
        this.productName = productName;
        this.subtotal = Float.parseFloat(subtotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }

    public String getProductName() {
        return productName;
    }

    public String getSubtotal() {return String.format(Locale.ROOT, "%.2f", subtotal);
    }

    public String getShipping() {
        return String.format(Locale.ROOT, "%.2f", shipping);
    }

    public String getTax() {
        return String.format(Locale.ROOT, "%.2f", tax);
    }

    public String getTotal() {
        return String.format(Locale.ROOT, "%.2f", total);
    }
}
