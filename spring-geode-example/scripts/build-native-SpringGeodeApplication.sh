#!/bin/bash

native-image -cp /Users/jblum/pivdev/java-graalvm-examples/spring-geode/target/classes:/Users/jblum/.m2/repository/org/assertj/assertj-core/3.21.0/assertj-core-3.21.0.jar:/Users/jblum/.m2/repository/org/springframework/geode/spring-geode-starter/1.5.6/spring-geode-starter-1.5.6.jar:/Users/jblum/.m2/repository/org/springframework/boot/spring-boot-starter/2.5.6/spring-boot-starter-2.5.6.jar:/Users/jblum/.m2/repository/org/springframework/boot/spring-boot/2.5.6/spring-boot-2.5.6.jar:/Users/jblum/.m2/repository/org/springframework/spring-context/5.3.12/spring-context-5.3.12.jar:/Users/jblum/.m2/repository/org/springframework/spring-aop/5.3.12/spring-aop-5.3.12.jar:/Users/jblum/.m2/repository/org/springframework/spring-expression/5.3.12/spring-expression-5.3.12.jar:/Users/jblum/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.5.6/spring-boot-autoconfigure-2.5.6.jar:/Users/jblum/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.5.6/spring-boot-starter-logging-2.5.6.jar:/Users/jblum/.m2/repository/ch/qos/logback/logback-classic/1.2.6/logback-classic-1.2.6.jar:/Users/jblum/.m2/repository/ch/qos/logback/logback-core/1.2.6/logback-core-1.2.6.jar:/Users/jblum/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.14.1/log4j-to-slf4j-2.14.1.jar:/Users/jblum/.m2/repository/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar:/Users/jblum/.m2/repository/org/slf4j/jul-to-slf4j/1.7.32/jul-to-slf4j-1.7.32.jar:/Users/jblum/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/jblum/.m2/repository/org/springframework/spring-core/5.3.12/spring-core-5.3.12.jar:/Users/jblum/.m2/repository/org/yaml/snakeyaml/1.28/snakeyaml-1.28.jar:/Users/jblum/.m2/repository/org/springframework/geode/spring-geode/1.5.6/spring-geode-1.5.6.jar:/Users/jblum/.m2/repository/org/springframework/data/spring-data-geode/2.5.6/spring-data-geode-2.5.6.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-core/1.13.4/geode-core-1.13.4.jar:/Users/jblum/.m2/repository/io/micrometer/micrometer-core/1.6.3/micrometer-core-1.6.3.jar:/Users/jblum/.m2/repository/org/hdrhistogram/HdrHistogram/2.1.12/HdrHistogram-2.1.12.jar:/Users/jblum/.m2/repository/org/latencyutils/LatencyUtils/2.0.3/LatencyUtils-2.0.3.jar:/Users/jblum/.m2/repository/javax/resource/javax.resource-api/1.7.1/javax.resource-api-1.7.1.jar:/Users/jblum/.m2/repository/javax/transaction/javax.transaction-api/1.3/javax.transaction-api-1.3.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-core/1.7.1/shiro-core-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-lang/1.7.1/shiro-lang-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-cache/1.7.1/shiro-cache-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-crypto-hash/1.7.1/shiro-crypto-hash-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-crypto-core/1.7.1/shiro-crypto-core-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-crypto-cipher/1.7.1/shiro-crypto-cipher-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-config-core/1.7.1/shiro-config-core-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-config-ogdl/1.7.1/shiro-config-ogdl-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-event/1.7.1/shiro-event-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-common/1.13.4/geode-common-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-management/1.13.4/geode-management-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/httpcomponents/httpclient/4.5.13/httpclient-4.5.13.jar:/Users/jblum/.m2/repository/org/apache/httpcomponents/httpcore/4.4.13/httpcore-4.4.13.jar:/Users/jblum/.m2/repository/org/jgroups/jgroups/3.6.14.Final/jgroups-3.6.14.Final.jar:/Users/jblum/.m2/repository/commons-validator/commons-validator/1.6/commons-validator-1.6.jar:/Users/jblum/.m2/repository/commons-beanutils/commons-beanutils/1.9.2/commons-beanutils-1.9.2.jar:/Users/jblum/.m2/repository/commons-digester/commons-digester/1.8.1/commons-digester-1.8.1.jar:/Users/jblum/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/Users/jblum/.m2/repository/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar:/Users/jblum/.m2/repository/javax/xml/bind/jaxb-api/2.3.1/jaxb-api-2.3.1.jar:/Users/jblum/.m2/repository/javax/activation/javax.activation-api/1.2.0/javax.activation-api-1.2.0.jar:/Users/jblum/.m2/repository/com/sun/xml/bind/jaxb-impl/2.3.2/jaxb-impl-2.3.2.jar:/Users/jblum/.m2/repository/org/apache/commons/commons-lang3/3.10/commons-lang3-3.10.jar:/Users/jblum/.m2/repository/it/unimi/dsi/fastutil/8.3.1/fastutil-8.3.1.jar:/Users/jblum/.m2/repository/net/java/dev/jna/jna/5.5.0/jna-5.5.0.jar:/Users/jblum/.m2/repository/net/java/dev/jna/jna-platform/5.5.0/jna-platform-5.5.0.jar:/Users/jblum/.m2/repository/net/sf/jopt-simple/jopt-simple/5.0.4/jopt-simple-5.0.4.jar:/Users/jblum/.m2/repository/io/github/classgraph/classgraph/4.8.52/classgraph-4.8.52.jar:/Users/jblum/.m2/repository/com/healthmarketscience/rmiio/rmiio/2.1.2/rmiio-2.1.2.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-logging/1.13.4/geode-logging-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-membership/1.13.4/geode-membership-1.13.4.jar:/Users/jblum/.m2/repository/com/github/stephenc/findbugs/findbugs-annotations/1.3.9-1/findbugs-annotations-1.3.9-1.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-unsafe/1.13.4/geode-unsafe-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-serialization/1.13.4/geode-serialization-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-tcp-server/1.13.4/geode-tcp-server-1.13.4.jar:/Users/jblum/.m2/repository/com/sun/activation/javax.activation/1.2.0/javax.activation-1.2.0.jar:/Users/jblum/.m2/repository/com/sun/istack/istack-commons-runtime/3.0.11/istack-commons-runtime-3.0.11.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-cq/1.13.4/geode-cq-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-lucene/1.13.4/geode-lucene-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/lucene/lucene-core/6.6.6/lucene-core-6.6.6.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-gfsh/1.13.4/geode-gfsh-1.13.4.jar:/Users/jblum/.m2/repository/org/apache/lucene/lucene-analyzers-common/6.6.6/lucene-analyzers-common-6.6.6.jar:/Users/jblum/.m2/repository/org/apache/lucene/lucene-queryparser/6.6.6/lucene-queryparser-6.6.6.jar:/Users/jblum/.m2/repository/org/apache/lucene/lucene-queries/6.6.6/lucene-queries-6.6.6.jar:/Users/jblum/.m2/repository/mx4j/mx4j/3.0.2/mx4j-3.0.2.jar:/Users/jblum/.m2/repository/org/apache/lucene/lucene-analyzers-phonetic/6.6.6/lucene-analyzers-phonetic-6.6.6.jar:/Users/jblum/.m2/repository/commons-codec/commons-codec/1.10/commons-codec-1.10.jar:/Users/jblum/.m2/repository/org/apache/geode/geode-wan/1.13.4/geode-wan-1.13.4.jar:/Users/jblum/.m2/repository/org/springframework/spring-tx/5.3.11/spring-tx-5.3.11.jar:/Users/jblum/.m2/repository/org/springframework/spring-web/5.3.11/spring-web-5.3.11.jar:/Users/jblum/.m2/repository/org/springframework/data/spring-data-commons/2.5.6/spring-data-commons-2.5.6.jar:/Users/jblum/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-spring/1.7.1/shiro-spring-1.7.1.jar:/Users/jblum/.m2/repository/org/apache/shiro/shiro-web/1.7.1/shiro-web-1.7.1.jar:/Users/jblum/.m2/repository/org/owasp/encoder/encoder/1.2.2/encoder-1.2.2.jar:/Users/jblum/.m2/repository/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar:/Users/jblum/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.12.5/jackson-annotations-2.12.5.jar:/Users/jblum/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.12.5/jackson-databind-2.12.5.jar:/Users/jblum/.m2/repository/org/slf4j/slf4j-api/1.7.26/slf4j-api-1.7.26.jar:/Users/jblum/.m2/repository/org/springframework/geode/apache-geode-extensions/1.5.6/apache-geode-extensions-1.5.6.jar:/Users/jblum/.m2/repository/org/springframework/spring-context-support/5.3.12/spring-context-support-5.3.12.jar:/Users/jblum/.m2/repository/org/springframework/spring-beans/5.3.12/spring-beans-5.3.12.jar:/Users/jblum/.m2/repository/org/springframework/spring-jcl/5.3.12/spring-jcl-5.3.12.jar:/Users/jblum/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.12.5/jackson-datatype-jsr310-2.12.5.jar:/Users/jblum/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.12.5/jackson-core-2.12.5.jar:/Users/jblum/.m2/repository/org/springframework/geode/spring-geode-autoconfigure/1.5.6/spring-geode-autoconfigure-1.5.6.jar:/Users/jblum/.m2/repository/org/springframework/shell/spring-shell/1.2.0.RELEASE/spring-shell-1.2.0.RELEASE.jar:/Users/jblum/.m2/repository/com/google/guava/guava/17.0/guava-17.0.jar:/Users/jblum/.m2/repository/jline/jline/2.12/jline-2.12.jar:/Users/jblum/.m2/repository/commons-io/commons-io/2.4/commons-io-2.4.jar:/Users/jblum/.m2/repository/org/projectlombok/lombok/1.18.22/lombok-1.18.22.jar io.example.app.spring.geode.SpringGeodeApplication springgeodeapp
