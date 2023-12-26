package com.example.GCPCloudFunctionSBTF.terraform;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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
            System.out.println("\nMy Application Closed\n");
            //executeTerraformDestroy();
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

            //*********** terraform apply starts ***********
            System.out.println("\nStarting: terraform apply\n");
            ProcessBuilder tfApplyProcessBuilder = new ProcessBuilder("terraform",
                    "apply","-auto-approve");
            tfApplyProcessBuilder.directory(new File("terraform"));
            Process tfApplyProcess = tfApplyProcessBuilder.start();
            captureAndPrintStream(tfApplyProcess.getInputStream(), "OUTPUT");
            captureAndPrintStream(tfApplyProcess.getErrorStream(), "ERROR");
            // Wait for the process to finish
            int exitCodeTfApply= tfApplyProcess.waitFor();
            System.out.println("My \"terraform apply\" exited with code: " + exitCodeTfApply);

            if(exitCodeTfApply==1){
                throw new RuntimeException("Error in \"terraform apply\". Stopping the App.");
            }
            //*********** terraform apply starts ***********

            System.out.println("\nTerraform Apply All Steps Done Successfully!\n");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeTerraformDestroy(){
        try{
            //*********** terraform destroy starts ***********
            System.out.println("\nStarting: terraform destroy\n");
            ProcessBuilder tfDestroyProcessBuilder = new ProcessBuilder("terraform",
                    "destroy","-auto-approve");
            tfDestroyProcessBuilder.directory(new File("terraform"));
            Process tfDestroyProcess = tfDestroyProcessBuilder.start();
            captureAndPrintStream(tfDestroyProcess.getInputStream(), "OUTPUT");
            captureAndPrintStream(tfDestroyProcess.getErrorStream(), "ERROR");
            // Wait for the process to finish
            int exitCodeTfDestroy= tfDestroyProcess.waitFor();
            System.out.println("My \"terraform destroy\" exited with code: " + exitCodeTfDestroy);

            if(exitCodeTfDestroy==1){
                throw new RuntimeException("Error in \"terraform destroy\". Stopping the App.");
            }
            //*********** terraform destroy starts ***********

            System.out.println("\nTerraform destroy All Steps Done Successfully!\n");
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
