package net.suppressio.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class CoreApplication{
public class CoreApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
	
	/*
	public class springbootpostgresql implements CommandLineRunner {
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		// main method
		public static void main (String[] args) {
			SpringApplication.run (springbootpostgresql.class, args);
		}
		
		@Override
		public void run(String... args) throws Exception {
			String sql = "INSERT INTO ...";
			int rows = jdbcTemplate.update (sql);
			if (rows > 0) 
				System.out.println ("One row inserted.");    
		}
	}/***/
}
