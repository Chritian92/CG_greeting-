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
                sh'./gradlew test jacocoTestReport check'
            }
        
            post {
                success {
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
            }     
        }
        stage('CodeQuality') {
            steps {
                echo 'Code Quality..'
                sh'./gradlew sonarqube'

            }
        }
        stage('Publish') {
            steps {
                echo 'Publishing Artifact....'
        		sh './gradlew uploadArchives '
        		echo 'Publishing Reports....'
        		sh './gradlew clean test jacocoTestReport'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/repos/*.jar', fingerprint: true
                }      
            }
        }
        stage('Deploy') {
            steps {
                sh './gradlew -b deploy.gradle deploy -Pdev_server=10.28.135.237 -Puser_server=ubuntu -Pkey_path=/var/jenkins_home/var.pem -Pjar_path=build/libs -Pjar_name=greeting-1.0-capsule -Puser_home=/home/ubuntu'
            }
        }     
    }   
}
