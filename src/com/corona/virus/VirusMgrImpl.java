package com.corona.virus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VirusMgrImpl implements VirusMgr {
	private static VirusMgrImpl instance; // ***
	private List<Virus> list;

	private VirusMgrImpl() {
		list = new ArrayList<>();
	}

	public static VirusMgrImpl getInstance() {
		if (instance == null) {
			instance = new VirusMgrImpl();
		}
		return instance;
	}

	@Override
	public void add(Virus v) throws DuplicatedException { // ***
		try {
			search(v.getName()); // 그 이름의 바이러스가 있다면 찾기 아니면 NotFoundException
			throw new DuplicatedException(v.getName() + ": 등록된 바이러스입니다."); // *** Exception 발생
		} catch (NotFoundException e) {
			list.add(v);
		}
	}

	@Override
	public Virus search(String name) throws NotFoundException {
		for (int i = 0; i < list.size(); i++) {
			Virus v = list.get(i); // list.get(i)를 여러번 호출하지 말고 변수로 받아서 사용하기
			if (v.getName().equals(name))
				return v;
		}
		throw new NotFoundException(name + ": 미등록 바이러스입니다."); // for문을 다 돌았는대도 못 찾으면 오류발생 시킴
	}

	// 같은 객체인지 비교하는 것 ==연산자로 비교하면 안되고 Virus 클래스 안의 equals 메소드를 이용해 비교해야 한다.
	public boolean search(Virus other) throws NotFoundException { // virus name으로만 비교해서 같은지 아닌지 판별
		for (Virus v : list) {
			if (v.equals(other))// ***
				return true;
		}
		return false;
//		throw new NotFoundException(other.getName() + ": 미등록 바이러스입니다.");
	}

	@Override
	public List<Virus> search() {
		return list;
	}

	@Override
	public List<Virus> sortByName() {
		List<Virus> temp = new ArrayList<>();
		for (Virus v : list) {
			temp.add(new Virus(v.getName(), v.getLevel()));
		}
		Collections.sort(temp);
		return temp;
	}

	@Override
	public List<Virus> sortByLevel() {
		List<Virus> temp = new ArrayList<>();
		for (Virus v : list) {
			temp.add(new Virus(v.getName(), v.getLevel()));
		}
		Collections.sort(temp, (e1, e2) -> {
			return e1.getLevel() - e2.getLevel();
		});
		return temp;
	}

	@Override
	public void delete(String name) {
		try {
			Virus v = search(name); // 그 이름의 바이러스가 있다면 찾기 아니면 NotFoundException
			list.remove(v);
		} catch (NotFoundException e) {
			System.out.println(name + ": 미등록바이러스입니다.");
		}

	}

	@Override
	public void load() { // ***
		File f = new File("src/virus.txt");
		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
				// ois.readObject(); 읽어오는 객체가 없으면 안된다.
				list = (List<Virus>) (ois.readObject()); // ***
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("[SYSTEM] 파일 읽기 실패");
			}
		}
	}

	@Override
	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/virus.txt"))) {
			oos.writeObject(list);
			oos.flush();
		} catch (Exception e) {
			System.out.println("[SYSTEM] 파일 쓰기 실패");
		}
		System.out.println("write success");
	}
}
