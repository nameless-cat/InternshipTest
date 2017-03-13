package com.nameless.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="user")
public class User
{
	
	@Id
	@Column(name="ID", columnDefinition = "INT(8)")
	@GeneratedValue
	private int id;
	
	@Column(name="NAME", columnDefinition = "VARCHAR(25)")
	private String name;
	
	@Column(name="AGE")
	private int age;

	@Column(name = "IS_ADMIN")
	private boolean admin;

	@Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date date;

	public User(){}

	public User(String name, int age)
	{
		this.name = name;
		this.age = age;
		this.admin = false;
	}

	public User(String name, int age, boolean admin, Date date)
	{
		this(name, age);
		this.admin = admin;
		this.date = date;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public boolean isAdmin()
	{
		return admin;
	}

	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
}