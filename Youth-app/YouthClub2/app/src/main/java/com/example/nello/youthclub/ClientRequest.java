package com.example.nello.youthclub;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ClientRequest {

public ClientRequest() {

    }

    public void autenticator(String Imei){
        CookieManager cookieManager = new CookieManager();

        try {
            URL url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?auth="+Imei);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            Map<String, List<String>> headerFields = connection.getHeaderFields(); //recupero i campi
            List<String> cookiesHeader = headerFields.get("Set-Cookie"); //prendo solo i cookies

            if (cookiesHeader != null) {
                for (String cookie : cookiesHeader) {
                    System.out.println(cookie);
                    cookieManager.getCookieStore().add(null,HttpCookie.parse(cookie).get(0));
                }

                //ottengo il jsessionid e lo riutilizzo per le volte successive!
            }

        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    //paese o coordinate gps
    public void search(String luogo,int cat,boolean mod) {//0 GPS 1 string
        try {
            URL url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?search="+luogo+"&cat="+cat+"&mod=1");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void search(Float lat,Float lng,int cat){
        try {
            URL url = new URL("http://10.0.2.2:8080/YouthClub/index.jsp?search="+lat+","+lng+"&cat="+cat+"&mod=1");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void SearchByName(String locale,String posto){
        try {
            URL url = new URL("http://10.0.2.2:8080/YouthClub/URL url=index.jsp?name=locale"+locale+"&posto"+posto);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
    //"lat,log"





