package com.gcrate.puttputt;

import com.gcrate.puttputt.entity.DataOnly;
import com.gcrate.puttputt.entity.Player;
import com.gcrate.puttputt.representations.PlayerList;
import com.gcrate.puttputt.representations.Registration;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gcrate
 */
@RestController
public class RestWebController {
    @Autowired
    SimpMessagingTemplate smt;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    //Register
    @RequestMapping(value="/register", method=RequestMethod.POST, consumes= {"application/json"})
    public void register(@RequestBody Registration registration) {
        Application.activePlayers.add(new Player(Application.nextId++, registration.getImage())); 
        
    }
    
    
    //send angle
    @RequestMapping(value="/angle", method=RequestMethod.GET)
    public String sendAngle(@RequestParam("angle") int angle, @RequestParam("speed") double speed) {
        if(!Application.activePlayers.isEmpty()) {
            Player player = Application.activePlayers.get(Application.activePlayers.size() - 1);
            if(player.getSpeed() == -1) {
                System.out.println("saving player w/ angle");
                player.setSpeed(speed);
                player.setAngle(angle);
                jdbcTemplate.update("INSERT INTO players (id, image, speed, angle, score) VALUES (?, ?, ?, ?, ?)",
                        player.getId(), player.getImage(), player.getSpeed(), player.getAngle(), player.getScore());
                smt.convertAndSend("/topic/playerList", new PlayerList(Application.activePlayers));
                return "angle set";
            }
            return "angle already set";
        }
        return "nothing to do";
    }
    
    //send score
    @RequestMapping(value="/score", method=RequestMethod.GET)
    public String addScore(@RequestParam("id") int id, @RequestParam("score") int score) {
        jdbcTemplate.update("UPDATE players SET score = ? WHERE id = ?", score, id);
        for (int i = 0; i < Application.activePlayers.size(); i++) {
            if (Application.activePlayers.get(i).getId() == id) {
                Application.activePlayers.remove(i);
                break;
            }
        }
        return "Score saved";
    }
  
    //get active players
    @RequestMapping(value="/players", method=RequestMethod.GET)
    public void getPlayers() {
        smt.convertAndSend("/topic/playerList", new PlayerList(Application.activePlayers));
    }
    
    //get all data
    @RequestMapping(value="/data", method=RequestMethod.GET)
    public List<DataOnly> getData() {
        List<DataOnly> data = jdbcTemplate.query("Select speed, angle, score from players", (ResultSet result, int rowNum) -> {
            DataOnly datum = new DataOnly(result.getDouble("speed"), result.getInt("angle"), result.getInt("score"));
            return datum;
        });
        return data;     
    }

}
