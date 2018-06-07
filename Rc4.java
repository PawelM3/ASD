package s14926PMRC4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Rc4 {
	static List<LinkedList<Character>> encodedTable = new ArrayList<LinkedList<Character>>(256); // FIFO list
	static int encodingTable[] = new int[256];

	static void swap(int i, int j) { // helping function for RC4
		int temp = encodingTable[i];
		encodingTable[i] = encodingTable[j];
		encodingTable[j] = temp;
	}
	
	static void rc4KeyTable(String passcode) {
		byte[] key = passcode.getBytes();
		int j = 0;
		for (int i = 0; i < 256; i++) {
			encodingTable[i] = i;
		}
		for (int i = 0; i < 256; i++) {
			j = (j + encodingTable[i] + (key[i % key.length]) & 0xFF) % encodingTable.length;
			swap(i, j);
		}

	}

	static int rc4(byte[] number) { // rc4 implementation after Wikipedia
		int code = 0;
		for (int n = 0; n < number.length; n++) {
			int i = 0;
			int j = 0;
			i = (i + 1) % 256;
			j = (j + encodingTable[i]) % 256;
			swap(i, j);
			code = encodingTable[(encodingTable[i] + encodingTable[j]) % 256];
		}
		return code;
	}

	public static String input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("You need to enter valid RPN expression");
			e.printStackTrace();
		} // read the input
		return input;
	}

	static void encode(String message) {
		int position = 0;
		for (int i = 0; i < message.length(); i++) {
			position = i + rc4(Integer.toString(i).getBytes());
			if (position>255) position=position&255;
			encodedTable.get(position).add(message.charAt(i));
		}
	}

	static String decode() {
		StringBuffer answer = new StringBuffer();
		int sizeOfMessage = 0;
		int position = 0;
		for (int i = 0; i < 256; i++) {
			sizeOfMessage += encodedTable.get(i).size();
		}
		for (int i = 0; i < sizeOfMessage; i++) {
			position = i + rc4(Integer.toString(i).getBytes());
			if (position>255) position=position&255;
			answer.append(encodedTable.get(position).removeFirst());
		}
		return answer.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 256; i++) {
			encodedTable.add(i, new LinkedList<Character>());
		}
		System.out.println("Enter the initialization vector:");
		String encodingVector = input();
		System.out.println("Encoding vector = " + encodingVector);
		rc4KeyTable(encodingVector);
		System.out.println("Enter the phrase to be encoded by Alice:");
		String phrase = input();
		System.out.println("Phrase to be encoded: " + phrase);
		encode(phrase);
		System.out.println("Bob receives the encoded table and the following passphrase: " + encodingVector);
		System.out.println("Bob prepares his own RC4 table using given passphrase");
		rc4KeyTable(encodingVector);
		System.out.println("After decryption: ");
		System.out.println(decode());
	}
}
