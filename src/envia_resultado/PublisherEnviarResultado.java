package envia_resultado;

//Imports para utilizar o protocolo MQTT
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PublisherEnviarResultado {
	
	//String que enviará a conta para o outro sistema
    String mensagemEnviaResultado;
    //construtor da classe para enviar a mensagem
	public PublisherEnviarResultado(String mensagemEnviaResultado){
		this.mensagemEnviaResultado = mensagemEnviaResultado;
	}

	//Método para enviar a mensagem para o mosquitto
	public void enviarMensagemResultado() throws MqttException {
		//Local para onde será enviado a mensagem (broker)
		MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		//abre conexão com o broker (Mosquitto)
		client.connect();
		//objeto de envio de mensagem do broker
		MqttMessage menssagem = new MqttMessage();
			//Payload, conteudo da mensagem montagem da mensagem que será enviada ao broker
		menssagem.setPayload(mensagemEnviaResultado.getBytes());
		//QoS - A mensagem é sempre entregue exatamente uma vez.
		menssagem.setQos(2);
		//Mensagem ficará retida no Broker (ultima mensagem fica retida)
		menssagem.setRetained(true);
	    //Publica a mensagem, com seu tópico (para alguém se escrever neste tópico) e a mensagem montada
	    client.publish("enviar_resultado", menssagem);
	    //fecha conexão com broker mosquitto
	    client.disconnect();
	}		
	
	
}
