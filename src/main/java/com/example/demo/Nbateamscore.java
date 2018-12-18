package com.example.demo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;




class awayTeam{
	
	
	@JsonProperty("ID")
	String ID;
	@JsonProperty("City")
	String City;
	@JsonProperty("Name")
	String Name;
	@JsonProperty("Abbreviation")
	String Abbreviation;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAbbreviation() {
		return Abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		Abbreviation = abbreviation;
	}
	
	
	
	
	
	
	
}

	
	class homeTeam{
		
		
		@JsonProperty("ID")
		String ID;
		@JsonProperty("City")
		String City;
		@JsonProperty("Name")
		String Name;
		@JsonProperty("Abbreviation")
		String Abbreviation;
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public String getCity() {
			return City;
		}
		public void setCity(String city) {
			City = city;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getAbbreviation() {
			return Abbreviation;
		}
		public void setAbbreviation(String abbreviation) {
			Abbreviation = abbreviation;
		}
	
		
		
		
		
		
		
		
		
		
		
	}
	
	
	class game{
		@JsonProperty("ID")
		String ID;
	@JsonProperty("scheduleStatus")
		String scheduleStatus;
		@JsonProperty("originalDate")
		String originalDate;
		@JsonProperty("originalTime")
		String originalTime;
		
		
		
	@JsonProperty("delayedOrPostponedReason")
		String delayedOrPostponedReason;
		@JsonProperty("date")
		String date;
		@JsonProperty("time")
		String time;
		@JsonProperty("location")
		String location;
		
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public String getScheduleStatus() {
			return scheduleStatus;
		}
		public void setScheduleStatus(String scheduleStatus) {
			this.scheduleStatus = scheduleStatus;
		}
		public String getOriginalDate() {
			return originalDate;
		}
		public void setOriginalDate(String originalDate) {
			this.originalDate = originalDate;
		}
		public String getOriginalTime() {
			return originalTime;
		}
		public void setOriginalTime(String originalTime) {
			this.originalTime = originalTime;
		}
		public String getDelayedOrPostponedReason() {
			return delayedOrPostponedReason;
		}
		public void setDelayedOrPostponedReason(String delayedOrPostponedReason) {
			this.delayedOrPostponedReason = delayedOrPostponedReason;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		
		
		
		
		
		
	}
	
	
class gameScore{
		
String isUnplayed;
String isInProgress;
String isCompleted;
long awayScore;
long homeScore;
@JsonProperty("game")
game game;
public String getIsUnplayed() {
	return isUnplayed;
}
public void setIsUnplayed(String isUnplayed) {
	this.isUnplayed = isUnplayed;
}
public String getIsInProgress() {
	return isInProgress;
}
public void setIsInProgress(String isInProgress) {
	this.isInProgress = isInProgress;
}
public String getIsCompleted() {
	return isCompleted;
}
public void setIsCompleted(String isCompleted) {
	this.isCompleted = isCompleted;
}
public long getAwayScore() {
	return awayScore;
}
public void setAwayScore(long awayScore) {
	this.awayScore = awayScore;
}
public long getHomeScore() {
	return homeScore;
}
public void setHomeScore(long homeScore) {
	this.homeScore = homeScore;
}
public game getGame() {
	return game;
}
public void setGame(game game) {
	this.game = game;
}
   
    
	






		








	
	
}
	
	
	
	
	
	
	

class scoreboard{
	String lastUpdatedOn;
	@JsonProperty("gameScore")
	ArrayList<gameScore> gameScoress;
	
	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	
	public ArrayList<gameScore> getGameScoress() {
		return gameScoress;
	}
	public void setGameScoress(ArrayList<gameScore> gameScoress) {
		this.gameScoress = gameScoress;
	}
	
	
	
	
	
	
	
	
	
	
}















@JsonIgnoreProperties(ignoreUnknown = true)
public class Nbateamscore {
	scoreboard scoreboard;

	public scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}

	
	
	
}