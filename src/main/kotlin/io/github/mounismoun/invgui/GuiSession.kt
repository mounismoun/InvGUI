package io.github.mounismoun.invgui

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin

class GuiSession internal constructor(
    val plugin: JavaPlugin,
    val template: GuiTemplate,
    val player: Player,
    val inventory: Inventory
) {

    var page: Int = 0
        private set

    fun nextPage() = goTo(page+1)
    fun prevPage() = goTo(page-1)

    fun goTo(newPage: Int) {
        page = newPage.coerceIn(0, template.layouts.lastIndex)
        inventory.clear()
        template.layouts[page].render(inventory, player)
    }
    internal fun handleClick(slot: Int) {
        template.layouts[page].clickHandlers[slot]?.invoke(
            GuiClickContext(player, this, slot)
        )
    }
    internal fun handleClose() {
        template.closeHandler?.invoke(
            GuiCloseContext(player, this)
        )
    }
}