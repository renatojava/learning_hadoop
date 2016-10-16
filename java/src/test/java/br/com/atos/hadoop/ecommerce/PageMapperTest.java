package br.com.atos.hadoop.ecommerce;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.*;

public class PageMapperTest {

	@Test
	public void mapperPages() throws IOException, InterruptedException {
		Text value = new Text("I, [2016-10-02T20:56:41.639836 #9402]  INFO -- : Started GET \"/admin\" for 10.64.16.67 at 2016-10-02 20:56:41 -0300");
		new MapDriver<LongWritable, Text, Text, IntWritable>().withMapper(new PageMapper())
				.withInput(new LongWritable(0), value).withOutput(new Text("/admin"), new IntWritable(1)).runTest();
	}

}
