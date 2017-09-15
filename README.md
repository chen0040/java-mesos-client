# java-mesos-client

Mesos client for chronos and marathon job management
 
# Usage

## Working with Mesos Master

Read states from mesos master:

```java
import com.github.chen0040.mesos.client.core.MesosUtil;

String mesosMasterUrl1 = "http://10.0.0.15:5050";
String json1 =MesosUtil.getStateJson(mesosMasterUrl1);
System.out.println(json1);
```

Read tasks from mesos master:

```java
import com.github.chen0040.mesos.client.core.MesosUtil;

String mesosMasterUrl1 = "http://10.0.0.15:5050";
String json1 = MesosUtil.getTasks("http://10.0.0.15:5050");
System.out.println(json1);
```

## Working with Chronos

To list jobs in Chronos

```java
String chronosUrl = "http://10.0.0.1:4400";
List<ChronosJobInfo> result = ChronosUtil.listJobs(chronosUrl);

result.stream().forEach(job -> {
 System.out.println("name: " + job.getName());
 System.out.println("command: " + job.getCommand());
});
```

To get a particular job in Chronos:

```java
String chronosUrl = "http://10.0.0.1:4400";
List<ChronosJobInfo> result = ChronosUtil.listJobs(chronosUrl);

if (!result.isEmpty()) {
 String appId = result.get(0).getName();

 Optional<ChronosJobInfo> jobInfo = ChronosUtil.getJob(chronosUrl, appId);

 System.out.println(JSON.toJSONString(jobInfo.orElseGet(ChronosJobInfo::new)));

 Assert.assertEquals(appId, jobInfo.get().getName());
}
```

To start a particular job in Chronos:

```java
String chronosUrl = "http://10.0.0.1:4400";
String regressionTestId = "regtest";
String regressionTestCmd = "echo 'Hello Chronos' ; sleep 5";

 if (!ChronosUtil.jobExists(chronosUrl, regressionTestId)) {
 ChronosJobRequest request = new ChronosJobRequest("PT15M");
 request.setCommand(regressionTestCmd);
 request.setName(regressionTestId);
 request.setCpus(0.1);
 request.setMem(100);

 System.out.println(ChronosUtil.startJob(chronosUrl, request));

 try {
    Thread.sleep(5000l);
 }
 catch (InterruptedException e) {
    e.printStackTrace();
 }

 Assert.assertTrue(ChronosUtil.jobExists(chronosUrl, regressionTestId));
}
```
