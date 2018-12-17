package item01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*	通过IO流进行学生信息的读取和写入并存储
 * 	中间需要用集合对象进行存储		
 * 	通过键盘录入学生信息
 */
public class studentManagerTest {

	public static void main(String[] args) throws IOException {
		// 开始先把主界面写好
		
		String file = "student.txt";
		while(true) {
				System.out.println("-----欢迎进入学生管理系统-----");
				System.out.println("1查看所有学生信息");
				System.out.println("2添加学生信息");
				System.out.println("3删除学生信息");
				System.out.println("4修改学生信息");
				System.out.println("5退出");
				System.out.println("请输入你的选择：");
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
					System.out.println("谢谢你的使用！");
					System.exit(0);
					break;
				}
		}

	}
	
	//从缓存流里面读出数据到学生集合
	public static void fileRead(String file,ArrayList<student> array) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line;
		while((line = br.readLine())!= null) {
			student s = new student();
			String[] datas = line.split(",");//将每一行按照逗号分隔成每个字符串存入字符串数组
			s.setId(datas[0]);//再将字符串数组的按照顺序将每个元素存储都学生对象的成员中
			s.setName(datas[1]);
			s.setAge(datas[2]);
			s.setAddress(datas[3]);
			array.add(s);//将学生对象写入到集合里面
			
		}
		br.close();
		
	}
	
	//从学生集合中将数据写入到缓存流里面
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
			System.out.println("没有学生信息，请先增加学生信息");
			return;
		}
			
		System.out.println("学号\t\t姓名\t年龄\t居住地");
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
				System.out.println("请输入学号：");				
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
					System.out.println("输入的学号已经存在，请重新输入学号");
				}else {
					break;
				}
				
		}
		
		System.out.println("请输入姓名：");
		String name = sc.nextLine();
		System.out.println("请输入年龄：");
		String age =  sc.nextLine();
		System.out.println("请输入居住地：");
		String address = sc.nextLine();
		student s = new student();
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(address);
		arry.add(s);
		System.out.println("添加成功");
		fileWrite(file,arry);
				
	}
	
	public static void delStudent(String file) throws IOException {
		
		ArrayList<student> arry = new ArrayList<student>();
		fileRead(file,arry);
		
		int index = -1;
		String id;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入需要删除的学生的学号：");
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
			System.out.println("不存在学号对应的学生信息，请返回重新选择");			
		}else {
			arry.remove(index);
			System.out.println("删除成功");
			fileWrite(file,arry);
		}
		
		
	}
	
	public static void updateStudent(String file) throws IOException {
		ArrayList<student> arry = new ArrayList<student>();
		fileRead(file, arry);
		int index = -1;
		String id;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入需要修改的学生的学号：");
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
			System.out.println("不存在学号对应的学生信息，请返回重新选择");			
		}else {
			
			student s = new student();
			s.setId(id);
			System.out.println("请输入需要修改的学生的新姓名：");
			String name = sc.nextLine();
			System.out.println("请输入需要修改的学生的新年龄：");
			String age = sc.nextLine();
			System.out.println("请输入需要修改的学生的新居住地：");
			String address = sc.nextLine();
			s.setName(name);
			s.setAge(age);
			s.setAddress(address);
			
			arry.set(index, s);
			System.out.println("修改成功");
			fileWrite(file,arry);
		}
			
		
	}
	
	
	
	
	
}
