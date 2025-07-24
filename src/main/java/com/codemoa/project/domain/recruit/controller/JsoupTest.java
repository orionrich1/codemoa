package com.codemoa.project.domain.recruit.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupTest {

	public static void main(String[] args) throws Exception{
		Document doc = Jsoup.connect("https://www.example.com").get();
		System.out.println(doc.title());
	}
}
