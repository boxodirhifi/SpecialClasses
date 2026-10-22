Laboratory 6 Special Classes. Answers to the questions.

Task 1.

Step 1. The program calls uppercase() because valueOf only understands the exact name of a constant, with capital letters. If the user types paid, valueOf would not find it and the program gets an error. With uppercase() the input paid becomes PAID, so the program works no matter how the user writes the word.

Step 2. Without try and catch, the program with lost throws an IllegalArgumentException, because there is no constant with the name LOST. The program stops with an error.

Step 3. The second version looks like this: val status = OrderStatus.entries.firstOrNull { it.name == text }. I would choose the firstOrNull version for text that a user types. A wrong word just gives null, so the program can print a message and continue. But valueOf with a wrong word throws an exception and the program dies. For text that a person types, a crash is worse than a message.

Step 4. I added CANCELLED at the end of the enum. Now for DELIVERED the program prints Next step: CANCELLED instead of "This is the last step.". I did not change any line of the program, because it does not keep the constants hard-coded anywhere. It only uses the ordinal and the entries list, and both update themselves when I add a constant.

Step 5. The line prints true. This means an enum constant exists only one time. PAID is one object in the whole program, and valueOf("PAID") gives back the same object, so === is true. It is not possible to have two objects for the same constant.

Task 2.

Step 1. With 12 * 4 the program prints 12.0 * 4.0 = 48.0. With 7 / 2 it prints 3.5. With 5 / 0 the division line never runs, because the check if (operation == Operation.DIVIDE && b == 0.0) in main stops it and prints Division by zero. With abc it stops at the check if (a == null || b == null || operation == null) in main, because abc cannot be changed into a number with toDoubleOrNull. So the zero case is stopped by the zero check and abc by the null checks.

Step 2. The symbol + is stored inside ADD("+"), in the parentheses of the constant, so every constant carries its own symbol. The code that adds is in the body of ADD, in the override of apply. The abstract fun apply forces every constant to write its own apply. If a constant has no body, the code does not compile, the same as with an abstract function in a normal class.

Step 3. When I added MODULO("%") with no body, the compiler gave an error on this constant and said that the abstract function apply must be implemented. After writing the body (a % b), 7 % 3 printed 7.0 % 3.0 = 1.0.

Step 4. fromSymbol is inside the companion object of the enum. A companion object belongs to the enum class itself, not to one constant, so I can call its function on the name of the class. That is why Operation.fromSymbol("+") works without any instance.

Step 5. I added POWER("^") with the body a.pow(b), and the IDE added the import kotlin.math.pow for me. 2 ^ 10 prints 2.0 ^ 10.0 = 1024.0.

Task 3.

Step 1. With courier and 2.5 the cost is 20000 UZS (15000 plus 2000 times 2.5). With express and 1 it is 45000 UZS. With pickup and 10 it is 0 UZS. With plane the program prints Unknown delivery type and stops, because no constant matches the name.

Step 2. After I added DRONE to the enum, the compiler gave an error in the when of shippingCost. It says the when must cover all the constants and the DRONE branch is missing. It does not complain about main, because main has no when over the enum; it finds the type by name with firstOrNull, so the compiler has nothing to check there.

Step 3. After deleting the DRONE branch and putting else -> 0.0 as the last branch, the code compiles. With drone and 1 the customer pays 0 UZS. This is worse than the red error, because the error tells me straight away that something is missing in the code, but with else the program looks fine and quietly gives a zero price to every new delivery type.

Step 4. I removed the else and put the correct DRONE branch back (25000 plus 3000 times the weight), so the compiler checks the when again.

Task 8.

Step 1. With Ali, 25 and ali@mail.uz the program prints Registration accepted. With an empty name it prints name: The name must not be empty. With abc as the age it prints age: 'abc' is not an age between 0 and 120. With xyz as the e-mail it prints email: 'xyz' is not an e-mail address.

Step 2. When I write ValidationError("x", "y") in main, the IDE gives an error. A sealed class is abstract, so I cannot create it directly. Its constructor exists only for its subclasses, so that they can give values like the field name and the message to it.

Step 3. The secondary constructor works like this: with no modifier it does not compile, because it is public by default. Public does not compile. Internal does not compile. Protected compiles and private compiles. The default visibility of a constructor in a sealed class is protected, so it is visible inside the class and in its subclasses. Private is also allowed, then it is visible only inside the class. Public and internal are not allowed, the compiler gives an error for them.

Step 4. After adding the class TooYoung with the message "You must be 18 or older" and adding it to validate for an age from 0 to 17, the age 15 prints age: You must be 18 or older. I put it after the check for 0 to 120, so a valid number under 18 gets the new error.

Task 9.

Step 1. With the input file report.txt, slow /home, db users, crash, hello and end the incidents print in this order: first the two critical ones (Database users is down, then The application crashed), then File report.txt cannot be read (major), then Page /home is slow (minor). They go from the most serious to the least serious, because the program sorts them by severity with sortedByDescending. An enum has a fixed order of its constants, so the constants can be compared with each other and sorted. The word hello gives Unknown report and is ignored.

Step 2. With db users and db orders the outputs are: dbs[0] === dbs[1] is false, and dbs[0].severity === dbs[1].severity is true. So the two DatabaseDown objects are two different objects; every report made a new one. But their severity is the same object, because CRITICAL is an enum constant, and an enum constant exists only once in the whole program. This is the difference shown in the lecture: an enum constant is a single instance, but a subclass of a sealed class can have many instances.

Step 3. After adding BLOCKER after CRITICAL and the subclass OutOfMemory, the when in describe stops compiling, because the compiler checks that a when over a sealed class covers every direct subclass, and OutOfMemory is missing there. The sorting line and the counting line keep working, because they only look at the severity of every incident and do not care which subclass it is.

Step 4. I added a line inside the printing loop: if the severity is CRITICAL or higher (incident.severity >= Severity.CRITICAL), the program prints ALERT before the description. Enums can be compared, so >= works on Severity.

Task 10.

Step 1. play gives Playing from 0s. seek 90 gives Jumped to 90s. pause gives Paused at 90s. volume 150 gives Unknown command, because the parser only accepts a number from 0 to 100. volume 80 gives Volume is now 80%. stop gives Stopped. fly gives Unknown command. quit ends the program.

Step 2. When I change the root to sealed class PlayerCommand and write enum class Basic : PlayerCommand() and so on, the compiler gives errors on the enums. The reason is in the lecture: an enum class cannot extend a sealed class, or any other class, because an enum already has its own base type. An enum can only implement interfaces, and sealed interfaces are fine. So if an enum should be part of such a family, the root of the family must be a sealed interface, not a sealed class.

Step 3. When I delete the branch Basic.STOP in the when of handle, the compiler says the when must cover every case and asks to add the STOP branch back (or an else branch). This is the main plus of a sealed hierarchy: the compiler knows all the subclasses, so it can check that no case is forgotten.

Step 4. I added the constant MUTE to Basic, the branch Basic.MUTE which prints Muted, and the word mute in parse. Now mute prints Muted.

Task 12.

Step 1. DiskFull is in the file DiskFull.kt and extends IoError, which is in Errors.kt. It compiles fine, because both files are in the same package task12. A direct subclass of a sealed class must be in the same package (and the same module), but it can be in another file. With the input missing a.txt, disk 12, timeout, quota 5, other oops and end the program prints every error with its kind: [I/O] for file missing, no permission and disk full, [timeout] for the timeout object, [custom] for the quota and the other error.

Step 2. When Overheated is in the package task12.other and extends IoError, the compiler gives an error, because task12.other is another package, and a sealed class does not allow new direct subclasses outside the package and module where it was declared. So I removed the file.

Step 3. The same class in task12.other but extending CustomError compiles. CustomError is not sealed, it is a normal open class, so anybody who sees it can extend it, in any package. The rule for the sealed interface AppError does not touch Overheated, because Overheated is not a direct subclass of AppError, it is one level down, and the rules only count for direct subclasses. The function report still handles it, but only through the is CustomError branch, so it prints [custom] The disk is too hot. Report knows the direct subclasses, but it does not know every indirect one.

Step 4. Writing class LocalError : IoError("local") inside a function gives an error, and val anon = object : IoError("x") {} also gives an error. A subclass of a sealed class must have a normal name, so that other places can use it. Local classes and anonymous objects do not have such a name, so they are not allowed as subclasses. I deleted both lines.

Step 5. Who can extend what: AppError is a sealed interface, so its direct subclasses can only be in the same package and the same module. IoError is a sealed class, so its direct subclasses can only be in the same package (and the same module). CustomError is an open class, so anyone who sees it can extend it, in any package, and after that all of it becomes indirect subclasses of AppError, which are not limited. So the sealed interface and the sealed class are closed, and the open class is not closed.
