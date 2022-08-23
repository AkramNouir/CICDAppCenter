pipeline {
  agent any
  environment {
    APP_NAME = 'test'
  }
  options {
    // Stop the build early in case of compile or test failures
    skipStagesAfterUnstable()
  }
  stages {
  stage('Detect build type') {
    steps {
      script {
        if (env.BRANCH_NAME == 'develop' || env.CHANGE_TARGET == 'develop') {
          env.BUILD_TYPE = 'Debug'
        } else if (env.BRANCH_NAME == 'master' || env.CHANGE_TARGET == 'master') {
          env.BUILD_TYPE = 'Release'
        }
      }
    }
  }

  stage('Compile') {
    steps {
      echo 'Start assemble application ${BUILD_TYPE}'
      // Compile the app and its dependencies
      sh './gradlew assemble${BUILD_TYPE}'
    }
  }
  }
}