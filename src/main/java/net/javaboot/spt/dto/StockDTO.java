package net.javaboot.spt.dto;

public class StockDTO {
    private Long id;
    private String stockName;
    private String ticker;
    private int quantity;
    private Double buyPrice;


    // Constructor
    public StockDTO(Long id, String stockName, String ticker, int quantity, Double buyPrice) {
        this.id = id;
        this.stockName = stockName;
        this.ticker = ticker;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

}
