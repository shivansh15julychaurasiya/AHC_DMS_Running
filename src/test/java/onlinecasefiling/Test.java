package onlinecasefiling;

import java.util.HashMap;

public class Test {
	
	
	private Integer id;
	
	private String name;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static void main(String[] args) {
		
		Test t= new Test();
		t.fabhotels();
		
		
		Test t1= new Test();
	}
	
	
	public void  fabhotels() {
		
		HashMap<String, String> map = new HashMap<>();
		
		    map.put("1", "deepak");
		    map.put("1", "nilesh");
	
		    String str="abc";
		    
		   if("abc".equals(str)){
			   System.out.println("yes");
			   
			   
		   }
		   String b="abc";
		   if("abc"==str)
		   {
			   System.out.println("yes");   
		   }
		   
		   if(b==str) {
			   System.out.println("yes");
			   
		   }
		   
		   
			   String c="a"+"bc";
		        if(c==str)
		        {
		        	System.out.println("yes");
		        }
		        if("abc".equals(c)) {
		        	System.out.println("yes");
		        }
		        
		        
		       int x=map.hashCode();
		       System.out.println(x);
		    
		    
		    System.out.println(map.get("1"));
		    
		
		
		
		
	}
	

}
