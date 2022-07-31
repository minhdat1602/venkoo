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
                sh 'cd /var/jenkins_home/workspace/VenkoName'
                dir('Venko') {
                    sh 'chmod 777 ./mvnw'
                    sh './mvnw package -DskipTests'
                }
            }
        }
        stage('Test'){
            steps{
                sh 'cd /var/jenkins_home/workspace/VenkoName'
                dir('Venko') {
                    sh './mvnw test'
                }
            }
        }
        stage('Deploy'){
            steps{
                // This step should not normally be used in your script. Consult the inline help for details.
                sh 'pwd'
                sh 'cd /var/jenkins_home/workspace/VenkoName'
                step([$class: 'DockerBuilderPublisher', cleanImages: false, cleanupWithJenkinsJobDelete: false, cloud: '', dockerFileDirectory: './Venko', fromRegistry: [credentialsId: 'venkoo-id', url: 'https://index.docker.io/v1/'], pushCredentialsId: 'venkoo-id', pushOnSuccess: true, tagsString: 'minhdat1602/venkoo'])
                // withDockerRegistry(credentialsId: 'venkoo-id', url: 'https://index.docker.io/v1/') {
                //     sh 'cd /var/jenkins_home/workspace/VenkoName'
                //     sh 'ls'
                //     dir('Venko'){
                //         sh 'pwd'
                //         // sh 'docker build -t minhdat1602/venkoo .'
                //         // sh 'docker push minhdat1602/venkoo'
                //     }
                // }
            }
        }
     }
 }