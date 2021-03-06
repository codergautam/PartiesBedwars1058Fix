package com.codergautamyt.bedwars1058partiesfix.events;

import com.alessiodp.parties.api.Parties;
import com.alessiodp.parties.api.interfaces.PartiesAPI;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import org.bukkit.Bukkit;
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
        IArena arena = e.getArena();
        //Bukkit.getServer().getLogger().info(player.getName()+" joined arena");
        //check if player is in party
        if(partyPlayer.isInParty()) {
            //Bukkit.getServer().getLogger().info(player.getName()+" in a party");
            Party party = api.getParty(partyPlayer.getPartyId());
            //check if player is the leader
            if(partyPlayer.getRank() == 20) {
                //Bukkit.getServer().getLogger().info(player.getName()+" is leader");
                //transfer all players into leaders game
                //if spectator, add them as a spectator
                if(e.isSpectator()) {
                    arena.addSpectator(player, false, arena.getSpectatorLocation());
                    //Bukkit.getServer().getLogger().info(player.getName()+" joined as spectator");
                    //loop through players in party that are not the leader....

                    for (PartyPlayer pp : party.getOnlineMembers()) {
                        if (!partyPlayer.getPlayerUUID().equals(pp.getPlayerUUID())) {
                            //add spectators
                            //Bukkit.getServer().getLogger().info(pp.getName()+" joined arena as spec");
                            arena.addSpectator(Bukkit.getPlayer(pp.getPlayerUUID()),false, arena.getSpectatorLocation());
                        }
                    }
                    e.setCancelled(true);
                } else {
                    arena.addPlayer(player, true);
                    //Bukkit.getServer().getLogger().info(player.getName()+" joined as player");
                    //if not spectator, add as player
                    //loop through players in party that are not the leader....
                    for (PartyPlayer pp : party.getOnlineMembers()) {
                        if (!partyPlayer.getPlayerUUID().equals(pp.getPlayerUUID())) {
                            //add players
                            arena.addPlayer(Bukkit.getPlayer(pp.getPlayerUUID()), true);
                            //Bukkit.getServer().getLogger().info(player.getName()+" joined as player");
                        }
                    }
                    e.setCancelled(true);
                }
            } else {
                //we check if the player is going to the leaders game
                //if they are, then we can allow them
                //if there going to different game, we cancel the event and stop them
                for (Player playr : arena.getPlayers()) {
                    if(playr.getUniqueId().equals(party.getLeader())) {
                        //they ARE going to the leaders game
                        //Bukkit.getServer().getLogger().info(player.getName()+" going to leaders game");
                        return;
                    }
                }
                //not going to leaders game, cancel event
                //Bukkit.getServer().getLogger().info(player.getName()+" not going to leaders arena");
                player.sendMessage(ChatColor.RED+"Only the party leader can warp you into bedwars");
                e.setCancelled(true);
            }

        }
    }
}
