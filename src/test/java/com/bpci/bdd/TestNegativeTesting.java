package com.bpci.bdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNegativeTesting {
	@Test
	public void negativeTest() {
		
		 Map<String, List<String>> map = new HashMap<String, List<String>>();
		  List<String> valSetOne = new ArrayList<String>();
		  valSetOne.add(" ");
		   map.put("InitiatorDetails",valSetOne );
		  
		  Map<String,String> m1=new HashMap<String,String>();
		  m1.put("InitiatorDetails", " ");
		  System.out.println(m1.keySet()+""+m1.values());
		  Object ob1=m1.values();
		/*
		 * if(m1.containsValue("")||m1.containsValue(" ")) {
		 * System.out.println("hiiiii"); }
		 */
		
		 for (Entry ent : map.entrySet()) {
			 System.out.println(ent.getKey() + " :: " + ent.getValue());
			
			Object ob2=ent.getValue();
			 String v= ent.getKey().toString();
			 if(v.contains("")||v.contains(" ")) {
				// System.out.println("pass");
				 ob1.equals(ob2);
				 System.out.println("pass");
			 }
			
			
			// Assert.assertEquals(ob1, ob2);
			//Assert.assertSame(ob1, ob2);
		 }
		
	}

}
