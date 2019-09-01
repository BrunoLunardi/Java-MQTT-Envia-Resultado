package envia_resultado;

public class ConfigServer {
	String enderecoServidor = "tcp://localhost:1883";
	//recupera endereco ip
	public String getEnderecoServidor() {
		return enderecoServidor;
	}
	//altera endereco ip
	public void setEnderecoServidor(String enderecoServidor) {
		this.enderecoServidor = enderecoServidor;
	}
}
