import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class RegrasCadastro{

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Parameter(0)
	public String nome;
	@Parameter(1)
	public String sobrenome;
	@Parameter(2)
	public String sexo;
	@Parameter(3)
	public List<String> comidas;
	@Parameter(4)
	public String[] esportes;
	@Parameter(5)
	public String msg;
	
	@Before
	public void inicializa(){
		 //System.setProperty("webdriver.gecko.driver","C:\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]>  getColleticon(){
		return Arrays.asList(new Object[][] {
			{"","","",Arrays.asList(), new String[] {},"Nome eh obrigatorio"},
			{"Ingrid Vanzeli","","",Arrays.asList(), new String[] {},"Sobrenome eh obrigatorio"},
			{"Ingrid Christine","Vanzeli Teixeira","",Arrays.asList(), new String[] {},"Sexo eh obrigatorio"},
			{"Ingrid Christine","Vanzeli Teixeira","Masculino",Arrays.asList("Carne","Vegetariano"), new String[] {},"Tem certeza que voce eh vegetariano?"},
			{"Ingrid Christine","Vanzeli Teixeira","Masculino",Arrays.asList("Carne"), new String[] {"O que eh esporte?","Karate"},"Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidarRegras(){
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")){
				page.setSexoMasculino();
		}
		
		if(sexo.equals("Feminino")){
					page.setSexoFeminino();
		}
		
		if(comidas.contains("Carne")) 
		      page.setComidaCarne();
		
		if(comidas.contains("Pizza")) 
		      page.setComidaPizza();
		
		if(comidas.contains("Vegetariano"))
			page.setComidaVegatariana();
		
		page.setEsportes(esportes);
		
		page.ClickCadastrar();
		
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	}
}
