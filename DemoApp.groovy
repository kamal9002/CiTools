/**
*  DemoApp.groovy - Demo app using CMAKE build system
**/
pipeline {
    agent {
	
	label "UBUNTU_MISC "
    }
    stages {
        stage('Git Checkout') {
        steps {
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'http', url: 'https://github.com/kamal9002/CIDemoApp.git']]])
             {
            echo "*********************DemoApp Cache-Setup***********************"
            sh '''
                cmake -H. -BBuild
            '''
            }
        }
       stage ('Release-Build'){
           steps{
               dir ('Build'){
                       echo "*********************DemoApp Release Build***********************"
                         sh '''
                            cmake --build . --config Release
                         '''
				}
            }
        }
       stage ('Debug-Build'){
           steps{
               dir ('Build'){
                       echo "*********************DemoApp Debug Build***********************"
                         sh '''
                            cmake --build . --config Debug
                         '''
                }
            }
        }
    }
}
