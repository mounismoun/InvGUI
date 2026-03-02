package io.github.mounismoun.invgui

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class GuiLayout(val rows: Int) {
    val items = mutableMapOf<Int, ItemStack>()
    val clickHandlers = mutableMapOf<Int, GuiClickContext.() -> Unit>()
    var closeHandler: (GuiCloseContext.() -> Unit)? = null

    fun render(inv: Inventory, player: Player) {
        val size = rows * 9

        for (i in 0 until size) {
            items[i]?.let {
                inv.setItem(i, it)
            }
        }
    }
}