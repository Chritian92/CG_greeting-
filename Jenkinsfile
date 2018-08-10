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
                sh'./gradlew clean test jacocoTestReport'
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
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true, onlyIfSuccessful: true
                junit 'build/test-results/test/*.xml'
                publishHTML([allowMissing: true,
                               alwaysLinkToLastBuild: false,
                               keepAll: true,
                               reportDir: 'build/reports/tests/test/',
                               reportFiles: 'index.html',
                               reportTitles: "Code Coverage Report",
                               reportName: 'Code Coverage Report'])
        }

    }
}
