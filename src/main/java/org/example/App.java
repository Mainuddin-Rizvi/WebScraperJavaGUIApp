package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static String getData(String country) throws IOException {
        String url = "https://www.worldometers.info/coronavirus/country/"+country+"/";
        StringBuffer sb  = new StringBuffer();
        Document  doc = Jsoup.connect(url).get();
//        printing body or rereiving body
//        System.out.println(doc.body());

        // to get particular element according to div id
        Elements element = doc.select("#maincounter-wrap");
        element.forEach((e) -> {
            String text = e.select("h1").text();
            String count = e.select(".maincounter-number>span").text();
            sb.append(text).append(" : ").append(count).append("\n");

        });

        return sb.toString();

    }
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        String country = sc.nextLine();
        System.out.println(getData(country));
    }
}