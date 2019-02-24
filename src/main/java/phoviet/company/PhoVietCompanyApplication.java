package phoviet.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "phoviet.company" })
public class PhoVietCompanyApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(PhoVietCompanyApplication.class, args);
    }
}
