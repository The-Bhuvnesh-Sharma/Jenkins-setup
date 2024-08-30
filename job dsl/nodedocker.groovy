job("node js project ver 2"){
    description("this project will clone node js proj and build and push it to docker hub")
     scm {
        git('https://github.com/The-Bhuvnesh-Sharma/Jenkins-setup.git','master') { node -> 
            node / gitConfigName('The-Bhuvnesh-Sharma')
            node / gitConfigEmail('bhuvi844964@gmail.com')
        }
    }
    wrappers {
          nodejs('node 16')
          credentialsBinding {
            usernamePassword('USERNAME_DOC', 'PASS_DOC','dockerhubcred')
        }
    }
    steps{
        shell('npm install')
        shell('docker login -u ${USERNAME_DOC} -p ${PASS_DOC}')
        dockerBuildAndPublish {
            repositoryName('sharmaji232001/nodejs-jenkins-demo')
            tag('${BUILD_NUMBER}')
            registryCredentials('dockerhubcred')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
         shell('docker logout')
    }
}