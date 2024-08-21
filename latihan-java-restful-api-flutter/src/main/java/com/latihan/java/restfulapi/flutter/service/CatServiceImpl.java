package com.latihan.java.restfulapi.flutter.service;

import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Service
public class CatServiceImpl implements CatService {

    @Override
    public String get() {
        String url = "https://catfact.ninja/fact";
        String Hasil = "";
        String stackTrace;
        String responseString;

        URL surl;
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            surl = new URL(url);
            URLConnection connection = surl.openConnection();
            HttpsURLConnection httpConn = (HttpsURLConnection) connection;


            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestMethod("GET");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            try (InputStreamReader isr
                         = new InputStreamReader(httpConn.getInputStream())) {

                BufferedReader in = new BufferedReader(isr);
                while ((responseString = in.readLine()) != null) {
                    Hasil = Hasil + responseString;
                }
            } catch (Exception ex) {
                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                stackTrace = sw.toString();
                Hasil = stackTrace;
            }

        } catch (MalformedURLException ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            stackTrace = sw.toString();
            Hasil = stackTrace;
        } catch (IOException | KeyManagementException | NoSuchAlgorithmException ex1) {
            StringWriter sw = new StringWriter();
            ex1.printStackTrace(new PrintWriter(sw));
            stackTrace = sw.toString();
            Hasil = stackTrace;
        }
        return Hasil;
    }
}
