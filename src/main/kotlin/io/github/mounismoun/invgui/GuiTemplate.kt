package io.github.mounismoun.invgui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class GuiTemplate internal constructor(
    private val plugin: JavaPlugin,
    val title: String,
    val rows: Int,
    internal val layouts: List<GuiLayout>,
    internal val closeHandler: (GuiCloseContext.() -> Unit)?
){
    fun open(player: Player): GuiSession{
        val inv = Bukkit.createInventory(player, rows * 9, title)
        layouts[0].render(inv,  player)
        val session = GuiSession(plugin, this, player, inv)
        GuiManager.open(player, session)
        player.openInventory(inv)
        return session
    }
}