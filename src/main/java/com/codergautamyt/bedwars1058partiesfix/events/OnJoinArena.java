package com.codergautamyt.bedwars1058partiesfix.events;

import com.alessiodp.parties.api.Parties;
import com.alessiodp.parties.api.interfaces.PartiesAPI;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJoinArena implements Listener {
    @EventHandler
    //called whena player joins the arena
    //we check if player is party leader
    //if they are, we warp members of party into their game
    //if there in party but not leader
    //we cancel event and prevent join
    public void onJoinArena(PlayerJoinArenaEvent e) {
        //player who tried to join
        Player player = e.getPlayer();
        PartiesAPI api = Parties.getApi();
        PartyPlayer partyPlayer = api.getPartyPlayer(player.getUniqueId());

        //check if player is in party
        if(partyPlayer.isInParty()) {
            //check if player is the leader
            if(partyPlayer.getRank() == 20) {
                //transfer all players into leaders game
            } else {
                //not leader, cancel event
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED+"Only the party leader can warp you into bedwars");
            }
        }
    }
}
