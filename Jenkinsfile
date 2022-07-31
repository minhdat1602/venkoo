pipeline {
     agent any
    // tools {
    //     // Install the Maven version configured as "M3" and add it to the path.
    //     maven "M3"
    // }
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
                script{
                    // This step should not normally be used in your script. Consult the inline help for details.
                    withDockerRegistry(credentialsId: 'venkoo-id', url: 'https://index.docker.io/v1/') {
                        // some block
                        sh 'docker login -u minhdat1602 -p hongNhi@2210 https://index.docker.io/v1/'
                        sh 'docker build -t minhdat1602/venkoo .'
                        sh 'docker push minhdat1602/venkoo'
                    }
                }
            }
        }
     }
 }