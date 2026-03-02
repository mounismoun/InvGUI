package io.github.mounismoun.invgui

import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class GuiBuilder(
    private val plugin: JavaPlugin,
    private val title: String,
    private val rows: Int
) {
    private val pages = mutableListOf<GuiLayout>()
    private var current: GuiLayout = GuiLayout(rows).also { pages += it }
    private var closeHandler: ((GuiCloseContext) -> Unit)? = null
    private val layout = GuiLayout(rows)

    fun page(block: GuiPageScope.() -> Unit) {
        current = GuiLayout(rows).also { pages += it }
        GuiPageScope(rows, current).block()
    }

    fun fill(item: ItemStack){
        for(i in 0 until rows * 9){
            current.items[i] = item
        }
    }

    fun slot(x: Int, y: Int, item: ItemStack){
        val index = index(x, y)
        layout.items[index] = item
    }

    fun button(x: Int, y: Int, item: ItemStack, block: GuiButtonBuilder.()->Unit){
        val index = index(x, y)
        current.items[index] = item
        val builder = GuiButtonBuilder()
        builder.block()
        current.clickHandlers[index] = builder.handler
    }

    fun onClose(block: GuiCloseContext.() -> Unit){
        closeHandler = block
    }

    private fun index(x: Int, y: Int): Int{
        require(x in 0..8)
        require(y in 0 until rows)

        return y * 9 + x
    }

    fun build(): GuiTemplate{
        return GuiTemplate(plugin, title, rows, pages.toList(), closeHandler)
    }
}

class GuiPageScope(
    private val rows: Int,
    private val layout: GuiLayout
){
    fun fill(item: ItemStack){
        for(i in 0 until rows * 9){
            layout.items[i] = item
        }
    }

    fun slot(x: Int, y: Int, item: ItemStack){
        val index = index(x, y)
        layout.items[index] = item
    }

    fun button(x: Int, y: Int, item: ItemStack, block: GuiButtonBuilder.()->Unit){
        val index = index(x, y)
        layout.items[index] = item
        val builder = GuiButtonBuilder().apply(block)
        layout.clickHandlers[index] = builder.handler
    }
    private fun index(x: Int, y: Int): Int{
        require(x in 0..8)
        require(y in 0 until rows)

        return y * 9 + x
    }
}

class GuiButtonBuilder {
    internal var handler: GuiClickContext.() -> Unit = {}

    fun onClick(block: GuiClickContext.() -> Unit) {
        handler = block
    }
}

fun gui(
    plugin: JavaPlugin,
    title: String,
    rows: Int,
    block: GuiBuilder.() -> Unit
): GuiTemplate {
    val builder = GuiBuilder(plugin, title, rows)
    builder.block()
    return builder.build()
}