 package org.example02.test;
import twitter4j.TwitterException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

   public class TwitterDriver
   {
      private static PrintStream consolePrint;
   
      public static void main (String []args) throws TwitterException, IOException, ClassNotFoundException, SQLException
      {
         // set up classpath and properties file
             
         Twitterer bigBird = new Twitterer(consolePrint);
         
         Welcome a = new Welcome(consolePrint);
         Scanner in = new Scanner(System.in);
         System.out.println("Welcome to the positive/negative twitter analyzer. \n Please Enter a Twitter Topic and we will display the percentage of positive tweets");
         String next = in.next();
        double tweetCheck = a.tweetAnalyzer(next) * 100;
        System.out.println(tweetCheck);
       //  System.out.println(a.tweetPositive(next));
         
         
        // System.out.println("The amount of tweets that are positive based on your entry are " + tweetCheck + "%");

         // Problem 1         
         // Create and set a String called message here
//      
//         String message = "Minnesota";
//         bigBird.tweetOut(message);

         // Problem 2
         // Choose a* public Twitter user's handle
         // and analyze their tweets
         
   
//         Scanner scan = new Scanner(System.in);
//         System.out.print("Please enter a Twitter handle, do not include the '@' symbol (or 'done' to quit.)");
//         String twitter_handle = scan.next();
//         while (!"done".equals(twitter_handle))
//         {
//             bigBird.queryHandle(twitter_handle);
//             System.out.print("Please enter a Twitter handle, do not include the '@' symbol (or 'done' to quit.)");
//             twitter_handle = scan.next();
//             
//         }
         
         // Problem 3
         // Find all Spurs-related tweets in a 20 mile radius around San Antonio
         // in the past day.
      // bigBird.saQuery("Minnesota Vikings");

      }//main         
         
   }//class    
         
   