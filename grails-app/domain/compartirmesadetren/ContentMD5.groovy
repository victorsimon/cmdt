package compartirmesadetren

class ContentMD5 {

	String key
	String md5HexString
	
    static constraints = {
		key(unique: true)
    }
	
	String toString() {
		return key + " - [" + md5HexString + "]"
	}
}
