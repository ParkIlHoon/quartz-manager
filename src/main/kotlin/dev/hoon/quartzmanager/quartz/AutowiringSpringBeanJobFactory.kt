package dev.hoon.quartzmanager.quartz

import org.quartz.spi.TriggerFiredBundle
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.scheduling.quartz.SpringBeanJobFactory

/**
 * This JobFactory autowires automatically the created quartz bean with spring @Autowired dependencies.
 *
 * @author jelies (thanks to Brian Matthews: http://webcache.googleusercontent.com/search?q=cache:FH-N1i--sDgJ:blog.btmatthews.com/2011/09/24/inject-application-context-dependencies-in-quartz-job-beans/+&cd=7&hl=en&ct=clnk&gl=es)
 */
class AutowiringSpringBeanJobFactory : SpringBeanJobFactory(), ApplicationContextAware {
    @Transient
    private var beanFactory: AutowireCapableBeanFactory? = null

    override fun setApplicationContext(context: ApplicationContext) {
        beanFactory = context.autowireCapableBeanFactory
    }

    override fun createJobInstance(bundle: TriggerFiredBundle): Any {
        val job = super.createJobInstance(bundle)
        beanFactory?.autowireBean(job)
        return job
    }
}
