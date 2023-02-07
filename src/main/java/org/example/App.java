package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
//        System.out.println("Hello world!");
//        Scanner sc = new Scanner(System.in);
//        String country = sc.nextLine();
//        System.out.println(getData(country));



//        creating GUI
        JFrame root = new JFrame("Details of Country");
        root.setSize(500,500);

        Font f  = new Font("Poppins",Font.BOLD,30);

        //text field
        JTextField field = new JTextField();

        //label
        JLabel dataL = new JLabel();

        field.setFont(f);
        dataL.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        dataL.setHorizontalAlignment(SwingConstants.CENTER);

        //button
        JButton button = new JButton("Get");
        button.addActionListener(event -> {
            try{
                String maindata = field.getText();
                String result = getData(maindata);
                dataL.setText(result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        button.setFont(f);
        root.setLayout(new BorderLayout());
        root.add(field, BorderLayout.NORTH);
        root.add(dataL, BorderLayout.CENTER);
        root.add(button, BorderLayout.SOUTH);
        root.setVisible(true);

    }
}