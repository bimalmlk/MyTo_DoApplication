package bimal.todo.model;

import java.util.Date;

import javax.validation.constraints.Size;
/*
 * This POJO class can hold the details of todos.
 */
public class Todo {
	private int id;
	private String user;
	@Size(min = 10, message = "Enter atleast 10 Characters.")
	private String desc;
	private Date startDate;
	private Date targetDate;
	private boolean isDone;
	public Todo() {
		super();
	}
	public Todo(int id, String user, String desc, Date startDate, Date targetDate,
			boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.startDate = startDate;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("Todo [id=%s, user=%s, desc=%s, startDate=%s, targetDate=%s, isDone=%s]", id,
				user, desc, startDate, targetDate, isDone);
	}
	
}
