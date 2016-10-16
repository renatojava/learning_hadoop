package br.com.atos.hadoop.ecommerce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	enum Counter {
		POST
	}

	private final IntWritable ONE = new IntWritable(1);

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		System.out.println("current key: " + key);
		PageParser parser = new PageParser();
		parser.parse(value);
		if (parser.isGet()) {
			System.out.println("linha: " + value);
			System.out.println("pagina extraida: " + parser.getPage());
			context.write(new Text(parser.getPage()), ONE);
		} else {
			System.err.println("Only get is accepted: " + value);
			context.getCounter(Counter.POST).increment(1);
		}
	}
}
