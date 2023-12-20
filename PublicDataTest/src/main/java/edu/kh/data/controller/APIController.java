package edu.kh.data.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {

	private static final String serviceKey = "rr7vDsNjY%2F1sl182O8PxBHK1k4QvaXByIKZ3mzrkDSeYvb8AONTfQ2YI%2B7Cn%2FFCQURQy%2BDFcKClQivvtQzvQBw%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="air.do", produces="application/json; charset=utf-8")
	public String airPollution(@RequestParam("location") String location) throws Exception {
		
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		// 공공데이터 항목명과 동일해야 함!
		url += "?serviceKey=" + serviceKey;
		url += "&returnType=json";
		url += "&sidoName=" + location;
		
		URL requestUrl = new URL(url); // url 객체 생성

		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();

		urlConnection.setRequestMethod("GET"); // 요청메소드 GET 설정

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

		String responseData = ""; // 응답데이터 변수 초기화
		String line; 

		while((line = br.readLine()) != null) {
			responseData += line;
		}
		
		br.close(); // BufferedReader 닫는 것

		urlConnection.disconnect(); // url 연결 끊는 것

		System.out.println("responseData::" + responseData);
				
		return responseData;
		
	}
}
