package org.example02.test;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Welcome {

	private Twitter twitter;
	private PrintStream consolePrint;
	private List<Status> statuses;
	public ArrayList<String> pos;
	public ArrayList<String> neg;


	public Welcome(PrintStream console) {

		pos = new ArrayList<String>();
		neg = new ArrayList<String>();
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

	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		
		
	}


	public static double compare(ArrayList<String> a, ArrayList<String> b, String c) {
		ArrayList<String> message = new ArrayList<String>();
		ArrayList<String> stopW = new ArrayList<String>();
		String stopwords = "im a about above after again against all am an and any are aren't as at be because been before being below between both but by can't cannot could couldn't did didn't do does doesn't doing don't down during each few for from further had hadn't has hasn't have haven't having he he'd he'll he's her here here's hers herself him himself his how how's i i'd i'll i'm i've if in into is isn't it it's its itself let's me more most mustn't my myself no nor not of off on once only or other ought our ours ourselves out over own same shan't she she'd she'll she's should shouldn't so some such than that that's the their theirs them themselves then there there's these they they'd they'll they're they've this those through to too under until up very was wasn't we we'd we'll we're we've were weren't what what's when when's where where's which while who who's whom why why's with won't would wouldn't you you'd you'll you're you've your yours yourself yourselves";
		// gives size of stop array
		String stop[] = stopwords.split(" ");
	
		int pos = 0;
		int neg = 0;
		String search[] = c.split(" ");

		
		
		for(int i = 0; i < search.length; i++) {
			for(int j = 0; j < stop.length; j++) {
				if(search[i].toLowerCase().equals(stop[j].toLowerCase())) {
					search[i] = "";
				}
			}
		}
		
		for (int i = 0; i < search.length; i++) {
			for (int j = 0; j < a.size(); j++) {
				if (search[i].toUpperCase().equals(a.get(j).toUpperCase())) {
					pos++;
					
				}
			}
		}

		for (int j = 0; j < search.length; j++) {
			for (int i = 0; i < b.size(); i++) {
				if (search[j].toUpperCase().equals(b.get(i).toUpperCase())) {
					neg++;
					
					}

				}
			}
		

		if(neg > pos) {
			return 0;
		}
		if(pos > 0 && neg == 0) {
			return 1;
		}
		if(neg > 0 && pos == 0) {
			return 0;
		}
		if(pos == 0 && neg == 0) {
			return 0;
		}
		if(pos <= 2 && neg >= 1) {
			return 0;
		}
		if(pos > 7 && neg <=1) {
			return 1;
		}
		
		else {
			return 1;
		}

		
	}

	public static ArrayList<String> PartTwo() throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://localhost:3306/sys";
		String name = "root";
		String password = "Sridevi123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, name, password);
		String query = "select * from Postive";
		PreparedStatement st = con.prepareStatement(query);

		ArrayList<String> b = new ArrayList<String>();
		query = "select * from Nej";
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			b.add(rs.getString(2));
		}

		b.add("won't");

		return b;

	}

	public ArrayList<String> SQL() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/sys";
		String name = "root";
		String password = "Sridevi123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, name, password);
		String query = "select * from Postive";
		PreparedStatement st = con.prepareStatement(query);

		query = "select * from Postive";
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> a = new ArrayList<String>();
		while (rs.next()) {
			a.add(rs.getString(2));
		}

		return a;

	}

	public double tweetAnalyzer(String searchTerm) throws ClassNotFoundException, SQLException {
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		int count = 0;
		int counter = 0;
		int wantedTweets = 500;
		double sum = 0;
		long lastSearchID = Long.MAX_VALUE;
		int remainingTweets = wantedTweets;
		Query query = new Query(searchTerm);
		List<Status> tweets = new ArrayList<Status>();
		 try
		{ 

		  while(remainingTweets > 0)
		  {
		    remainingTweets = wantedTweets - tweets.size();
		    if(remainingTweets > 100)
		    {
		      query.count(100);
		    }
		    else
		    {
		     query.count(remainingTweets); 
		    }
		    QueryResult result = twitter.search(query);
		    tweets.addAll(result.getTweets());
		    Status s = tweets.get(tweets.size()-1);
		    
		    query.setMaxId(s.getId());
		    remainingTweets = wantedTweets - tweets.size();
		  }

		  for (int k = 0; k < tweets.size(); k++) {
				Status t = (Status) tweets.get(k);
				String msg = t.getText();
				sum = sum + compare(SQL(), PartTwo(), msg);
				
			
		}
		  
		  
		}
		catch(TwitterException te)
		{
		  System.out.println("Failed to search tweets: " + te.getMessage());
		  System.exit(-1);
		}
		 
//		double sum = 0;
//		Query query = new Query(searchTerm);
//		int numberOfTweets = 512;
//		long lastID = Long.MAX_VALUE;
//		List<Status> tweets = new ArrayList<Status>();
//		int i = 0;
//		while (tweets.size() < numberOfTweets || i < 10) {
//			i++;
//			if (numberOfTweets - tweets.size() > 100)
//				query.setCount(100);
//			else
//				query.setCount(numberOfTweets - tweets.size());
//			try {
//				QueryResult result = twitter.search(query);
//				tweets.addAll(result.getTweets());
//				System.out.println("Gathered " + tweets.size() + " tweets");
//				for (Status t : tweets)
//					if (t.getId() < lastID)
//						lastID = t.getId();
//
//			}
//
//			catch (TwitterException te) {
//				System.out.println("Couldn't connect: " + te);
//			}
//			;
//			query.setMaxId(lastID - 1);
//		}
//
//		for (int k = 0; k < tweets.size(); k++) {
//			Status t = (Status) tweets.get(k);
//			String msg = t.getText();
//			sum = sum + compare(SQL(), PartTwo(), msg);
//
//		}

//		String s = "Im about to go outside with my friends";
//		String ab[] = s.split("\\W+");
//		
//		String stopwords = "im a about above after again against all am an and any are aren't as at be because been before being below between both but by can't cannot could couldn't did didn't do does doesn't doing don't down during each few for from further had hadn't has hasn't have haven't having he he'd he'll he's her here here's hers herself him himself his how how's i i'd i'll i'm i've if in into is isn't it it's its itself let's me more most mustn't my myself no nor not of off on once only or other ought our ours ourselves out over own same shan't she she'd she'll she's should shouldn't so some such than that that's the their theirs them themselves then there there's these they they'd they'll they're they've this those through to too under until up very was wasn't we we'd we'll we're we've were weren't what what's when when's where where's which while who who's whom why why's with won't would wouldn't you you'd you'll you're you've your yours yourself yourselves";
//		String[] arrin = stopwords.split("\\W+");
//		ArrayList<String>d = new ArrayList<String>();
//		for(int i=0; i < ab.length; i++) {
//			d.add(ab[i]);
//		}
//		ArrayList<String> c = new ArrayList<String>();
//		for(int i = 0; i < arrin.length; i++) {
//			c.add(arrin[i]);
//		}
//		
//		System.out.println(c.toString());
//		for(int i= 0; i < d.size(); i++) {
//			for(int j = 0; j < c.size(); j++) {
//				if(d.get(i).toLowerCase().equals(c.get(j).toLowerCase())) {
//					d.remove(i);
//				}
//			}
//			
//		}
//		
//		System.out.println(d.toString());
//		
//		System.out.println(Arrays.toString(arrin));
////		
//		System.out.println(Arrays.toString(ab));
		//return sum / tweets.size();
		return sum /tweets.size();
	}
	
//	public ArrayList<String> tweetPositive(ArrayList<String> a){
//		 return a;
//	}
//	public ArrayList<String> tweetNegative(ArrayList<String> b){
//		return b;
//	}
	
//	public ArrayList<String> tweetPositive(String searchTerm) throws ClassNotFoundException, SQLException {
//
//		ArrayList<String> pos = new ArrayList<String>();
//		double counter = 0;
//		int wantedTweets = 250;
//		double sum = 0;
//		long lastSearchID = Long.MAX_VALUE;
//		int remainingTweets = wantedTweets;
//		Query query = new Query(searchTerm);
//		List<Status> tweets = new ArrayList<Status>();
//		 try
//		{ 
//
//		  while(remainingTweets > 0)
//		  {
//		    remainingTweets = wantedTweets - tweets.size();
//		    if(remainingTweets > 100)
//		    {
//		      query.count(100);
//		    }
//		    else
//		    {
//		     query.count(remainingTweets); 
//		    }
//		    QueryResult result = twitter.search(query);
//		    tweets.addAll(result.getTweets());
//		    Status s = tweets.get(tweets.size()-1);
//		    
//		    query.setMaxId(s.getId());
//		    remainingTweets = wantedTweets - tweets.size();
//		    
//			  for (int k = 0; k < tweets.size(); k++) {
//					Status t = (Status) tweets.get(k);
//					String msg = t.getText();
//					if(compare(SQL(), PartTwo(), msg) == 1) {
//						counter++;
//					}
//						
//					
//		
//			}
//			  
//		  }
//
//		  
//		  
//		}
//		catch(TwitterException te)
//		{
//		  System.out.println("Failed to search tweets: " + te.getMessage());
//		  System.exit(-1);
//		}
//		 return counter;
//	}
	
//	public ArrayList<String> tweetNegative(String searchTerm) throws ClassNotFoundException, SQLException {
//
//		ArrayList<String> negative = new ArrayList<String>();
//		int counter = 0;
//		int wantedTweets = 59;
//		double sum = 0;
//		long lastSearchID = Long.MAX_VALUE;
//		int remainingTweets = wantedTweets;
//		Query query = new Query(searchTerm);
//		List<Status> tweets = new ArrayList<Status>();
//		 try
//		{ 
//
//		  while(remainingTweets > 0)
//		  {
//		    remainingTweets = wantedTweets - tweets.size();
//		    if(remainingTweets > 100)
//		    {
//		      query.count(100);
//		    }
//		    else
//		    {
//		     query.count(remainingTweets); 
//		    }
//		    QueryResult result = twitter.search(query);
//		    tweets.addAll(result.getTweets());
//		    Status s = tweets.get(tweets.size()-1);
//		    
//		    query.setMaxId(s.getId());
//		    remainingTweets = wantedTweets - tweets.size();
//		    
//		    for (int k = 0; k < tweets.size(); k++) {
//				Status t = (Status) tweets.get(k);
//				String msg = t.getText();
//				if(compare(SQL(), PartTwo(), msg) == 0)
//					counter++;
//						if(counter < 6){
//							negative.add(msg);
//						}
//				
//	
//		}
//		  }
//
//		  
//		  
//		}
//		catch(TwitterException te)
//		{
//		  System.out.println("Failed to search tweets: " + te.getMessage());
//		  System.exit(-1);
//		}
//		 return negative;
//	}

}
