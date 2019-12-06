package com.example.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;

@Service
public class TestService {

	public static boolean MONITOR = false;
	private List<String> statusList = new ArrayList<>();
	
	@Autowired
	private MeterRegistry meterRegistry;

    public TestService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

	public void StartMonitoring(int delay) {
		TestService.MONITOR = true;
		while (TestService.MONITOR == true) {
			try {
				String url = "https://developer.paysafe.com/en/turnkey/accounts/api/#/reference/0/verify-that-the-service-is-accessible/verify-that-the-service-is-accessible";
				int statusCode = 200;

				URL siteURL = new URL(url);

				HttpURLConnection conn = (HttpURLConnection) siteURL.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(3000);
				conn.connect();

				statusCode = conn.getResponseCode();

				if (statusCode == 200) {
					System.out.println("application is UP");
				} else {
					System.out.println("application is DOWN");
				}
				increaseCount(statusCode);
				TimeUnit.SECONDS.sleep(delay);
			} catch (Exception e) {
				System.out.println("application is DOWN");
			}
		}
		
	}

	public List<ResponseObject> StopMonitoring() {
		TestService.MONITOR = false;
		List<ResponseObject> ResponseObjectList = new ArrayList<>();
		for(String s :statusList) {
			ResponseObjectList.add(new ResponseObject(s,meterRegistry.counter(s).count()));
		}
		return ResponseObjectList;
		
	}
	
	public void increaseCount(final int status) {
	    String counterName = "counter.status." + status;
	    meterRegistry.counter(counterName).increment(1);
	    System.out.println(meterRegistry.counter(counterName).count());
	    if (!statusList.contains(counterName)) {
	        statusList.add(counterName);
	    }
	}
}
