package net.javaboot.spt.controller;

import net.javaboot.spt.entity.Stock;
import net.javaboot.spt.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stocks")
public class StockController {
//

    private final StockService stockService;

    // Constructor-based dependency injection
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Get all stocks
     *
     */
    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }


    /**
     * Add a new stock
     */
    @PostMapping("/{add}")
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }

    /**
     * Update an existing stock by ID
     */
    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        stock.setId(id); // Set the ID provided in the path to the stock object
        return stockService.updateStock(stock);
    }

    /**
     * Delete a stock by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id); // Delete the stock with the given ID
        return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
    }
}
