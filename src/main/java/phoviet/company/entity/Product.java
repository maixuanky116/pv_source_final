package phoviet.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pv_products")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="product_name")
	private String name;
	
	@Column(name="product_content")
	private String content;
	
	@Column(name="product_type")
	private String type;
		
	@Column(name="product_status")
	private String status;
	
	@Column(name="product_createday")
	private String createday;
	
	@Column(name="product_finishday")
	private String finishday;
	
	@Column(name="product_createpostday")
	private String createpostday;
		
	@Column(name="product_author")
	private String author;

	@Column(name="product_address")
	private String address;
	
	@Column(name="product_size")
	private String size;
	
	@Column(name="product_scale")
	private String scale;
	
	@Column(name="product_money")
	private String money;
	
	@Column(name="product_image")
	private String image;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCreateday() {
		return createday;
	}

	public void setCreateday(String createday) {
		this.createday = createday;
	}
	
	public String getFinishday() {
		return finishday;
	}

	public void setFinishday(String finishday) {
		this.finishday = finishday;
	}
	
	public String getCreatepostday() {
		return createpostday;
	}

	public void setCreatepostday(String createpostday) {
		this.createpostday = createpostday;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}
