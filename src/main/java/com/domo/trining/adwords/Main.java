package com.domo.trining.adwords;

import java.io.IOException;

import com.domo.trining.adwords.Properties.Report;
import com.domo.trining.adwords.api.AdGroupServiceClient;
import com.domo.trining.adwords.api.CampaignServiceClient;
import com.domo.trining.adwords.api.Client;

public class Main {
	private static Report report = Report.CAMPAIGNS;
	private static Client client;
	
	public static void main(String[] args) throws IOException {
		setClient();
		
		try {
			client.get();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setClient() {
		switch (report) {
		case AD_GROUPS:
			client = new AdGroupServiceClient();
			break;
		case CAMPAIGNS:
			client = new CampaignServiceClient();
			break;
		}
	}
}
