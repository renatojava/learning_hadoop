package br.com.atos.hadoop.ecommerce;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.*;

public class PageReducerTest {

	@Test
	public void countPages() throws IOException, InterruptedException {
		new ReduceDriver<Text, IntWritable, Text, IntWritable>().withReducer(new PageReducer())
				.withInput(new Text("/admin"), Arrays.asList(new IntWritable(1), new IntWritable(1)))
				.withOutput(new Text("/admin"), new IntWritable(2)).runTest();
	}
}
