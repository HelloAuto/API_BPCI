package com.bpci.practice.example;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC007_Get_EpisodeSummaryDetails extends TestBase{
	@BeforeClass
	void getEpisodeSummaryDetails() throws InterruptedException {
		
		logger.info("********started TC007_Get_EpisodeSummaryDetails*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		//response = httpRequest.request(Method.GET,"/getEpisodeSummaryDetails?episodeid="+episodeid);
		Thread.sleep(3000);
	}

	@Test
	void checkStatusCode() {
		
		logger.info("********Checking Status Code*********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkEpisodeSummaryDetails() {
		try {
			logger.info("********Checking Episode Summary Details*********"); 
			JsonPath jsonpath = response.jsonPath();
			
			List<Map<String, Object>> episodeDetail = jsonpath.getList("episodeDetails");
				Assert.assertEquals(episodeDetail.get(0).get("startDate"), "12/1/2018 12:00:00 AM");
				logger.info("Start Date for Episode is :"+episodeDetail.get(0).get("startDate"));
				logger.info("End Date for Episode is :"+episodeDetail.get(0).get("endDate")); 
				Assert.assertEquals(episodeDetail.get(0).get("endDate"), "4/30/2019 12:00:00 AM");
				logger.info("Description for Episode is : "+episodeDetail.get(0).get("description"));
				Assert.assertEquals(episodeDetail.get(0).get("description"), "despriction2");
				logger.info("InPatient for Episode is :"+episodeDetail.get(0).get("inPatient"));
				Assert.assertEquals(episodeDetail.get(0).get("inPatient"), "inpatient2");
				logger.info("OutPatient for Episode is :"+episodeDetail.get(0).get("outPatient"));
				Assert.assertEquals(episodeDetail.get(0).get("outPatient"), "outpatient");
				logger.info("Status for Episode is :"+episodeDetail.get(0).get("status"));
				Assert.assertEquals(episodeDetail.get(0).get("status"), "INACTIVE");
				logger.info("Source for Episode is :"+episodeDetail.get(0).get("source"));
				Assert.assertEquals(episodeDetail.get(0).get("source"), "source1");
				
			List<Map<String, Object>> memberDetail = jsonpath.getList("memberDetais");
				logger.info("mbi for Members is : "+memberDetail.get(0).get("mbi"));
				Assert.assertEquals(memberDetail.get(0).get("mbi"), "102");
				logger.info("hcin for Memeber is :"+memberDetail.get(0).get("hcin"));
				Assert.assertEquals(memberDetail.get(0).get("hcin"), "1012");
				logger.info("First Name for Memeber is :"+memberDetail.get(0).get("firstName"));
				Assert.assertEquals(memberDetail.get(0).get("firstName"), "firstname2");
				logger.info("Middle Name for Memeber is :"+memberDetail.get(0).get("middleName"));
				Assert.assertEquals(memberDetail.get(0).get("middleName"), "middlename2");
				logger.info("Last Name for Memeber is :"+memberDetail.get(0).get("lastName"));
				Assert.assertEquals(memberDetail.get(0).get("lastName"), "lastname2");
				logger.info("Full Name for Memeber is :"+memberDetail.get(0).get("fullName"));
				Assert.assertEquals(memberDetail.get(0).get("fullName"), "fullname2");
				logger.info("Source for Memeber is :"+memberDetail.get(0).get("source"));
				Assert.assertEquals(memberDetail.get(0).get("source"), null);
			
			
			List<Map<String, Object>> ownerDetail = jsonpath.getList("ownerDetails");
				logger.info("Owner ID for Owner is :"+ownerDetail.get(0).get("ownerId"));
				Assert.assertEquals(ownerDetail.get(0).get("ownerId"), 2);
				logger.info("Owner Nme for Owner is :"+ownerDetail.get(0).get("ownerName"));
				Assert.assertEquals(ownerDetail.get(0).get("ownerName"), "owner2");
				logger.info("source for Owner is :"+ownerDetail.get(0).get("source"));
				Assert.assertEquals(ownerDetail.get(0).get("source"), null);
			
			List<Map<String, Object>> providerDetail = jsonpath.getList("providerDetails");
				logger.info("Admitting Physician Name  for Provider is :"+providerDetail.get(0).get("AdmittingPhysicianName"));
				Assert.assertEquals(providerDetail.get(0).get("AdmittingPhysicianName"), "AdmitingPhy2");
				logger.info("Attending Physician Name  for Provider is :"+providerDetail.get(0).get("AttendingPhysicianName"));
				Assert.assertEquals(providerDetail.get(0).get("AttendingPhysicianName"), "attendingphy2");
				logger.info("Consulting Physician Name  for Provider is :"+providerDetail.get(0).get("ConsultingPhysicianName"));
				Assert.assertEquals(providerDetail.get(0).get("ConsultingPhysicianName"), "ConsultingPhy2");
				logger.info("Referring Physician Name  for Provider is :"+providerDetail.get(0).get("ReferringPhysicianName"));
				Assert.assertEquals(providerDetail.get(0).get("ReferringPhysicianName"), "referPhy2");
			
			List<Map<String, Object>> facilityDetail = jsonpath.getList("facilityDetails");
				logger.info("Facility ID is :"+facilityDetail.get(0).get("facilityId"));
				Assert.assertEquals(facilityDetail.get(0).get("facilityId"), 0);
				logger.info("Facility Name is :"+facilityDetail.get(0).get("faciltyName"));
				Assert.assertEquals(facilityDetail.get(0).get("faciltyName"), "name2");
				logger.info("Source for Facility is :"+facilityDetail.get(0).get("source"));
				Assert.assertEquals(facilityDetail.get(0).get("source"), null);
			
			List<Map<String, Object>> diagnosisDetail = jsonpath.getList("DiagnosisDetails");
				logger.info("Diagnosis Code is : "+diagnosisDetail.get(0).get("DiagnosisCode"));
				Assert.assertEquals(diagnosisDetail.get(0).get("DiagnosisCode"), "DC1");
				logger.info("Source for Diagnosis is : "+diagnosisDetail.get(0).get("Source"));
				Assert.assertEquals(diagnosisDetail.get(0).get("Source"), "source1");
				logger.info("Admit Date for Diagnosis is : "+diagnosisDetail.get(0).get("AdmitDate"));
				Assert.assertEquals(diagnosisDetail.get(0).get("AdmitDate"), "2/10/2019 12:00:00 AM");
				logger.info("Discharge Date for Diagnosis is : "+diagnosisDetail.get(0).get("DischargeDate"));
				Assert.assertEquals(diagnosisDetail.get(0).get("DischargeDate"), "2/23/2019 12:00:00 AM");
				logger.info("Discharge Dispostion for Diagnosis is : "+diagnosisDetail.get(0).get("DischargeDispostion"));
				Assert.assertEquals(diagnosisDetail.get(0).get("DischargeDispostion"), "DD2");
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			logger.warn(e);
			
		}
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********Checking Status Line*********");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkResponseTime() {
		logger.info("********Checking Response Time*********");
		long responseTime = response.getTime();
		logger.info("Response Time is ==> "+responseTime);
		if(responseTime >2000) 
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime < 2000);
	}
	
	@Test
	void checkContentType() {
		logger.info("********Checking Content Type*********");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	void checkContentLength() {
		logger.info("********Checking Content Length*********");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==> "+contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
	}
	
	@Test
	void checkServerType() { 
		logger.info("********Checking Server Type*********");
		String serverType = response.header("Server");
		logger.info("Server Type is ==> "+serverType);
		Assert.assertEquals(serverType, "Microsoft-IIS/8.5");
	}
	
	@AfterClass 
	void tearDown() {
		logger.info("********Finished TC007_Get_EpisodeSummarydetails*********");
	}


}
