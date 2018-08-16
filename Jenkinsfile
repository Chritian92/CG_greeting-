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
        post {
           success {
                junit 'build/test-results/test/*.xml'
                publishHTML (target: [
                            allowMissing: true,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'build/reports/tests/test',
                            reportFiles: 'index.html',
                            reportName: "Test Summary"
                    	    ])
                publishHTML (target: [
                            allowMissing: true,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'build/reports/coverage/',
                            reportFiles: 'index.html',
                            reportName: 'Code Coverage Report'
                            ])
                publishHTML (target: [
                            allowMissing: true,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'build/reports/findbugs',
                            reportFiles: 'main.html',
                            reportName: "Findbugs Main Analysis"
                            ])
                publishHTML (target: [
                            allowMissing: true,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'build/reports/findbugs',
                            reportFiles: 'test.html',
                            reportName: "Findbugs Test Analysis"
                            ])
            }
        }
        stage('CodeQuality') {
            steps {
                echo 'Code Quality..'
                sh'./gradlew sonarqube'

            }
        }
        stage('Package') {
            steps {
                sh './gradlew build capsule'
            }
        }
        post {
           success {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
        stage('Publish') {
            steps {
                echo 'Publishing Artifact....'
        		sh './gradlew uploadArchives '
            }
        }
        
    }
    post {
       success {
           archiveArtifacts artifacts: '**/repos/*.jar', fingerprint: true
       }      

    }
}
