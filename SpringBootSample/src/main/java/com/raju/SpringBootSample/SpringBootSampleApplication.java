package com.raju.SpringBootSample;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
		getModelNumbers();
	}

	public static void getModelNumbers() {
		String url = "https://www.theiphonewiki.com/wiki/Models";
		try {
			Document source = Jsoup.connect(url).get();
			Element table = source.select("table").get(10);
			Elements rows = table.select("tr");
			for(Element row : rows) {
				Elements data1 = row.select("td");
				//if(row.select("td").size()>5) {
				//Element modelNumber = row.select("td").get(1);
				//Element InternalModelNumber = row.select("td").get(5);
				System.out.println(data1.text());
				//System.out.println(modelNumber.text() +" : "+InternalModelNumber.text());
				//}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mw-content-text");
	}
}

