package com.gcrate.puttputt.representations;

import com.gcrate.puttputt.entity.Player;
import java.util.List;

/**
 *
 * @author gcrate
 */
public class PlayerList {
    private List<Player> players;

    public PlayerList() {
    }

    public PlayerList(List<Player> players) {
        this.players = players;
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    
}
