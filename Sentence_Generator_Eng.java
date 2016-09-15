package personal;
import java.io.*;
import java.lang.Math;
import java.util.Scanner;
import java.util.InputMismatchException;

//http://www.momswhothink.com/reading/list-of-nouns.html

public class SentenceGenerator_Eng_v2 {
	
	public static int randomRange(int n1, int n2) {
	    return (int)(Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	public static void main(String[] args) {		
		DictE newInst = new DictE();
		
		try {
			newInst.getBoolean();
		} catch (IOException e) {}
		
		int n = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문장 개수 입력 : ");
		
		try {
			n = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("입력 오류. 10개의 문장을 생성합니다.");
			n = 10;
		}
		
		if (n > 100 || n < 0) n = 100;

		for (int i = 0; i < n; i++) {
			System.out.print((i + 1) + ". ");
			switch (randomRange(1,2)) {
			case 1: newInst.generate(true, true); break;
			case 2: newInst.generate(false, true); break;
			//case 3: ing.pgen(); break;
			}
			System.out.println();
		}
		
		sc.close();
		
	}
	
}

class DictE {
	//딕셔너리
	String noun[] = new String[100];
	String verb[] = new String[100];
	boolean isActive[] = new boolean[100];
	boolean isFood[] = new boolean[100];
	
	//추후 파일 입출력을 이용할 예정.
	String auxverb[] = {"", "", "should", "can", "will"};
	String adverb[] = {"rapidly", "slowly", "carefully", "a lot", "", "", ""};
	String connective[] = {"And", "But", "Also,", "However,", "Even", "So,", "By the way,", ""};

	Scanner scan = new Scanner(System.in);
	
	public void setBoolean() throws IOException {
		//파일로부터 읽는 것 : noun 배열 값
		//파일로 출력하는 것 : isFood 배열 값, isActive 배열 값
		FileInputStream dictIn = new FileInputStream("E:\\JavaSpace\\Dict_noun.txt");
		FileWriter isFoodOut = new FileWriter("E:\\JavaSpace\\Dict_isFood.txt", false);
		FileWriter isActiveOut = new FileWriter("E:\\JavaSpace\\Dict_isActive.txt", false);
		
		boolean stopFlag = false;
		isFoodOut.write("");
		
		for (int i = 0; i < 100; i++) {
			String tmp = "";
			
			while (true) {
				int c = dictIn.read();
				char ch = (char)c;
				if (ch != ',' && ch != '!') tmp += ch;
				else {
					if (ch == '!') stopFlag = true;
					break;
				}
			}
			
			System.out.println("Is '" + tmp + "' food? ");
			int t = scan.nextInt();
			if (t==1) isFoodOut.append("1");
			else isFoodOut.append("0");
			
			System.out.println("Is '" + tmp + "' active? ");
			t = scan.nextInt();
			if (t==1) isActiveOut.append("1");
			else isActiveOut.append("0");
			
			if (stopFlag) break;
		}

		System.out.println("입력 완료");
		dictIn.close();
		isFoodOut.close();
		isActiveOut.close();
	}
	
	public void getBoolean() throws IOException {
		//파일로부터 읽는 것 : noun 배열 값, isFood 배열 값
		FileInputStream dictIn = new FileInputStream("E:\\JavaSpace\\Dict_noun.txt");
		FileInputStream dictIsFood = new FileInputStream("E:\\JavaSpace\\Dict_isFood.txt");
		FileInputStream dictVerbIn = new FileInputStream("E:\\JavaSpace\\Dict_verb.txt");
		
		boolean stopFlag = false;
		
		for (int i = 0; i < 100; i++) {
			String tmp = "";
			
			while (true) {
				int c = dictIn.read();
				char ch = (char)c;
				if (ch != ',' && ch != '!') tmp += ch;
				else {
					if (ch == '!') stopFlag = true;
					break;
				}
			}
			
			noun[i] = tmp;	
			if (stopFlag) break;
		}
		
		for (int i = 0; i < 100; i++) {
			int c = dictIsFood.read();
			
			if (c != -1) {
				switch (c) {
				case '1': isFood[i] = true; break;
				default: isFood[i] = false; break;
				}
			} else break;
		}
		
		stopFlag = false;
		
		for (int i = 0; i < 100; i++) {
			String tmp = "";
			
			while (true) {
				int c = dictVerbIn.read();
				char ch = (char)c;
				if (ch != ',' && ch != '!') tmp += ch;
				else {
					if (ch == '!') stopFlag = true;
					break;
				}
			}
			
			verb[i] = tmp;	
			if (stopFlag) break;
		}
		
		dictIn.close();
		dictIsFood.close();
		dictVerbIn.close();
	}
	
	public void filetest() throws IOException {
		//noun 배열과 isFood 배열 값 출력
		for (int i = 0; i < 100; i++) {
			System.out.println(noun[i] + " = " + isFood[i]);
		}
	}
	
	public static int randomRange(int n1, int n2) {
		//랜덤 수 반환
	    return (int)(Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	public String capital(String s) {
		//문자열 첫 글자만 대문자로
		String firstUpperE = s.substring(0, 1).toUpperCase();
		firstUpperE += s.substring(1);
		return firstUpperE;
	}
	
	public String uncapital(String s) {
		//문자열 첫 글자만 소문자로
		String firstLowerE = s.substring(0, 1).toLowerCase();
		firstLowerE += s.substring(1);
		return firstLowerE;
	}
	
	public String noun(boolean Capital, boolean Active, boolean PersonFine) {
		//명사
		int i = 0;
		
		if (Active) {
			if (PersonFine) i = randomRange(4, 8);
			else i = randomRange(4, 6);
		} else {
			if (PersonFine) i = randomRange(0, 9);
			else i = randomRange(0, 6);
		}
		
		String chosen = this.noun[i];
		if (Capital) return capital(chosen);
		else return chosen;
	}
	
	public String verb(int c) {
		//동사
		int i;
		if (isFood[c]) i = randomRange(0,8);
		else i = randomRange(0,9);
		return verb[i];
	}
	
	public String auxverb() {
		//조동사
		return auxverb[randomRange(0, auxverb.length-1)];
	}
	
	public String adverb() {
		//부사
		return adverb[randomRange(0, adverb.length-1)];
	}
	
	public String article(String original, boolean Countable) {
		//관사
		String newString = "";
		
		if (Countable) {
			switch (randomRange(0,1)) {
			case 0: 
				if (original.charAt(0) == 'a') newString += "An "; //부정관사
				else newString += "A ";
				break;
			case 1:
				newString += "The "; //정관사
				break;
			}		
			newString += uncapital(original);
		} else newString = original;
		
		return newString;
	}
	
	public void generate(boolean genQuestion, boolean subjectCapital) {
		//문장 생성
		String newSubject = noun(true, true, true);
		newSubject = article(newSubject, true);
		if (!subjectCapital) newSubject = uncapital(newSubject);
		
		String newObject = noun(false, false, false);
		newObject = article(newObject, true);
		newObject = uncapital(newObject);
		
		int newObjectIndex = 0;
		
		for (int i=0; i<10; i++) {
			if (newObject.equals(noun[i])) {
				newObjectIndex = i;
				break;
			}
		}
		
		String newAuxVerb = auxverb();
		String newVerb = verb(newObjectIndex);
		String newAdverb = adverb();
		
		if (genQuestion) {
			if (newAuxVerb == "") System.out.print("Does ");
			else System.out.print(capital(newAuxVerb) + " ");
			System.out.print(uncapital(newSubject) + " " + newVerb + " " + newObject + "?");
		}
		else {
			if (subjectCapital) System.out.print(newSubject);
			else System.out.print(uncapital(newSubject));
			System.out.print(" " + newAuxVerb);
			
			if (newAuxVerb.equals("")) {
				System.out.print(newVerb);
				if (newVerb.substring(newVerb.length()-2).equals("ch")) System.out.print("es ");
				else System.out.print("s ");
			} else System.out.print(" " + newVerb + " ");
			
			if (newVerb.equals("want")) {
				newObject = noun(false, false, false);
				newObjectIndex = 0;
				
				for (int i=0; i<10; i++) {
					if (newObject.equals(noun[i])) {
						newObjectIndex = i;
						break;
					}
				}
				
				newVerb = verb(newObjectIndex);
				System.out.print("to " + newVerb + " ");
			} 
			
			System.out.print(newObject);
			if (newAdverb.equals("")) System.out.print(".");
			else System.out.print(" " + newAdverb + ".");
		}
	}
	
	public void connective() {
		//접속사
		System.out.println(" " + connective[randomRange(0,7)] + " ");
	}
	
	public void doublegen(){
		//접속사로 연결된 문장 생성
		generate(false, true);
		connective();
		generate(false, false);
	}
	
	public void pgen() {
		//문단 생성
		for (int i=0; i<randomRange(1,5); i++) {
			if (i==0) {
				generate(false, true);
				connective();
				generate(false, false);
			} else {
				connective();
				generate(false, false);
			}
		}
	}
	
}
