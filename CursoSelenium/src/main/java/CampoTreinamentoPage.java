import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	/********* Sets ************/
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	public void setNome(String nome){
		  dsl.escrever("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome){
		  dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino(){
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino(){
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
	}
	public void setComidaVegatariana(){
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public void setComidaPizza(){
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setEscolaridade(String escolaridade){
		dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
	}
	
	public void setEsportes(String...  esportes){
		for(String valor:  esportes)
				dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	/********* Gets ************/
	
	public void ClickCadastrar () {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto("resultado");
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	
	public String obterSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	
	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	
	public String obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}
	
	public String obterEsporteCadastro() {
		return dsl.obterTexto("descEsportes");
	}
}
