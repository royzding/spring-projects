package com.sb.jwt.pri.pub;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTJavaWithPublicPrivateKey {

    public static void main(String[] args) {

        System.out.println("generating keys");
        Map<String, Object> rsaKeys = null;

        try {
            rsaKeys = getRSAKeys();
        } catch (Exception e) {

            e.printStackTrace();
        }
        PublicKey publicKey = (PublicKey) rsaKeys.get("public");
        PrivateKey privateKey = (PrivateKey) rsaKeys.get("private");
        
        byte[] bPubKey = publicKey.getEncoded();
        byte[] bPriKey = privateKey.getEncoded();
        
        String sPubKey = Base64.getEncoder().encodeToString(bPubKey);
        String sPriKey = Base64.getEncoder().encodeToString(bPriKey);
        
        byte[] publicBytes = Base64.getDecoder().decode(sPubKey);
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory;
        
        
        byte[] privateBytes = Base64.getDecoder().decode(sPriKey);
        PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        
        PublicKey pubKey = null;
        PrivateKey priKey = null;
        
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			
	        try {
				pubKey = keyFactory.generatePublic(pubKeySpec);
				priKey = keyFactory.generatePrivate(priKeySpec);
				
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        System.out.println("generated keys");

        String token = generateToken(priKey);
        //String token = generateToken(privateKey);
        System.out.println("Generated Token:\n" + token);

        verifyToken(token, pubKey);
        //verifyToken(token, publicKey);

    }

    public static String generateToken(PrivateKey privateKey) {
        String token = null;
        try {
            Map<String, Object> claims = new HashMap<String, Object>();

            // put your information into claim
            claims.put("id", "xxx");
            claims.put("role", "user");
            claims.put("created", new Date());

            token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.RS512, privateKey).compact();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    // verify and get claims using public key

    private static Claims verifyToken(String token, PublicKey publicKey) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();

            System.out.println(claims.get("id"));
            System.out.println(claims.get("role"));

        } catch (Exception e) {

            claims = null;
        }
        return claims;
    }

    // Get RSA keys. Uses key size of 2048.
    private static Map<String, Object> getRSAKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("private", privateKey);
        keys.put("public", publicKey);
        return keys;
    }
}