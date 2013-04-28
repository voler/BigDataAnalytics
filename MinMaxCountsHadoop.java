import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.bloom.*;

public class MinMaxCountsHadoop {
  public static class MinMaxCountMapper extends Mapper<Object, Text, Text, MinMaxCountsTuple> {
		private Text month = new Text();
		private MinMaxCountsTuple outTuple = new MinMaxCountsTuple();
		public void map(Object key, Text value, Context context)
		throws IOException, InterruptedException {
			String[] line = value.toString().split(",");
			month.set(line[2]);
			int delay = Integer.parseInt(line[20]);
			outTuple.setCount(1);
			outTuple.setMax(delay);
			outTuple.setMin(delay);
			context.write(month, outTuple);
		}
}

	public static class MinMaxCountReducer extends
	Reducer<Text, MinMaxCountsTuple, Text, MinMaxCountsTuple> {
		private MinMaxCountsTuple result = new MinMaxCountsTuple();

		public void reduce(Text key, Iterable<MinMaxCountsTuple> values, Context context) throws 
		IOException, InterruptedException {
			result.setMin(Integer.MAX_VALUE);
			result.setMax(Integer.MIN_VALUE);
			result.setCount(0);
			int sum = 0;
			
			for (MinMaxCountsTuple val : values) {
				if (val.getMin() < result.getMin()) {
					result.setMin(val.getMin());
					}
				if (val.getMax() > result.getMax()) {
					result.setMax(val.getMax());
					}
				sum += val.getCount();
			}

			result.setCount(sum);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = new Job(conf, "MinMaxCount");

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MinMaxCountsTuple.class);

		job.setMapperClass(MinMaxCountMapper.class);
		job.setReducerClass(MinMaxCountReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}

}
