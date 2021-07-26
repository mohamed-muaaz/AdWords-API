package com.domo.trining.adwords.api;

import java.net.HttpURLConnection;
import java.net.URL;

import com.domo.trining.adwords.Properties;

public class CampaignServiceClient extends Client{
	private static final String API_URL = "/CampaignService";
	
	public void get() throws Throwable {
		URL url = new URL(Properties.BASE_URL + API_URL);
		connection = (HttpURLConnection) url.openConnection();
		preRequest();
		
		bodyContentFilePath = Properties.BODY_CONTENT_DIR + "/CampaignService.xml";
		constructBody(bodyContentFilePath);
		
		String response = getResponse();
		printXml(response);
		
	}

}
