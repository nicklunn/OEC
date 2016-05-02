package com.oec.publishing.core;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "oem")
@NamedQueries({
    @NamedQuery(
		name = "com.oec.publishing.core.Oem.findAll",
        query = "select e from Oem e"
	),
    @NamedQuery(
		name = "com.oec.publishing.core.Oem.findByName",
        query = "select e from Oem e where e.oemName like :oemName"
	),
    @NamedQuery(
		name = "com.oec.publishing.core.Oem.findByNumber",
        query = "select e from Oem e where e.oemId = :oemId"
	)
})
public class Oem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "OemId")
	private int oemId;
	
	@Column(name = "OemName")
	private String oemName;
	
	//constructors
	public Oem(){}
	
	public Oem(int oemId, String oemName) {
		this.oemId = oemId;
		this.oemName = oemName;
	}
	
	//getters and setters
    public long getId() { return id; }
	public void setId(long id) { this.id = id; }

    public int getOEMId() { return oemId; }
	public void setOEMId(int OemId) { this.oemId = OemId; }
	
	public String getOemName() { return oemName; }
	public void setOemName(String oemName) { this.oemName = oemName; }
}