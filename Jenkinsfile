pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean assemble'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh'./gradlew test jacocoTestReport'
            }
        }
        stage('CodeQuality') {
            steps {
                echo 'Code Quality..'
                sh'./gradlew sonarqube'
            }
        }
        stage('checkstyle') {
             steps {
                 echo 'Code check..'
                 sh'./gradlew check'
             }
        }
        stage('Publish') {
            steps {
                echo 'Publishing Artifact....'
        		sh './gradlew uploadArchives '
        		echo 'Publishing Reports....'
        		sh './gradlew clean test jacocoTestReport'
            }
        }
        
    }
    post {
        always {
           junit 'build/test-results/test/*.xml'
           publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'build/reports/tests/test',
             reportFiles: 'index.html',
             reportName: "Test Summary"
	       ])
           publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'build/jacocoHtml',
             reportFiles: 'index.html',
             reportName: "Code Coverage"
           ])	 
           publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'build/reports/pmd',
             reportFiles: 'main.html',
             reportName: "PMD Main Analysis"
           ])	
           publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'build/reports/pmd',
             reportFiles: 'test.html',
             reportName: "PMD Test Analysis"
           ])	
           publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'build/reports/findbugs',
             reportFiles: 'main.html',
             reportName: "Findbugs Main Analysis"
           ])		  
           publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: 'build/reports/findbugs',
             reportFiles: 'test.html',
             reportName: "Findbugs Test Analysis"
           ])			   
       }
       success {
           archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
       }      

    }
}
