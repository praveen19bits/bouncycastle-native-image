package com.example.demo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@SpringBootApplication
@RestController
public class DemoApplication {



	public static void main(String[] args) throws Exception {

		SpringApplication.run(DemoApplication.class, args);

		int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
		System.out.println("Max Key Size for AES : " + maxKeySize);




	}
	@GetMapping("/test1")
	public void test() throws Exception {
		// Add Bouncy Castle as a security provider
		//Security.addProvider(new BouncyCastleProvider());

		// Generate a symmetric key (AES)
		SecretKey secretKey = generateSecretKey();

		// Encrypt and decrypt a message
		String originalMessage = "Hello, Bouncy Castle!";
		byte[] encryptedMessage = encrypt(secretKey, originalMessage.getBytes());
		byte[] decryptedMessage = decrypt(secretKey, encryptedMessage);

		// Print results
		System.out.println("Original Message: " + originalMessage);
		System.out.println("Encrypted Message: " + new String(encryptedMessage));
		System.out.println("Decrypted Message: " + new String(decryptedMessage));
	}
	private static SecretKey generateSecretKey() throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");
		generator.init(256); // Key size: 256 bits
		return generator.generateKey();
	}

	private static byte[] encrypt(SecretKey secretKey, byte[] input) throws Exception {
		Cipher cipher = Cipher.getInstance("AES", "BC");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(input);
	}

	private static byte[] decrypt(SecretKey secretKey, byte[] input) throws Exception {
		Cipher cipher = Cipher.getInstance("AES", "BC");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(input);
	}

}
