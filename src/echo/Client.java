package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		Socket socket = new Socket();

		System.out.println("<클라이언트 시작>");
		System.out.println("============================================");

		System.out.println("[서버에 연결을 요청합니다.]");
		socket.connect(new InetSocketAddress("192.168.0.32", 10001));

		System.out.println("[서버에 연결 되었습니다.]");

		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");;
		BufferedReader br = new BufferedReader(isr);
		
		//스캐너준비
		Scanner sc = new Scanner(System.in);
		/*
		InputStream sc = System.in;
		InputStreamReader scIsr = new InputStreamReader(sc);
		BufferedReader scBr = new BufferedReader(scIsr);
		*/
		while(true) {
			// 키보드 입력
			String str = sc.nextLine();
			//String str = scBr.readLine();
			
			if("/q".equals(str)) {
				break;
			}
			
			// 메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush();
			
			// 메세지 받기용
			String reMsg = br.readLine();
			System.out.println("server:[" + reMsg + "]");
			
		}
		
		System.out.println("===================================");
		System.out.println("<클라이언트 종료>");
		
		
		// 메세지 보내기용 스트림

		//////////////////////////////////////////
		//println만들기
		OutputStream pos = System.out;
		OutputStreamWriter posw = new OutputStreamWriter(pos);
		BufferedWriter pbw = new BufferedWriter(posw);
		pbw.write("println 테스트");
		pbw.newLine();
		pbw.flush();
		
		//닫기
		sc.close();
		br.close();
		bw.close();
		socket.close();

	}

}