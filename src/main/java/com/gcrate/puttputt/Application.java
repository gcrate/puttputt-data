package com.gcrate.puttputt;

import com.gcrate.puttputt.entity.Player;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.SocketUtils;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static List<Player> activePlayers = new ArrayList<>();
    public static int nextId = 1;

    @Bean
    public Integer port() {
        return 8080;
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(port());
        return connector;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //Create DB table
        jdbcTemplate.execute("DROP TABLE players IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE players ("
                + "id INT, image TEXT, speed DOUBLE, angle INT, score INT)");

    }

}
