package task08

sealed class ValidationError(val field: String, val message: String) {
    class EmptyName : ValidationError("name", "The name must not be empty")
    class InvalidAge(text: String) : ValidationError("age", "'$text' is not an age between 0 and 120")
    class BadEmail(text: String) : ValidationError("email", "'$text' is not an e-mail address")
    class TooYoung : ValidationError("age", "You must be 18 or older")
}

fun validate(name: String, age: String, email: String): List<ValidationError> {
    val errors = mutableListOf<ValidationError>()
    if (name.isBlank()) errors.add(ValidationError.EmptyName())
    val ageNumber = age.toIntOrNull()
    if (ageNumber == null || ageNumber !in 0..120) errors.add(ValidationError.InvalidAge(age))
    else if (ageNumber in 0..17) errors.add(ValidationError.TooYoung())
    if ('@' !in email) errors.add(ValidationError.BadEmail(email))
    return errors
}

fun main() {
    print("Name: ")
    val name = readln()
    print("Age: ")
    val age = readln()
    print("E-mail: ")
    val email = readln()
    val errors = validate(name, age, email)
    if (errors.isEmpty()) {
        println("Registration accepted")
    } else {
        errors.forEach { println("${it.field}: ${it.message}") }
    }
}
