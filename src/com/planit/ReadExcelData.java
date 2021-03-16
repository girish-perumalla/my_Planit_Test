package com.planit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ReadExcelData {
	
	@DataProvider(name="shoppingcart")
	public Iterator<ShoppingData> getExcelData() throws FilloException{
		List<ShoppingData> shoppingDatas = new ArrayList<>();
		String strQuery ="SELECT * From ShoppingCart";
		String excelPath = System.getProperty("user.dir")+"\\Testdata.xlsx";
		System.out.println(excelPath);
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(excelPath);
		Recordset recordset =null;
	    recordset = connection.executeQuery(strQuery);
	    while(recordset.next()){
	         ShoppingData shoopingData = new ShoppingData();
	         shoopingData.setEmail(recordset.getField("email"));
	         shoopingData.setPassword(recordset.getField("password"));
	         shoopingData.setQuantity(recordset.getField("quantity"));
	         shoopingData.setNewaddress(recordset.getField("Newaddress"));
	         shoopingData.setFirstname(recordset.getField("firstname"));
	         shoopingData.setLastname(recordset.getField("lastname"));
	         shoopingData.setCountry(recordset.getField("country"));
	         shoopingData.setCity(recordset.getField("city"));
	         shoopingData.setAddress_1(recordset.getField("address_1"));
	         shoopingData.setPostacode(recordset.getField("postalcode"));
	         shoopingData.setPhone(recordset.getField("phone"));
	         shoopingData.setShippingaddress(recordset.getField("shippingaddress"));
	         shoppingDatas.add(shoopingData);
	    }
		return shoppingDatas.iterator();
	}
	
}
