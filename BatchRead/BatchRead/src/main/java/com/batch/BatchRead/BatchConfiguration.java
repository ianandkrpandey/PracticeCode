package com.batch.BatchRead;

import java.io.FileWriter;
import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration{
	int count=1;
	
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

	    ItemWriter<Person> CsvItemWriter() {
	        FlatFileItemWriter<Person> csvFileWriter = new FlatFileItemWriter<>();
	 
	        String exportFileHeader = "FIRSTNME;LASTNAME";
	        StringHeaderWriter headerWriter = new StringHeaderWriter(exportFileHeader);
	        csvFileWriter.setHeaderCallback(headerWriter);
	 
	        String exportFilePath = "/SpringBatch/src/main/resources/outputcsv/output.csv";
	        csvFileWriter.setResource(new FileSystemResource(exportFilePath));
	 
	        LineAggregator<Person> lineAggregator = createStudentLineAggregator();
	        csvFileWriter.setLineAggregator(lineAggregator);
	 
	        return csvFileWriter;
	    }
	@Bean
	public Step csvFileToCsvStep() throws IOException {
		return stepBuilderFactory.get("csvFileToCsvStep")
				.<Person, Person>chunk(10)
				.reader(csvFileReader())
				.writer(CsvItemWriter())
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
	private LineAggregator<Person> createStudentLineAggregator() {
        DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";");
 
        FieldExtractor<Person> fieldExtractor = createPersonFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
 
        return lineAggregator;
    }
	 private FieldExtractor<Person> createPersonFieldExtractor() {
	        BeanWrapperFieldExtractor<Person> extractor = new BeanWrapperFieldExtractor<>();
	        extractor.setNames(new String[] {"firstname", "lastname",});
	        return extractor;
	    }
	 

   
} 