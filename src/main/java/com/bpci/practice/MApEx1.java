package com.bpci.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MApEx1 {
	public static void main(String... args) {
		List<Map<String,String>> responseBody=new ArrayList<Map<String,String>>();
		//responseBody.put("InitiatorDetails","[]");
	System.out.println("hhhhhh");
	    for(Map<String,String> res:responseBody) {
	    	res.put("InitiatorDetails", "");
	    	System.out.println(res.values());
	    	Set<String> keySet = res.keySet();
	    	String value=res.get(keySet);
	    	System.out.println("hii");
	    	if(value.isEmpty()) {
	    		System.out.println("hello");
	    	}
	    	
	    }
	 
	   
	}

}
