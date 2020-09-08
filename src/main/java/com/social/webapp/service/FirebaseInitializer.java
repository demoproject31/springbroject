package com.social.webapp.service;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {

	@PostConstruct
	private void intiDB() throws IOException {
		
		InputStream serviceAccount =this.getClass().getClassLoader().getResourceAsStream("./demoone-ef509-firebase-adminsdk-mok8q-61c7ccbabc.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://demoone-ef509.firebaseio.com")
				  .build();

				if(FirebaseApp.getApps().isEmpty()) {
					FirebaseApp.initializeApp(options);	
				}
				
		
	}
	
	public Firestore getfirebase() {
		return FirestoreClient.getFirestore();
	}
	

}
