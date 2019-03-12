/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tfttp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JFileChooser;

/**
 *
 * @author ovall
 */
public class Cliente {
   	/*
	 * TFTP  RFC 1350 
         * opcode - operation
         * 1 - Read request (RRQ) 
	 * 2 - Write request (WRQ) 
         * 3 - Data (DATA) 
         * 4 - Acknowledgment (ACK) 
         * 5 - Error (ERROR)
	 */

	private static final String TFTP_SERVER_IP = "192.168.0.18";
	private static final int TFTP_DEFAULT_PORT = 69;

	// TFTP opcode
	private static final byte OP_RRQ = 1;
        private static final byte OP_WRQ = 2;
	private static final byte OP_DATAPACKET = 3;
	private static final byte OP_ACK = 4;
	private static final byte OP_ERROR = 5;
        // el paquete de datos tiene: 2 byte para opcode, 2 bytes para num_paq y 512 bytes para los datos
	private final static int PACKET_SIZE = 516;

	private DatagramSocket datagramSocket = null;
	private InetAddress inetAddress = null;
	private byte[] rrqBuffer;
	private byte[] buffer;
	private DatagramPacket salida;
	private DatagramPacket entrada;

	
        //la mayoria de servidores tftp usan este mÃ©todo para indicar la  obtencion de archivos
        // tftp IP
        // tftp> get archivo.pdf
        // tftp> put otroarchivo.pdf   --- para subir un archivo
	private void get(String archivo) throws IOException {
		// Paso0: prepara para la comunicacion
		inetAddress = InetAddress.getByName(TFTP_SERVER_IP);
		datagramSocket = new DatagramSocket();
		rrqBuffer = crearRequest(OP_RRQ, archivo, "octet");
		salida = new DatagramPacket(rrqBuffer, rrqBuffer.length, inetAddress, TFTP_DEFAULT_PORT);

		// Paso 1: enviar peticion RRQ al servidor TFTP para bajar un archivo
		datagramSocket.send(salida);

		// Paso 2: recibir archivo del servidor TFTP 
		ByteArrayOutputStream byteOutOS = recibeArchivo();

		// Paso 3: escribir el archivo en la ubicacion local
		writeFile(byteOutOS, archivo);
	}
        
        private void put (String archivo) throws IOException {
            inetAddress = InetAddress.getByName(TFTP_SERVER_IP);
            datagramSocket = new DatagramSocket();
            rrqBuffer = crearRequest( OP_WRQ, archivo, "octet");
            entrada = new DatagramPacket(rrqBuffer, rrqBuffer.length, inetAddress, TFTP_DEFAULT_PORT);
            datagramSocket.send(entrada);
            ByteArrayOutputStream byteOutOS = recibeArchivo();
            writeFile(byteOutOS, archivo);
	}

        // recibir el archivo, paquete por paquete
	private ByteArrayOutputStream recibeArchivo() throws IOException {
		ByteArrayOutputStream byteOutOS = new ByteArrayOutputStream();
		int block = 1;
		do {
			System.out.println("Contador de paquete TFTP: " + block);
			block++;
			buffer = new byte[PACKET_SIZE];
			entrada = new DatagramPacket(buffer,
					buffer.length, inetAddress,
					datagramSocket.getLocalPort());
			
			//Paso 2.1: recibe paquete del servidor TFTP 
			datagramSocket.receive(entrada);

			// Obtener los primeros 4 caracteres  del paquete tftp para conocer la opc-code
			byte[] opCode = { buffer[0], buffer[1] };

			if (opCode[1] == OP_ERROR) {
				reportarError();
			} else if (opCode[1] == OP_DATAPACKET) {
				// Verificar el nÃºmero de bloque del paquete
				byte[] blockNumber = { buffer[2], buffer[3] };

				DataOutputStream dos = new DataOutputStream(byteOutOS);
				dos.write(entrada.getData(), 4,
						entrada.getLength() - 4);

				//Paso 2.2: mandar ACK al servidor TFTP 
				enviarACK(blockNumber);
			}

		} while (!isLastPacket(entrada));
		return byteOutOS;
	}

	private void enviarACK(byte[] blockNumber) {
                // crear paquete ACK
		byte[] ACK = { 0, OP_ACK, blockNumber[0], blockNumber[1] };
		DatagramPacket ack = new DatagramPacket(ACK, ACK.length, inetAddress,
				entrada.getPort());
		try {
			datagramSocket.send(ack);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void reportarError() {
		String errorCode = new String(buffer, 3, 1);
		String errorText = new String(buffer, 4,
				entrada.getLength() - 4);
		System.err.println("Error: " + errorCode + " " + errorText);
	}

        // escribe el contenido del servidor en el archivo local
	private void writeFile(ByteArrayOutputStream baoStream, String fileName) {
		try {
			OutputStream outputStream = new FileOutputStream(fileName);
			baoStream.writeTo(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Los paquete de datos de TFTP son de maximo 512 bytes por lo tanto el Ãºltimo
	 * debe ser menor que 512 bytes
	 */
	private boolean isLastPacket(DatagramPacket datagramPacket) {
		if (datagramPacket.getLength() < 512)
			return true;
		else
			return false;
	}

	/*
	 * RRQ / WRQ packet format
	 * 
	 * 2 bytes - Opcode; string - filename; 1 byte - 0; string - mode; 1 byte - 0;
	 */
	private byte[] crearRequest( byte opCode,  String archivo, String mode) {
		// se define el tamaño del paquete
		int rrqByteLength = 2 + archivo.length() + 1 + mode.length() + 1;
		byte[] rrqBuffer = new byte[rrqByteLength];

		int position = 0;
		rrqBuffer[position] = 0;
		position++;
		rrqBuffer[position] = opCode;
		position++;
		for (int i = 0; i < archivo.length(); i++) {
			rrqBuffer[position] = (byte) archivo.charAt(i);
			position++;
		}
		rrqBuffer[position] = 0;
		position++;
		for (int i = 0; i < mode.length(); i++) {
			rrqBuffer[position] = (byte) mode.charAt(i);
			position++;
		}
		rrqBuffer[position] = 0;
		return rrqBuffer;
	}
        
        public File openFile(){
            JFileChooser selector = new JFileChooser();
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = selector.showOpenDialog(null);
            if (res == 1 )
                return null;
            File archivo = selector.getSelectedFile();
            return selector.getSelectedFile();
        }
        
        public static void main(String[] args) throws IOException {
		String fileName = "casaMickey2.jpg";
		Cliente tFTPClient = new Cliente();
		//tFTPClient.get(fileName);
                tFTPClient.put(fileName);
	}
}
