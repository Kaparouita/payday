package View;

import Controller.controller;

import java.io.IOException;

public class Dialogs {
    public static void main(String[] args) throws IOException {
        controller c= new controller();

        view test = new view(c);
       /* PayDayCards pdv = new PayDayCards();
        pdv.setVisible(true);
        pdv.readFile("View/resources/dealCards_greeklish.csv", "Deal");
        pdv.readFile("View/resources/mailCards.csv", "Mail"); }

        */
    }}