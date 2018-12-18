package com.example.demo;


import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@SessionAttributes("user")
@Controller
public class fbcontroller {

	@Autowired 
	Repo repo;
	
	@Autowired
	private userservice userservice1;
	
	@Autowired
	RepoDeluser RepoDeluser12;
	
	@Autowired
	Deluserservice Deluserservice12; 
	
	@Value("#{environment.accesskey}")
	String accesskey;
	@Value("#{environment.secretkey}")
	String secretkey;

	@ModelAttribute("user")
	public User setUpUserForm() {
	   return new User();
	}
	
	 @GetMapping("/")
	    public String log(HttpServletRequest req)
	    {
	    	return ("latestfb");
	    }
	 
	
	    
	    @PostMapping(value="/facebookRedirect")
	    public ModelAndView handle(HttpServletRequest req,@RequestParam(name="myId") String myId,
	    		@RequestParam(name="myName") String myName,
	    		@RequestParam(name="myFriends") String myFriends,
	    		@RequestParam(name="myEmail") String myEmail,@ModelAttribute User user)
	    {
	    
	    	ModelAndView indexPage= new ModelAndView();
	    	User u1=new User();
	    	u1.setId(Long.parseLong(myId));
	    	user.setId(Long.parseLong(myId));
	    	u1.setName(myName);
	    	user.setName(myName);
	    	
	  
	   
	    	if(repo.findById(Long.parseLong(myId))==null) 	
	    	{
	    		repo.save(u1);
	    	return new ModelAndView("afterfblogin");
	    	
	    	}

	    	User u2=repo.findById(Long.parseLong(myId)); 
	    	
	    	System.out.print( myId+" "+myName + "   " + myFriends+"   Description   ");
	    	
	    	String[] splitted=myFriends.split("/");
	    	for(int i =0;i<splitted.length;i++)
	    	{
	    		System.out.println(i+":"+splitted[i]);
	    	}
	    	if(myId.equals("101973917498982"))
	    	{
	    		
	    		return new ModelAndView("admin");
	    	}
	    	else 
	    	{
	    		System.out.println("name"+user.getName());
	    		indexPage.addObject("n",user.getName());
	    		User u3=repo.findById(Long.parseLong(myId));
	    		System.out.println("name from user 3 " + u3.getName()+"descr");
	    		System.out.println("u3  name is "+u3.getName());
	    		
	    		
		         indexPage.setViewName("home");
	    		return indexPage;
	    	}
	    	
	    }
	    
	    
	    
	    // Admin user story 2 working 
	    @GetMapping("/show-users")
	    public String showAllUser(HttpServletRequest request) {
	    request.setAttribute("users",userservice1.showAllUsers());
	    
	    	return "showusers";
	    }
	    
	    
	    // Admin user story 3 
	    @RequestMapping("/delete-user")
		 	
	    public String deleteMyUser(@RequestParam int id, HttpServletRequest request) {
	    	
	    	RepoDeluser12.deleteById(id);
	    	request.setAttribute("users", Deluserservice12.showAllUsers());	
			return "showusers";
						
			
		} 
	 
	    
	  //ninad code starts here 
	    //user story 3 and 8 together
	    
	  //Using PoJo Classes
		@GetMapping("/teams")
		public ModelAndView getTeams() {
			ModelAndView showTeams = new ModelAndView("showTeams");
			showTeams.addObject("name", "hemanshu"); 
			
			//Endpoint to call
			String url ="https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/overall_team_standings.json";
			//Encode Username and Password
	        String encoding = Base64.getEncoder().encodeToString("59045d1e-7518-4ebe-9ca8-3e0dd4:Hemanshu68@".getBytes());
	        // TOKEN:PASS
	        //Add headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Basic "+encoding);
			HttpEntity<String> request = new HttpEntity<String>(headers);

			//Make the call
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<NBATeamStanding> response = restTemplate.exchange(url, HttpMethod.GET, request, NBATeamStanding.class);
			NBATeamStanding ts = response.getBody(); 
	        System.out.println(ts.toString());
			//Send the object to view
	        showTeams.addObject("teamStandingEntries", ts.getOverallteamstandings().getTeamstandingsentries());
	        
			return showTeams;
		}
		
				
	    //ninad code ends here
		
		//User story 4
		@GetMapping("/teamdailyscore")
		public ModelAndView getTeamsa() {
			ModelAndView showscore = new ModelAndView("showscore");
			showscore.addObject("name", "hemanshu"); 
			
			//Endpoint to call
			String url ="https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/scoreboard.json?fordate=20181022";
			//Encode Username and Password
	        String encoding = Base64.getEncoder().encodeToString("59045d1e-7518-4ebe-9ca8-3e0dd4:Hemanshu68@".getBytes());
	        // TOKEN:PASS
	        //Add headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Basic "+encoding);
			HttpEntity<String> request = new HttpEntity<String>(headers);

			//Make the call
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Nbateamscore> response = restTemplate.exchange(url, HttpMethod.GET, request, Nbateamscore.class);
			Nbateamscore ts = response.getBody(); 
			
			
			
	        System.out.println(ts.toString());
			//Send the object to view
	        showscore.addObject("gameScore", ts.getScoreboard().getGameScoress());
	        
	    
	        
			return showscore;
		}
		
		
		
		//user story 5 for profile of team 
		
		@GetMapping("/teamsprof")
		public ModelAndView getTeamsA() {
			ModelAndView showteamtoprof = new ModelAndView("showteamtoprof");
			showteamtoprof.addObject("name", "hemanshu"); 
			
			//Endpoint to call
			String url ="https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/overall_team_standings.json";
			//Encode Username and Password
	        String encoding = Base64.getEncoder().encodeToString("59045d1e-7518-4ebe-9ca8-3e0dd4:Hemanshu68@".getBytes());
	        // TOKEN:PASS
	        //Add headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Basic "+encoding);
			HttpEntity<String> request = new HttpEntity<String>(headers);

			//Make the call
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<NBATeamStanding> response = restTemplate.exchange(url, HttpMethod.GET, request, NBATeamStanding.class);
			NBATeamStanding ts = response.getBody(); 
	        System.out.println(ts.toString());
			//Send the object to view
	        showteamtoprof.addObject("teamStandingEntries", ts.getOverallteamstandings().getTeamstandingsentries());
	        
			return showteamtoprof;
		}
		
		
		
		//Using objectMapper  for user story 5 profile of a team 
		@GetMapping("/team")
		public ModelAndView getTeamInfonew(
				@RequestParam("id") String teamID,HttpRequest request1 
				) {
			ModelAndView teamprofile = new ModelAndView("teamprofile");
			ArrayList<HashMap<String, String>> gameDetails = new ArrayList<HashMap<String, String>>();
			String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/team_gamelogs.json?team=" + teamID;
			String encoding = Base64.getEncoder().encodeToString("59045d1e-7518-4ebe-9ca8-3e0dd4:Hemanshu68@".getBytes());
	        
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Basic "+encoding);
			HttpEntity<String> request = new HttpEntity<String>(headers);

			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
			String str = response.getBody(); 
			ObjectMapper mapper = new ObjectMapper();
			try {
				JsonNode root = mapper.readTree(str);
				System.out.println(str);
			
		        System.out.println(root.get("teamgamelogs").get("lastUpdatedOn").asText());
		        System.out.println(root.get("teamgamelogs").get("gamelogs").getNodeType());
		        
		  
		        
		        JsonNode gamelogs = root.get("teamgamelogs").get("gamelogs");
		        
		        if(gamelogs.isArray()) {
		        	
		        	
		        	gamelogs.forEach(gamelog -> {
		        		
		        		JsonNode game = gamelog.get("game");
		     
		        		JsonNode stats = gamelog.get("stats");
		        		JsonNode team = gamelog.get("team");
		        		
		        		
		        		HashMap<String,String> gameDetail = new HashMap<String, String>();
		        		gameDetail.put("id", game.get("id").asText());
		        		gameDetail.put("date", game.get("date").asText());
		        		gameDetail.put("time", game.get("time").asText());
		        		
		        		        		
		        		gameDetail.put("awayTeam", game.get("awayTeam").get("Name").asText());
		        		gameDetail.put("homeTeam", game.get("homeTeam").get("Name").asText());
		        		
		        		
		        		gameDetail.put("Teamname", team.get("Name").asText());
		        		
		        		
		        		gameDetail.put("Wins", stats.get("Wins").get("#text").asText());
		            	gameDetail.put("Losses", stats.get("Losses").get("#text").asText());
		        	
		        		gameDetails.add(gameDetail);
		        		
		        	});
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			
			teamprofile.addObject("gameDetails", gameDetails);
			teamprofile.addObject("id",teamID);
	        
			return teamprofile;
		}
		
		
		@RequestMapping("/save-fav")
		public String savfav(HttpServletRequest req,@RequestParam("makefav") String id,@ModelAttribute("user") User user)
	    {
			
			 User t=repo.findById(user.getId());
	         System.out.println("id is " + id);
	         System.out.println(user.getTeamid());
	         user.setTeamid(id);
	         System.out.println(user.getId());
             System.out.println(user.getName());
             String sb= user.getTeamid().toString();
             StringBuilder sbb= new StringBuilder();
             sbb.append(",").append(id);
             sbb.toString();
             
             user.setTeamid(sbb.toString());
             repo.save(user);
	         
             
             return "home";
	         
	    } 
		
		
		@GetMapping("/home")
		public String home(HttpServletRequest req,@ModelAttribute("user") User user)
	    {
			
				ModelAndView home = new ModelAndView("home");
				ArrayList<HashMap<String, String>> gameDetails = new ArrayList<HashMap<String, String>>();
				String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/team_gamelogs.json?team=" + user.getTeamid();
				String encoding = Base64.getEncoder().encodeToString("59045d1e-7518-4ebe-9ca8-3e0dd4:Hemanshu68@".getBytes());
		        
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Basic "+encoding);
				HttpEntity<String> request = new HttpEntity<String>(headers);

				
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
				String str = response.getBody(); 
				ObjectMapper mapper = new ObjectMapper();
				try {
					JsonNode root = mapper.readTree(str);
					System.out.println(str);
				
			        System.out.println(root.get("teamgamelogs").get("lastUpdatedOn").asText());
			        System.out.println(root.get("teamgamelogs").get("gamelogs").getNodeType());
			        
			
			        
			        JsonNode gamelogs = root.get("teamgamelogs").get("gamelogs");
			        
			        if(gamelogs.isArray()) {
			        	
			        	gamelogs.forEach(gamelog -> {
			        		
			        		JsonNode game = gamelog.get("game");
			 
			        		JsonNode stats = gamelog.get("stats");
			        		JsonNode team = gamelog.get("team");
			        		
			        		
			        		HashMap<String,String> gameDetail = new HashMap<String, String>();
			        		
			        		
			        		
			        		gameDetail.put("team", team.get("Name").asText());
			        		System.out.println(team.get("Name"));
			        		
			        		
			        	
			        		gameDetails.add(gameDetail);
			        		
			        	});
			        }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
				
				home.addObject("gameDetails", gameDetails);
			
				req.setAttribute("gameDetails", gameDetails);
				
				return "home";
			
	    }
	 
		
		
		
		
	
		}



