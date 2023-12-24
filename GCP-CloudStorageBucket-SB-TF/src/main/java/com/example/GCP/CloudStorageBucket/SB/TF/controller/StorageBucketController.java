package com.example.GCP.CloudStorageBucket.SB.TF.controller;


import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gcp")
public class StorageBucketController {

    @Autowired
    private Storage storage;

    private final String myBucketName = "test-project-1-406807-my-bucket-pro";
    private final String myFileName = "My-File-Upload.txt";

    @PostMapping("/upload_my_file")
    public String upload_my_file() throws IOException {
        BlobId blobId = BlobId.of(myBucketName, myFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        File file = new File("C:\\Pro\\MyITWorldPro\\Workspaces\\workspace1\\GCP_Basics_Demos_1\\GCP-CloudStorageBucket-SB-TF\\src\\main\\resources\\My-File-Upload.txt");
        System.out.println(file.exists());
        byte[] arr = Files.readAllBytes(Paths.get(file.toURI()));
        storage.create(blobInfo, arr);
        return "File Uploaded Successfully!!";
    }

    @GetMapping("/get_file_content")
    public String get_file_content() {
        StringBuffer sb = new StringBuffer();
        try (ReadChannel channel = storage.reader(myBucketName, myFileName)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(64 * 1024);
            while (channel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                String data = new String(byteBuffer.array(), 0, byteBuffer.limit());
                sb.append(data);
                byteBuffer.clear();
            }
            return "My Files List:" + sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get_all_files_list")
    public String get_all_files_list() {
        StringBuilder sb = new StringBuilder();
        try {
            Storage storage = StorageOptions.getDefaultInstance().getService();
            Iterable<Blob> blobs = storage.list(myBucketName).iterateAll();

            List<Blob> blobList = new ArrayList<>();
            blobs.forEach(blobList::add);

            for (Blob blob : blobList) {
                sb.append("File Name: ").append(blob.getName()).append("\n");
            }

            return "Files in Bucket:\n" + sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete_file/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        try {
            Storage storage = StorageOptions.getDefaultInstance().getService();
            storage.delete(myBucketName, fileName);

            return "File deleted successfully: " + fileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
