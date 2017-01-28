package com.practice.hadoop;

/**
 * Created by sunilchandani on 16/08/16.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * A reducer class that just emits the sum of the input values.
 */

public class WordCountReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable > {
    @Override
    public void reduce(Text text, Iterator<IntWritable> iterator, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        int sum = 0;
        while (iterator.hasNext()) {
            sum += iterator.next().get();
        }
        outputCollector.collect(text, new IntWritable(sum));
    }
}