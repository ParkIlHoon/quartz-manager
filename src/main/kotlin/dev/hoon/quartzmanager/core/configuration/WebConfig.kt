package dev.hoon.quartzmanager.core.configuration

import dev.hoon.quartzmanager.core.interceptor.WorkspaceInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val workspaceInterceptor: WorkspaceInterceptor
): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(workspaceInterceptor)
            .addPathPatterns("/api/**")
    }
}