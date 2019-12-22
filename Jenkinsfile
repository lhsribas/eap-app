pipeline
{
    
    agent 
    {
        node 
        {      
            label "maven"
        }
    }

    parameters 
    {
        string(name: 'PROJECT_NAME', defaultValue: 'apps' ,description: 'What is the project name?')
    }

    environment 
    {
        def version = ""
        def artifactId = ""
        def rollout = true
    }

    stages
    {
        stage("Test Project") 
        {
            steps 
            {
                script 
                {
                    withMaven(mavenSettingsConfig: "maven-settings") {
                        sh "mvn clean test"
                    }
                }
            }
        }
        
        stage("Build Project") 
        {
            steps 
            {
                script 
                {
                    withMaven(mavenSettingsConfig: "maven-settings") {
                        sh "mvn clean package"
                    }
                }
            }
        }
        
        stage("Copy Ear") 
        {
            steps 
            {
                script 
                {
                    sh "cp -R jboss-javaee-app-ear/target/jboss-javaee-app-ear.ear standalone/deployments/."
                }
            }
        }
        
        stage('Create Image Builder') 
        {
            steps 
            {
                script 
                {
                    openshift.withCluster() 
                    {
                        openshift.withProject("${params.PROJECT_NAME}") 
                        {
                            echo "Using project: ${openshift.project()}"
                            if (!openshift.selector("bc", "${artifactId}").exists()) 
                            {
                                openshift.newBuild("--name=${artifactId}", "--image-stream=openshift/jboss-eap72-openshift:latest", "--binary")
                            }
                        }
                    }
                }
            }
        }

        stage('Start Build Image') 
        {
            steps 
            {
                script 
                {
                    openshift.withCluster() 
                    {
                        openshift.withProject("${params.PROJECT_NAME}") 
                        {
                            echo "Using project: ${openshift.project()}"
                            openshift.selector("bc", "${artifactId}").startBuild("--from-dir=standalone/.", "--wait=true")
                        }
                    }
                }
            }
        }
    }  
}