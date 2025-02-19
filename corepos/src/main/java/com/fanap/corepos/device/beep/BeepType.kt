package com.fanap.corepos.device.beep

enum class BeepType(val value: Int){
    BEEP_TYPE_SUCCESS(0),
    BEEP_TYPE_ERROR(1),
    BEEP_TYPE_FAULT(2),
    BEEP_TYPE_PROMPT(3);
}