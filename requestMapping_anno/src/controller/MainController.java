package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anno.RequestMapping;

public class MainController {
	
	@RequestMapping(value="/board",method="GET")
	public void board(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("board");
			
	}
    
    @RequestMapping(value="/insert",method="GET")
    public void insert(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("insert");
	}
    
    @RequestMapping(value="/delete",method="GET")
    public void delete(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("delete");
	}
}
