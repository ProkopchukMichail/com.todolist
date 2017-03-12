import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by andrey on 10.11.15.
 */
@ComponentScan(value = "controller")
@EnableAutoConfiguration
@Import(DBConfig.class)
public class TodoList {
    public static void main(String[] args) {
        SpringApplication.run(TodoList.class);

    }
}