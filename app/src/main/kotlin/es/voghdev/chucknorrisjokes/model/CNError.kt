package es.voghdev.chucknorrisjokes.model

class CNError(val message: String = "") : AbsError {
    override fun message(): String {
        return message
    }
}
