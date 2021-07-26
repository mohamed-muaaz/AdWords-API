package com.domo.trining.adwords;

import java.io.File;

public class Properties {
	public static final String BASE_URL = "https://adwords.google.com/api/adwords/cm/v201809";
	public static final String ACCESS_TOKEN = "";
	public static final String DEVELOPER_TOKEN = "UY4cVHSjCO7A3tihUCXOkQ";
	public static final String CLIENT_CUSTOMER_ID = "774-035-4481";
	public static final String USER_AGENT = "adWordsTest.com:ReportDownloader:V1.0";
	public static final String BODY_CONTENT_DIR = new File("src/main/java/request/body").getAbsolutePath();
	public enum Report{
		AD_GROUPS,
		CAMPAIGNS
	}
}
