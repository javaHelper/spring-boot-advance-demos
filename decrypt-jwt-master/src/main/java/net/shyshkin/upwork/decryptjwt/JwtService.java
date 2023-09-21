package net.shyshkin.upwork.decryptjwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;

@Service
public class JwtService {

    public EncryptedJWT decrypt(String encryptedJwt, String privateKey) {
        try {
            PrivateKey pKey = buildPrivateKey(privateKey);
            JWEDecrypter decrypter = new RSADecrypter(pKey);
//            decrypter.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());

            encryptedJwt = encryptedJwt.replaceAll("\\s+", "");
            EncryptedJWT jwt = EncryptedJWT.parse(encryptedJwt);
            jwt.decrypt(decrypter);
            return jwt;
        } catch (ParseException | JOSEException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public EncryptedJWT encrypt(JWTClaimsSet jwtClaims, String publicKey) {

        // Request JWT encrypted with RSA-OAEP-256 and 128-bit AES/GCM
        JWEHeader header = new JWEHeader(
                JWEAlgorithm.RSA_OAEP,
//                JWEAlgorithm.RSA_OAEP_256,
                EncryptionMethod.A128GCM
        );

        try {
            // Create an encrypter with the specified public RSA key
            var pKey = buildPublicKey(publicKey);
            RSAEncrypter encrypter = new RSAEncrypter((RSAPublicKey) pKey);

            // Create the encrypted JWT object
            EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

            // Do the actual encryption
            jwt.encrypt(encrypter);

            return jwt;
        } catch (JOSEException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKey buildPrivateKey(String plainStringKey) throws InvalidKeySpecException, NoSuchAlgorithmException {

        // Remove the "BEGIN" and "END" lines, as well as any whitespace
        plainStringKey = plainStringKey.replace("-----BEGIN PRIVATE KEY-----", "");
        plainStringKey = plainStringKey.replace("-----END PRIVATE KEY-----", "");
        plainStringKey = plainStringKey.replaceAll("\\s+", "");

        // Base64 decode the result

        byte[] bytes = Base64.getDecoder().decode(plainStringKey);

        // extract the private key

        KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private PublicKey buildPublicKey(String plainStringKey) throws InvalidKeySpecException, NoSuchAlgorithmException {

        // Remove the "BEGIN" and "END" lines, as well as any whitespace
        plainStringKey = plainStringKey.replace("-----BEGIN PUBLIC KEY-----", "");
        plainStringKey = plainStringKey.replace("-----END PUBLIC KEY-----", "");
        plainStringKey = plainStringKey.replaceAll("\\s+", "");

        // Base64 decode the result

        byte[] bytes = Base64.getDecoder().decode(plainStringKey);

        KeySpec keySpec = new X509EncodedKeySpec(bytes);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(keySpec);
    }

}
