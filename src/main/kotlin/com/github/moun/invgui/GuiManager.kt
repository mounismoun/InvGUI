package io.github.mounismoun.invgui

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import java.util.UUID

object GuiManager : Listener {
    private val open = mutableMapOf<UUID, GuiSession>()
    fun open(player: Player, session: GuiSession) {
        open[player.uniqueId] = session
    }

    @EventHandler
    fun click(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        val session = open[player.uniqueId] ?: return
        if (event.view.topInventory != session.inventory) return
        event.isCancelled = true

        val slot = event.rawSlot
        if (slot < 0) return

        session.handleClick(slot)
    }

    @EventHandler
    fun close(event: InventoryCloseEvent) {
        val player = event.player as? Player ?: return
        val session = open.remove(player.uniqueId) ?: return
        if (event.view.topInventory != session.inventory) return
        session.handleClose()
    }
}