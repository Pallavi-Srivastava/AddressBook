package com.btm.collectionex;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AddressBookCollection 
{
	public static void main(String[] args) 
	{
		AddressBook a1=new AddressBook("Raj","Singh","sector 15","noida","delhi",221008,9918191132L);
		AddressBook a2=new AddressBook("Anni","Asthna","sector 18","noida","delhi",221004,9918872641L);
		AddressBook a3=new AddressBook("Ravi","Sharma","sector 16","noida","delhi",221005,9470342132L);
		AddressBook a4=new AddressBook("Gyan","Mishra","sector 18","Gr.noida","delhi",221098,9470343212L);
		
		//adding elements to list
		List<AddressBook> lst=new LinkedList<>();
		lst.add(a1);
		lst.add(a2);
		lst.add(a3);
		lst.add(a4);
		
		for (AddressBook addressBook : lst) 
		{
			System.out.println(addressBook);
		}
		
		boolean val=lst.remove(a4);   //will remove the specified element/object from the collection
        System.out.println("Remove method will return : "+val);
        
        //Edit some specific row
        lst.get(2).setFirstName("Zivya");
        
 	   System.out.println("Person FirstName Sorting:"); //Sort by FirstName
 	   Collections.sort(lst, AddressBook.NameComparator);

 	   for(AddressBook str: lst)
 	   {
 			System.out.println(str);
 	   }

 	   System.out.println("ZipCode Sorting:"); //Sort by ZipCode
 	   Collections.sort(lst, AddressBook.ZipComparator);
 	   for(AddressBook str: lst)
 	   {
 			System.out.println(str);
 	   }  
	}
}
