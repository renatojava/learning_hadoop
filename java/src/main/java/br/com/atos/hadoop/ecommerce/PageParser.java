package br.com.atos.hadoop.ecommerce;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

public class PageParser {

	private String httpMethod;
	private String page;

	private Pattern pattern = Pattern.compile("(GET|POST)(.*\"(.*)\")");

	public static void main(String[] args) {
		String line = "I, [2016-10-02T20:56:41.639836 #9402]  INFO -- : Started GET \"/admin\" for 10.64.16.67 at 2016-10-02 20:56:41 -0300";
		Pattern pattern = Pattern.compile("(GET|POST)(.*\"(.*)\")");

		// Now create matcher object.
		Matcher m = pattern.matcher(line);

		if (m.find()) {
			System.out.println(m.groupCount());
			int count = 0;
			while (count++ < m.groupCount()) {
				System.out.println(String.format("Found value %s : %s", count, m.group(count)));
			}
		} else {
			System.out.println("NO MATCH");
		}
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	} 

	public void parse(String record) {
		Matcher m = pattern.matcher(record);
		if (m.find() && m.groupCount() == 3) {
			httpMethod = m.group(1);
			page = m.group(3);
		}
	}

	public void parse(Text record) {
		parse(record.toString());
	}

	public boolean isGet() {
		return "GET".equals(httpMethod);
	}

	public boolean isPost() {
		return "POST".equals(httpMethod);
	}

}