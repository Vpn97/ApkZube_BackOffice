#!/usr/bin/env groovy

node {

    env.JAVA_HOME="${tool 'jdk-12.0.2'}"
    env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"


    env.M2_HOME="${tool 'apache-maven-3.8.1'}"
    env.PATH="${env.M2_HOME}/bin:${env.PATH}"


    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        sh "java -version"
    }

    stage('clean') {
        sh "chmod +x mvnw"
        sh "./mvnw -ntp clean -P-webapp -DskipTests"
    }
    stage('nohttp') {
        sh "./mvnw -ntp checkstyle:check"
    }

    stage('install tools') {
        sh "./mvnw -ntp com.github.eirslett:frontend-maven-plugin:install-node-and-npm@install-node-and-npm"
    }

    stage('npm install') {
        sh "./mvnw -ntp com.github.eirslett:frontend-maven-plugin:npm"
    }
    stage('backend tests') {
        try {
            sh "./mvnw -ntp verify -P-webapp -DskipTests"
        } catch(err) {
            throw err
        } finally {
            //junit '**/target/surefire-reports/TEST-*.xml,**/target/failsafe-reports/TEST-*.xml'
        }
    }

    stage('frontend tests') {
        try {
            sh "./mvnw -ntp com.github.eirslett:frontend-maven-plugin:npm -DskipTests"
        } catch(err) {
            throw err
        } finally {
            //junit '**/target/test-results/TESTS-results-jest.xml'
        }
    }

     stage('packaging') {
            sh "./mvnw clean install -Prod -DskipTests"
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
     }

    /* stage('deploy') {
        sh "java -jar ./target/apk-zube-back-office-0.0.1-SNAPSHOT.jar"
    } */

     stage("Staging") {
        sh "pid=\$(lsof -i:8080 -t); kill -TERM \$pid || kill -KILL \$pid"
        withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
            sh 'nohup java -Dserver.port=8080 -jar ./target/apk-zube-back-office-0.0.1-SNAPSHOT.jar &'
        }
     }

}
