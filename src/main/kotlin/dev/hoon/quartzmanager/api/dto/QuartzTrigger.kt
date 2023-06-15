package dev.hoon.quartzmanager.api.dto

import org.quartz.CronTrigger
import org.quartz.SimpleTrigger
import org.quartz.Trigger
import org.quartz.Trigger.TriggerState
import java.util.Date
import java.util.TimeZone

data class QuartzTrigger(
    val key: QuartzKey,
    val jobKey: QuartzKey,
    val description: String? = null,
    val calendarName: String? = null,
    val jobDataMap: Map<String, Any>,
    val priority: Int,
    val mayFireAgain: Boolean,
    val startTime: Date?,
    val endTime: Date?,
    val nextFireTime: Date?,
    val previousFireTime: Date?,
    val finalFireTime: Date?,
    val misfireInstruction: Int,

    val type: String,

    val repeatCount: Int? = null,
    val repeatInterval: Long? = null,
    val timesTriggered: Int? = null,

    val cronExpression: String? = null,
    val timeZone: TimeZone? = null,
    val expressionSummary: String? = null,

    val state: TriggerState,
) {
    companion object {
        fun of(trigger: Trigger, triggerState: TriggerState): QuartzTrigger =
            with(trigger) {
                QuartzTrigger(
                    key = QuartzKey.of(key),
                    jobKey = QuartzKey.of(jobKey),
                    description = description,
                    calendarName = calendarName,
                    jobDataMap = jobDataMap,
                    priority = priority,
                    mayFireAgain = mayFireAgain(),
                    startTime = startTime,
                    endTime = endTime,
                    nextFireTime = nextFireTime,
                    previousFireTime = previousFireTime,
                    finalFireTime = finalFireTime,
                    misfireInstruction = misfireInstruction,

                    type = if (trigger is SimpleTrigger) "SimpleTrigger"
                            else if (trigger is CronTrigger) "CronTrigger"
                            else "Trigger",

                    repeatCount = if (trigger is SimpleTrigger) trigger.repeatCount else null,
                    repeatInterval = if (trigger is SimpleTrigger) trigger.repeatInterval else null,
                    timesTriggered = if (trigger is SimpleTrigger) trigger.timesTriggered else null,

                    cronExpression = if (trigger is CronTrigger) trigger.cronExpression else null,
                    timeZone = if (trigger is CronTrigger) trigger.timeZone else null,
                    expressionSummary = if (trigger is CronTrigger) trigger.expressionSummary else null,

                    state = triggerState
                )
            }
    }
}

