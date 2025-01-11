package net.javaboot.spt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaboot.spt.dto.StockDTO;
import net.javaboot.spt.entity.Stock;
import net.javaboot.spt.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final RestTemplate restTemplate;

    @Value("${alphavantage.api.key}")
    private String apiKey;

    @Value("${alphavantage.api.url}")
    private String apiUrl;


    public List<StockDTO> getAllStockDTOs() {
        // Fetch all stock entries from the database
        List<Stock> stocks = stockRepository.findAll();
        // Create a list to hold the DTOs
        List<StockDTO> stockDTOs = new ArrayList<>();

        // Iterate over each stock entity to create a DTO
        for (Stock stock : stocks) {
            // Assuming 'fetchCurrentStockPrice' retrieves the current market price for the stock
            Double currentPrice = fetchCurrentStockPrice(stock.getTicker());

            // Create the DTO instance with the modified fields
            StockDTO stockDTO = new StockDTO(
                    stock.getId(),                  // Stock ID
                    stock.getStockName(),                // Stock Name (this should still be mapped appropriately)
                    stock.getTicker(),              // Stock Ticker
                    stock.getQuantity(),            // Quantity owned
                    currentPrice                    // Current price retrieved
            );

            // Add the DTO to the list
            stockDTOs.add(stockDTO);
        }

        // Return the list of StockDTOs
        return stockDTOs;
    }

    public StockService(StockRepository stockRepository, RestTemplate restTemplate) {
        this.stockRepository = stockRepository;
        this.restTemplate = restTemplate;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    public double getTotalPortfolioValue() {
        List<Stock> stocks = stockRepository.findAll();
        double totalValue = 0.0;

        for (Stock stock : stocks) {
            Double currentPrice = fetchCurrentStockPrice(stock.getTicker());
            if (currentPrice != null) {
                totalValue += currentPrice * stock.getQuantity();
            }
        }
        return totalValue;
    }

    private Double fetchCurrentStockPrice(String ticker) {
        String url = String.format("%s?function=TIME_SERIES_INTRADAY&symbol=%s&interval=1min&apikey=%s", apiUrl, ticker, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

         if (response.getStatusCode().is2xxSuccessful()) {
             ObjectMapper objectMapper = new ObjectMapper();
             try{
                        JsonNode rootNode = objectMapper.readTree(response.getBody());
                        JsonNode timeSeriesNode = rootNode.path("Time Series (1min)");
                        // get the most recent timestape
                        if (timeSeriesNode.size() > 0) {
                            String lastKey = timeSeriesNode.fieldNames().next();
                            JsonNode latestDate = timeSeriesNode.path(lastKey);
                            return latestDate.path("1. open").asDouble();

                            // use this as the current price

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
         }

        return null; // in case of an err[r
    }

}
