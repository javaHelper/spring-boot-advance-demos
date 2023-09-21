package net.shyshkin.upwork.decryptjwt;

import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    private final JwtService jwtService;

    private final static String encryptedJwt = """
            eyJraWQiOiJlbmMxIiwiY3R5IjoiSldUIiwiZW5jIjoiQTI1NkdDTSIsImFsZyI6IlJTQS1PQUVQIn0.K8iJjhXeTQuH4WxtO3s
            jdnPFVe_l_4DgyWExI7KMBkpSts5Lg4iG9JjPbrl4-tqlX7I3-ghIu-pVMk8W5_th8OYZ-6qWEAhV25coYP1EDXhki6ySS7
            l1-9XLbVllbzMYusSKdidP6hSllVn2KEFuBJuT80x0dR0hiKFWGnGUkyeiqrse0a5O-vlMZHOHlaOvZeBevQS6ruafDSY
            Qdp-MA2sn8fUfO67Pfo97WYSW7wMrgEQk5t9qpe4v5eyQKInK6oy7_reIRAMef0TqLsc7TdBQpY-hM2uSoBs90zc-w
            F9wI4wqpT9lFB11UGeleiO2b4BzxgyVqH_PbAGsFvrHqw.yxGVr9Muqxn9IPP_.r_RRlacm3ESaJQQUneJ6vUwiSTkJ
            QOYF7Ko3sG1M2601rQ7k4GJ3ay22Lj0MQUl3C9eUV4tgiPz0V04vXFBw5enWtQBywn9FNIItKo1vOaGw2IoL2Wyuw
            ow2HsglkfdDHF2dMvlXR0yRf6jHI6hQNZN9uZ4_L4nmOTlPkjOLRwacIRHvn55ZzTEKavxdvk5FxxycSOgn96CfnaCt_
            wRBPPne923X3nmKz3dyxIFjd948jUzxMLxR-ZAkto0nFEcGHRndlSODARqK4i5VoNG__UoqVP5xyibowISoFdu5PS8
            F5W6DlacCG51heAsb5i8yApSEZWVvOlOB4Hbxdh2I1n-UVqQJh1ZxiZWY8htk7YidnVnYhxt6gb87MmZNxQK8G1UK
            x4J0vN_lf_cXAB_tR_hMxvlz1Ck-2MIQ4HGvXJh0y4mazwSAaQxOyjc2mqaXJhR_ZhMJp3WJ6RLayKdLqhZH5uOkM
            XG6WDIzyVHuAP2lyxEVBJf6-MfcjmnTsX32eVbsysmPe38VLb6TGl-qZd8gadvJuZ-TZ14y1m3ud8Y60Yp63vWeE3Jm
            5xfo3I_-925aSUSJ3ImHwV_pKh8YcVP5sEWCl6Ri2RHZVKXtLgbicnYdxYSqxdlpPrDR2E6mlKui8carbyLw2n0OoW3
            8rsSTSxyjibp3KO86cM77QZt2VOV3SN0B6WqGbg-LpkJf7GfE4hWvgVmoMvNXGxxpTOdPBgNfWEaK_rNpWkDOcI
            wGjJ3tEOP4Qzwh4YZJM1BRh3JISQhnO2gaT9bjDzjhRY1IglnOCyoz3x_o3vktQikkFt0wwjT_gs1nkW-rchLaVU8zMhL
            QvAsFR321_1O0J8ii8CwLJKWkLQHSkB_CVtkVEgTUjgyCnVqjUkjGBxUGnxZIkJ5NJKI-Tm-yUDJgURaWuJPb-MBY
            U8wexyeOJ59-XQqDe2NsTZ5tMBm3RuslMtpkg66pOz3etAEqejj1BbixlvI85DA-yYDzPuzUDa3G2pWyYTa6GGDAq7
            1di4jwg_uEyZFHK-7yTCcKO-lcEKz3.nO4-13u24NkruhO8MNsxZA
            """;
    private final static String privateKey = """
            MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCpJUELTPWOF58q
            OYBRwTo2H0JdhVxtBELYUfVcWrYwBrq0qBU5fn81rFkvcO3UmNV1GDiuNR5v50k1
            mCwkkaAUnqfizLIZ7cG1LPqUmao32E9GDjyaGeO18+drWbs3d+aaUJmImNHuyT5s
            HWtIAcmX5oDgOToCL+ZQXXpiqUl/BSJlI8hnlLA6q6M5WP+CXMYXwLM0aWEaGvuc
            aAF4idRc6TTmORaYNWfv2j+jnwYzd64KRHL3EXsfygJhArIZV0tey+2Q79bO8ovS
            LhMmE1p1QsXRrbk/mOVXmeX9gdM5bBVZ3iAdORaLXAp6+Cpv9OBzxzF38S/R+s5u
            tc0J/AANAgMBAAECggEBAI1xJLIhedmHcUaq7N3wug/WFfYEdiSHgqpH5uiCxoTk
            q640PANm/1NPuwiL46V65EzVo1gx/TDQdIzHomo2CGoVvghO6cP66Jfrc+NdmFS4
            opnPf+fRLHTT77n6QC8DKafzLGBogmyuhm98LmD5da17SFu4HwUDLxIXCSeFrnry
            4QZ1vochSWl98MD5CQic4+hrDBijXcUXnacpvd0+IH9ioBksznKIYrGTAFaV+i+u
            dFlaOM6OaLa3CO/jTPTwCMQslnVD8XqsL37pgu7V9rtrCdWUh8g3Mxiar07brU2S
            8sJ3BiD+Oy+hEc7nCwKEtguzSaucLB0Q+cqrEvqQ9vUCgYEA1g0+lBu0W7wlWTer
            pdcKXLcbZgxkKFEUqUzaO00oZwJPwHtS5uIAJFLyNnBy7zzSCGaKkayT/A67b6Uf
            uB6BYVc2Luts/++Mejs3h2QiinOMer/UjFdgjrVcYcUkYXm6KawBDxq2EetJUdMQ
            OpnzvVhlJI5LlTBUHzF3N2Ld+8sCgYEAyksccmWdqxwu5c7dLfA5Proxva31MCaN
            G5VGg33hSmMSkvRjqc7ZjnmY/mDjd2dQtYSkCB/Uw45qn1BlDupWMYlcV1K/igNk
            eGiiIJjL6odGOTQwmyQetKYXKAetJkrhE1rksR4lN83wb8/m9NAbHbuqv29zocqZ
            Xz7KkgUtqIcCgYEApUGCb8xX2X8PgqrVTWsRJ/WCJK4qYWn/vpmD3sa6C5UgR2Wu
            4mvXWjPjph34zjUxnqS0FLWdZrtlAFQGghqjHqi7P0wpUgr6dJ/lRhUMCcId0eLq
            URNgi6z/TshqRws4q9WM18FykbEKYS7HV8bmA+gL5kYpmlu+hnqlJNCiuzMCgYBP
            Xx0wPXKZmptEC+B4j9LGK+QsWj7jv5yKAA4EtbVZrHmzMzSPgduWrVs8I+PcC6t9
            LSf3EBY5DH4FOFQOgB+FnDGhCZShoCchn1xNkeYghScmdrXDiW6KzsgzKp/jcGnT
            YExuujc8KA94r4gc8jJRfjrGlgI2rvlyh36PwHxjrQKBgQC8QqE7hCXWtLTybhQn
            ZpoqNKiU3ik6ts8BwbemJWZyZ7tmRYLqwPCrhfj0dF6q+FvEs2NPN5Ev3G4m8NVM
            oqReiza/SbRwo64giuiVduK0krR+HgxkO00EBfNDqtxcCLnFM1/WcZnV04XqXo2k
            XYJpnLZ7FbfTIh4EZ5UbZqG6Qw==
            """;

    private final static String publicKey = """
            -----BEGIN PUBLIC KEY-----
            MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqSVBC0z1jhefKjmAUcE6
            Nh9CXYVcbQRC2FH1XFq2MAa6tKgVOX5/NaxZL3Dt1JjVdRg4rjUeb+dJNZgsJJGg
            FJ6n4syyGe3BtSz6lJmqN9hPRg48mhnjtfPna1m7N3fmmlCZiJjR7sk+bB1rSAHJ
            l+aA4Dk6Ai/mUF16YqlJfwUiZSPIZ5SwOqujOVj/glzGF8CzNGlhGhr7nGgBeInU
            XOk05jkWmDVn79o/o58GM3euCkRy9xF7H8oCYQKyGVdLXsvtkO/WzvKL0i4TJhNa
            dULF0a25P5jlV5nl/YHTOWwVWd4gHTkWi1wKevgqb/Tgc8cxd/Ev0frObrXNCfwA
            DQIDAQAB
            -----END PUBLIC KEY-----
            """;

    public AppRunner(JwtService jwtService) {
        this.jwtService = jwtService;
    }

//generated token
//eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQ.K52jFwAQJH-DxMhtaq7sg5tMuot_mT5dm1DR_01wj6ZUQQhJFO02vPI44W5nDjC5C_v4pW1UiJa3cwb5y2Rd9kSvb0ZxAqGX9c4Z4zouRU57729ML3V05UArUhck9ZvssfkDW1VclingL8LfagRUs2z95UkwhiZyaKpmrgqpKX8azQFGNLBvEjXnx-xoDFZIYwHOno290HOpig3aUsDxhsioweiXbeLXxLeRsivaLwUWRUZfHRC_HGAo8KSF4gQZmeJtRgai5mz6qgbVkg7jPQyZFtM5_ul0UKHE2y0AtWm8IzDE_rbAV14OCRZJ6n38X5urVFFE5sdphdGsNlA.gjI_RIFWZXJwaO9R.oaE5a-z0N1MW9FBkhKeKeFa5e7hxVXOuANZsNmBYYT8G_xlXkMD0nz4fIaGtuWd3t9Xp-kufvvfD-xOnAs2SBX_Y1kYGPto4mibBjIrXQEjDsKyKwndxzrutN9csmFwqWhx1sLHMpJkgsnfLTi9yWBPKH5Krx23IhoDGoSfqOquuhxn0y0WkuqH1R3z-fluUs6sxx9qx6NFVS1NRQ-LVn9sWT5yx8m9AQ_ng8MBWz2BfBTV0tjliV74ogNDikNXTAkD9rsWFV0IX4IpA.sOLijuVySaKI-FYUaBywpg
//initial task token
//eyJraWQiOiJlbmMxIiwiY3R5IjoiSldUIiwiZW5jIjoiQTI1NkdDTSIsImFsZyI6IlJTQS1PQUVQIn0.K8iJjhXeTQuH4WxtO3sjdnPFVe_l_4DgyWExI7KMBkpSts5Lg4iG9JjPbrl4-tqlX7I3-ghIu-pVMk8W5_th8OYZ-6qWEAhV25coYP1EDXhki6ySS7l1-9XLbVllbzMYusSKdidP6hSllVn2KEFuBJuT80x0dR0hiKFWGnGUkyeiqrse0a5O-vlMZHOHlaOvZeBevQS6ruafDSYQdp-MA2sn8fUfO67Pfo97WYSW7wMrgEQk5t9qpe4v5eyQKInK6oy7_reIRAMef0TqLsc7TdBQpY-hM2uSoBs90zc-wF9wI4wqpT9lFB11UGeleiO2b4BzxgyVqH_PbAGsFvrHqw.yxGVr9Muqxn9IPP_.r_RRlacm3ESaJQQUneJ6vUwiSTkJQOYF7Ko3sG1M2601rQ7k4GJ3ay22Lj0MQUl3C9eUV4tgiPz0V04vXFBw5enWtQBywn9FNIItKo1vOaGw2IoL2Wyuwow2HsglkfdDHF2dMvlXR0yRf6jHI6hQNZN9uZ4_L4nmOTlPkjOLRwacIRHvn55ZzTEKavxdvk5FxxycSOgn96CfnaCt_wRBPPne923X3nmKz3dyxIFjd948jUzxMLxR-ZAkto0nFEcGHRndlSODARqK4i5VoNG__UoqVP5xyibowISoFdu5PS8F5W6DlacCG51heAsb5i8yApSEZWVvOlOB4Hbxdh2I1n-UVqQJh1ZxiZWY8htk7YidnVnYhxt6gb87MmZNxQK8G1UKx4J0vN_lf_cXAB_tR_hMxvlz1Ck-2MIQ4HGvXJh0y4mazwSAaQxOyjc2mqaXJhR_ZhMJp3WJ6RLayKdLqhZH5uOkMXG6WDIzyVHuAP2lyxEVBJf6-MfcjmnTsX32eVbsysmPe38VLb6TGl-qZd8gadvJuZ-TZ14y1m3ud8Y60Yp63vWeE3Jm5xfo3I_-925aSUSJ3ImHwV_pKh8YcVP5sEWCl6Ri2RHZVKXtLgbicnYdxYSqxdlpPrDR2E6mlKui8carbyLw2n0OoW38rsSTSxyjibp3KO86cM77QZt2VOV3SN0B6WqGbg-LpkJf7GfE4hWvgVmoMvNXGxxpTOdPBgNfWEaK_rNpWkDOcIwGjJ3tEOP4Qzwh4YZJM1BRh3JISQhnO2gaT9bjDzjhRY1IglnOCyoz3x_o3vktQikkFt0wwjT_gs1nkW-rchLaVU8zMhLQvAsFR321_1O0J8ii8CwLJKWkLQHSkB_CVtkVEgTUjgyCnVqjUkjGBxUGnxZIkJ5NJKI-Tm-yUDJgURaWuJPb-MBYU8wexyeOJ59-XQqDe2NsTZ5tMBm3RuslMtpkg66pOz3etAEqejj1BbixlvI85DA-yYDzPuzUDa3G2pWyYTa6GGDAq71di4jwg_uEyZFHK-7yTCcKO-lcEKz3.nO4-13u24NkruhO8MNsxZA

    @Override
    public void run(String... args) throws Exception {

//        EncryptedJWT encryptedJWT = encryptTestJWT();
//        String testJwtString = encryptedJWT.serialize();
//        var decrypted = jwtService.decrypt(testJwtString, privateKey);

        var decrypted = jwtService.decrypt(encryptedJwt, privateKey);

        Payload payload = decrypted.getPayload();
        JWTClaimsSet jwtClaimsSet = null;
        try {
            var payloadJson = payload.toJSONObject();
            var payloadSignedJwt = payload.toSignedJWT();
            if (payloadJson != null) {
                jwtClaimsSet = decrypted.getJWTClaimsSet();
            } else if (payloadSignedJwt != null) {
                jwtClaimsSet = payloadSignedJwt.getJWTClaimsSet();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("jwtClaimsSet: {}", jwtClaimsSet);
//        System.out.println(decrypted.serialize());
    }

    private EncryptedJWT encryptTestJWT() {
        Date now = new Date();

        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer("https://openid.net")
                .subject("alice")
                .audience(Arrays.asList("https://app-one.com", "https://app-two.com"))
                .expirationTime(new Date(now.getTime() + 1000 * 60 * 10)) // expires in 10 minutes
                .notBeforeTime(now)
                .issueTime(now)
                .jwtID(UUID.randomUUID().toString())
                .build();

        return jwtService.encrypt(jwtClaims, publicKey);
    }
}
