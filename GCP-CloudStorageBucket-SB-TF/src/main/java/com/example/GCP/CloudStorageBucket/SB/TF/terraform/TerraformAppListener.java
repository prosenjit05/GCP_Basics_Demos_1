package com.example.GCP.CloudStorageBucket.SB.TF.terraform;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.io.Resource;

import java.io.*;


@Component
public class TerraformAppListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            // Execute Terraform apply on startup
            System.out.println("\nMy Application Started\n");
            executeTerraformApply();
        } else if (event instanceof ContextClosedEvent) {
            // Execute Terraform destroy on shutdown
            //executeTerraformDestroy();
            System.out.println("\nMy Application Closed\n");
        }
    }

    private void executeTerraformApply() {
        try {
            System.out.println("\nStarting: terraform init\n");
            //*********** terraform init starts ***********
            ProcessBuilder tfInitProcessBuilder = new ProcessBuilder("terraform",
                    "init");
            tfInitProcessBuilder.directory(new File("terraform"));
            Process tfInitProcess = tfInitProcessBuilder.start();
            captureAndPrintStream(tfInitProcess.getInputStream(), "OUTPUT");
            captureAndPrintStream(tfInitProcess.getErrorStream(), "ERROR");
            // Wait for the process to finish
            int exitCodeTfInit = tfInitProcess.waitFor();
            System.out.println("My \"terraform init\" exited with code: " + exitCodeTfInit);

            if(exitCodeTfInit==1){
                throw new RuntimeException("Error in \"terraform init\". Stopping the App.");
            }
            //*********** terraform init ends ***********

            //*********** terraform plan starts ***********
            System.out.println("\nStarting: terraform plan\n");
            ProcessBuilder tfPlanProcessBuilder = new ProcessBuilder("terraform",
                    "plan");
            tfPlanProcessBuilder.directory(new File("terraform"));
            Process tfPlanProcess = tfPlanProcessBuilder.start();
            captureAndPrintStream(tfPlanProcess.getInputStream(), "OUTPUT");
            captureAndPrintStream(tfPlanProcess.getErrorStream(), "ERROR");
            // Wait for the process to finish
            int exitCodeTfPlan = tfInitProcess.waitFor();
            System.out.println("My \"terraform plan\" exited with code: " + exitCodeTfPlan);

            if(exitCodeTfPlan==1){
                throw new RuntimeException("Error in \"terraform plan\". Stopping the App.");
            }
            //*********** terraform plan starts ***********

            System.out.println("\nTerraform All Steps Done!\n");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void captureAndPrintStream(InputStream inputStream, String streamType) {
        //new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[" + streamType + "] " + line);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        //}).start();
    }

}
