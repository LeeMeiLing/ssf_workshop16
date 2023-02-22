package ibf2022.batch2.ssf.ssf_workshop16.model;

import java.io.Serializable;

import jakarta.json.JsonObject;

public class LineItem implements Serializable{

    private String item;
    private Integer quantity;

    public static LineItem create(JsonObject json) {
        LineItem lineItem = new LineItem();
        lineItem.setItem(json.getString("item"));
        lineItem.setQuantity(json.getInt("quantity"));
        return lineItem;
    }

    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem [item=" + item + ", quantity=" + quantity + "]";
    }
    
}
