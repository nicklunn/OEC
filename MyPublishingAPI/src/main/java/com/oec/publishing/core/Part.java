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
@Table(name = "part")
@NamedQueries({
    @NamedQuery(name = "com.oec.publishing.core.Part.findAll",
            query = "select e from Part e"),
    @NamedQuery(name = "com.oec.publishing.core.Part.findByNumber",
            query = "select e from Part e "
            + "where e.partNumber like :partNumber")
})
public class Part {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "OemId")
	private int oemid;
	
	@Column(name = "partNumber")
	private String partNumber;
	
	@Column(name = "partName")
	private String partName;
	
	@Column(name = "partImage")
	private String partImage;
	
	@Column(name = "partIllustration")
	private String partIllustration;
	
	@Column(name = "partUsage")
	private String partUsage;
	
	@Column(name = "partSpec")
	private String partSpec;
    
	//constructors
	public Part(){}
	
    public Part(int oemId, String partNumber, String partName, String partImage, String partIllustration, String partUsage, String partSpec) {
        this.oemid = oemId;
        this.partNumber = partNumber;
		this.partName = partName;
		this.partImage = partImage;
		this.partIllustration = partIllustration;
		this.partUsage = partUsage;
		this.partSpec = partSpec;
    }

	//getters and setters
    public long getId() { return id; }
	public void setId(long id) { this.id = id; }

    public int getOEM() { return oemid; }
	public void setOEM(int OemId) { this.oemid = OemId; }
	
	public String getPartNumber() { return partNumber; }
	public void setPartNumber(String partNumber) { this.partNumber = partNumber; }
	
	public String getPartName() { return partName; }
	public void setPartName(String partName) { this.partName =  partName; }
	
	public String getPartImage() { return partImage; }
	public void setPartImage(String partImage) { this.partImage = partImage; }
	
	public String getPartIllustration() { return partIllustration; }
	public void setPartIllustration(String partIllustration) { this.partIllustration = partIllustration; }
	
	public String getPartUsage() {return partUsage; }
	public void setPartUsage(String partUsage) {this.partUsage = partUsage; }
	
	public String getPartSpec() { return partSpec; }
	public void setPartSpec(String partSpec) { this.partSpec = partSpec; }
}