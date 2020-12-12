package com.luna.subin.Firebase;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase {

	public static DataSnapshot dataSnapshot;

	static private boolean testFlag = false;
	
	private static FirebaseApp app;
	public static FirebaseDatabase database;
	public static DatabaseReference rootReference;

	public static void initFirebase() throws IOException {
		if(testFlag)
			return;
		testFlag = true;
		FileInputStream serviceAccount = new FileInputStream("config/serviceAccount.json");

		FirebaseOptions options = null;
		BasicConfigurator.configure();
		options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://discord-subinbot-default-rtdb.firebaseio.com/").build();

		
		app = FirebaseApp.initializeApp(options);
		
		database = FirebaseDatabase.getInstance(app);
		rootReference = database.getReference();
		
		DBListener dbListener = new DBListener();
		rootReference.addValueEventListener(dbListener);
	}
	
private static class DBListener implements ValueEventListener {
		
		
		public void onDataChange(DataSnapshot snapshot) {
			dataSnapshot = snapshot;
		}
		public void onCancelled(DatabaseError error) { }

	}

}
