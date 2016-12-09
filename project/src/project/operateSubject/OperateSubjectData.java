package project.operateSubject;

public class OperateSubjectData {

	public String getSubjectProcess() {
		return subjectProcess;
	}
//KSC5601
	public void setSubjectProcess(String subjectProcess) {
		try {
			this.subjectProcess = new String(subjectProcess.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		try {
			this.target = new String(target.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		try {
			this.feature = new String(feature.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}

	public int getRecruitNumber() {
		return recruitNumber;
	}

	public void setRecruitNumber(int recruitNumber) {
		this.recruitNumber = recruitNumber;
	}

	public int getTuition() {
		return tuition;
	}

	public void setTuition(int tuition) {
		this.tuition = tuition;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		try {
			this.subjectCode = new String(subjectCode.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		try {
			this.purpose = new String(purpose.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}

	public String getChangeRecord() {
		return changeRecord;
	}

	public void setChangeRecord(String changeRecord) {
		try {
			this.changeRecord = new String(changeRecord.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		try {
			this.subjectName = new String(subjectName.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}

	public int getTotalDate() {
		return totalDate;
	}

	public void setTotalDate(int totalDate) {
		this.totalDate = totalDate;
	}

	public int getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(int totalHour) {
		this.totalHour = totalHour;
	}

	public int getLectureHour() {
		return lectureHour;
	}

	public void setLectureHour(int lectureHour) {
		this.lectureHour = lectureHour;
	}
	public String getLectureContent() {
		return lectureContent;
	}

	public void setLectureContent(String lectureContent) {
		try {
			this.lectureContent = new String(lectureContent.getBytes("8859_1"),"KSC5601");
		} catch(java.io.UnsupportedEncodingException uee) {}
	}
	public String subjectProcess;
	public String target;
	public String feature;
	public int recruitNumber;
	public int tuition;
	public String subjectCode;
	public String purpose;
	public String changeRecord;
	public String subjectName;
	public int totalDate;
	public int totalHour;
	public int lectureHour;
	public String lectureContent;
	
	public OperateSubjectData(){
		
	}
}
