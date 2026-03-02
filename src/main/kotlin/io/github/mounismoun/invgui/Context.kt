package io.github.mounismoun.invgui

import org.bukkit.Sound
import org.bukkit.entity.Player

class GuiClickContext(
    val player: Player,
    val session: GuiSession,
    val slot: Int
){
    fun sound(sound: Sound, volume: Float = 1.0f, pitch: Float = 1.0f) {
        player.playSound(player.location, sound, volume, pitch)
    }
}

class GuiCloseContext(
    val player: Player,
    val session: GuiSession
){
    fun sound(sound: Sound, volume: Float = 1.0f, pitch: Float = 1.0f) {
        player.playSound(player.location, sound, volume, pitch)
    }
}