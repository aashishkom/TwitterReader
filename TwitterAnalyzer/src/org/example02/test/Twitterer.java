package org.example02.test;



import twitter4j.GeoLocation;      
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

public class Twitterer
   {
      private Twitter twitter;
      private PrintStream consolePrint;
      private List<Status> statuses;

     
      public Twitterer(PrintStream console)
      {
    	  
    	  ConfigurationBuilder cb = new ConfigurationBuilder();
          cb.setDebugEnabled(true)
           .setOAuthConsumerKey("hrcoS8R1RW2V7PcLKUVZ66Kqn")
           .setOAuthConsumerSecret("bkLvLYgiCKCkujdoo9RPhyUMSpH5mNLh9TlSexbSzIGkztlENN")
           .setOAuthAccessToken("1258967068868820992-izytBwXkgZZlVG7yvgoTkLBYfNJVxz")
           .setOAuthAccessTokenSecret("3Wd2rX8WoHr957jYo6uEYNF9K9XpwpmzouQtGgxxhLoZb");
       
          TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance(); 
         consolePrint = console;
         statuses = new ArrayList<Status>();
      }
   
      public void tweetOut(String message) throws TwitterException, IOException
      {
    	  
    	  Status status = twitter.updateStatus(message);
    	  System.out.println("Successfully updated status to [" +status.getText() +"].");
      }
   
    
      @SuppressWarnings("unchecked")
      public void queryHandle(String handle) throws TwitterException, IOException
      {
    	  statuses.clear();
    	  fetchTweets(handle);
    	  int counter = statuses.size();
    	  
    	  while(counter > 0) {
    		  
    		  counter--;
    		  System.out.println("Tweet #" + counter + ": " + statuses.get(counter).getText());
    	  }
    	  
      }
  
      private void fetchTweets(String handle) throws TwitterException, IOException
      {
    	  
    	  Paging page = new Paging(1,200);
    	  int p = 1;
    	  while(p <=10) {
    		  page.setPage(p);
    		  statuses.addAll(twitter.getUserTimeline(handle, page));
    		  p++;
    		  
    	  }
    
       }   
    
  
   
      public void saQuery (String searchTerm)
      {
    	  
    	  Query query = new Query(searchTerm);
    	  int numberOfTweets = 512;
    	  long lastID = Long.MAX_VALUE;
    	  int k = 0;
    	  ArrayList<Status> tweets = new ArrayList<Status>();
    	  while (tweets.size () < numberOfTweets || k < 10) {
    		  k++;
    	    if (numberOfTweets - tweets.size() > 100)
    	      query.setCount(100);
    	    else 
    	      query.setCount(numberOfTweets - tweets.size());
    	    try {
    	      QueryResult result = twitter.search(query);
    	      tweets.addAll(result.getTweets());
    	      System.out.println("Gathered " + tweets.size() + " tweets");
    	      for (Status t: tweets) 
    	        if(t.getId() < lastID) lastID = t.getId();

    	    }

    	    catch (TwitterException te) {
    	      System.out.println("Couldn't connect: " + te);
    	    }; 
    	    query.setMaxId(lastID-1);
    	  }

    	  for (int i = 0; i < tweets.size(); i++) {
    	    Status t = (Status) tweets.get(i);

    	    GeoLocation loc = t.getGeoLocation();

    	    String user = t.getUser().getScreenName();
    	    String msg = t.getText();
    	    String time = "";
    	    if (loc!=null) {
    	      Double lat = t.getGeoLocation().getLatitude();
    	      Double lon = t.getGeoLocation().getLongitude();
    	      System.out.println(i + " USER: " + user + " wrote: " + msg + " located at " + lat + ", " + lon);
    	    } 
    	    else 
    	      System.out.println(i + " USER: " + user + " wrote: " + msg);
    	  }
    	}
//    	  query.setCount(500);
//    	  query.setGeoCode(new GeoLocation(44.986656, -93.258133), 1000, Query.MILES);
//    	  query.setSince("2019-05-04");
//    	  
//    	  try {
//    		  QueryResult result = twitter.search(query);
//    		  int counter = 0;
//    		  System.out.println("Count: " + result.getTweets().size());
//    		  for(Status tweet: result.getTweets()) {
//    			  counter++;
//    			  System.out.println("Tweet #" + counter + ": @" + tweet.getUser().getName() + " tweeted \"" + tweet.getText() + "\"");
//    		  }
//    	  }catch(Exception e) {
//    		  e.printStackTrace();
//    	  }
//    	  
    
       }
   
   
   