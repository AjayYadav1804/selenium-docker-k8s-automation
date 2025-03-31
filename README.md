# Docker Selenium Grid Kubernetes

A comprehensive web automation framework that combines Docker, Selenium Grid 4, and Kubernetes to execute test scripts across multiple browsers in parallel.

## 📖 Project Overview

This project demonstrates the setup and implementation of Selenium Grid using Docker containers orchestrated in a Kubernetes cluster. It allows for efficient parallel execution of automated test scripts across different browsers (Chrome, Firefox, Edge) to verify the functionality of web applications.

## 🧪 Test Scenarios Automated

- **Test 1**: Launch Google Chrome and get the title of the page
- **Test 2**: Launch LinkedIn and verify the title of the page
- **Test 3**: Launch Gmail and get the title of the page
- **Test 4**: Launch Facebook and get the title of the page

## 🛠️ Tech Stack

- **Java**: Programming language for test script development
- **Selenium WebDriver**: Browser automation framework
- **TestNG**: Testing framework for test execution and reporting
- **Docker**: Container platform for Selenium Grid components
- **Kubernetes**: Container orchestration for managing Docker containers
- **Selenium Grid 4**: Distributed test execution infrastructure
- **Maven**: Dependency management and project build

## 📁 Project Structure

```
Docker_SeleniumGridKubernetes/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   ├── test/
│   │   ├── java/
│   │   │   └── tests/
│   │   │       └── DockerSeleniumGridKubernetesTest.java
│   │   ├── resources/
├── reports/
│   └── testReport.html
├── test-output/
├── pom.xml
├── testing.xml
├── kubernetes/
│   ├── selenium-hub-deployment.yaml
│   ├── selenium-hub-service.yaml
│   ├── selenium-node-chrome-deployment.yaml
│   ├── selenium-node-firefox-deployment.yaml
│   ├── selenium-node-edge-deployment.yaml
├── docker-compose.yml
├── Dockerfile
└── README.md
```

## 🔧 Setup and Execution

### Prerequisites

- [Java JDK 17](https://www.oracle.com/java/technologies/downloads/)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Kubernetes](https://kubernetes.io/docs/setup/) (Minikube for local setup)
- [Maven](https://maven.apache.org/download.cgi)

### Setup Steps

1. **Clone the repository**

```bash
git clone https://github.com/yourusername/Docker_SeleniumGridKubernetes.git
cd Docker_SeleniumGridKubernetes
```

2. **Start Minikube** (if using local Kubernetes)

```bash
minikube start
```

3. **Deploy Selenium Grid on Kubernetes**

```bash
kubectl apply -f kubernetes/selenium-hub-deployment.yaml
kubectl apply -f kubernetes/selenium-hub-service.yaml
kubectl apply -f kubernetes/selenium-node-chrome-deployment.yaml
kubectl apply -f kubernetes/selenium-node-firefox-deployment.yaml
kubectl apply -f kubernetes/selenium-node-edge-deployment.yaml
```

4. **Verify deployments**

```bash
kubectl get deployments
kubectl get pods
kubectl get services
```

5. **Forward Selenium Hub port** (if needed for local access)

```bash
kubectl port-forward service/selenium-hub 4444:4444
```

### Running Tests

**Using Maven:**

```bash
mvn clean test -DsuiteXmlFile=testing.xml
```

**Using TestNG in IDE:**
- Right-click on `testing.xml`
- Select "Run As" > "TestNG Suite"

## 📊 Test Report

Test execution reports are generated in:
- `test-output/` directory (TestNG default reports)
- `reports/testReport.html` (Custom HTML report)


## 🖼️ Screenshots

### Project Structure in IDE
![Project Structure](project_structure.png)

### Docker Images
![Docker Images](docker_images.png)

### Selenium Grid Dashboard
![Selenium Grid Dashboard](selenium_grid.png)

### Kubernetes Dashboard
![Kubernetes Dashboard](kubernetes_dashboard.png)

### Test Execution Report
![Test Execution Report](test_report.png)


## 🔄 CI/CD Integration

This project can be integrated with CI/CD pipelines (e.g., Jenkins, GitHub Actions, GitLab CI) for automated test execution:

```yaml
# Sample GitHub Actions workflow
name: Selenium Grid Tests

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'adopt'
    - name: Start Minikube
      uses: medyagh/setup-minikube@master
    - name: Deploy Selenium Grid
      run: |
        kubectl apply -f kubernetes/selenium-hub-deployment.yaml
        kubectl apply -f kubernetes/selenium-hub-service.yaml
        kubectl apply -f kubernetes/selenium-node-chrome-deployment.yaml
        kubectl apply -f kubernetes/selenium-node-firefox-deployment.yaml
        kubectl apply -f kubernetes/selenium-node-edge-deployment.yaml
        sleep 30
    - name: Run Tests
      run: mvn clean test -DsuiteXmlFile=testing.xml
    - name: Archive test results
      uses: actions/upload-artifact@v2
      with:
        name: test-reports
        path: |
          test-output/
          reports/
```

## 🚀 Benefits of This Architecture

- **Scalability**: Easily scale up test execution by adding more browser nodes
- **Isolated Testing**: Each test runs in its own container
- **Cross-browser Testing**: Run tests on multiple browsers in parallel
- **Kubernetes Management**: Leverage Kubernetes for efficient resource allocation and fault tolerance
- **Reduced Execution Time**: Parallel test execution reduces overall test runtime

## 📝 Notes

- Ensure Docker and Kubernetes are properly configured on your machine
- For Windows users, Docker Desktop includes Kubernetes support
- Adjust resource allocations in Kubernetes configurations based on your machine capabilities

