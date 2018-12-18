package hello;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public FlatFileItemReader<Person> csvFileReader(){
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource("input.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstname", "lastname"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
	}
	@Bean
	public FlatFileItemWriter<Person> csvFileWriter() throws IOException{
		int count = 1;
		String csvFile = "/SpringBatch/src/main/resources/"+count+".csv";
		return null;
        //return new FlatFileItemWriterBuilder().name(csvFile).build();
        
	}
	@Bean
	public Step csvFileToCsvStep() throws IOException {
		return stepBuilderFactory.get("csvFileToCsvStep")
				.<Person, Person>chunk(10)
				.reader(csvFileReader())
				.writer(csvFileWriter())
				.build();
	}
	@Bean
	public Job csvFileToCsvJob(JobCompletionNotificationListener listener) throws IOException {
		return jobBuilderFactory.get("csvFileToCsvJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(csvFileToCsvStep())
				.end()
				.build();
	}
   
} 