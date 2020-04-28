package com.dmitco.blogproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Version implements Serializable {
	@SerializedName("versionId")
	private int versionId;
	@SerializedName("isImportant")
	private boolean isImportant;
	@SerializedName("versionName")
	private String versionName;
	@SerializedName("isActive")
	private boolean isActive;
	@SerializedName("versionCode")
	private int versionCode;
	@SerializedName("urlVersion")
	private String urlVersion;

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public boolean isImportant() {
		return isImportant;
	}

	public void setImportant(boolean important) {
		isImportant = important;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getUrlVersion() {
		return urlVersion;
	}

	public void setUrlVersion(String urlVersion) {
		this.urlVersion = urlVersion;
	}

	@Override
	public String toString() {
		return "Version{" +
				"versionId=" + versionId +
				", isImportant=" + isImportant +
				", versionName='" + versionName + '\'' +
				", isActive=" + isActive +
				", versionCode=" + versionCode +
				", urlVersion='" + urlVersion + '\'' +
				'}';
	}
}
