INSERT INTO BATCH_A_JOB_INSTANCE (JOB_INSTANCE_ID, VERSION, JOB_NAME, JOB_KEY) VALUES (1, 0, 'DummySpringBatchJob', '615e7f405d019964df80a4baa178d469');
INSERT INTO BATCH_A_JOB_EXECUTION (JOB_EXECUTION_ID, VERSION, JOB_INSTANCE_ID, CREATE_TIME, START_TIME, END_TIME, STATUS, EXIT_CODE, EXIT_MESSAGE, LAST_UPDATED) VALUES (1, 2, 1, '2023-06-13 11:42:34.925483', '2023-06-13 11:42:34.996670', '2023-06-13 11:42:40.366633', 'FAILED', 'FAILED', '', '2023-06-13 11:42:40.369908');
INSERT INTO BATCH_A_JOB_EXECUTION_CONTEXT (JOB_EXECUTION_ID, SHORT_CONTEXT, SERIALIZED_CONTEXT) VALUES (1, 'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABdAANYmF0Y2gudmVyc2lvbnQABTUuMC4weA==', null);
INSERT INTO BATCH_A_JOB_EXECUTION_PARAMS (JOB_EXECUTION_ID, PARAMETER_NAME, PARAMETER_TYPE, PARAMETER_VALUE, IDENTIFYING) VALUES (1, 'sendDate', 'java.lang.String', '2023-06-13', 'Y');
INSERT INTO BATCH_A_JOB_EXECUTION_PARAMS (JOB_EXECUTION_ID, PARAMETER_NAME, PARAMETER_TYPE, PARAMETER_VALUE, IDENTIFYING) VALUES (1, 'SYS_JOB_EXECUTED_DATE', 'java.util.Date', 'Tue Jun 13 11:42:34 KST 2023', 'Y');
INSERT INTO BATCH_A_JOB_EXECUTION_PARAMS (JOB_EXECUTION_ID, PARAMETER_NAME, PARAMETER_TYPE, PARAMETER_VALUE, IDENTIFYING) VALUES (1, 'SYS_JOB_NAME', 'java.lang.String', 'DummySpringBatchJob', 'Y');

INSERT INTO BATCH_A_STEP_EXECUTION (STEP_EXECUTION_ID, VERSION, STEP_NAME, JOB_EXECUTION_ID, CREATE_TIME, START_TIME, END_TIME, STATUS, COMMIT_COUNT, READ_COUNT, FILTER_COUNT, WRITE_COUNT, READ_SKIP_COUNT, WRITE_SKIP_COUNT, PROCESS_SKIP_COUNT, ROLLBACK_COUNT, EXIT_CODE, EXIT_MESSAGE, LAST_UPDATED) VALUES (1, 2, 'DummySpringBatchJob_findReservedPush', 1, '2023-06-13 11:42:35.059460', '2023-06-13 11:42:35.090696', '2023-06-13 11:42:40.301306', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.transaction.CannotCreateTransactionException: Could not open JDBC Connection for transaction
	at org.springframework.jdbc.datasource.DataSourceTransactionManager.doBegin(DataSourceTransactionManager.java:309)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.startTransaction(AbstractPlatformTransactionManager.java:400)
	at org.springframework.transaction.support.AbstractPlatformTransactionManager.getTransaction(AbstractPlatformTransactionManager.java:373)
	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:137)
	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:256)
	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)
	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:362)
	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:206)
	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:139)
	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:241)
	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:227)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:343)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.support.DelegatingIntroductionInterceptor.doProceed(DelegatingIntroductionInterceptor.java:137)
	at org.springframework.aop.support.DelegatingIntroductionInterceptor.invoke(DelegatingIntroductionInterceptor.java:124)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:218)
	at jdk.proxy2/jdk.proxy2.$Proxy61.execute(Unknown Source)
	at org.s', '2023-06-13 11:42:40.306200');
INSERT INTO BATCH_A_STEP_EXECUTION_CONTEXT (STEP_EXECUTION_ID, SHORT_CONTEXT, SERIALIZED_CONTEXT) VALUES (1, 'rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAADdAARYmF0Y2gudGFza2xldFR5cGV0AFNrci5waW5nZGEuYmF0Y2gucGlua2RpYXJ5LnB1c2guc2VuZC50YXNrbGV0LkZpbmRSZXNlcnZlZFB1c2hUYXNrbGV0JCRTcHJpbmdDR0xJQiQkMHQADWJhdGNoLnZlcnNpb250AAU1LjAuMHQADmJhdGNoLnN0ZXBUeXBldAA3b3JnLnNwcmluZ2ZyYW1ld29yay5iYXRjaC5jb3JlLnN0ZXAudGFza2xldC5UYXNrbGV0U3RlcHg=', null);

