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

package twittercounter4j;

import java.util.Date;
import java.util.TreeMap;

/**
 * 
 * @author Venil Noronha
 *
 */
public interface User {

	String getVersion(); // version
	Long getTwitterId(); // twitter_id
	String getUsername(); // username
	String getUrl(); // url
	String getAvatar(); // avatar
	String getName(); // name
	String getDescription(); // description
	String getTimeZone(); // timezone
	String getLocation(); // location
	Long getCurrentFollowers(); // followers_current
	Date getUpdatedDate(); // date_updated
	Long getFollowDays(); // follow_days
	Long getFollowersAtStart(); // started_followers
	Long getFollowerGrowthSinceStart(); // growth_since
	Long getAverageFollowerGrowth(); // average_growth
	Long getFollowersTomorrow(); // tomorrow
	Long getFollowersNextMonth(); // next_month
	Long getFollowersYesterday(); // followers_yesterday
	Long getRank(); // rank
	Long getFollowersTwoWeeksAgo(); // followers_2w_ago
	Long getFollowerGrowthSinceTwoWeeks(); // growth_since_2w
	Long getAverageFollowerGrowthSinceTwoWeeks(); // average_growth_2w
	Long getFollowersTwoWeeksLater(); // tomorrow_2w
	Long getFollowersOneMonthTwoWeeksLater(); // next_month_2w
	TreeMap<Date, Long> getFollowersPerDate(); // followersperdate
	Date getLastUpdate(); // last_update
	
}
