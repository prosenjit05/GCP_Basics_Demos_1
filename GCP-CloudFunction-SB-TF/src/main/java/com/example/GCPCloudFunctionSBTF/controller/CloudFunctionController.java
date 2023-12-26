package com.example.GCPCloudFunctionSBTF.controller;

import com.google.cloud.functions.v1.CallFunctionResponse;
import com.google.cloud.functions.v1.CloudFunction;
import com.google.cloud.functions.v1.CloudFunctionsServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/gcp")
public class CloudFunctionController {

    //format:
    //"projects/your-project-id/locations/your-region/functions/your-function-name"
    private final String myCLoudFunction = "projects/test-project-1-406807/locations/us-central1/functions/my_function_pro";

    @GetMapping("/get_cloud_function_details")
    public String get_cloud_function_details() throws IOException {
        //Pro: This is not working. NOT_FOUND - check later pro

        // Using Google Cloud Java Client Library
        CloudFunctionsServiceClient client = CloudFunctionsServiceClient.create();
        CloudFunction function = client.getFunction(myCLoudFunction);
        String functionId = function.getName();
        System.out.println(functionId);

        return functionId;
    }

    @GetMapping("/get_cloud_function_response")
    public String get_cloud_function_response(){
        try (CloudFunctionsServiceClient client = CloudFunctionsServiceClient.create()) {
            CloudFunction function = client.getFunction(myCLoudFunction);

            // Call the Cloud Function (replace with appropriate data if needed)
            CallFunctionResponse response = client.callFunction(function.getName(), null);

            return response.getResult();
        } catch (Exception e) {
            // Handle exceptions gracefully
            return "Error calling Cloud Function: " + e.getMessage();
        }
    }

}
