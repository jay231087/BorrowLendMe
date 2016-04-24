import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "BorrowLendMeAPI"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
		 javaCore, javaJdbc, jdbc,javaJpa,
		 "org.apache.poi" % "poi" % "3.8",	
		 "org.apache.poi" % "poi-ooxml" % "3.8",
	     "mysql" % "mysql-connector-java" % "5.1.23",
	     "org.hibernate" % "hibernate-entitymanager" % "3.6.0.Final",
	     "com.fasterxml.jackson.core" % "jackson-databind" % "2.1.3",
	     "log4j" % "log4j" % "1.2.16",
	     "com.lowagie" % "itext" % "2.1.7",
	     "org.apache.commons" % "commons-email" % "1.2",
 	     "commons-lang" % "commons-lang" % "2.3",
	     "org.eclipse.jetty"  % "jetty-websocket" % "8.1.11.v20130520",
	     "org.eclipse.jetty.websocket" % "websocket-api" % "9.0.0.M3",
		 "com.jolbox" % "bonecp" % "0.8.0.RELEASE",
	     "net.sf.jasperreports" % "jasperreports" % "5.5.0",
	     "com.google.code.simple-spring-memcached" % "spymemcached" % "2.8.4",
	     "org.apache.lucene" % "lucene-core" % "4.10.0",
	     "org.apache.lucene" % "lucene-analyzers-common" % "4.10.0",
	     "org.apache.lucene" % "lucene-queryparser" % "4.10.0",
	     "org.apache.lucene" % "lucene-demo" % "4.10.0",
	     "org.apache.lucene" % "lucene-test-framework" % "4.10.0",
	     "org.apache.lucene" % "lucene-memory" % "4.10.0"
	)
		
    val main = play.Project(appName, appVersion, appDependencies)
}
