package com.ruraaratech.p4dafrica.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInit {
    @PostConstruct
    public void initialize() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("/home/qci_user/apps/p4d/service-account.json");// /home/qci_user/apps/p4d/
        //./service-account.json
        //

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://p4d-africa-default-rtdb.firebaseio.com/")
                .setStorageBucket("p4d-africa.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);

    }
}
