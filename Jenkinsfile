pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './gradlew clean assemble'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh'./gradlew test jacocoTestReport'
		echo 'Code check..'
                sh'./gradlew check'
            }
        }
        stage('CodeQuality') {
            steps {
                echo 'Code Quality..'
                sh'./gradlew sonarqube -Dsonar.organization=chritian92-github -Dsonar.host.url=https://sonarcloud.io  -Dsonar.login=9e466878cd5882f00bb4127599e7623efa322e91'

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
       }
       success {
           archiveArtifacts artifacts: '**/repos/*.jar', fingerprint: true
       }      

    }
}
