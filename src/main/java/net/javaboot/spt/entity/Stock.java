package net.javaboot.spt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Getter and Setter for ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



//    @Column(name = "stock_name")
//    private String stockName;

//    // Getter and Setter for stockName
//    public String getStockName() {
//        return stockName;
//    }
//
//    public void setStockName(String stockName) {
//        this.stockName = stockName;
//    }

    @Column(name = "stock_name", nullable = false)
    private String stockName;

    // Getter
    public String getStockName() {
        return stockName;
    }

    // Setter with validation
    public void setStockName(String stockName) {
        if (stockName == null || stockName.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock name cannot be null or empty");
        }
        this.stockName = stockName;
    }

    @Column(name = "ticker")
    private String ticker;
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Column(name = "quantity")
    private int quantity;
    public int getQuantity() {
        return quantity;
    }

    @Column(name = "buy_price")
//    private double buyPrice;
//
//    // Getter and Setter for buyPrice
//    public double getBuyPrice() {
//        return buyPrice;
//    }

    private Double buyPrice; // Use Double instead of double (allows null values)

    // Getters and setters
    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }


//    public void setBuyPrice(double buyPrice) {
//        this.buyPrice = buyPrice;
//    }
}
