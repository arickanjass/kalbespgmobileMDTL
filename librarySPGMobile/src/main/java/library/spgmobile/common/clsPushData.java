package library.spgmobile.common;

import java.util.HashMap;

public class clsPushData {
	private dataJson dtdataJson;
	private HashMap<clsMappingPushFile, byte[]> FileUpload;
	public dataJson getDtdataJson() {
		return dtdataJson;
	}
	public void setDtdataJson(dataJson dtdataJson) {
		this.dtdataJson = dtdataJson;
	}
	public HashMap<clsMappingPushFile, byte[]> getFileUpload() {
		return FileUpload;
	}
	public void setFileUpload(HashMap<clsMappingPushFile, byte[]> fileUpload) {
		FileUpload = fileUpload;
	}
}
