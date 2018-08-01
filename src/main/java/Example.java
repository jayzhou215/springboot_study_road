import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
 
@RestController
@EnableAutoConfiguration
public class Example {
 
 	private int count;
    @RequestMapping("/")
    String home() {
    	count++;
        return "Hello Jay!" + count; 
    }
 
    @RequestMapping("/hi")
    String hi() {
        return "hi Jay!"; 
    }
 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }


 
}
