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
	String nounK[] = {"���", "����", "ġ��", "ġŲ", "������", "�����", "ȣ����", "������", "�л�", "����"};
	String verbK[] = {"�Դ´�", "�����", "�Ⱦ��Ѵ�", "�Ӵ´�", "���Ѵ�", "������", "����Ѵ�", "���Ѻ���", "����ģ��", "���Ŵ�"};
	
	public String nounK(int c) {
		int i = (int)(Math.random() * 10);
		if (c == 0)	return this.nounK[i];
		else {
			//ù ���ڸ� �빮�ڷ�
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
		//��ħ ���� �Ǵ�
		char last = a.charAt(a.length()-1);
		int i = (last - 0xAC00) % 28;
		if (i > 0) return true;
		else return false;
	}
	
	public void generateK() {
		//�־� ���
		String newnoun = nounK(1);
		System.out.print(newnoun);
		if (judge(newnoun)) System.out.print("�� ");
		else System.out.print("�� ");
		
		//������ ���
		newnoun = nounK(1);
		System.out.print(newnoun);
		if (judge(newnoun)) System.out.print("�� ");
		else System.out.print("�� ");
		
		//���� ���
		System.out.println(verbK() + ".");
		 
	}
	
}
