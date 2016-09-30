package personal;
import java.lang.Math;

public class SentenceGenerator_Kor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DictK Korean = new DictK();
		for (int i=0; i<10; i++) Korean.generateK();
	}
	
}

class DictK {
	String nounK[] = {"사과", "우유", "치즈", "치킨", "강아지", "고양이", "호랑이", "선생님", "학생", "나무"};
	String verbK[] = {"먹는다", "만든다", "싫어한다", "핥는다", "원한다", "때린다", "사랑한다", "지켜본다", "가르친다", "마신다"};
	
	public String nounK(int c) {
		int i = (int)(Math.random() * 10);
		if (c == 0)	return this.nounK[i];
		else {
			//첫 문자만 대문자로
			String firstUpperK = this.nounK[i].substring(0, 1).toUpperCase();
			firstUpperK += this.nounK[i].substring(1);
			return firstUpperK;
		}
	}
	
	public String verbK() {
		int i = (int)(Math.random() * 10);
		return this.verbK[i];
	}
	
	public boolean judge(String a) {
		//받침 유무 판단
		char last = a.charAt(a.length()-1);
		int i = (last - 0xAC00) % 28;
		if (i > 0) return true;
		else return false;
	}
	
	public void generateK() {
		//주어 출력
		String newnoun = nounK(1);
		System.out.print(newnoun);
		if (judge(newnoun)) System.out.print("이 ");
		else System.out.print("가 ");
		
		//목적어 출력
		newnoun = nounK(1);
		System.out.print(newnoun);
		if (judge(newnoun)) System.out.print("을 ");
		else System.out.print("를 ");
		
		//동사 출력
		System.out.println(verbK() + ".");
		 
	}
	
}
