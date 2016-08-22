package com.gcrate.puttputt;

import com.gcrate.puttputt.entity.Player;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public static List<Player> activePlayers = new ArrayList<>();
    public static int nextId = 1;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //Create DB table
        jdbcTemplate.execute("DROP TABLE players IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE players (" +
                "id INT, image TEXT, speed DOUBLE, angle INT, score INT)");
        
        
    }
    
    
}
