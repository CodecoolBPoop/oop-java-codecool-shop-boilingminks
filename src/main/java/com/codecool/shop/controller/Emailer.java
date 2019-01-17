package com.codecool.shop.controller;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class Emailer {
    private static final String USER_NAME = "testmann689";
    private static final String PASSWORD = "asdasdasd#123";

    public static void mailTo(String email, String name) {
        // DATA
        int userId = 1; // TODO: USER SYSTEM!
        Map<Integer, Integer>  userCart = ShoppingCartDaoMem.getInstance().getAll().get(userId);
        ProductDao productDaoMem = ProductDaoMem.getInstance();

        String from = USER_NAME;
        String pass = PASSWORD;

        if (email.equals("")){
            email = "a@gmail.com";
        }

        String[] to = { email };
        String subject = "Your purchase";
        String body = "Dear " + name + ",\n"
                + "You have successfully bought the following items from our store:\n";

        for (Integer key : userCart.keySet()) {
            Product product = productDaoMem.find(key);
            body = body.concat(userCart.get(key) + " " + product.getName() + "\n");
        }

        body = body.concat("\nThank you for using our website!\n\nYours sincerely,\nBoiling Minks web shop");

        sendFromGMail(from, pass, to, subject, body);
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }












}
