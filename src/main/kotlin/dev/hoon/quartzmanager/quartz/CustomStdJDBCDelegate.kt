package dev.hoon.quartzmanager.quartz

import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.quartz.JobKey
import org.quartz.impl.JobDetailImpl
import org.quartz.impl.jdbcjobstore.StdJDBCDelegate
import org.quartz.spi.ClassLoadHelper
import java.io.InputStream
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

class CustomStdJDBCDelegate: StdJDBCDelegate() {

    override fun selectJobDetail(conn: Connection?, jobKey: JobKey?, loadHelper: ClassLoadHelper?): JobDetail {
        var ps: PreparedStatement? = null
        var rs: ResultSet? = null

        return try {
            ps = conn!!.prepareStatement(rtp(SELECT_JOB_DETAIL))
            ps.setString(1, jobKey!!.name)
            ps.setString(2, jobKey.group)
            rs = ps.executeQuery()
            var job: JobDetailImpl? = null
            if (rs.next()) {
                job = JobDetailImpl()
                job.name = rs.getString(COL_JOB_NAME)
                job.group = rs.getString(COL_JOB_GROUP)
                job.description = rs.getString(COL_DESCRIPTION)
                job.jobClass = DummyJob::class.java
                job.setDurability(getBoolean(rs, COL_IS_DURABLE))
                job.setRequestsRecovery(getBoolean(rs, COL_REQUESTS_RECOVERY))
                var map: Map<*, *>? = if (canUseProperties()) {
                    getMapFromProperties(rs)
                } else {
                    getObjectFromBlob(rs, COL_JOB_DATAMAP) as Map<*, *>
                }
                if (null != map) {
                    job.jobDataMap = JobDataMap(map)
                }
            }
            job!!
        } finally {
            closeResultSet(rs)
            closeStatement(ps)
        }


    }

    private fun getMapFromProperties(rs: ResultSet): Map<*, *>? {
        val map: Map<*, *>
        val inputStream = getJobDataFromBlob(rs, COL_JOB_DATAMAP) as InputStream
        val properties = Properties()
        inputStream.use { properties.load(it) }
        map = convertFromProperty(properties)
        return map
    }
}