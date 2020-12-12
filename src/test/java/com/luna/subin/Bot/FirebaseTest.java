package com.luna.subin.Bot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luna.subin.Firebase.Firebase;
import com.luna.subin.Model.User;

import junit.framework.TestCase;

public class FirebaseTest extends TestCase {
	
	public void setUp() throws IOException {
		Firebase.initFirebase();
	}

	public void testPut() throws IOException {
		Map map = new HashMap();
		map.put("Hello", "World");
		Firebase.rootReference.child("test").setValueAsync(map);
	}
	
	public void testGet() throws IOException {
		User user;
		String test = null;

//		DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("test");
//		
//		ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//			@Override
//			public void onDataChange(DataSnapshot snapshot) {
//				fail();
//				System.out.println(snapshot.getValue());
//			}
//
//			@Override
//			public void onCancelled(DatabaseError error) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
		
		
		assertTrue(test.equals("first"));
	}
}
