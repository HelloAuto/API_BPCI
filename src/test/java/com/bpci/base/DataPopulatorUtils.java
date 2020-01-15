package com.bpci.base;

import com.bpci.testCases.EpisodeDetailsDataPopulator;
import com.bpci.config.GetEpisodeMemberDetails;
import com.bpci.practice.example.GetEpisodeDetails;
import com.bpci.practice.example.GetEpisodeDetailsBasedOnMember;
import com.bpci.practice.example.GetEpisodeFacilityDetails;
import com.bpci.practice.example.GetInitiatorNames;
import com.bpci.testCases.APIMethodDeclare;


  public class DataPopulatorUtils { public static final String
  EPISODE_DETAILS_LIST = "EpisodeDetailsList"; public static final String
  EPISODE_DETAILS="EpisodeDetails"; public static final String
  EPISODE_MEMBER_DETAILS="MemberDetails"; public static final String
  EPISODE_PHYSICIAN_DETAILS="InitiatorDetails"; public static final String
  EPISODE_FACILITY_DETAILS="FacilityDetails"; public static final String
  EPISODE_DETAILS_BASED_ON_MEMBER="MemberDetailsList"; public static final
  String INITIATOR_NAMES="InitiatorDetails";
  
  public static TestBase getPopulatorClassName(String jsonObjectName) {
 
	  TestBase obj = null;
	  if(jsonObjectName.equalsIgnoreCase(EPISODE_PHYSICIAN_DETAILS)){ obj = new
			  APIMethodDeclare(); }
  
  return obj; } }
 