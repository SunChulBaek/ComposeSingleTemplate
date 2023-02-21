package com.example.composetemplate.event

/**
 * @param timestamp route만 설정시 연속 재선택이 되지 않아 이벤트 구분을 위한 필드
 */
data class NavItemReselectEvent(
    val route: String = "",
    val timestamp: Long = System.currentTimeMillis()
)