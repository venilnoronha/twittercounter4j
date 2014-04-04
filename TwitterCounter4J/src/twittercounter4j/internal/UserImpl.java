/*
 * Copyright 2014 Venil Noronha
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twittercounter4j.internal;

import java.text.ParseException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.TreeMap;

import twittercounter4j.TwitterCounterException;
import twittercounter4j.User;
import twittercounter4j.util.ParseUtil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author Venil Noronha
 *
 */
class UserImpl implements User {
	
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    
	private String version;
    private Long twitterId;
    private String username;
    private String url;
    private String avatar;
    private String name;
    private String description;
    private String timeZone;
    private String location;
    private Long currentFollowers;
    private Date updatedDate;
    private Long followDays;
    private Long followersAtStart;
    private Long followerGrowthSinceStart;
    private Long averageFollowerGrowth;
    private Long followersTomorrow;
    private Long followersNextMonth;
    private Long followersYesterday;
    private Long rank;
    private Long followersTwoWeeksAgo;
    private Long followerGrowthSinceTwoWeeks;
    private Long averageFollowerGrowthSinceTwoWeeks;
    private Long followersTwoWeeksLater;
    private Long followersOneMonthTwoWeeksLater;
	private TreeMap<Date, Long> followersPerDate = new TreeMap<Date, Long>();
	private Date lastUpdate;

    public UserImpl(JsonObject jsonObject) throws TwitterCounterException {
    	version = ParseUtil.getString("version", jsonObject);
    	twitterId = ParseUtil.getLong("twitter_id", jsonObject);
    	username = ParseUtil.getString("username", jsonObject);
        url = ParseUtil.getString("url", jsonObject);;
        avatar = ParseUtil.getString("avatar", jsonObject);;
        name = ParseUtil.getString("name", jsonObject);;
        description = ParseUtil.getString("description", jsonObject);;
        timeZone = ParseUtil.getString("timezone", jsonObject);
        location = ParseUtil.getString("location", jsonObject);;
        currentFollowers = ParseUtil.getLong("followers_current", jsonObject);
        try {
			updatedDate = ParseUtil.getDate("date_updated", jsonObject, DATE_FORMAT);
		} catch (ParseException e) {
			throw new TwitterCounterException(e);
		}
        followDays = ParseUtil.getLong("follow_days", jsonObject);
        followersAtStart = ParseUtil.getLong("started_followers", jsonObject);
        followerGrowthSinceStart = ParseUtil.getLong("growth_since", jsonObject);
        averageFollowerGrowth = ParseUtil.getLong("average_growth", jsonObject);
        followersTomorrow = ParseUtil.getLong("tomorrow", jsonObject);
        followersNextMonth = ParseUtil.getLong("next_month", jsonObject);
        followersYesterday = ParseUtil.getLong("followers_yesterday", jsonObject);
        rank = ParseUtil.getLong("rank", jsonObject);
        followersTwoWeeksAgo = ParseUtil.getLong("followers_2w_ago", jsonObject);
        followerGrowthSinceTwoWeeks = ParseUtil.getLong("growth_since_2w", jsonObject);
        averageFollowerGrowthSinceTwoWeeks = ParseUtil.getLong("average_growth_2w", jsonObject);
        followersTwoWeeksLater = ParseUtil.getLong("tomorrow_2w", jsonObject);
        followersOneMonthTwoWeeksLater = ParseUtil.getLong("next_month_2w", jsonObject);
        Long lastUpdateInMs = ParseUtil.getLong("last_update", jsonObject);
        if (lastUpdateInMs != null) {
        	lastUpdate = new Date(lastUpdateInMs * 1000);
        }
    	if (jsonObject.has("followersperdate")) {
    		JsonObject dateFollowersMap = jsonObject.get("followersperdate").getAsJsonObject();
    		for (Entry<String, JsonElement> entry : dateFollowersMap.entrySet()) {
    			String dateKey = entry.getKey();
    			try {
    				Date date = ParseUtil.parseDate(dateKey.replaceAll("date", ""), DATE_FORMAT);
    				Long followers = entry.getValue().getAsLong();
    				followersPerDate.put(date, followers);
    			} catch (ParseException e) {
    				throw new TwitterCounterException(e);
    			}
    		}
    	}
    }

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public Long getTwitterId() {
		return twitterId;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getAvatar() {
		return avatar;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getTimeZone() {
		return timeZone;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public Long getCurrentFollowers() {
		return currentFollowers;
	}

	@Override
	public Date getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public Long getFollowDays() {
		return followDays;
	}

	@Override
	public Long getFollowersAtStart() {
		return followersAtStart;
	}

	@Override
	public Long getFollowerGrowthSinceStart() {
		return followerGrowthSinceStart;
	}

	@Override
	public Long getAverageFollowerGrowth() {
		return averageFollowerGrowth;
	}

	@Override
	public Long getFollowersTomorrow() {
		return followersTomorrow;
	}

	@Override
	public Long getFollowersNextMonth() {
		return followersNextMonth;
	}

	@Override
	public Long getFollowersYesterday() {
		return followersYesterday;
	}

	@Override
	public Long getRank() {
		return rank;
	}

	@Override
	public Long getFollowersTwoWeeksAgo() {
		return followersTwoWeeksAgo;
	}

	@Override
	public Long getFollowerGrowthSinceTwoWeeks() {
		return followerGrowthSinceTwoWeeks;
	}

	@Override
	public Long getAverageFollowerGrowthSinceTwoWeeks() {
		return averageFollowerGrowthSinceTwoWeeks;
	}

	@Override
	public Long getFollowersTwoWeeksLater() {
		return followersTwoWeeksLater;
	}

	@Override
	public Long getFollowersOneMonthTwoWeeksLater() {
		return followersOneMonthTwoWeeksLater;
	}

	@Override
	public TreeMap<Date, Long> getFollowersPerDate() {
		return followersPerDate;
	}

	@Override
	public Date getLastUpdate() {
		return lastUpdate;
	}

	@Override
	public String toString() {
		return "UserImpl [version=" + version + ", twitterId=" + twitterId
				+ ", username=" + username + ", url=" + url + ", avatar="
				+ avatar + ", name=" + name + ", description=" + description
				+ ", timeZone=" + timeZone + ", location=" + location
				+ ", currentFollowers=" + currentFollowers + ", updatedDate="
				+ updatedDate + ", followDays=" + followDays
				+ ", followersAtStart=" + followersAtStart
				+ ", followerGrowthSinceStart=" + followerGrowthSinceStart
				+ ", averageFollowerGrowth=" + averageFollowerGrowth
				+ ", followersTomorrow=" + followersTomorrow
				+ ", followersNextMonth=" + followersNextMonth
				+ ", followersYesterday=" + followersYesterday + ", rank="
				+ rank + ", followersTwoWeeksAgo=" + followersTwoWeeksAgo
				+ ", followerGrowthSinceTwoWeeks="
				+ followerGrowthSinceTwoWeeks
				+ ", averageFollowerGrowthSinceTwoWeeks="
				+ averageFollowerGrowthSinceTwoWeeks
				+ ", followersTwoWeeksLater=" + followersTwoWeeksLater
				+ ", followersOneMonthTwoWeeksLater="
				+ followersOneMonthTwoWeeksLater + ", followersPerDate="
				+ followersPerDate + ", lastUpdate=" + lastUpdate + "]";
	}

}
