pipeline {
    agent any
    stages {
        stage('Clone') {
            steps{
                git 'https://github.com/minhdat1602/venkoo.git'
            }
        }
        // stage('Build'){
        //     steps{
        //         sh 'cd /var/jenkins_home/workspace/VenkoName'
        //         dir('Venko') {
        //             sh 'chmod 777 ./mvnw'
        //             sh './mvnw package -DskipTests'
        //         }
        //     }
        // }
        // stage('Test'){
        //     steps{
        //         sh 'cd /var/jenkins_home/workspace/VenkoName'
        //         dir('Venko') {
        //             sh './mvnw test'
        //         }
        //     }
        // }
        stage('Deploy'){
            steps{
                sh 'cd /var/jenkins_home/workspace/VenkoName'
                dir('Venko'){
                    sh 'docker build -t minhdat1602/venkoo .'
                }
                
            }
        }
    }
 }