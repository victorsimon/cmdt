package compartirmesadetren

class ContentMD5 {

	String contentKey
	String md5HexString
	Long timeStamp
	
    static constraints = {
		contentKey(unique: true)
    }
	
	String toString() {
		return contentKey + " - [" + md5HexString + "]"
	}
}
