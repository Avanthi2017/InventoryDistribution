package com.deliverr;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
	private String name;
	private Map<String,Integer> inventory;
	
	
	public Warehouse(String name, Map<String,Integer> inventory) {
		super();
		this.name = name;
		this.inventory = inventory;
	}
	public Warehouse(String name, String item,int quantity) {
		super();
		this.name = name;
		this.inventory = new HashMap<>();
		inventory.put(item, quantity);
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String,Integer> getInventory() {
		return inventory;
	}
	
	public String toString(){
		StringBuffer res = new StringBuffer("{ name: " +name+", "+ "inventory:");
		for(Map.Entry<String,Integer> item:inventory.entrySet()){
			res.append("{"+ item.getKey() +": "+ item.getValue()+" }");
		}
		return res.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Warehouse other = (Warehouse) obj;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
