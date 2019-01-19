package blog.controller;

import com.amazonaws.services.secretsmanager.*;
import com.amazonaws.services.secretsmanager.model.*;

import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import blog.model.Secret;

@Component
public class SecretsController {


    private static String secretName;
    private static String region;

    @Autowired
    public SecretsController(@Value("${aws.secret}") String secretName, @Value("${aws.region}") String region)
    {
        this.secretName = secretName;
        this.region = region;
    }

    public static void getSecret() {

        // Create a Secrets Manager client
        AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();


        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (DecryptionFailureException e) {
            // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InternalServiceErrorException e) {
            // An error occurred on the server side.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidParameterException e) {
            // You provided an invalid value for a parameter.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidRequestException e) {
            // You provided a parameter value that is not valid for the current state of the resource.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (ResourceNotFoundException e) {
            // We can't find the resource that you asked for.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        }

        // Decrypts secret using the associated KMS CMK.
        // Depending on whether the secret is a string or binary, one of these fields will be populated.
        if (getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            //printSecret(secret);
            toSecretObject(secret);
        }
        else {
            decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
        }

    }

    public static void printSecret(String secret){
        System.out.println("=============");
        System.out.println(secret);
        System.out.println("=============");

    }

    public static void toSecretObject(String secret){
        //Secret secretObject = new Gson().fromJson(secret, Secret.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            final HashMap<String, Object > secretMap = mapper.readValue(secret, HashMap.class);
            printSecret((secretMap.get("port")).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

