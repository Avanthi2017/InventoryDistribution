package test.copy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import inventory_distribution.InventoryAllocator;
import inventory_distribution.Warehouse;

public class InventoryAllocatorTest {
	InventoryAllocator allocator = new InventoryAllocator();
	@Test
	public void testLogicForBestShipment(){
		Map<String,Integer> input = new HashMap<>();
		input.put("apples", 3);
		List<Warehouse> warehouses = new ArrayList<>();
		Map<String,Integer> item1 = new HashMap<>();
		item1.put("apples", 5);
		item1.put("Oranges", 5);
		Map<String,Integer> item2 = new HashMap<>();
		item2.put("apples", 5);
		item2.put("Oranges", 5);
		warehouses.add(new Warehouse("db1",item1));
		warehouses.add(new Warehouse("db2",item2));
		List<Warehouse> expected = new ArrayList<>();
		Map<String,Integer> items = new HashMap<>();
		items.put("apples", 3);
		expected.add(new Warehouse("db1",input));
		List<Warehouse> actualWH = allocator.computeShipmentInfo(input, warehouses);
		System.out.println("<---------logicForBestShipment----------->");
		System.out.println("order: " + input);
		System.out.println("warehouses: " + warehouses );
		System.out.println("shipmentInfo: "+actualWH);
		
		assertTrue(actualWH.equals(expected));
		
	}
	@Test
	public void multipleItemsShipmentTest(){
		Map<String,Integer> input = new HashMap<>();
		input.put("apples", 5);
		input.put("Oranges", 10);
		List<Warehouse> warehouses = new ArrayList<>();
		Map<String,Integer> item1 = new HashMap<>();
		item1.put("apples", 5);
		item1.put("Oranges", 5);
		Map<String,Integer> item2 = new HashMap<>();
		item2.put("apples", 5);
		item2.put("Oranges", 5);
		warehouses.add(new Warehouse("db1",item1));
		warehouses.add(new Warehouse("db2",item2));
		List<Warehouse> expected = new ArrayList<>();
		Map<String,Integer> item3 = new HashMap<>();
		item3.put("Oranges", 5);
		expected.add(new Warehouse("db2",item3));
		expected.add(new Warehouse("db1",item1));
		List<Warehouse> actualWH = allocator.computeShipmentInfo(input, warehouses);
		System.out.println("<---------multipleItemsShipmentTest----------->");
		System.out.println("order: " + input);
		System.out.println("warehouses: " + warehouses );
		System.out.println("shipmentInfo: "+actualWH);
		assertEquals(actualWH, expected);
	}
	
	@Test
	public void warehouseSplittingTest(){
		Map<String,Integer> input = new HashMap<>();
		input.put("apples", 10);
		List<Warehouse> warehouses = new ArrayList<>();
		Map<String,Integer> item1 = new HashMap<>();
		item1.put("apples", 5);
		item1.put("Oranges", 5);
		Map<String,Integer> item2 = new HashMap<>();
		item2.put("apples", 5);
		item2.put("Oranges", 5);
		warehouses.add(new Warehouse("db1",item1));
		warehouses.add(new Warehouse("db2",item2));
		List<Warehouse> expected = new ArrayList<>();
		Map<String,Integer> item3 = new HashMap<>();
		item3.put("apples", 5);
		expected.add(new Warehouse("db2",item3));
		expected.add(new Warehouse("db1",item3));
		List<Warehouse> actualWH = allocator.computeShipmentInfo(input, warehouses);
		System.out.println("<---------warehouseSplittingTest----------->");
		System.out.println("order: " + input);
		System.out.println("warehouses: " + warehouses );
		System.out.println("shipmentInfo: "+actualWH);
		assertTrue(actualWH.equals(expected));
	}
	 
	@Test
	public void testForInadequateWareHouseItems(){
		Map<String,Integer> input = new HashMap<>();
		input.put("apples", 20);
		input.put("Oranges", 5);
		List<Warehouse> warehouses = new ArrayList<>();
		Map<String,Integer> item1 = new HashMap<>();
		item1.put("apples", 5);
		Map<String,Integer> item2 = new HashMap<>();
		item2.put("apples", 5);
		item2.put("Oranges", 5);
		warehouses.add(new Warehouse("db1",item1));
		warehouses.add(new Warehouse("db2",item2));
		List<Warehouse> expected = new ArrayList<>();
		Map<String,Integer> item3 = new HashMap<>();
		item3.put("Oranges", 5);
		expected.add(new Warehouse("db2",item3));
		List<Warehouse> actualWH = allocator.computeShipmentInfo(input, warehouses);
		System.out.println("<---------testForInadequateWareHouseItems----------->");
		System.out.println("order: " + input);
		System.out.println("warehouses: " + warehouses );
		System.out.println("shipmentInfo: "+actualWH);
		assertTrue(actualWH.equals(expected));
	}
	
}
