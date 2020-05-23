package org.example02.test;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
public class TwitterActual extends HttpServlet {
	
	public static String user;
    private static PrintStream consolePrint;
    public static double sum;
   //public Welcome a;

	public static void main(String [] args) throws ClassNotFoundException, SQLException {
		//a = new Welcome(consolePrint);

		//Scanner in = new Scanner(System.in);
		//sum = a.tweetAnalyzer(getString());
		
		
		
		}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		user = req.getParameter("tweet");
		 double a = 0;
		 double s = 0;
		 double percentile;
		 String character = "";
		 String pos1 = "";
		 String pos2 = "";
		 String pos3 = "";
		 String pos4 = "";
		 String neg1 = "";
		 String neg2 = "";
		 String neg3 = "";
		 String neg4
		 
		 = "";
		try {
			s = Math.round(getSum(user));
//			pos1 = getPos(user).get(1).toString();
//			pos2 = getPos(user).get(5).toString();
//			pos3 = getPos(user).get(2).toString();
//			pos4 = getPos(user).get(4).toString();
//			neg1 = getNeg(user).get(0).toString();
//			neg2 = getNeg(user).get(2).toString();
//			neg3 = getNeg(user).get(4).toString();
//			neg4 = getNeg(user).get(5).toString();
			a = 100-s;
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(s > 0 && s <= 30) {
			character = user + " is very unpopular with the public. Our PositiveSpectrum gave " + user + " a Positivity Rating of "  + s + " %. " + user + " is definitely not a fan-favorite as " +a + "% of people dislike this user.";
		}
		if(s > 30 && s <=50) {
			character = user + " is neutral with the public. Our PositiveSpectrum gave " + user + " a Positivity Rating of "  + s + " %. " + user + " is not favored by everyone, but is loved by certain people! ";
		}
		if(s >50 && s <=60) {
			character = user + " is great with the public. Our PositiveSpectrum gave " + user + " a Positivity Rating of "  + s + " %. " + user + " is favored by the majority as a stunning " + a + "% showed negaivity towards this person";

		}
		if(s > 60) {
			character = user + " is an inspiration to the public. Our PositiveSpectrum gave " + user + " a Positivity Rating of "  + s + " %. " + user + " is favored by the majority of people! Follow " +user+" on Twitter right now!";

		}
		
			
				resp.getWriter().println("<html>\n" + 
						"<head>\n" + 
						"<meta name=\"viewport\" content=\"width=device-width\">\n" + 
						"<meta name=\"description\" content=\"Twitter Analyzer\">\n" + 
						"<meta name=\"keywords\"\n" + 
						"	content=\"Find How Positive Celelbrities Are Considered In Society\">\n" + 
						"<meta name=\"author\" content=\"Aashish Komaragiri\">\n" + 
						"<title>Percentage of Positivity</title>\n" + 
						"\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />\n" + 
						"\n" + 
						"</head>\n" + 
						"\n" + 
						"<body>\n" + 
						"	<header>\n" + 
						"		<div class=\"container\">\n" + 
						"			<div id=\"branding\">\n" + 
						"				<h1>\n" + 
						"					<span class=\"highlight\">Celebrity </span>Analyzer\n" + 
						"				</h1>\n" + 
						"			</div>\n" + 
						"			<nav>\n" + 
						"				<ul>\n" + 
						"					<li><a class=\"current\" href=\"indexOne.html\">Home</a></li>\n" + 
						"					<li><a href=\"about.html\">About</a></li>\n" + 
						"					<li><a href=\"services.html\">Services</a></li>\n" + 
						"				</ul>\n" + 
						"			</nav>\n" + 
						"		</div>\n" + 
						"	</header>\n" + 
						"\n" + 
						"\n" + 
						"	<section id=\"newsletter\">\n" + 
						"		<div class=\"container\">\n" + 
						"			<h1>Please Enter Any Person</h1>\n" + 
						"			<form action=\"twitter.html\" method=\"get\">\n" + 
						"				<input type=\"text\" placeholder=\"Enter Name\" name=\"tweet\"><br>\n" + 
						"				<button type=\"submit\" class=\"button_1\">Search</button>\n" + 
						"			</form>\n" + 
						"		</div>\n" + 
						"	</section>\n" + 
						"\n" + 
						"	<section id=\"main\">\n" + 
						"		<div id=\"donutchart\" style=\"width: 900px; height: 500px;\"></div>\n" + 
//						"		<ul>\n" + 
//						"			<li>Positive Tweets: \n" + 
//						"				<ul>\n" + 
//						"					<li>" + pos1 +"</li>\n" + 
//						"					<li>" + pos2 + "</li>\n" + 
//						"					<li>"+  pos3 + "</li>\n" + 
//						"					<li>" + pos4 + "</li>\n" + 
//						"				</ul>\n" + 
//						"			</li>\n" + 
//						"		</ul>\n" + 
//						"		<ul>\n" + 
//						"			<li>Negative Tweets: \n" + 
//						"				<ul>\n" + 
//						"					<li>" + neg1 + "</li>\n" + 
//						"					<li>" + neg2 + "</li>\n" + 
//						"					<li>" + neg3 + "</li>\n" + 
//						"					<li>" + neg4 + "</li>\n" + 
//						"				</ul>\n" + 
//						"			</li>\n" + 
//						"		</ul>\n" + 
						"		<div class=\"container\">\n" + 
						"			<article id=\"main-col\">\n" + 
						"				<h1 class=\"page-title\">Percent of Positivity</h1>\n" + 
						"				<p>The Percent of Positivity surrounding " + user +" is "+ s + ". " + character + "</p>\n" + 
						"\n" + 
						"				<script type=\"text/javascript\"\n" + 
						"					src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" + 
						"				<script type=\"text/javascript\">\n" + 
						"					google.charts.load(\"current\", {\n" + 
						"						packages : [ \"corechart\" ]\n" + 
						"					});\n" + 
						"					google.charts.setOnLoadCallback(drawChart);\n" + 
						"					function drawChart() {\n" + 
						"						var data = google.visualization.arrayToDataTable([\n" + 
						"								[ 'Percent of Positivity', '%' ],\n" + 
						"								[ 'Positive  Tweets'," + s + " ],\n" + 
						"								[ 'Negative Tweets'," + a + " ],\n" + 
						"\n" + 
						"						]);\n" + 
						"\n" + 
						"						var options = {\n" + 
						"							title : 'Analyzing Tweets for Certain Person',\n" + 
						"							pieHole : 0.4,\n" + 
						"						};\n" + 
						"\n" + 
						"						var chart = new google.visualization.PieChart(document\n" + 
						"								.getElementById('donutchart'));\n" + 
						"						chart.draw(data, options);\n" + 
						"					}\n" + 
						"				</script>\n" + 
						"\n" + 
						"			</article>\n" + 
						"		</div>\n" + 
						"	</section>\n" + 
						"	<footer>\n" + 
						"		<p>Aashish Web Design, Copyright &copy; 2017</p>\n" + 
						"\n" + 
						"	</footer>\n" + 
						"</body>\n" + 
						"</html>");
			
//			resp.getWriter().println("<head>");
//			resp.getWriter().println("<title> This is the response </title>");
//			resp.getWriter().println("</head>");
//			resp.getWriter().println("<body>");
//			resp.getWriter().println("The Percent of Positive Tweets associating " + user + " is " + sum + "%");
//			
//			resp.getWriter().println("</body>");
//			resp.getWriter().println("</html>");
		
	}
	
//	public static void and(HttpServletResponse resp, String a) throws IOException, ClassNotFoundException, SQLException {
//		
//		double sum = getSum();
//		resp.getWriter().println("<html>");
//		resp.getWriter().println("<head>");
//		resp.getWriter().println("<title> This is the response </title>");
//		resp.getWriter().println("</head>");
//		resp.getWriter().println("<body>");
//		resp.getWriter().println("Your name is " + sum);
//		
//		resp.getWriter().println("</body>");
//		resp.getWriter().println("</html>");
//	}
//	
//	public static String getString() {
//		return user;
//	}
	
	public static double getSum(String s) throws ClassNotFoundException, SQLException {
		
		Welcome a = new Welcome(consolePrint);
		return a.tweetAnalyzer(s) * 100;
	}
	
//	private static ArrayList<String> getPos(String s) throws ClassNotFoundException, SQLException {
//		Welcome a = new Welcome(consolePrint);
//		return a.tweetPositive(s);
//	}
//	private static ArrayList<String> getNeg(String s) throws ClassNotFoundException, SQLException {
//		Welcome a = new Welcome(consolePrint);
//		return a.tweetNegative(s);
//	}
		
}
