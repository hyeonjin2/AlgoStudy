package com.corona.virus;

import java.util.List;

public interface VirusMgr {
	void add(Virus v) throws DuplicatedException;
	List<Virus> search(); // 모든 바이러스 정보 리턴
	Virus search(String name) throws NotFoundException;
	boolean search(Virus v) throws NotFoundException;
	
	List<Virus> sortByName(); // 이름 기준 정렬 모든 바이러스 정보 리턴 -> Virus 객체가 정렬 가능한 상태여야함.
	List<Virus> sortByLevel(); // 레벨 기준 정렬 모든 바이러스 정보 리턴
	void delete(String name); // 이름으로 검색해서 해당 바이러스 삭제
	void load(); // Virus 정보를 파일에서 읽어와서 로딩
	void save(); // Virus 정보를 파일로 저장
}