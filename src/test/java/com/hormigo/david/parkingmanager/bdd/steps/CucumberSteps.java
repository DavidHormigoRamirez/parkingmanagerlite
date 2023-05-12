package com.hormigo.david.parkingmanager.bdd.steps;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.bdd.CucumberConfiguration;
import com.hormigo.david.parkingmanager.user.service.UserService;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
public class CucumberSteps extends CucumberConfiguration {

    @MockBean
    private UserService userService;
    @Value("${local.server.port}")
    private  int port;
    private static ChromeDriver driver;
    @BeforeAll
    public static void prepareWebDriver() {

        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        
    }

    
    @Given("un usuario esta en la pagina inicial")
    public void openHome() {
        driver.get("http://localhost:" + port + "/");

    }

    @Then("se muestra la pagina inicial")
    public void showIndex() {
        WebElement actualHeading = driver.findElement(By.id("home-title"));
        assertEquals("Bienvenidos al CPIFP Los Camaleones", actualHeading);
    }

    @Given("un usuario esta en la pagina de usuarios")
    public void openUser() {
        driver.get("http://localhost:" + port + "/users");

    }

    @Then("se muestra la lista de usuarios")
    public void showUserList() {
        WebElement actualHeading = driver.findElement(By.id("users-title"));
        assertEquals("Usuarios", actualHeading);
    }

    @Given("un usuario esta en la pagina de sorteos")
    public void openDraws() {
        driver.get("http://localhost:" + port + "/draws");

    }

    @Then("se muestra la lista de sorteos")
    public void showDrawList() {
        WebElement actualHeading = driver.findElement(By.id("draws-title"));
        assertEquals("Sorteos", actualHeading);
    }

    @When("el usuario hace click sobre el botón de Usuarios")
    public void clickUserButton(){
        driver.findElement(By.id("to-users-link")).click();

    }

    @Then("se muestran todos los usuarios del sistema")
    public void navigateToUsersList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/users"));
    }

    @When("el usuario hace click sobre el botón de Sorteos")
    public void clickDrawButton(){
        driver.findElement(By.id("to-draws-link")).click();

    }

    @Then("se muestran todos los sorteos del sistema")
    public void navigateToDrawList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/draws"));
    }

    @Given("un administrador esta en el formulario de creación")
    public void openUserCreateForm()
    {
        driver.get("http://localhost:" + port + "/createUser");
    }

}
