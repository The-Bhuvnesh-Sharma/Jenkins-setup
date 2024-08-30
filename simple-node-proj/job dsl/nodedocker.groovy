job("node js project ver 2"){
    description("this project will clone node js proj and build and push it to docker hub")
     scm {
        git('https://github.com/mukeshphulwani66/simple-node-proj.git','main') { node -> 
            node / gitConfigName('mukeshphulwani66')
            node / gitConfigEmail('mukeshphulwani66@gmail.com')
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
            repositoryName('mukeshphulwani66/nodejs-jenkins-demo')
            tag('${BUILD_NUMBER}')
            registryCredentials('dockerhubcred')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
         shell('docker logout')
    }
}