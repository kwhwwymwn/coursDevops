# coursDevOps Project

This project is a full-stack application built with Spring Boot for the backend and Angular for the frontend, using a MySQL database.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Docker Configuration](#docker-configuration)
   - [Backend (Spring Boot)](#backend-spring-boot)
   - [Frontend (Angular)](#frontend-angular)
   - [Docker Compose](#docker-compose)
3. [CI/CD with GitHub Actions](#cicd-with-github-actions)
4. [How to Run the Application](#how-to-run-the-application)
5. [Testing](#testing)

## Project Structure

```plaintext
coursDevOps/
├── .github/workflows/        # GitHub Actions workflow files
├── back/coursDevOps/         # Spring Boot backend application
│   ├── src/main/java/        # Java source code
│   ├── src/main/resources/   # Resources (application.properties, etc.)
│   └── pom.xml               # Maven project file
├── front/coursDevOps/        # Angular frontend application
│   ├── src/                  # Angular source code
│   ├── angular.json          # Angular CLI configuration
│   └── package.json          # npm dependencies
├── docker-compose.yml        # Docker Compose configuration
└── README.md                 # This file
```

## Docker Configuration

### Backend (Spring Boot)

```dockerfile
FROM openjdk:21
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Frontend (Angular)

```dockerfile
FROM node:alpine
WORKDIR /usr/src/app
COPY . .
RUN npm install -g @angular/cli
RUN npm install
CMD ["ng", "serve", "--host", "0.0.0.0"]
```

### Docker Compose

```yaml
services:
  mysqldb:
    image: "mysql:8.0"
    ports:
      - 3306:3306
    volumes: 
      - crazy-mysql-data:/var/lib/mysql
    environment:
      - MYSQL_ROOT_PASSWORD=root_password
      - MYSQL_DATABASE=project_db
      - MYSQL_USER=user
      - MYSQL_PASSWORD=password
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      interval: 10s
      timeout: 5s
      retries: 5

  springboot:
    depends_on:
      - mysqldb
    build: ./back/coursDevOps
    ports:
      - 8080:8080
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysqldb/project_db?serverTimezone=UTC
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root_password
      SPRING_DATASOURCE_DRIVER_CLASS_NAME: com.mysql.cj.jdbc.Driver

  angular:
    depends_on:
      - springboot
    build: ./front/coursDevOps
    ports:
      - 80:4200

volumes:
  crazy-mysql-data:
```

## CI/CD with GitHub Actions

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: maven
      - name: Build with Maven
        run: mvn -B package --file ./back/coursDevOps/pom.xml
```

## How to Run the Application

1. Clone the repository:
```bash
git clone https://github.com/kwhwwymwn/coursDevops.git
cd coursDevOps
```

2. Start the application:
```bash
docker-compose up -d
```

3. Access the application:
- Frontend: http://localhost
- Backend: http://localhost:8080

## Testing

### Backend Testing (Spring Boot)

#### Unit Tests with MockitoBean and JUnit 5

The backend uses MockitoBean for unit testing Spring Boot components, located in `src/test/java`. Here's an example of testing the CartService:

```java
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @Mock
    private CartRepository repo;

    @InjectMocks
    private CartService service;

    // Test data setup
    private static final User TEST_USER = new User(1, "login", "password");
    private static final Product TEST_PRODUCT = new Product(1, "Product 1", "Description 1", "image.jpg", 10.0f, 0.2f);
    private static final Cart TEST_CART = new Cart(1, TEST_USER, 1, 10.0f);

    @Test
    void testGetCartById_ShouldReturnCart() throws CartNotFoundException {
        // Given
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));

        // When
        Cart cart = service.getCartById(1);

        // Then
        assertNotNull(cart);
        assertEquals(TEST_CART.getId(), cart.getId());
        verify(repo, times(1)).findById(1);
    }

    @Test
    void testAddProductToCart_ShouldAddProduct() throws CartNotFoundException, ProductNotFoundException {
        // Given
        when(repo.findById(1)).thenReturn(Optional.of(TEST_CART));
        when(repo.save(any(Cart.class))).thenReturn(TEST_CART);

        // When
        service.addProductToCart(1, TEST_PRODUCT);

        // Then
        assertTrue(TEST_CART.getProducts().contains(TEST_PRODUCT));
        verify(repo).findById(1);
        verify(repo).save(any(Cart.class));
    }
}
```

Key testing concepts demonstrated:
- `@ExtendWith(MockitoExtension.class)`: Enables Mockito annotations
- `@Mock`: Creates mock objects
- `@InjectMocks`: Injects mocked dependencies
- Use of given/when/then pattern
- Exception testing
- Verification of mock interactions

### Frontend Testing (Selenium)

#### E2E Tests with Selenium WebDriver in Python

The frontend uses Selenium for end-to-end testing. Here's an example of testing the product creation functionality:

```python
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By

def test_new_product():
    # Setup
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument('--no-sandbox')
    driver = webdriver.Chrome(options=chrome_options)
    driver.get("http://localhost:4200?userType=2")
    
    wait = WebDriverWait(driver, timeout=2)

    try:
        # Test data
        name = 'test'
        price = 10
        tva = 1
        image = 'https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png'

        # Locate form elements
        nameField = wait.until(EC.presence_of_element_located((
            By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[1]')))
        priceField = wait.until(EC.presence_of_element_located((
            By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[2]')))
        tvaField = wait.until(EC.presence_of_element_located((
            By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[3]')))
        imageField = wait.until(EC.presence_of_element_located((
            By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/input[4]')))
        sendBtn = wait.until(EC.presence_of_element_located((
            By.XPATH, '/html/body/app-root/app-main-page/div/div[2]/app-new-product/div/div/form/button')))

        # Fill form
        nameField.send_keys(name)
        priceField.send_keys(price)
        tvaField.send_keys(tva)
        imageField.send_keys(image)
        sendBtn.click()

        # Verify results
        cardTitle = wait.until(EC.presence_of_element_located((
            By.XPATH, '/html/body/app-root/app-main-page/div/div[1]/app-catalogue/div[2]/app-product-card[4]/div/h2')))
        assert cardTitle.text == name

    finally:
        driver.quit()
```

Key Selenium features demonstrated:
- Headless browser configuration
- Explicit waits for element presence
- XPath element location
- Form interaction
- Assertion verification
- Proper resource cleanup

### Running Tests

1. Backend Tests:
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CartServiceTest
```

2. Frontend Tests:
```bash
# Install Selenium dependencies
pip install selenium

# Run Selenium tests
python -m pytest test_frontend.py
```
