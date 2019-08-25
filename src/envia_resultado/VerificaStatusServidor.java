package envia_resultado;

import java.util.Arrays;

import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class VerificaStatusServidor implements MqttCallback {
	
	//método chamado quando cai conexão com o serviodr	
	public void connectionLost(Throwable throwable) {
		System.out.println("Sem conexão com o sevidor!");
	}
	//método invocado quando chega uma mensagem do broker
	public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
		//recebe a mensagem do broker
		String mensagem = new String(mqttMessage.getPayload());
		//divide a mensagem em um array
		String[] textoSeparado = mensagem.split(" ");
		//indice zero do array é o primeiro valor
		Double x = Double.parseDouble(textoSeparado[0]);
		//indice dois do array é o segundo valor
		Double y = Double.parseDouble(textoSeparado[2]);
		//indice um do array é o primeiro valor
		String op = textoSeparado[1];
		//variável que exibirá a mensagem de resultado
		String mensagemResultado = "";
		//resultado da conta
		Double result = null;
		//flag para indicar se tentou dividir com zero (para formatar a mesangem corretamente)
		int flag = 0;
		
		//Verifica qual o tipo da conta a ser realizada
		switch(op) {
			case "0":
				result = x + y;
			break;
			case "1":
				result = x - y;
			break;
			case "2":
				if(y == 0) {
					mensagemResultado = "Não existe divisão por zero!";
					flag = 1;
					break;
				}else {
					result = x / y;
				}
			break;
			case "3":
				result = x * y;
			break;			
		}
		
		if(flag == 0) {
			mensagemResultado = String.format("%.2f", result);
		}
		//exibe resultado na tela
		JOptionPane.showMessageDialog(null, mensagemResultado ,"Resultado",
				JOptionPane.INFORMATION_MESSAGE);		
		
	}
	//Chamado quando a entrega de uma mensagem foi concluída e todas as confirmações foram recebidas
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
	}		

}
