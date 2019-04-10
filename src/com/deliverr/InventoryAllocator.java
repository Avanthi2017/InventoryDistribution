package com.deliverr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryAllocator {


	/**This method calculates the best way an order can be shipped 
	 * @param items
	 * @param warehouses
	 * @return shipmentInformation
	 */
	public List<Warehouse> computeShipmentInfo(Map<String, Integer> items, List<Warehouse> warehouses) {
		List<Warehouse> shipments = new ArrayList<Warehouse>();

		items.forEach((item, quantity) -> {
			Warehouse prev_wh = null;
			for (Warehouse warehouse : warehouses) {
				if (warehouse.getInventory().containsKey(item)) {
					Integer curr_wh_quant = warehouse.getInventory().get(item);
					Integer prev_wh_quant = (prev_wh != null) ? prev_wh.getInventory().get(item) : 0;
					int req_wh_quant = quantity - prev_wh_quant;
					if (curr_wh_quant >= req_wh_quant) {
						
							/** 
							 * Check if Warehouse already exists in shipment order to update with new items
							**/
						if(shipments.stream().anyMatch((wh)->wh.getName().equals(warehouse.getName()))){
							 shipments.stream().filter(wh->wh.getName().equals(warehouse.getName())).forEach((wh1)->wh1.getInventory().put(item, req_wh_quant));
						}
						else {
							shipments.add(new Warehouse(warehouse.getName(), item, req_wh_quant));
						}
						/** 
						 * Update the shipment with previous warehouse order when items are splitted 
						**/
						Warehouse prev_wh1 = prev_wh;
						if(prev_wh != null && shipments.stream().anyMatch((wh)->wh.getName().equals(prev_wh1.getName()))){
							 shipments.stream().filter(wh->wh.getName().equals(warehouse.getName())).forEach((wh1)->wh1.getInventory().put(item, prev_wh_quant));
						}
						else {
							shipments.add(prev_wh);
						}

						return;

					} else {
						prev_wh = new Warehouse(warehouse.getName(), item, curr_wh_quant);
					}
				}

			}
		});
		shipments.remove(null);
		return shipments;
	}

}
