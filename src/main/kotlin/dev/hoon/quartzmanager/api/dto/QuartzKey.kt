package dev.hoon.quartzmanager.api.dto

import org.quartz.JobKey
import org.quartz.TriggerKey

data class QuartzKey(
    val group: String,
    val name: String,
) {
    companion object {
        fun of(key: JobKey): QuartzKey =
            QuartzKey(
                group = key.group,
                name = key.name
            )

        fun of(key: TriggerKey): QuartzKey =
            QuartzKey(
                group = key.group,
                name = key.name
            )
    }
}
