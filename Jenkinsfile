node {
    stage 'Checkout'
    git url: 'https://github.com/reasonthearchitect/AD_Addcar.git'

    stage 'Build'
    sh "./gradlew clean build sonarqube"

    stage 'BuildRunDocker'
    sh 'docker kill addcar'
    sh 'docker rm addcar'
    sh 'docker build -t reasonthearchitect/addcar .'
    sh 'docker run -d --name addcar -p 8201:8201 reasonthearchitect/addcar'
}