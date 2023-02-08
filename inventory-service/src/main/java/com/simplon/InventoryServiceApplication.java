package com.simplon;

import com.simplon.model.Inventory;
import com.simplon.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return (args) -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("sku-code-1");
//			inventory.setQuantity(100);
//
//			Inventory inventory2 = new Inventory();
//			inventory2.setSkuCode("sku-code-2");
//			inventory2.setQuantity(100);
//
////			Inventory inventory3 = new Inventory();
////			inventory3.setSkuCode("sku-code-3");
////			inventory3.setQuantity(30);
//
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory2);
////			inventoryRepository.save(inventory3);
//		};
//	}
}
