package io.github.mounismoun.invgui

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class ItemBuilder(private val material: Material) {
    private var name: String? = null
    private val lore = mutableListOf<String>()

    fun name(text: String){
        name = text
    }

    fun lore(vararg lines: String){
        lore.addAll(lines)
    }

    fun build(): ItemStack {
        val item = ItemStack(material)
        val meta: ItemMeta = item.itemMeta!!

        name?.let { meta.setDisplayName(it) }

        if (lore.isNotEmpty()){
            meta.lore = lore
        }
        item.itemMeta = meta
        return item
    }
}

fun item(material: Material, block: ItemBuilder.() -> Unit): ItemStack {
    val builder = ItemBuilder(material)
    builder.block()
    return builder.build()
}

fun pane(): ItemStack = item(Material.GRAY_STAINED_GLASS_PANE) {
    name(" ")
}