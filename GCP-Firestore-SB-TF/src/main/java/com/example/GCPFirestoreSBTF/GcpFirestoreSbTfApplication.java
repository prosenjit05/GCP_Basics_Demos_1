package com.example.GCPFirestoreSBTF;

import com.google.firebase.FirebaseApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcpFirestoreSbTfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpFirestoreSbTfApplication.class, args);
		FirebaseApp.initializeApp();
	}

}
