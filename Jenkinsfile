pipeline {
     agent any
     stages {
         stage('Clone') {
             steps{
                 git 'https://github.com/minhdat1602/venkoo.git'
             }
         }
        stage('Build'){
            steps{
                sh 'cd /var/jenkins_home/workspace/Venko/Venko'
                sh 'chmod 777 ./mvnw'
                sh './mvnw clean install'
            }
        }
        stage('Test'){
            steps{
                sh 'cd /var/jenkins_home/workspace/Venko/Venko'
                sh './mvnw test'
            }
        }
        stage('Deploy'){
            steps{
                // This step should not normally be used in your script. Consult the inline help for details.
                withDockerRegistry(credentialsId: '59ce4ba3-3524-434a-9ea8-c01b36f1e1d5', url: 'https://index.docker.io/v1/') {
                    sh 'cd /var/jenkins_home/workspace/Venko/Venko'
                    sh 'docker build -t minhdat16/venko .'
                    sh 'docker push minhdat1602/venkoo'
                }
            }
        }
     }
 }