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

    /* -------------------------------
     * Given de los diferentes inicios
     * -------------------------------
     */
    @Given("un usuario esta en la pagina de inicio")
    public void openHome() {
        driver.get("http://localhost:" + port + "/");

    }

    @Given("un usuario esta en la pagina de usuarios")
    public void openUser() {
        driver.get("http://localhost:" + port + "/users");

    }

    @Given("un usuario esta en la pagina de sorteos")
    public void openDraws() {
        driver.get("http://localhost:" + port + "/draws");

    }

    @Given("un usuario esta en el formulario de usuarios")
    public void openUsersForm() {
        driver.get("http://localhost:" + port + "/newUser");

    }

    @Given("un usuario esta en el formulario de sorteos")
    public void openDrawsForm() {
        driver.get("http://localhost:" + port + "/newDraw");

    }
    // Fin Given

    /* ------------------------------
     * Test de verificación de Vistas
     * ------------------------------
     */
    // Indice
    @Then("se muestra el titulo la pagina de inicio")
    public void showIndex() {
        WebElement actualHeading = driver.findElement(By.id("home-title"));
        assertEquals("Bienvenidos al CPIFP Los Camaleones", actualHeading);
    }

    @Then("se muestra el titulo la pagina de usuarios")
    public void showUserTitle() {
        WebElement actualHeading = driver.findElement(By.id("users-title"));
        assertEquals("Usuarios", actualHeading);
    }

    // Usuario
    @Then("se muestra el boton de crear usuario")
    public void showUserCreateBtn() {
        WebElement createBtn = driver.findElement(By.id("users-button-create"));
        assertEquals("Crear nuevo usuario", createBtn);
    }

    @Then("se muestra el titulo la pagina de sorteos")
    public void showDrawTitle() {
        WebElement actualHeading = driver.findElement(By.id("draws-title"));
        assertEquals("Sorteos", actualHeading);
    }

    // Sorteo
    @Then("se muestra el boton de crear sorteo")
    public void showDrawCreateBtn() {
        WebElement createBtn = driver.findElement(By.id("draws-button-create"));
        assertEquals("Crear sorteo", createBtn);
    }

    // Formulario Usuario
    @Then("se muestra el titulo de formulario de usuarios")
    public void showUserFormTitle() {
        WebElement actualHeading = driver.findElement(By.id("user-form-title"));
        assertEquals("Crear nuevo usuario", actualHeading);
    }

    @Then("se muestra el boton de usuario submit")
    public void showUserSubmitBtn() {
        WebElement createBtn = driver.findElement(By.id("user-button-submit"));
        assertEquals("Crear", createBtn);
    }

    // Formulario Sorteo
    @Then("se muestra el titulo de formulario de sorteos")
    public void showDrawFormTitle() {
        WebElement actualHeading = driver.findElement(By.id("draw-form-title"));
        assertEquals("Crear nuevo sorteo", actualHeading);
    }

    @Then("se muestra el boton de sorteo submit")
    public void showDrawSubmitBtn() {
        WebElement createBtn = driver.findElement(By.id("draw-button-submit"));
        assertEquals("Crear", createBtn);
    }
    // Fin Test Vistas


    /* ------------------
     * Test de navegación
     * ------------------
     */
    // Navegación a inicio
    @When("el usuario hace click sobre el botón de Inicio")
    public void clickIndexBtn(){
        driver.findElement(By.id("to-home-link")).click();

    }

    @Then("se muestra la pagina de inicio")
    public void navigateToIndex(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/ "));
    }

    // Navegación a usuarios
    @When("el usuario hace click sobre el botón de Usuarios")
    public void clickUserButton(){
        driver.findElement(By.id("to-users-link")).click();

    }

    @Then("se muestran todos los usuarios del sistema")
    public void navigateToUsersList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/users"));
    }

    // Navegación a sorteos
    @When("el usuario hace click sobre el botón de Sorteos")
    public void clickDrawButton(){
        driver.findElement(By.id("to-draws-link")).click();

    }

    @Then("se muestran todos los sorteos del sistema")
    public void navigateToDrawList(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/draws"));
    }

    // Navegación a Formulario Usuario
    @When("el usuario hace click sobre el botón de crear Usuarios")
    public void clickUserFormButton(){
        driver.findElement(By.id("users-button-create")).click();

    }

    @Then("se muestra el formulario de creacion de Usuarios")
    public void navigateToUsersForm(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/newUser"));
    }

    // Navegación a Formulario Sorteo
    @When("el usuario hace click sobre el botón de crear Sorteos")
    public void clickDrawFormButton(){
        driver.findElement(By.id("draws-button-create")).click();

    }

    @Then("se muestra el formulario de creacion de Sorteos")
    public void navigateToDrawForm(){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/newDraw"));
    }

    // Fin Test Navegación


    @Given("un administrador esta en el formulario de creación")
    public void openUserCreateForm()
    {
        driver.get("http://localhost:" + port + "/createUser");
    }

}
