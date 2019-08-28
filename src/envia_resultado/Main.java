package envia_resultado;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
	
	//Método principal
	public static void main(String[] args) throws MqttException {
		
		System.out.println("Programa aguardado dados para realiza calculo!");
		
		//Local para onde será enviado a mensagem (broker)
		MqttClient client=new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		/*Utilização da interface MqttCallback, que Permite que um aplicativo seja notificado 
		quando ocorrerem eventos assíncronos relacionados ao cliente*/
		client.setCallback(new VerificaStatusServidor());
		//Cria conexão com o servidor
		client.connect();
		//subscribe no tópico para receber os valores para realização da conta
		client.subscribe("enviar_conta");
	}		
	

}
