package com.javaex.vo;

public class BoardVo {

	private int boardno;
	private String title;
	private String contents;
	private String name;
	private String views;
	private String datecreated;
	private int no;

	public BoardVo() {
		super();
	}

	
	public BoardVo(int boardno, String title, String contents) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.contents = contents;
	}


	public BoardVo(int boardno, String title, String contents, String name, String views, String datecreated, int no) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.contents = contents;
		this.name = name;
		this.views = views;
		this.datecreated = datecreated;
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public BoardVo(int no) {
		super();
		this.no = no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}

	@Override
	public String toString() {
		return "BoardVo [boardno=" + boardno + ", title=" + title + ", contents=" + contents + ", name=" + name
				+ ", views=" + views + ", datecreated=" + datecreated + ", no=" + no + "]";
	}

}