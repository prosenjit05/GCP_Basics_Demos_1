package com.example.GCPDatastoreSBTF;

import com.google.firebase.FirebaseApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcpDatastoreSbTfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpDatastoreSbTfApplication.class, args);
		FirebaseApp.initializeApp();
	}

}
