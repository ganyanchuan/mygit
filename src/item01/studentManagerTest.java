package item01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*	ͨ��IO������ѧ����Ϣ�Ķ�ȡ��д�벢�洢
 * 	�м���Ҫ�ü��϶�����д洢		
 * 	ͨ������¼��ѧ����Ϣ
 */
public class studentManagerTest {

	public static void main(String[] args) throws IOException {
		// ��ʼ�Ȱ�������д��
		
		String file = "student.txt";
		while(true) {
				System.out.println("-----��ӭ����ѧ������ϵͳ-----");
				System.out.println("1�鿴����ѧ����Ϣ");
				System.out.println("2���ѧ����Ϣ");
				System.out.println("3ɾ��ѧ����Ϣ");
				System.out.println("4�޸�ѧ����Ϣ");
				System.out.println("5�˳�");
				System.out.println("���������ѡ��");
				Scanner sc = new Scanner(System.in);
				String choice =  sc.nextLine();
				switch (choice) {
				case "1":
					showStudent(file);
					break;
				case "2":
					addStudent(file);
					break;
				case "3":
					delStudent(file);
					break;
				case "4":
					updateStudent(file);
					break;
				case "5":
				default:
					System.out.println("лл���ʹ�ã�");
					System.exit(0);
					break;
				}
		}

	}
	
	//�ӻ���������������ݵ�ѧ������
	public static void fileRead(String file,ArrayList<student> array) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line;
		while((line = br.readLine())!= null) {
			student s = new student();
			String[] datas = line.split(",");//��ÿһ�а��ն��ŷָ���ÿ���ַ��������ַ�������
			s.setId(datas[0]);//�ٽ��ַ�������İ���˳��ÿ��Ԫ�ش洢��ѧ������ĳ�Ա��
			s.setName(datas[1]);
			s.setAge(datas[2]);
			s.setAddress(datas[3]);
			array.add(s);//��ѧ������д�뵽��������
			
		}
		br.close();
		
	}
	
	//��ѧ�������н�����д�뵽����������
	public static void fileWrite(String file,ArrayList<student> array) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		student s = new student();
		
		for (int x=0;x<array.size();x++) {
			s = array.get(x);
			StringBuilder sb = new StringBuilder();
			sb.append(s.id).append(",").append(s.name).append(",").append(s.age).append(",").append(s.address);
			bw.write(sb.toString());
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		
	}
	
	public static void showStudent(String file) throws IOException {
		ArrayList<student> arry = new ArrayList<student>();
		fileRead(file,arry);
		
		if(arry.size() == 0) {
			System.out.println("û��ѧ����Ϣ����������ѧ����Ϣ");
			return;
		}
			
		System.out.println("ѧ��\t\t����\t����\t��ס��");
			for(int i=0;i<arry.size();i++) {
				student s = new student();
				s = arry.get(i);
				System.out.println((s.getId())+"\t"+(s.getName())+"\t"+(s.getAge())+"\t"+(s.getAddress()));
				
		}	
		
	}
	
	
	public static void addStudent(String file) throws IOException {
		
		ArrayList<student> arry = new ArrayList<student>();
		fileRead(file,arry);
		String id;
		Scanner sc = new Scanner(System.in);
		while(true) {
				System.out.println("������ѧ�ţ�");				
				id = sc.nextLine();
				boolean flag = false;
				for(int x=0;x<arry.size();x++) {
					student s = new student();
					s = arry.get(x);
					if(s.getId().equals(id)){	
						flag = true;
						break;
					}
				}
					
				if(flag) {
					System.out.println("�����ѧ���Ѿ����ڣ�����������ѧ��");
				}else {
					break;
				}
				
		}
		
		System.out.println("������������");
		String name = sc.nextLine();
		System.out.println("���������䣺");
		String age =  sc.nextLine();
		System.out.println("�������ס�أ�");
		String address = sc.nextLine();
		student s = new student();
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(address);
		arry.add(s);
		System.out.println("��ӳɹ�");
		fileWrite(file,arry);
				
	}
	
	public static void delStudent(String file) throws IOException {
		
		ArrayList<student> arry = new ArrayList<student>();
		fileRead(file,arry);
		
		int index = -1;
		String id;
		Scanner sc = new Scanner(System.in);
		System.out.println("��������Ҫɾ����ѧ����ѧ�ţ�");
		id = sc.nextLine();
		
		for(int x=0;x<arry.size();x++) {
			student s = new student();
			s = arry.get(x);	
			if((s.getId().equals(id))) {
				index = x;
				break;
			}
		}
		
		if (index == -1) {
			System.out.println("������ѧ�Ŷ�Ӧ��ѧ����Ϣ���뷵������ѡ��");			
		}else {
			arry.remove(index);
			System.out.println("ɾ���ɹ�");
			fileWrite(file,arry);
		}
		
		
	}
	
	public static void updateStudent(String file) throws IOException {
		ArrayList<student> arry = new ArrayList<student>();
		fileRead(file, arry);
		int index = -1;
		String id;
		Scanner sc = new Scanner(System.in);
		System.out.println("��������Ҫ�޸ĵ�ѧ����ѧ�ţ�");
		id = sc.nextLine();
		
		for(int x=0;x<arry.size();x++) {
			student s = new student();
			s = arry.get(x);	
			if((s.getId().equals(id))) {
				index = x;
				break;
			}
		}
		
		if (index == -1) {
			System.out.println("������ѧ�Ŷ�Ӧ��ѧ����Ϣ���뷵������ѡ��");			
		}else {
			
			student s = new student();
			s.setId(id);
			System.out.println("��������Ҫ�޸ĵ�ѧ������������");
			String name = sc.nextLine();
			System.out.println("��������Ҫ�޸ĵ�ѧ���������䣺");
			String age = sc.nextLine();
			System.out.println("��������Ҫ�޸ĵ�ѧ�����¾�ס�أ�");
			String address = sc.nextLine();
			s.setName(name);
			s.setAge(age);
			s.setAddress(address);
			
			arry.set(index, s);
			System.out.println("�޸ĳɹ�");
			fileWrite(file,arry);
		}
			
		
	}
	
	
	
	
	
}
