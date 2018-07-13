package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo{

	private Map<String, Customer> data = new HashMap<String, Customer>(); 
	private static Map<String, List<String>> trans = new HashMap<String,List<String>>();
	private List<String> li;
	public WalletRepoImpl()
	{
	super();	
	}
	
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public boolean save(Customer customer) throws InvalidInputException {
		
		
		String mob = customer.getMobileNo();		
		if(data.get(mob)==null)
		{
		 li=new ArrayList<String>();
		li.add( new java.util.Date() + " : Account created.with Balance of" +customer.getWallet().getBalance() );
		trans.put(customer.getMobileNo(), li);
		data.put(customer.getMobileNo(), customer);
	
		}
		else 
			throw new InvalidInputException("mobile number exists");
		return true;
	}

	public Customer findOne(String mobileNo) throws InvalidInputException {
		if(data.containsKey(mobileNo))
		{
			Customer customer=data.get(mobileNo);
			return customer;
		}
		else
			throw new InvalidInputException();
		
		
	}

	//@Override
//	public void saveTransaction(String mobNo , BigDecimal bd, String meth) {
//		if(trans.containsKey(mobNo))
//		{
//			
//			String transaction = Instant.now() +"  your account ("+ mobNo + ") is " +  meth +" with "+ bd;
//			System.out.println(" values "+transaction);
//			li =trans.get(mobNo);
//			//System.out.println(l);
//			li.add(transaction);
//			//System.out.println(l);
//			trans.put(mobNo, li);
//			
//		}
//	
//	}

	@Override
	public List getTransaction(String mob) {
		 li=trans.get(mob);
		//System.out.println(l);
		return li;
	}
}
