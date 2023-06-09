package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class NetProg {
     public static void main(String[] args) {
         String urlName = "https://www.onliner.by/";
         int timeout = 10_000;
         URL url = null;
         try {
             url = new URL("https://www.onliner.by/");
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
             conn.setRequestMethod("GET");
             conn.setRequestProperty("User-Agent", "Mozilla/5.0");

             int responseCode = conn.getResponseCode(); //ответ от сервера
             System.out.println("Response Code : " + responseCode);
            //чтение содержимого ответа
             BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             String inputLine;
             StringBuilder content = new StringBuilder();

             while ((inputLine = in.readLine()) != null) {
                 content.append(inputLine);
             }
             in.close();
             System.out.println("Content : " + content.toString());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
