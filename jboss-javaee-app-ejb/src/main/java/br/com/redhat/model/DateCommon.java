package br.com.redhat.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DateCommon {

	@Column
	private Date created = new Date(System.currentTimeMillis());

	@Column
	private Date updated = new Date(System.currentTimeMillis());

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
