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
                        reportDir: 'build/reports/jacoco',
                        reportFiles: 'index.html',
                        reportName: "Code Coverage Jacoco"
                        ])
                    publishHTML(target: [allowMissing: true, 
                        alwaysLinkToLastBuild: false,  
                        keepAll: true, 
                        reportDir: 'build/reports/checkstyle', 
                        reportFiles: 'main.html', 
                        reportTitles: "Checkstyle report",
                        reportName: 'CheckstyleReport'
                        ])
                    publishHTML(target: [allowMissing: true, 
                        alwaysLinkToLastBuild: false,  
                        keepAll: true, 
                        reportDir: 'build/reports/findbugs', 
                        reportFiles: 'main.html', 
                        reportTitles: "Bugs Report",
                        reportName: 'BugReport'
                        ])
                    publishHTML(target: [allowMissing: true, 
                        alwaysLinkToLastBuild: false,  
                        keepAll: true, 
                        reportDir: 'build/reports/pmd', 
                        reportFiles: 'main.html', 
                        reportTitles: "source code analyzer",
                        reportName: 'PmdReport'
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
        stage('Deploy') {
            steps {
                sh './gradlew -b deploy.gradle deploy -Pdev_server=10.28.135.237 -Puser_server=ubuntu -Pkey_path=/home/var.pem'
            }
        }     
    }   
}
